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
package org.apache.commons.numbers.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utilities for comparing numbers.
 */
public final class Precision {

    /**
     * <p>
     * Largest double-precision floating-point number such that
     * {@code 1 + EPSILON} is numerically equal to 1. This value is an upper
     * bound on the relative error due to rounding real numbers to double
     * precision floating-point numbers.
     * </p>
     * <p>
     * In IEEE 754 arithmetic, this is 2<sup>-53</sup>.
     * </p>
     *
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">Machine epsilon</a>
     */
    public static final double EPSILON;

    /**
     * Safe minimum, such that {@code 1 / SAFE_MIN} does not overflow.
     * In IEEE 754 arithmetic, this is also the smallest normalized
     * number 2<sup>-1022</sup>.
     *
     * @see Double#MIN_NORMAL
     */
    public static final double SAFE_MIN = Double.MIN_NORMAL;

    /**
     * Exponent offset in IEEE754 representation.
     */
    private static final long EXPONENT_OFFSET = 1023L;

    /**
     * Positive zero.
     */
    private static final double POSITIVE_ZERO = 0d;

    static {
        /*
         *  This was previously expressed as = 0x1.0p-53
         *  However, OpenJDK (Sparc Solaris) cannot handle such small
         *  constants: MATH-721
         */
        EPSILON = Double.longBitsToDouble((EXPONENT_OFFSET - 53L) << 52);
    }

    /**
     * Private constructor.
     */
    private Precision() {
    }

