/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.numbers.examples.jmh.core;

/**
 * Computes double-length precision floating-point operations.
 *
 * <p>It is based on the 1971 paper
 * <a href="https://doi.org/10.1007/BF01397083">
 * Dekker (1971) A floating-point technique for extending the available precision</a>.
 */
final class DoublePrecision {

    /*
     * Caveat:
     *
     * The code below uses many additions/subtractions that may
     * appear redundant. However, they should NOT be simplified, as they
     * do use IEEE754 floating point arithmetic rounding properties.
     *
     * Algorithms are based on computing the product or sum of two values x and y in
     * extended precision. The standard result is stored using a double (high part z) and
     * the round-off error (or low part zz) is stored in a second double, e.g:
     * x * y = (z, zz); z + zz = x * y
     * x + y = (z, zz); z + zz = x + y
     *
     * To sum multiple (z, zz) results ideally the parts are sorted in order of
     * non-decreasing magnitude and summed. This is exact if each number's most significant
     * bit is below the least significant bit of the next (i.e. does not
     * overlap). Creating non-overlapping parts requires a rebalancing
     * of adjacent pairs using a summation z + zz = (z1, zz1) iteratively through the parts
     * (see Shewchuk (1997) Grow-Expansion and Expansion-Sum [1]).
     *
     * [1] Shewchuk (1997): Arbitrary Precision Floating-Point Arithmetic
     * http://www-2.cs.cmu.edu/afs/cs/project/quake/public/papers/robust-arithmetic.ps
     */
    /**
     * The multiplier used to split the double value into high and low parts. From
     * Dekker (1971): "The constant should be chosen equal to 2^(p - p/2) + 1,
     * where p is the number of binary digits in the mantissa". Here p is 53
     * and the multiplier is {@code 2^27 + 1}.
     */
    private static final double MULTIPLIER = 1.34217729E8;

    /**
     * The upper limit above which a number may overflow during the split into a high part.
     * Assuming the multiplier is above 2^27 and the maximum exponent is 1023 then a safe
     * limit is a value with an exponent of (1023 - 27) = 2^996.
     */
    private static final double SAFE_UPPER = 0x1.0p996;

    /**
     * The lower limit for a product {@code x * y} below which the round-off component may be
     * sub-normal. This is set as 2^-1022 * 2^54.
     */
    private static final double SAFE_LOWER = 0x1.0p-968;

    /**
     * The scale to use when down-scaling during a split into a high part.
     * This must be smaller than the inverse of the multiplier and a power of 2 for exact scaling.
     */
    private static final double DOWN_SCALE = 0x1.0p-30;

    /**
     * The scale to use when re-scaling during a split into a high part.
     * This is the inverse of {@link #DOWN_SCALE}.
     */
    private static final double UP_SCALE = 0x1.0p30;

    /**
     * The upscale factor squared.
     */
    private static final double UP_SCALE2 = 0x1.0p60;

    /**
     * The downscale factor squared.
     */
    private static final double DOWN_SCALE2 = 0x1.0p-60;

    /**
     * The safe upper limit so the product {@code x * y} can be upscaled by 2^60.
     */
    private static final double SAFE_UPPER_S = 0x1.0p963;

    /**
     * The mask to zero the lower 27-bits of a long .
     */
    private static final long ZERO_LOWER_27_BITS = 0xffff_ffff_f800_0000L;

    /**
     * The mask to extract the raw 11-bit exponent.
     * The value must be shifted 52-bits to remove the mantissa bits.
     */
    private static final int EXP_MASK = 0x7ff;

    /**
     * The value 2046 converted for use if using {@link Integer#compareUnsigned(int, int)}.
     * This requires adding {@link Integer#MIN_VALUE} to 2046.
     */
    private static final int CMP_UNSIGNED_2046 = Integer.MIN_VALUE + 2046;

    /**
     * The value -1 converted for use if using {@link Integer#compareUnsigned(int, int)}.
     * This requires adding {@link Integer#MIN_VALUE} to -1.
     */
    private static final int CMP_UNSIGNED_MINUS_1 = Integer.MIN_VALUE - 1;

    /**
     * Represents a floating-point number with twice the precision of a {@code double}.
     */
    static final class Quad {

