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

import java.math.BigInteger;

/**
 * Some useful, arithmetics related, additions to the built-in functions in
 * {@link Math}.
 */
public final class ArithmeticUtils {

    /**
     * Negative exponent exception message part 1.
     */
    private static final String NEGATIVE_EXPONENT_1 = "negative exponent ({";

    /**
     * Negative exponent exception message part 2.
     */
    private static final String NEGATIVE_EXPONENT_2 = "})";

    /**
     * Private constructor.
     */
    private ArithmeticUtils() {
        // intentionally empty.
    }

    /**
     * Computes the greatest common divisor of the absolute value of two
     * numbers, using a modified version of the "binary gcd" method.
     * See Knuth 4.5.2 algorithm B.
     * The algorithm is due to Josef Stein (1961).
     * <br>
     * Special cases:
     * <ul>
     *  <li>The invocations
     *   {@code gcd(Integer.MIN_VALUE, Integer.MIN_VALUE)},
     *   {@code gcd(Integer.MIN_VALUE, 0)} and
     *   {@code gcd(0, Integer.MIN_VALUE)} throw an
     *   {@code ArithmeticException}, because the result would be 2^31, which
     *   is too large for an int value.</li>
     *  <li>The result of {@code gcd(x, x)}, {@code gcd(0, x)} and
     *   {@code gcd(x, 0)} is the absolute value of {@code x}, except
     *   for the special cases above.</li>
     *  <li>The invocation {@code gcd(0, 0)} is the only one which returns
     *   {@code 0}.</li>
     * </ul>
     *
     * <p>Two numbers are relatively prime, or coprime, if their gcd is 1.</p>
     *
     * @param p Number.
     * @param q Number.
     * @return the greatest common divisor (never negative).
     * @throws ArithmeticException if the result cannot be represented as
     * a non-negative {@code int} value.
     */
    public static int gcd(int p, int q) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * <p>
     * Gets the greatest common divisor of the absolute value of two numbers,
     * using the "binary gcd" method which avoids division and modulo
     * operations. See Knuth 4.5.2 algorithm B. This algorithm is due to Josef
     * Stein (1961).
     * </p>
     * Special cases:
     * <ul>
     * <li>The invocations
     * {@code gcd(Long.MIN_VALUE, Long.MIN_VALUE)},
     * {@code gcd(Long.MIN_VALUE, 0L)} and
     * {@code gcd(0L, Long.MIN_VALUE)} throw an
     * {@code ArithmeticException}, because the result would be 2^63, which
     * is too large for a long value.</li>
     * <li>The result of {@code gcd(x, x)}, {@code gcd(0L, x)} and
     * {@code gcd(x, 0L)} is the absolute value of {@code x}, except
     * for the special cases above.</li>
     * <li>The invocation {@code gcd(0L, 0L)} is the only one which returns
     * {@code 0L}.</li>
     * </ul>
     *
     * <p>Two numbers are relatively prime, or coprime, if their gcd is 1.</p>
     *
     * @param p Number.
     * @param q Number.
     * @return the greatest common divisor, never negative.
     * @throws ArithmeticException if the result cannot be represented as
     * a non-negative {@code long} value.
     */
    public static long gcd(long p, long q) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * <p>
     * Returns the least common multiple of the absolute value of two numbers,
     * using the formula {@code lcm(a,b) = (a / gcd(a,b)) * b}.
     * </p>
     * Special cases:
     * <ul>
     * <li>The invocations {@code lcm(Integer.MIN_VALUE, n)} and
     * {@code lcm(n, Integer.MIN_VALUE)}, where {@code abs(n)} is a
     * power of 2, throw an {@code ArithmeticException}, because the result
     * would be 2^31, which is too large for an int value.</li>
     * <li>The result of {@code lcm(0, x)} and {@code lcm(x, 0)} is
     * {@code 0} for any {@code x}.</li>
     * </ul>
     *
     * @param a Number.
     * @param b Number.
     * @return the least common multiple, never negative.
     * @throws ArithmeticException if the result cannot be represented as
     * a non-negative {@code int} value.
     */
    public static int lcm(int a, int b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * <p>
     * Returns the least common multiple of the absolute value of two numbers,
     * using the formula {@code lcm(a,b) = (a / gcd(a,b)) * b}.
     * </p>
     * Special cases:
     * <ul>
     * <li>The invocations {@code lcm(Long.MIN_VALUE, n)} and
     * {@code lcm(n, Long.MIN_VALUE)}, where {@code abs(n)} is a
     * power of 2, throw an {@code ArithmeticException}, because the result
     * would be 2^63, which is too large for an int value.</li>
     * <li>The result of {@code lcm(0L, x)} and {@code lcm(x, 0L)} is
     * {@code 0L} for any {@code x}.</li>
     * </ul>
     *
     * @param a Number.
     * @param b Number.
     * @return the least common multiple, never negative.
     * @throws ArithmeticException if the result cannot be represented
     * as a non-negative {@code long} value.
     */
    public static long lcm(long a, long b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Raise an int to an int power.
     *
     * <p>Special cases:</p>
     * <ul>
     *   <li>{@code k^0} returns {@code 1} (including {@code k=0})</li>
     *   <li>{@code k^1} returns {@code k} (including {@code k=0})</li>
     *   <li>{@code 0^0} returns {@code 1}</li>
     *   <li>{@code 0^e} returns {@code 0}</li>
     *   <li>{@code 1^e} returns {@code 1}</li>
     *   <li>{@code (-1)^e} returns {@code -1 or 1} if {@code e} is odd or even</li>
     * </ul>
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return \( k^e \)
     * @throws IllegalArgumentException if {@code e < 0}.
     * @throws ArithmeticException if the result would overflow.
     */
    public static int pow(final int k, final int e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Raise a long to an int power.
     *
     * <p>Special cases:</p>
     * <ul>
     *   <li>{@code k^0} returns {@code 1} (including {@code k=0})</li>
     *   <li>{@code k^1} returns {@code k} (including {@code k=0})</li>
     *   <li>{@code 0^0} returns {@code 1}</li>
     *   <li>{@code 0^e} returns {@code 0}</li>
     *   <li>{@code 1^e} returns {@code 1}</li>
     *   <li>{@code (-1)^e} returns {@code -1 or 1} if {@code e} is odd or even</li>
     * </ul>
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return \( k^e \)
     * @throws IllegalArgumentException if {@code e < 0}.
     * @throws ArithmeticException if the result would overflow.
     */
    public static long pow(final long k, final int e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Raise a BigInteger to an int power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return k<sup>e</sup>
     * @throws IllegalArgumentException if {@code e < 0}.
     */
    public static BigInteger pow(final BigInteger k, int e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Raise a BigInteger to a long power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return k<sup>e</sup>
     * @throws IllegalArgumentException if {@code e < 0}.
     */
    public static BigInteger pow(final BigInteger k, final long e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Raise a BigInteger to a BigInteger power.
     *
     * @param k Number to raise.
     * @param e Exponent (must be positive or zero).
     * @return k<sup>e</sup>
     * @throws IllegalArgumentException if {@code e < 0}.
     */
    public static BigInteger pow(final BigInteger k, final BigInteger e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns true if the argument is a power of two.
     *
     * @param n the number to test
     * @return true if the argument is a power of two
     */
    public static boolean isPowerOfTwo(long n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the unsigned remainder from dividing the first argument
     * by the second where each argument and the result is interpreted
     * as an unsigned value.
     *
     * <p>Implementation note
     *
     * <p>In v1.0 this method did not use the {@code long} datatype.
     * Modern 64-bit processors make use of the {@code long} datatype
     * faster than an algorithm using the {@code int} datatype. This method
     * now delegates to {@link Integer#remainderUnsigned(int, int)}
     * which uses {@code long} arithmetic; or from JDK 19 an intrinsic method.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned remainder of the first argument divided by
     * the second argument.
     * @see Integer#remainderUnsigned(int, int)
     */
    public static int remainderUnsigned(int dividend, int divisor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the unsigned remainder from dividing the first argument
     * by the second where each argument and the result is interpreted
     * as an unsigned value.
     *
     * <p>Implementation note
     *
     * <p>This method does not use the {@code BigInteger} datatype.
     * The JDK implementation of {@link Long#remainderUnsigned(long, long)}
     * uses {@code BigInteger} prior to JDK 17 and this method is 15-25x faster.
     * From JDK 17 onwards the JDK implementation is as fast; or from JDK 19
     * even faster due to use of an intrinsic method.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned remainder of the first argument divided by
     * the second argument.
     * @see Long#remainderUnsigned(long, long)
     */
    public static long remainderUnsigned(long dividend, long divisor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the unsigned quotient of dividing the first argument by
     * the second where each argument and the result is interpreted as
     * an unsigned value.
     * <p>Note that in two's complement arithmetic, the three other
     * basic arithmetic operations of add, subtract, and multiply are
     * bit-wise identical if the two operands are regarded as both
     * being signed or both being unsigned. Therefore separate {@code
     * addUnsigned}, etc. methods are not provided.</p>
     *
     * <p>Implementation note
     *
     * <p>In v1.0 this method did not use the {@code long} datatype.
     * Modern 64-bit processors make use of the {@code long} datatype
     * faster than an algorithm using the {@code int} datatype. This method
     * now delegates to {@link Integer#divideUnsigned(int, int)}
     * which uses {@code long} arithmetic; or from JDK 19 an intrinsic method.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned quotient of the first argument divided by
     * the second argument
     * @see Integer#divideUnsigned(int, int)
     */
    public static int divideUnsigned(int dividend, int divisor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the unsigned quotient of dividing the first argument by
     * the second where each argument and the result is interpreted as
     * an unsigned value.
     * <p>Note that in two's complement arithmetic, the three other
     * basic arithmetic operations of add, subtract, and multiply are
     * bit-wise identical if the two operands are regarded as both
     * being signed or both being unsigned. Therefore separate {@code
     * addUnsigned}, etc. methods are not provided.</p>
     *
     * <p>Implementation note
     *
     * <p>This method does not use the {@code BigInteger} datatype.
     * The JDK implementation of {@link Long#divideUnsigned(long, long)}
     * uses {@code BigInteger} prior to JDK 17 and this method is 15-25x faster.
     * From JDK 17 onwards the JDK implementation is as fast; or from JDK 19
     * even faster due to use of an intrinsic method.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned quotient of the first argument divided by
     * the second argument.
     * @see Long#divideUnsigned(long, long)
     */
    public static long divideUnsigned(long dividend, long divisor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Exception.
     */
    private static class NumbersArithmeticException extends ArithmeticException {

        /**
         * Serializable version Id.
         */
        private static final long serialVersionUID = 20180130L;

        /**
         * Create an exception where the message is constructed by applying
         * {@link String#format(String, Object...)}.
         *
         * @param message Exception message format string
         * @param args Arguments for formatting the message
         */
        NumbersArithmeticException(String message, Object... args) {
            super(String.format(message, args));
        }
    }
}