    /**
     * Compares two numbers given some amount of allowed error.
     * The returned value is:
     * <ul>
     *  <li>zero if considered equal using {@link #equals(double,double,double) equals(x, y, eps)}</li>
     *  <li>negative if not equal and {@code x < y}</li>
     *  <li>positive if not equal and {@code x > y}</li>
     * </ul>
     *
     * <p>NaN values are handled as if using {@link Double#compare(double, double)} where the
     * returned value is:
     * <ul>
     *  <li>zero if {@code NaN, NaN}</li>
     *  <li>negative if {@code !NaN, NaN}</li>
     *  <li>positive if {@code NaN, !NaN}</li>
     * </ul>
     *
     * @param x First value.
     * @param y Second value.
     * @param eps Allowed error when checking for equality.
     * @return 0 if the value are considered equal, -1 if the first is smaller than
     * the second, 1 if the first is larger than the second.
     * @see #equals(double, double, double)
     */
    public static int compareTo(double x, double y, double eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compares two numbers given some amount of allowed error.
     * The returned value is:
     * <ul>
     *  <li>zero if considered equal using {@link #equals(double,double,int) equals(x, y, maxUlps)}</li>
     *  <li>negative if not equal and {@code x < y}</li>
     *  <li>positive if not equal and {@code x > y}</li>
     * </ul>
     *
     * <p>NaN values are handled as if using {@link Double#compare(double, double)} where the
     * returned value is:
     * <ul>
     *  <li>zero if {@code NaN, NaN}</li>
     *  <li>negative if {@code !NaN, NaN}</li>
     *  <li>positive if {@code NaN, !NaN}</li>
     * </ul>
     *
     * @param x First value.
     * @param y Second value.
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return 0 if the value are considered equal, -1 if the first is smaller than
     * the second, 1 if the first is larger than the second.
     * @see #equals(double, double, int)
     */
    public static int compareTo(final double x, final double y, final int maxUlps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true iff they are equal as defined by
     * {@link #equals(float,float,int) equals(x, y, 1)}.
     *
     * @param x first value
     * @param y second value
     * @return {@code true} if the values are equal.
     */
    public static boolean equals(float x, float y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if both arguments are NaN or they are
     * equal as defined by {@link #equals(float,float) equals(x, y, 1)}.
     *
     * @param x first value
     * @param y second value
     * @return {@code true} if the values are equal or both are NaN.
     */
    public static boolean equalsIncludingNaN(float x, float y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code true} if there is no float value strictly between the
     * arguments or the difference between them is within the range of allowed
     * error (inclusive). Returns {@code false} if either of the arguments
     * is NaN.
     *
     * @param x first value
     * @param y second value
     * @param eps the amount of absolute error to allow.
     * @return {@code true} if the values are equal or within range of each other.
     */
    public static boolean equals(float x, float y, float eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if the arguments are both NaN, there are no float value strictly
     * between the arguments or the difference between them is within the range of allowed
     * error (inclusive).
     *
     * @param x first value
     * @param y second value
     * @param eps the amount of absolute error to allow.
     * @return {@code true} if the values are equal or within range of each other,
     * or both are NaN.
     */
    public static boolean equalsIncludingNaN(float x, float y, float eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if the arguments are equal or within the range of allowed
     * error (inclusive). Returns {@code false} if either of the arguments is NaN.
     * <p>
     * Two double numbers are considered equal if there are {@code (maxUlps - 1)}
     * (or fewer) floating point numbers between them, i.e. two adjacent
     * floating point numbers are considered equal.
     * </p>
     * <p>
     * Adapted from <a
     * href="https://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/">
     * Bruce Dawson</a>.
     * </p>
     *
     * @param x first value
     * @param y second value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return {@code true} if there are fewer than {@code maxUlps} floating
     * point values between {@code x} and {@code y}.
     */
    public static boolean equals(final float x, final float y, final int maxUlps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if both arguments are NaN or if they are equal as defined
     * by {@link #equals(float,float,int) equals(x, y, maxUlps)}.
     *
     * @param x first value
     * @param y second value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return {@code true} if both arguments are NaN or if there are less than
     * {@code maxUlps} floating point values between {@code x} and {@code y}.
     */
    public static boolean equalsIncludingNaN(float x, float y, int maxUlps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true iff they are equal as defined by
     * {@link #equals(double,double,int) equals(x, y, 1)}.
     *
     * @param x first value
     * @param y second value
     * @return {@code true} if the values are equal.
     */
    public static boolean equals(double x, double y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if the arguments are both NaN or they are
     * equal as defined by {@link #equals(double,double) equals(x, y, 1)}.
     *
     * @param x first value
     * @param y second value
     * @return {@code true} if the values are equal or both are NaN.
     */
    public static boolean equalsIncludingNaN(double x, double y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code true} if there is no double value strictly between the
     * arguments or the difference between them is within the range of allowed
     * error (inclusive). Returns {@code false} if either of the arguments
     * is NaN.
     *
     * @param x First value.
     * @param y Second value.
     * @param eps Amount of allowed absolute error.
     * @return {@code true} if the values are equal or within range of each other.
     */
    public static boolean equals(double x, double y, double eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code true} if there is no double value strictly between the
     * arguments or the relative difference between them is less than or equal
     * to the given tolerance. Returns {@code false} if either of the arguments
     * is NaN.
     *
     * @param x First value.
     * @param y Second value.
     * @param eps Amount of allowed relative error.
     * @return {@code true} if the values are two adjacent floating point
     * numbers or they are within range of each other.
     */
    public static boolean equalsWithRelativeTolerance(double x, double y, double eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if the arguments are both NaN, there are no double value strictly
     * between the arguments or the difference between them is within the range of allowed
     * error (inclusive).
     *
     * @param x first value
     * @param y second value
     * @param eps the amount of absolute error to allow.
     * @return {@code true} if the values are equal or within range of each other,
     * or both are NaN.
     */
    public static boolean equalsIncludingNaN(double x, double y, double eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if the arguments are equal or within the range of allowed
     * error (inclusive). Returns {@code false} if either of the arguments is NaN.
     * <p>
     * Two float numbers are considered equal if there are {@code (maxUlps - 1)}
     * (or fewer) floating point numbers between them, i.e. two adjacent
     * floating point numbers are considered equal.
     * </p>
     * <p>
     * Adapted from <a
     * href="https://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/">
     * Bruce Dawson</a>.
     * </p>
     *
     * @param x first value
     * @param y second value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return {@code true} if there are fewer than {@code maxUlps} floating
     * point values between {@code x} and {@code y}.
     */
    public static boolean equals(final double x, final double y, final int maxUlps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if both arguments are NaN or if they are equal as defined
     * by {@link #equals(double,double,int) equals(x, y, maxUlps)}.
     *
     * @param x first value
     * @param y second value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return {@code true} if both arguments are NaN or if there are less than
     * {@code maxUlps} floating point values between {@code x} and {@code y}.
     */
    public static boolean equalsIncludingNaN(double x, double y, int maxUlps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Rounds the given value to the specified number of decimal places.
     * The value is rounded using {@link RoundingMode#HALF_UP}.
     *
     * <p>Note: This method is intended to act on the String representation
     * of the {@code double} argument. See {@link #round(double, int, RoundingMode)}
     * for details.
     *
     * @param x Value to round.
     * @param scale Number of digits to the right of the decimal point.
     * @return the rounded value.
     * @see #round(double, int, RoundingMode)
     */
    public static double round(double x, int scale) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Rounds the given value to the specified number of decimal places.
     * The value is rounded using the given {@link RoundingMode rounding mode}.
     *
     * <p>If {@code x} is infinite or {@code NaN}, then the value of {@code x} is
     * returned unchanged, regardless of the other parameters.
     *
     * <p><b>Note</b>
     *
     * <p>Rounding of a 64-bit base-2 format {@code double} using a decimal representation
     * may result in rounding during conversion to and/or from a base-10 representation.
     *
     * <p>This method is intended to act on the String representation of the {@code double}
     * argument, i.e. the closest representable decimal value. The argument is converted to
     * a String (with possible rounding), rounding is performed on the decimal representation,
     * and the resulting String is returned as the closest representable {@code double}.
     *
     * <p>Conversion from base-2 to base-10 format uses the {@link BigDecimal#valueOf(double)}
     * method. The alternative would be to use
     * {@link BigDecimal#BigDecimal(double) new BigDecimal(x)} to construct an exact decimal
     * representation of the value.
     *
     * <p>Conversion from base-10 to base-2 format uses the equivalent of
     * {@link Double#parseDouble(String)} to create the closest representable {@code double}
     * to the decimal value.
     *
     * <p>The following code demonstrates how to eliminate rounding during conversion to a
     * decimal format. The return conversion to a {@code double} may be inexact:
     * <pre>
     * double rounded = new BigDecimal(x).setScale(scale, roundingMode).doubleValue();
     * </pre>
     *
     * <p>Acting on the String representation of the {@code double} allows this method to
     * return expected output when rounding {@code double} representations of decimal text.
     * <pre>
     * Precision.round(39.245, 2) == 39.25
     * Precision.round(30.095, 2) == 30.1
     * Precision.round(30.645, 2) == 30.65
     * </pre>
     *
     * @param x Value to round.
     * @param scale Number of digits to the right of the decimal point.
     * @param roundingMode Rounding mode as defined in {@link BigDecimal}.
     * @return the rounded value.
     * @see BigDecimal#doubleValue()
     * @throws ArithmeticException if {@code roundingMode} is
     * {@link RoundingMode#UNNECESSARY} and the specified scaling operation
     * would require rounding.
     */
    public static double round(double x, int scale, RoundingMode roundingMode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Computes a number close to {@code delta} with the property that
     * {@code (x + delta - x)} is exactly machine-representable.
     * This is useful when computing numerical derivatives, in order to
     * reduce roundoff errors.
     *
     * @param x Value.
     * @param delta Offset value.
     * @return the machine-representable floating number closest to the
     * difference between {@code x + delta} and {@code x}.
     */
    public static double representableDelta(double x, double delta) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a {@link DoubleEquivalence} instance that uses the given epsilon
     * value for determining equality.
     *
     * @param eps Value to use for determining equality.
     * @return a new instance.
     */
    public static DoubleEquivalence doubleEquivalenceOfEpsilon(final double eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Interface containing comparison operations for doubles that allow values
     * to be <em>considered</em> equal even if they are not exactly equal.
     * It is intended for comparing outputs of a computation where floating
     * point errors may have occurred.
     */
    @FunctionalInterface
    public interface DoubleEquivalence {

        /**
         * Indicates whether given values are considered equal to each other.
         *
         * @param a Value.
         * @param b Value.
         * @return true if the given values are considered equal.
         */
        default boolean eq(double a, double b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Indicates whether the given value is considered equal to zero.
         * It is a shortcut for {@code eq(a, 0.0)}.
         *
         * @param a Value.
         * @return true if the argument is considered equal to zero.
         */
        default boolean eqZero(double a) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Indicates whether the first argument is strictly smaller than the second.
         *
         * @param a Value.
         * @param b Value.
         * @return true if {@code a < b}
         */
        default boolean lt(double a, double b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Indicates whether the first argument is smaller or considered equal to the second.
         *
         * @param a Value.
         * @param b Value.
         * @return true if {@code a <= b}
         */
        default boolean lte(double a, double b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Indicates whether the first argument is strictly greater than the second.
         *
         * @param a Value.
         * @param b Value.
         * @return true if {@code a > b}
         */
        default boolean gt(double a, double b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Indicates whether the first argument is greater than or considered equal to the second.
         *
         * @param a Value.
         * @param b Value.
         * @return true if {@code a >= b}
         */
        default boolean gte(double a, double b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Returns the {@link Math#signum(double) sign} of the argument.
         * The returned value is
         * <ul>
         *  <li>{@code -0.0} if {@code a} is considered equal to zero and negatively signed,</li>
         *  <li>{@code +0.0} if {@code a} is considered equal to zero and positively signed,</li>
         *  <li>{@code -1.0} if {@code a} is considered less than zero,</li>
         *  <li>{@code +1.0} if {@code a} is considered greater than zero.</li>
         * </ul>
         *
         * <p>The equality with zero uses the {@link #eqZero(double) eqZero} method.
         *
         * @param a Value.
         * @return the sign (or {@code a} if {@code a == 0} or
         * {@code a} is NaN).
         * @see #eqZero(double)
         */
        default double signum(double a) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compares two values.
         * The returned value is
         * <ul>
         *  <li>{@code 0} if the arguments are considered equal,</li>
         *  <li>{@code -1} if {@code a < b},</li>
         *  <li>{@code +1} if {@code a > b} or if either value is NaN.</li>
         * </ul>
         *
         * @param a Value.
         * @param b Value.
         * @return {@code 0} if the values are considered equal, {@code -1}
         * if the first is smaller than the second, {@code 1} is the first
         * is larger than the second or either value is NaN.
         */
        int compare(double a, double b);
    }
}
