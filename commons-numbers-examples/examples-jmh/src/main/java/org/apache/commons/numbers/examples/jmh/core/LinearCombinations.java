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

import java.math.BigDecimal;
import java.util.function.IntFunction;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.FourD;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.ND;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.ThreeD;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.TwoD;

/**
 * Provides implementations to computes linear combinations as the sum of
 * the products of two sequences of numbers
 * <code>a<sub>i</sub> b<sub>i</sub></code>.
 *
 * @see LinearCombination
 */
public final class LinearCombinations {

    /**
     * No public constructor.
     */
    private LinearCombinations() {
    }

    /**
     * Base class to compute a linear combination with high accuracy.
     * Contains common code for computing short combinations and computing
     * the standard precision sum of products.
     */
    public abstract static class BaseLinearCombination implements LinearCombination.ND {

        /**
         * {@inheritDoc}
         */
        @Override
        public double value(double[] a, double[] b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the sum of the products of two sequences of factors with high accuracy.
         * The input arrays will have a length of at least 2; the lengths will
         * be the same.
         *
         * @param a Factors.
         * @param b Factors.
         * @return \( \sum_i a_i b_i \).
         */
        protected abstract double computeValue(double[] a, double[] b);

        /**
         * Compute the sum of the products of two sequences of factors.
         * This is a standard precision summation for the IEEE754 result.
         *
         * @param a Factors.
         * @param b Factors.
         * @return \( \sum_i a_i b_i \).
         */
        static double standardDotProduct(double[] a, double[] b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Computes linear combinations using standard precision multiplication and summation.
     *
     * <p>This class is used for a baseline and does not provide high precision results.
     */
    static final class StandardPrecision implements TwoD, ThreeD, FourD, ND {

        /**
         * An instance.
         */
        static final StandardPrecision INSTANCE = new StandardPrecision();

        /**
         * Private constructor.
         */
        private StandardPrecision() {
        }

        @Override
        public double value(double[] a, double[] b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Computes linear combinations using the double-length multiplication and summation
     * algorithms of Dekker.
     *
     * @see <a href="https://doi.org/10.1007/BF01397083">
     * Dekker (1971) A floating-point technique for extending the available precision</a>
     */
    public static final class Dekker extends BaseLinearCombination implements TwoD, ThreeD, FourD {

        /**
         * An instance.
         */
        public static final Dekker INSTANCE = new Dekker();

        /**
         * Private constructor.
         */
        private Dekker() {
        }

        @Override
        protected double computeValue(double[] a, double[] b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Computes linear combinations accurately using the DotK algorithm of Ogita et al
     * for K-fold precision of the sum.
     *
     * <p>It is based on the 2005 paper
     * <a href="https://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita, Siegfried M. Rump,
     * and Shin'ichi Oishi published in <em>SIAM J. Sci. Comput</em>.
     *
     * <p>Note: It is possible to use this class to compute 2-fold precision. In this case
     * the round-off parts of the dot-product are stored in an array and summed. The
     * {@link Dot2s} class provides an alternative faster implementation that sums the
     * round-off parts during processing to avoid array allocation overhead. The results will
     * not be identical due to a different order for the summation of the round-off parts.
     *
     * <p>The number of operations will scale with {@code k}. When computing the small linear
     * combinations {@link TwoD}, {@link ThreeD} and {@link FourD} using a {@code k} value too
     * large will result in more operations than the number used by {@link ExtendedPrecision}.
     * For a small {@code n}-dimension linear combinations the value {@code k = n+1} will have the
     * same number of operations as {@link ExtendedPrecision} and users should switch to the
     * {@link ExtendedPrecision} class. For example 3D combinations should use {@code k} up to
     * 3 or else switch to using {@link ExtendedPrecision}. This rule does not apply to the
     * {@link org.apache.commons.numbers.examples.jmh.core.LinearCombination.ND ND}
     * implementations as {@link ExtendedPrecision} eliminates redundant operations
     * on zeros. In this case equivalent performance will be observed when {@code k <= n+1}.
     * Choice of implementation must consider performance and accuracy on real-world data.
     */
    public static final class DotK extends BaseLinearCombination implements TwoD, ThreeD, FourD {

        /**
         * An instance computing 3-fold precision.
         */
        public static final DotK DOT_3 = new DotK(3);

        /**
         * An instance computing 4-fold precision.
         */
        public static final DotK DOT_4 = new DotK(4);

        /**
         * An instance computing 5-fold precision.
         */
        public static final DotK DOT_5 = new DotK(5);

        /**
         * An instance computing 6-fold precision.
         */
        public static final DotK DOT_6 = new DotK(6);

        /**
         * An instance computing 7-fold precision.
         */
        public static final DotK DOT_7 = new DotK(7);

        /**
         * The k-fold precision to compute.
         */
        private final int k;

        /**
         * The factory to create a working array.
         */
        private final IntFunction<double[]> arrayFactory;

        /**
         * Instantiates a new dot K.
         *
         * @param k K-fold precision.
         */
        public DotK(int k) {
            this(k, double[]::new);
        }

        /**
         * Instantiates a new dot K.
         *
         * @param k K-fold precision.
         * @param arrayFactory The factory to create a working array.
         */
        DotK(int k, IntFunction<double[]> arrayFactory) {
            this.k = k;
            this.arrayFactory = arrayFactory;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected double computeValue(double[] a, double[] b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public double value(double a1, double b1, double a2, double b2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sum to K-fold precision.
         *
         * @param p Data to sum.
         * @param km1 The precision (k-1).
         * @return the sum
         */
        private static double sumK(double[] p, int km1) {
            // (k-1)=1 will skip the vector transformation and sum in standard precision.
            for (int i = 1; i < km1; i++) {
                vectorSum(p);
            }
            double sum = 0;
            for (final double pi : p) {
                sum += pi;
            }
            return sum;
        }

        /**
         * Error free vector transformation for summation.
         *
         * @param p Data.
         */
        private static void vectorSum(double[] p) {
            for (int i = 1; i < p.length; i++) {
                final double x = p[i] + p[i - 1];
                p[i - 1] = DoublePrecision.twoSumLow(p[i], p[i - 1], x);
                p[i] = x;
            }
        }
    }

    /**
     * Computes linear combinations accurately using the Dot2s algorithm of Ogita et al
     * for 2-fold precision of the sum.
     *
     * <p>It is based on the 2005 paper
     * <a href="https://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita, Siegfried M. Rump,
     * and Shin'ichi Oishi published in <em>SIAM J. Sci. Comput</em>.
     *
     * <p>This is faster than using {@link DotK} with a {@code k} of 2. The results will
     * not be identical due to a different summation order of the round-off parts.
     */
    public static final class Dot2s extends BaseLinearCombination implements TwoD, ThreeD, FourD {

        /**
         * An instance computing 2-fold precision.
         */
        public static final Dot2s INSTANCE = new Dot2s();

        /**
         * Private constructor.
         */
        private Dot2s() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected double computeValue(double[] a, double[] b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public double value(double a1, double b1, double a2, double b2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Computes linear combinations exactly using BigDecimal.
     * This computation may be prohibitively slow on large combination sums.
     */
    public static final class Exact extends BaseLinearCombination implements TwoD, ThreeD, FourD {

        /**
         * An instance.
         */
        public static final Exact INSTANCE = new Exact();

        /**
         * Private constructor.
         */
        private Exact() {
        }

        @Override
        protected double computeValue(double[] a, double[] b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Test that all the values are finite.
         *
         * @param values the values
         * @return true if finite
         */
        private static boolean areFinite(double... values) {
            for (final double value : values) {
                if (!Double.isFinite(value)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Multiply the factors to a BigDecimal.
         *
         * @param a First factor.
         * @param b Second factor.
         * @return the product
         */
        private static BigDecimal multiply(double a, double b) {
            return new BigDecimal(a).multiply(new BigDecimal(b));
        }
    }

    /**
     * Computes linear combinations accurately using extended precision representations of
     * floating point numbers.
     *
     * <p>It is based on the paper by
     * <a href="https://www-2.cs.cmu.edu/afs/cs/project/quake/public/papers/robust-arithmetic.ps">
     * Shewchuk (1997): Arbitrary Precision Floating-Point Arithmetic</a>.
     */
    public static final class ExtendedPrecision extends BaseLinearCombination implements TwoD, ThreeD, FourD {

        /*
         * Note:
         *
         * An expansion representation of a number is a series of non-overlapping floating-point
         * values where the most significant bit of each value is less than the least significant
         * bit of the next value. The summation of the expansion is exact (without round-off error)
         * and is equal to the original number. The largest magnitude value in the expansion is
         * an approximation of the number.
         *
         * Expansions are created from normal numbers by multiplications or additions that
         * represent the result exactly with extended precision. Addition of expansions f and g
         * of length m and n creates an expansion of length (m+n). Some parts of the expansion
         * may be zero and can be eliminated. The size of the expansion is constrained by the
         * maximum and minimum exponents required to represent the original results of
         * multiplication and addition. If all products in the linear combination have
         * approximately the same magnitude the expansion size will be small and the sum
         * is efficient. If the products have a very large range of magnitudes then the expansion
         * will require a large size and the sum is computationally more expensive.
         */
        /**
         * An instance computing the final summation of the extended precision value
         * using standard precision. The sum will be within 1 ULP of the exact result.
         */
        public static final ExtendedPrecision INSTANCE = new ExtendedPrecision(ApproximationSum.INSTANCE);

        /**
         * An instance computing the final summation of the extended precision value
         * using two-fold precision. The sum will be within 1 ULP of the exact result.
         */
        public static final ExtendedPrecision DOUBLE = new ExtendedPrecision(TwoSum.INSTANCE);

        /**
         * An instance computing the final summation of the extended precision value exactly.
         */
        public static final ExtendedPrecision EXACT = new ExtendedPrecision(ExactSum.INSTANCE);

        /**
         * An instance computing the final summation of the extended precision value exactly.
         */
        public static final ExtendedPrecision EXACT2 = new ExtendedPrecision(ExactSum2.INSTANCE);

        /**
         * Define methods to sum an expansion.
         */
        private interface ExpansionSum {

            /**
             * Sum the expansion of size m. It can be assumed that the size is non-zero.
             *
             * @param e Expansion.
             * @param m Size.
             * @return the sum
             */
            double sum(double[] e, int m);

            /**
             * Sum the expansion; low parts have smaller magnitudes.
             *
             * @param e0 Part 0.
             * @param e1 Part 1.
             * @param e2 Part 2.
             * @param e3 Part 3.
             * @return the sum
             */
            double sum(double e0, double e1, double e2, double e3);

            /**
             * Sum the expansion; low parts have smaller magnitudes.
             *
             * @param e0 Part 0.
             * @param e1 Part 1.
             * @param e2 Part 2.
             * @param e3 Part 3.
             * @param e4 Part 4.
             * @param e5 Part 5.
             * @return the sum
             */
            double sum(double e0, double e1, double e2, double e3, double e4, double e5);

            /**
             * Sum the expansion; low parts have smaller magnitudes.
             *
             * @param e0 Part 0.
             * @param e1 Part 1.
             * @param e2 Part 2.
             * @param e3 Part 3.
             * @param e4 Part 4.
             * @param e5 Part 5.
             * @param e6 Part 6.
             * @param e7 Part 7.
             * @return the sum
             */
            double sum(double e0, double e1, double e2, double e3, double e4, double e5, double e6, double e7);
        }

        /**
         * Shewchuk's APPROXIMATION algorithm sums the parts in order of increasing magnitude.
         * The sum is within 1 ulp of the true expansion value.
         */
        private static final class ApproximationSum implements ExpansionSum {

            /**
             * An instance.
             */
            static final ApproximationSum INSTANCE = new ApproximationSum();

            /**
             * No construction.
             */
            private ApproximationSum() {
            }

            @Override
            public double sum(double[] e, int m) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3, double e4, double e5) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3, double e4, double e5, double e6, double e7) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        /**
         * Perform a two-sum through the expansion.
         * The single pass with two-sum ensures that the final term e_m is a good approximation
         * for e: |e - e_m| < ulp(e_m); and the sum of the parts to
         * e_(m-1) is within 1 ULP of the round-off ulp(|e - e_m|).
         */
        private static final class TwoSum implements ExpansionSum {

            /**
             * An instance.
             */
            static final TwoSum INSTANCE = new TwoSum();

            /**
             * No construction.
             */
            private TwoSum() {
            }

            @Override
            public double sum(double[] e, int m) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3, double e4, double e5) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3, double e4, double e5, double e6, double e7) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        /**
         * Shewchuk's COMPRESS(e) algorithm uses fast-two-sum with a double pass
         * (big->small then small->big) and zero elimination for up to 6m additions (zero
         * elimination reduces the size of the second pass). This ensure e_m is within 1
         * ULP of the expansion value: |e - e_m| < ulp(e_m); and the sum to e_(m-1) is
         * within 1 ULP of the round-off ulp(|e - e_m|). Summation of the expansion should
         * then have low error.
         *
         * <p>Here we modify the second pass to set a sticky bit on the carried sum
         * that allows the rounding of the next addition to compute round-to-nearest,
         * ties-to-even rounding. This simulates the floating point addition of a+b into
         * an extended register where some extra digits are held beyond the precision of
         * the result for use in rounding. The final digit is the sticky bit, a bit wise OR
         * of all the bits from the exact result that are discarded. This works if the value
         * with the sticky bit is added to a value with a higher exponent. The use of the
         * COMPRESS algorithm ensures the next addition is to a number larger in magnitude.
         *
         * <p>Note: The final addition of the sticky carry must be to a non-zero value.
         * Otherwise the sticky bit will be left in the result potentially causing a 1
         * ULP error.
         *
         * <p>Details of the sticky bit can be found in:
         * <blockquote>
         * Coonen, J.T., "An Implementation Guide to a Proposed Standard for Floating Point
         * Arithmetic", Computer, Vol. 13, No. 1, Jan. 1980, pp 68-79.
         * </blockquote>
         */
        private static class ExactSum implements ExpansionSum {

            /**
             * An instance.
             */
            static final ExactSum INSTANCE = new ExactSum();

            /**
             * Package-private construction to allow class extension.
             */
            ExactSum() {
            }

            @Override
            public double sum(double[] e, int size) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3, double e4, double e5) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3, double e4, double e5, double e6, double e7) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            /**
             * Compute the summation of the parts using a fast-two-sum. The remainder is
             * carried into the next part using a sticky bit for the final correctly
             * rounded result.
             *
             * @param g0 Part 0
             * @param g1 Part 1
             * @param g2 Part 2
             * @param g3 Part 3
             * @param g4 Part 4
             * @param g5 Part 5
             * @param g6 Part 6
             * @param g7 Part 7
             * @return the sum
             */
            private static double stickySum(double g0, double g1, double g2, double g3, double g4, double g5, double g6, double g7) {
                if (g7 == 0) {
                    return stickySum(g0, g1, g2, g3, g4, g5, g6);
                }
                double q = fastSumWithStickyBit(g1, g0);
                q = fastSumWithStickyBit(g2, q);
                q = fastSumWithStickyBit(g3, q);
                q = fastSumWithStickyBit(g4, q);
                q = fastSumWithStickyBit(g5, q);
                return g7 + fastSumWithStickyBit(g6, q);
            }

            /**
             * Compute the summation of the parts using a fast-two-sum. The remainder is
             * carried into the next part using a sticky bit for the final correctly
             * rounded result.
             *
             * @param g0 Part 0
             * @param g1 Part 1
             * @param g2 Part 2
             * @param g3 Part 3
             * @param g4 Part 4
             * @param g5 Part 5
             * @param g6 Part 6
             * @return the sum
             */
            private static double stickySum(double g0, double g1, double g2, double g3, double g4, double g5, double g6) {
                if (g6 == 0) {
                    return stickySum(g0, g1, g2, g3, g4, g5);
                }
                double q = fastSumWithStickyBit(g1, g0);
                q = fastSumWithStickyBit(g2, q);
                q = fastSumWithStickyBit(g3, q);
                q = fastSumWithStickyBit(g4, q);
                return g6 + fastSumWithStickyBit(g5, q);
            }

            /**
             * Compute the summation of the parts using a fast-two-sum. The remainder is
             * carried into the next part using a sticky bit for the final correctly
             * rounded result.
             *
             * @param g0 Part 0
             * @param g1 Part 1
             * @param g2 Part 2
             * @param g3 Part 3
             * @param g4 Part 4
             * @param g5 Part 5
             * @return the sum
             */
            private static double stickySum(double g0, double g1, double g2, double g3, double g4, double g5) {
                if (g5 == 0) {
                    return stickySum(g0, g1, g2, g3, g4);
                }
                double q = fastSumWithStickyBit(g1, g0);
                q = fastSumWithStickyBit(g2, q);
                q = fastSumWithStickyBit(g3, q);
                return g5 + fastSumWithStickyBit(g4, q);
            }

            /**
             * Compute the summation of the parts using a fast-two-sum. The remainder is
             * carried into the next part using a sticky bit for the final correctly
             * rounded result.
             *
             * @param g0 Part 0
             * @param g1 Part 1
             * @param g2 Part 2
             * @param g3 Part 3
             * @param g4 Part 4
             * @return the sum
             */
            private static double stickySum(double g0, double g1, double g2, double g3, double g4) {
                if (g4 == 0) {
                    return stickySum(g0, g1, g2, g3);
                }
                double q = fastSumWithStickyBit(g1, g0);
                q = fastSumWithStickyBit(g2, q);
                return g4 + fastSumWithStickyBit(g3, q);
            }

            /**
             * Compute the summation of the parts using a fast-two-sum. The remainder is
             * carried into the next part using a sticky bit for the final correctly
             * rounded result.
             *
             * @param g0 Part 0
             * @param g1 Part 1
             * @param g2 Part 2
             * @param g3 Part 3
             * @return the sum
             */
            private static double stickySum(double g0, double g1, double g2, double g3) {
                if (g3 == 0) {
                    return stickySum(g0, g1, g2);
                }
                final double q = fastSumWithStickyBit(g1, g0);
                return g3 + fastSumWithStickyBit(g2, q);
            }

            /**
             * Compute the summation of the parts using a fast-two-sum. The remainder is
             * carried into the next part using a sticky bit for the final correctly
             * rounded result.
             *
             * @param g0 Part 0
             * @param g1 Part 1
             * @param g2 Part 2
             * @return the sum
             */
            private static double stickySum(double g0, double g1, double g2) {
                if (g2 == 0) {
                    // No sticky bit needed
                    return g1 + g0;
                }
                return g2 + fastSumWithStickyBit(g1, g0);
            }

            /**
             * Compute the sum of two numbers {@code a} and {@code b} using
             * Dekker's two-sum algorithm. The values are required to be ordered by magnitude
             * {@code |a| >= |b|}. The result is adjusted to set the lowest bit as a sticky
             * bit that summarises the magnitude of the round-off that were lost. The
             * result is not the correctly rounded result; it is intended the result is to
             * be used in an addition with a value with a greater magnitude exponent. This
             * addition will have exact round-to-nearest, ties-to-even rounding taking account
             * of bits lots in the previous sum.
             *
             * <p>Details of the sticky bit can be found in:
             * <blockquote>
             * Coonen, J.T., "An Implementation Guide to a Proposed Standard for Floating Point
             * Arithmetic", Computer, Vol. 13, No. 1, Jan. 1980, pp 68-79.
             * </blockquote>
             *
             * @param a First part of sum.
             * @param b Second part of sum.
             * @return <code>b - (sum - a)</code>
             * @see <a href="https://www-2.cs.cmu.edu/afs/cs/project/quake/public/papers/robust-arithmetic.ps">
             * Shewchuk (1997) Theorum 6</a>
             */
            static double fastSumWithStickyBit(double a, double b) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        /**
         * Shewchuk's COMPRESS(e) algorithm uses fast-two-sum with a double pass
         * (big->small then small->big) and zero elimination. This is a variant
         * of {@link ExactSum}. Methods differ for the inlined versions which use an array
         * to store non-zero values for an efficient second pass summation. This avoids
         * the cascading function calls used in {@link ExactSum}. This variant exists for
         * benchmarking.
         */
        private static final class ExactSum2 extends ExactSum {

            /**
             * An instance.
             */
            static final ExactSum2 INSTANCE = new ExactSum2();

            /**
             * No construction.
             */
            private ExactSum2() {
            }

            @Override
            public double sum(double[] e, int size) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            /**
             * Compute the summation of the parts using a fast-two-sum. The remainder is
             * carried into the next part using a sticky bit for the final correctly
             * rounded result.
             *
             * @param q the lowest term
             * @param e the remaining terms from in the range (bottom, m]
             * @param bottom the bottom of the range (exclusive)
             * @param m the upper of the range (inclusive)
             * @return the sum
             */
            private static double stickySum(double q, double[] e, int bottom, int m) {
                if (bottom == m) {
                    // Complete compression to size 1
                    return q;
                }
                // Second traversal (from small to big)
                // Here we do not store the round-off parts back into the expansion
                // but summarise them into the carry using a sticky bit.
                for (int i = bottom + 1; i < m; i++) {
                    q = fastSumWithStickyBit(e[i], q);
                }
                // Final sum. No requirement to compute round-off.
                return e[m] + q;
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3, double e4, double e5) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public double sum(double e0, double e1, double e2, double e3, double e4, double e5, double e6, double e7) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        /**
         * Define the summation algorithm for the expansion.
         */
        enum Summation {

            /**
             * Standard precision sum of the parts.
             */
            STANDARD,
            /**
             * Double-length precision sum of the parts.
             */
            DOUBLE,
            /**
             * Exact sum of the parts.
             */
            EXACT
        }

        /**
         * The summation algorithm.
         */
        private final ExpansionSum summation;

        /**
         * The factory to create a working array.
         */
        private final IntFunction<double[]> arrayFactory;

        /**
         * Private constructor.
         *
         * @param summation The summation algorithm.
         */
        private ExtendedPrecision(ExpansionSum summation) {
            this(summation, double[]::new);
        }

        /**
         * Private constructor.
         *
         * @param summation The summation algorithm.
         * @param arrayFactory The factory to create a working array.
         */
        private ExtendedPrecision(ExpansionSum summation, IntFunction<double[]> arrayFactory) {
            this.summation = summation;
            this.arrayFactory = arrayFactory;
        }

        /**
         * Create an instance with the specified summation algorithm and array factory.
         * Thread-safety is dependent on the array factory.
         *
         * @param type Summation algorithm
         * @param arrayFactory The factory to create a working array.
         * @return the instance
         */
        static ExtendedPrecision of(Summation type, IntFunction<double[]> arrayFactory) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected double computeValue(double[] a, double[] b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public double value(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the sum of the two products and store the result in the expansion.
         * Interspersed zeros are removed and the length returned.
         *
         * @param a1 First factor of the first term.
         * @param b1 Second factor of the first term.
         * @param a2 First factor of the second term.
         * @param b2 Second factor of the second term.
         * @param e Expansion
         * @return the length of the new expansion (can be zero)
         */
        private static int sumProduct(double a1, double b1, double a2, double b2, double[] e) {
            // Expansion e
            double e1 = a1 * b1;
            double e0 = DoublePrecision.productLow(a1, b1, e1);
            // Expansion f
            final double f1 = a2 * b2;
            final double f0 = DoublePrecision.productLow(a2, b2, f1);
            // Inline an expansion sum to avoid sorting e and f into a sequence g.
            // f0 into e
            double q = e0 + f0;
            e0 = DoublePrecision.twoSumLow(e0, f0, q);
            double e2 = e1 + q;
            e1 = DoublePrecision.twoSumLow(e1, q, e2);
            // f1 into e
            q = e1 + f1;
            e1 = DoublePrecision.twoSumLow(e1, f1, q);
            final double e3 = e2 + q;
            e2 = DoublePrecision.twoSumLow(e2, q, e3);
            // Store but remove interspersed zeros
            int ei = 0;
            if (e0 != 0) {
                e[ei++] = e0;
            }
            if (e1 != 0) {
                e[ei++] = e1;
            }
            if (e2 != 0) {
                e[ei++] = e2;
            }
            // Unlikely that the overall representation of the two-product is zero
            // so no check for non-zero here.
            e[ei++] = e3;
            return ei;
        }

        /**
         * Grow the expansion. This maintains the increasing non-overlapping expansion
         * by two-summing the new value through the entire expansion from the given
         * start.
         *
         * @param expansion Expansion.
         * @param length Expansion size.
         * @param start Start point to begin the merge. To be used for optimised
         * expansion sum.
         * @param value Value to add.
         */
        private static void growExpansion(double[] expansion, int length, int start, double value) {
            double p = value;
            for (int i = start; i < length; i++) {
                final double ei = expansion[i];
                final double q = ei + p;
                expansion[i] = DoublePrecision.twoSumLow(ei, p, q);
                // Carry the larger magnitude up to the next iteration.
                p = q;
            }
            expansion[length] = p;
        }

        /**
         * Perform zero elimination on the expansion.
         * The new size can be zero.
         *
         * @param e Expansion.
         * @param size Expansion size.
         * @return the new size
         */
        private static int zeroElimination(double[] e, int size) {
            int newSize = 0;
            // Skip to the first zero
            while (newSize < size && e[newSize] != 0) {
                newSize++;
            }
            if (newSize != size) {
                // Skip the zero and copy remaining non-zeros.
                // This avoids building blocks of non-zeros to bulk-copy using
                // System.arraycopy. This extra complexity may be useful
                // if the number of zeros is small compared to the
                // length of the expansion.
                for (int i = newSize + 1; i < size; i++) {
                    if (e[i] != 0) {
                        e[newSize++] = e[i];
                    }
                }
            }
            return newSize;
        }
    }

    /**
     * Gets the final sum. This checks the high precision sum is finite, otherwise
     * returns the standard precision sum for the IEEE754 result.
     *
     * <p>The high precision sum may be non-finite due to input infinite
     * or NaN numbers or overflow in the summation. However the high precision sum
     * can also be non-finite when the standard sum is finite. This occurs when
     * the split product had a component high-part that overflowed during
     * computation of the hx * hy partial result. In all cases returning the
     * standard sum ensures the IEEE754 result.
     *
     * @param sum Standard sum.
     * @param hpSum High precision sum.
     * @return the sum
     */
    static double getSum(double sum, double hpSum) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