        // This is treated as a simple struct.
        // CHECKSTYLE: stop VisibilityModifier
        /**
         * The high part of the number.
         */
        double hi;

        /**
         * The low part of the number.
         */
        double lo;
        // CHECKSTYLE: resume VisibilityModifier
    }

    /**
     * Private constructor.
     */
    private DoublePrecision() {
        // intentionally empty.
    }

    /**
     * Multiply the values {@code x} and {@code y} into a double-precision result {@code z}.
     * It is assumed the numbers are normalized so no over/underflow will occurs.
     *
     * <p>Implements Dekker's mul12 method to split the numbers and multiply them
     * in extended precision.
     *
     * <p>Note: The quad satisfies the condition {@code x * y == z.hi + z.lo}. The high
     * part may be different from {@code x * y} by 1 ulp due to rounding.
     *
     * @param x First value
     * @param y Second value
     * @param z Result
     */
    static void multiplyUnscaled(double x, double y, Quad z) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Multiply the values {@code x} and {@code y} into a double-precision result {@code c}.
     * Scaling is performed on the numbers to protect against intermediate over/underflow.
     *
     * <p>The quadruple precision result has the standard double precision result
     * {@code x * y} in the high part and the round-off in the low part,
     *
     * <p>Special cases:
     *
     * <ul>
     *  <li>If {@code x * y} is sub-normal or zero then the low part is 0.0.</li>
     *  <li>If {@code x * y} is infinite or NaN then the low part is NaN.</li>
     * </ul>
     *
     * <p>Note: This does not represent the low part of infinity with zero. This is because the
     * method is intended to be used for extended precision computations. The NaN low part
     * signals that an extended precision computation using the result is invalid (i.e. the
     * result of summation/multiplication of the parts will not be finite).
     *
     * @param x First value
     * @param y Second value
     * @param c Result
     * @see DoublePrecision#productLowUnscaled(double, double, double)
     */
    static void multiply(double x, double y, Quad c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if the number is not normal. This is functionally equivalent to:
     * <pre>
     * final double abs = Math.abs(a);
     * return (abs <= Double.MIN_NORMAL || !(absXy <= Double.MAX_VALUE));
     * </pre>
     *
     * @param a The value.
     * @return true if the value is not normal
     */
    static boolean isNotNormal(double a) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the exponent.
     *
     * @param bits the bits
     * @return the exponent
     */
    private static int getBiasedExponent(long bits) {
        return (int) (bits >>> 52) & 0x7ff;
    }

    /**
     * Gets the normalized fraction in the range [0.5, 1).
     *
     * @param bits the bits
     * @return the exponent
     */
    private static double getNormalisedFraction(long bits) {
        // Mask out the exponent and set it to 1022.
        return Double.longBitsToDouble((bits & 0x800f_ffff_ffff_ffffL) | 0x3fe0_0000_0000_0000L);
    }

    /**
     * Implement Dekker's method to split a value into two parts. Multiplying by (2^s + 1) creates
     * a big value from which to derive the two split parts.
     * <pre>
     * c = (2^s + 1) * a
     * a_big = c - a
     * a_hi = c - a_big
     * a_lo = a - a_hi
     * a = a_hi + a_lo
     * </pre>
     *
     * <p>The multiplicand allows a p-bit value to be split into
     * (p-s)-bit value {@code a_hi} and a non-overlapping (s-1)-bit value {@code a_lo}.
     * Combined they have (p􏰔-1) bits of significand but the sign bit of {@code a_lo}
     * contains a bit of information. The constant is chosen so that s is ceil(p/2) where
     * the precision p for a double is 53-bits (1-bit of the mantissa is assumed to be
     * 1 for a non sub-normal number) and s is 27.
     *
     * <p>This conversion uses scaling to avoid overflow in intermediate computations.
     *
     * <p>Splitting a NaN or infinite value will return NaN. Any finite value will return
     * a finite value.
     *
     * @param value Value.
     * @return the high part of the value.
     */
    static double highPart(double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Implement Dekker's method to split a value into two parts (see {@link #highPart(double)}).
     *
     * <p>This conversion does not use scaling and the result of overflow is NaN. Overflow
     * may occur when the exponent of the input value is above 996.
     *
     * <p>Splitting a NaN or infinite value will return NaN.
     *
     * @param value Value.
     * @return the high part of the value.
     * @see Math#getExponent(double)
     */
    static double highPartUnscaled(double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Implement a split using the upper and lower raw bits from the value.
     *
     * <p>Note: This method will not work for very small sub-normal numbers
     * ({@code <= 27} bits) as the high part will be zero and the low part will
     * have all the information. Methods that assume {@code hi > lo} will have
     * undefined behaviour.
     *
     * <p>Splitting a NaN value will return NaN or infinite. Splitting an infinite
     * value will return infinite. Any finite value will return a finite value.
     *
     * @param value Value.
     * @return the high part of the value.
     */
    static double highPartSplit(double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the low part of the double length number {@code (z,zz)} for the exact
     * product of {@code x} and {@code y}. This is equivalent to computing a {@code double}
     * containing the magnitude of the rounding error when converting the exact 106-bit
     * significand of the multiplication result to a 53-bit significand.
     *
     * <p>The method is written to be functionally similar to using a fused multiply add (FMA)
     * operation to compute the low part, for example JDK 9's Math.fma function (note the sign
     * change in the input argument for the product):
     * <pre>
     *  double x = ...;
     *  double y = ...;
     *  double xy = x * y;
     *  double low1 = Math.fma(x, y, -xy);
     *  double low2 = DoublePrecision.productLow(x, y, xy);
     * </pre>
     *
     * <p>Special cases:
     *
     * <ul>
     *  <li>If {@code x * y} is sub-normal or zero then the result is 0.0.</li>
     *  <li>If {@code x * y} is infinite or NaN then the result is NaN.</li>
     * </ul>
     *
     * @param x First factor.
     * @param y Second factor.
     * @param xy Product of the factors (x * y).
     * @return the low part of the product double length number
     * @see #highPart(double)
     * @see #productLow(double, double, double, double, double)
     */
    static double productLow(double x, double y, double xy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the low part of the double length number {@code (z,zz)} for the exact
     * product of {@code x} and {@code y}. This is equivalent to computing a {@code double}
     * containing the magnitude of the rounding error when converting the exact 106-bit
     * significand of the multiplication result to a 53-bit significand.
     *
     * <p>The method is written to be functionally similar to using a fused multiply add (FMA)
     * operation to compute the low part, for example JDK 9's Math.fma function (note the sign
     * change in the input argument for the product):
     * <pre>
     *  double x = ...;
     *  double y = ...;
     *  double xy = x * y;
     *  double low1 = Math.fma(x, y, -xy);
     *  double low2 = DoublePrecision.productLow(x, y, xy);
     * </pre>
     *
     * <p>Special cases:
     *
     * <ul>
     *  <li>If {@code x * y} is sub-normal or zero then the result is 0.0.</li>
     *  <li>If {@code x * y} is infinite or NaN then the result is NaN.</li>
     * </ul>
     *
     * @param x First factor.
     * @param y Second factor.
     * @param xy Product of the factors (x * y).
     * @return the low part of the product double length number
     * @see #highPart(double)
     * @see #productLow(double, double, double, double, double)
     */
    static double productLowS(double x, double y, double xy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the low part of the double length number {@code (z,zz)} for the exact
     * product of {@code x} and {@code y}. This is equivalent to computing a {@code double}
     * containing the magnitude of the rounding error when converting the exact 106-bit
     * significand of the multiplication result to a 53-bit significand.
     *
     * <p>The method is written to be functionally similar to using a fused multiply add (FMA)
     * operation to compute the low part, for example JDK 9's Math.fma function (note the sign
     * change in the input argument for the product):
     * <pre>
     *  double x = ...;
     *  double y = ...;
     *  double xy = x * y;
     *  double low1 = Math.fma(x, y, -xy);
     *  double low2 = DoublePrecision.productLow(x, y, xy);
     * </pre>
     *
     * <p>Special cases:
     *
     * <ul>
     *  <li>If {@code x * y} is sub-normal or zero then the result is 0.0.</li>
     *  <li>If {@code x * y} is infinite or NaN then the result is NaN.</li>
     * </ul>
     *
     * @param x First factor.
     * @param y Second factor.
     * @param xy Product of the factors (x * y).
     * @return the low part of the product double length number
     * @see #highPart(double)
     * @see #productLow(double, double, double, double, double)
     */
    static double productLow0(double x, double y, double xy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the low part of the double length number {@code (z,zz)} for the exact
     * product of {@code x} and {@code y}. This is equivalent to computing a {@code double}
     * containing the magnitude of the rounding error when converting the exact 106-bit
     * significand of the multiplication result to a 53-bit significand.
     *
     * <p>Special cases:
     *
     * <ul>
     *  <li>If {@code x * y} is sub-normal or zero then the result is 0.0.</li>
     *  <li>If {@code x * y} is infinite, and {@code x} and {@code y} are finite then the
     *      result is the opposite infinity.</li>
     *  <li>If {@code x} or {@code y} are infinite then the result is NaN.</li>
     *  <li>If {@code x * y} is NaN then the result is NaN.</li>
     * </ul>
     *
     * @param x First factor.
     * @param y Second factor.
     * @param xy Product of the factors (x * y).
     * @return the low part of the product double length number
     * @see #highPart(double)
     * @see #productLow(double, double, double, double, double)
     */
    static double productLow1(double x, double y, double xy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the low part of the double length number {@code (z,zz)} for the exact
     * product of {@code x} and {@code y}. This is equivalent to computing a {@code double}
     * containing the magnitude of the rounding error when converting the exact 106-bit
     * significand of the multiplication result to a 53-bit significand.
     *
     * <p>The method is written to be functionally similar to using a fused multiply add (FMA)
     * operation to compute the low part, for example JDK 9's Math.fma function (note the sign
     * change in the input argument for the product):
     * <pre>
     *  double x = ...;
     *  double y = ...;
     *  double xy = x * y;
     *  double low1 = Math.fma(x, y, -xy);
     *  double low2 = DoublePrecision.productLow(x, y, xy);
     * </pre>
     *
     * <p>Special cases:
     *
     * <ul>
     *  <li>If {@code x * y} is sub-normal or zero then the result is 0.0.</li>
     *  <li>If {@code x * y} is infinite or NaN then the result is NaN.</li>
     * </ul>
     *
     * @param x First factor.
     * @param y Second factor.
     * @param xy Product of the factors (x * y).
     * @return the low part of the product double length number
     * @see #highPart(double)
     * @see #productLow(double, double, double, double, double)
     */
    static double productLow2(double x, double y, double xy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the low part of the double length number {@code (z,zz)} for the exact
     * product of {@code x} and {@code y} using Dekker's mult12 algorithm. The standard
     * precision product {@code x*y} must be provided. The numbers {@code x} and {@code y}
     * are split into high and low parts using Dekker's algorithm.
     *
     * <p>This method performs scaling in Dekker's split for large finite numbers to avoid
     * overflow when generating the high part of the number.
     *
     * <p>Warning: Dekker's split can produce high parts that are larger in magnitude than
     * the input number as the high part is a 26-bit approximation of the number. Thus it is
     * possible that the standard product {@code x * y} does not overflow but the extended
     * precision sub-product {@code hx * hy} does overflow. This method should not be
     * considered safe for all combinations where {@code Double.isFinite(x * y)} is true.
     * The method is used for benchmarking.
     *
     * @param x First factor.
     * @param y Second factor.
     * @param xy Product of the factors (x * y).
     * @return the low part of the product double length number
     * @see #highPart(double)
     * @see #productLow(double, double, double, double, double)
     */
    static double productLow3(double x, double y, double xy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the low part of the double length number {@code (z,zz)} for the
     * product of {@code x} and {@code y} using a Dekker's mult12 algorithm. The
     * standard precision product {@code x*y} must be provided. The numbers
     * {@code x} and {@code y} are split into high and low parts by zeroing the
     * lower 27-bits of the mantissa to create the high part. This may lose 1 bit of
     * precision in the resulting low part computed by subtraction. The intermediate
     * computations will not overflow as the split results are always smaller in
     * magnitude than the input numbers.
     *
     * <p>The method is used for benchmarking as results may not be exact due to
     * loss of a bit during splitting of the input factors.
     *
     * @param x First factor.
     * @param y Second factor.
     * @param xy Product of the factors (x * y).
     * @return the low part of the product double length number
     * @see #highPart(double)
     * @see #productLow(double, double, double, double, double)
     */
    static double productLowSplit(double x, double y, double xy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the low part of the double length number {@code (z,zz)} for the exact
     * product of {@code x} and {@code y} using Dekker's mult12 algorithm. The standard
     * precision product {@code x*y} must be provided. The numbers {@code x} and {@code y}
     * are split into high and low parts using Dekker's algorithm.
     *
     * <p>Warning: This method does not perform scaling in Dekker's split and large
     * finite numbers can create NaN results.
     *
     * @param x First factor.
     * @param y Second factor.
     * @param xy Product of the factors (x * y).
     * @return the low part of the product double length number
     * @see #highPartUnscaled(double)
     * @see #productLow(double, double, double, double, double)
     */
    static double productLowUnscaled(double x, double y, double xy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the low part of the double length number {@code (z,zz)} for the exact
     * product of {@code x} and {@code y} using Dekker's mult12 algorithm. The standard
     * precision product {@code x*y} must be provided. The numbers {@code x} and {@code y}
     * should already be split into low and high parts.
     *
     * <p>Note: This uses the high part of the result {@code (z,zz)} as {@code x * y} and not
     * {@code hx * hy + hx * ty + tx * hy} as specified in Dekker's original paper.
     * See Shewchuk (1997) for working examples.
     *
     * @param hx High part of first factor.
     * @param lx Low part of first factor.
     * @param hy High part of second factor.
     * @param ly Low part of second factor.
     * @param xy Product of the factors.
     * @return <code>lx * ly - (((xy - hx * hy) - lx * hy) - hx * ly)</code>
     * @see <a href="https://www-2.cs.cmu.edu/afs/cs/project/quake/public/papers/robust-arithmetic.ps">
     * Shewchuk (1997) Theorum 18</a>
     */
    static double productLow(double hx, double lx, double hy, double ly, double xy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the round-off {@code s} from the sum of two split numbers {@code (x, xx)}
     * and {@code (y, yy)} using Dekker's add2 algorithm. The values are not required to be
     * ordered by magnitude as an absolute comparison is made to determine the summation order.
     * The sum of the high parts {@code r} must be provided.
     *
     * <p>The result {@code (r, s)} must be re-balanced to create the split result {@code (z, zz)}:
     * <pre>
     * z = r + s
     * zz = r - z + s
     * </pre>
     *
     * @param x High part of first number.
     * @param xx Low part of first number.
     * @param y High part of second number.
     * @param yy Low part of second number.
     * @param r Sum of the parts (x + y) = r
     * @return The round-off from the sum (x + y) = s
     */
    static double sumLow(double x, double xx, double y, double yy, double r) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the round-off from the sum of two numbers {@code a} and {@code b} using
     * Dekker's two-sum algorithm. The values are required to be ordered by magnitude
     * {@code |a| >= |b|}. The standard precision sum must be provided.
     *
     * @param a First part of sum.
     * @param b Second part of sum.
     * @param sum Sum of the parts (a + b).
     * @return <code>b - (sum - a)</code>
     * @see <a href="https://www-2.cs.cmu.edu/afs/cs/project/quake/public/papers/robust-arithmetic.ps">
     * Shewchuk (1997) Theorum 6</a>
     */
    static double fastTwoSumLow(double a, double b, double sum) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the round-off from the sum of two numbers {@code a} and {@code b} using
     * Knuth's two-sum algorithm. The values are not required to be ordered by magnitude.
     * The standard precision sum must be provided.
     *
     * @param a First part of sum.
     * @param b Second part of sum.
     * @param sum Sum of the parts (a + b).
     * @return <code>(b - (sum - (sum - b))) + (a - (sum - b))</code>
     * @see <a href="https://www-2.cs.cmu.edu/afs/cs/project/quake/public/papers/robust-arithmetic.ps">
     * Shewchuk (1997) Theorum 7</a>
     */
    static double twoSumLow(double a, double b, double sum) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
