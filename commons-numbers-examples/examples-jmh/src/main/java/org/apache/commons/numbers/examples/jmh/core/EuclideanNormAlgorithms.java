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
import java.math.MathContext;
import java.util.function.ToDoubleFunction;
import org.apache.commons.numbers.core.Sum;

/**
 * Class containing various Euclidean norm computation methods for comparison.
 */
public final class EuclideanNormAlgorithms {

    /**
     * No instantiation.
     */
    private EuclideanNormAlgorithms() {
    }

    /**
     * Exact computation method using {@link BigDecimal} and {@link MathContext#DECIMAL128}.
     */
    static final class Exact implements ToDoubleFunction<double[]> {

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Direct computation method that simply computes the sums of squares and takes
     * the square root with no special handling of values.
     */
    static final class Direct implements ToDoubleFunction<double[]> {

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Translation of the <a href="https://www.netlib.org/minpack">minpack</a>
     * "enorm" subroutine. This method handles overflow and underflow.
     */
    static final class Enorm implements ToDoubleFunction<double[]> {

        /**
         * Constant.
         */
        private static final double R_DWARF = 3.834e-20;

        /**
         * Constant.
         */
        private static final double R_GIANT = 1.304e+19;

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Modified version of {@link Enorm} created by Alex Herbert.
     */
    static final class EnormMod implements ToDoubleFunction<double[]> {

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the square of {@code x}.
         * @param x input value
         * @return square of {@code x}
         */
        private static double square(final double x) {
            return x * x;
        }
    }

    /**
     * Version of {@link EnormMod} using Kahan summation.
     */
    static final class EnormModKahan implements ToDoubleFunction<double[]> {

        /**
         * Threshold for scaling small numbers.
         */
        private static final double SMALL_THRESH = 0x1.0p-500;

        /**
         * Threshold for scaling large numbers.
         */
        private static final double LARGE_THRESH = 0x1.0p+500;

        /**
         * Value used to scale down large numbers.
         */
        private static final double SCALE_DOWN = 0x1.0p-600;

        /**
         * Value used to scale up small numbers.
         */
        private static final double SCALE_UP = 0x1.0p+600;

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the square of {@code x}.
         * @param x input value
         * @return square of {@code x}
         */
        private static double square(final double x) {
            return x * x;
        }
    }

    /**
     * Version of {@link EnormMod} using extended precision summation.
     */
    static final class EnormModExt implements ToDoubleFunction<double[]> {

        /**
         * Threshold for scaling small numbers.
         */
        private static final double SMALL_THRESH = 0x1.0p-500;

        /**
         * Threshold for scaling large numbers.
         */
        private static final double LARGE_THRESH = 0x1.0p+500;

        /**
         * Value used to scale down large numbers.
         */
        private static final double SCALE_DOWN = 0x1.0p-600;

        /**
         * Value used to scale up small numbers.
         */
        private static final double SCALE_UP = 0x1.0p+600;

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the square of {@code x}.
         * @param x input value
         * @return square of {@code x}
         */
        private static double square(final double x) {
            return x * x;
        }
    }

    /**
     * Euclidean norm computation algorithm that uses {@link LinearCombinations} to perform
     * an extended precision summation.
     */
    static final class ExtendedPrecisionLinearCombination implements ToDoubleFunction<double[]> {

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Modification of {@link ExtendedPrecisionLinearCombination} that uses an optimized version of the
     * linear combination computation.
     */
    static final class ExtendedPrecisionLinearCombinationMod implements ToDoubleFunction<double[]> {

        /**
         * Threshold for scaling small numbers.
         */
        private static final double SMALL_THRESH = 0x1.0p-500;

        /**
         * Threshold for scaling large numbers.
         */
        private static final double LARGE_THRESH = 0x1.0p+500;

        /**
         * Value used to scale down large numbers.
         */
        private static final double SCALE_DOWN = 0x1.0p-600;

        /**
         * Value used to scale up small numbers.
         */
        private static final double SCALE_UP = 0x1.0p+600;

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the low part of the double length number {@code (z,zz)} for the exact
         * square of {@code x} using Dekker's mult12 algorithm. The standard
         * precision product {@code x*x} must be provided. The number {@code x}
         * is split into high and low parts using Dekker's algorithm.
         *
         * <p>Warning: This method does not perform scaling in Dekker's split and large
         * finite numbers can create NaN results.
         *
         * @param x The factor.
         * @param xx Square of the factor (x * x).
         * @return the low part of the product double length number
         */
        private static double productLowUnscaled(double x, double xx) {
            // Split the numbers using Dekker's algorithm without scaling
            final double hx = DoublePrecision.highPartUnscaled(x);
            final double lx = x - hx;
            return DoublePrecision.productLow(hx, lx, hx, lx, xx);
        }
    }

    /**
     * Modification of {@link ExtendedPrecisionLinearCombination} that only uses a single pass through
     * the input array.
     */
    static final class ExtendedPrecisionLinearCombinationSinglePass implements ToDoubleFunction<double[]> {

        /**
         * Threshold for scaling small numbers.
         */
        private static final double SMALL_THRESH = 0x1.0p-500;

        /**
         * Threshold for scaling large numbers.
         */
        private static final double LARGE_THRESH = 0x1.0p+500;

        /**
         * Value used to scale down large numbers.
         */
        private static final double SCALE_DOWN = 0x1.0p-600;

        /**
         * Value used to scale up small numbers.
         */
        private static final double SCALE_UP = 0x1.0p+600;

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the low part of the double length number {@code (z,zz)} for the exact
         * square of {@code x} using Dekker's mult12 algorithm. The standard
         * precision product {@code x*x} must be provided. The number {@code x}
         * is split into high and low parts using Dekker's algorithm.
         *
         * <p>Warning: This method does not perform scaling in Dekker's split and large
         * finite numbers can create NaN results.
         *
         * @param x The factor.
         * @param xx Square of the factor (x * x).
         * @return the low part of the product double length number
         */
        private static double productLowUnscaled(double x, double xx) {
            // Split the numbers using Dekker's algorithm without scaling
            final double hx = DoublePrecision.highPartUnscaled(x);
            final double lx = x - hx;
            return DoublePrecision.productLow(hx, lx, hx, lx, xx);
        }
    }

    /**
     * Modification of {@link ExtendedPrecisionLinearCombination} that only uses a single pass through
     * the input array as well as an extended precision sqrt computation.
     */
    static final class ExtendedPrecisionLinearCombinationSqrt2 implements ToDoubleFunction<double[]> {

        /**
         * Threshold for scaling small numbers.
         */
        private static final double SMALL_THRESH = 0x1.0p-500;

        /**
         * Threshold for scaling large numbers.
         */
        private static final double LARGE_THRESH = 0x1.0p+500;

        /**
         * Value used to scale down large numbers.
         */
        private static final double SCALE_DOWN = 0x1.0p-600;

        /**
         * Value used to scale up small numbers.
         */
        private static final double SCALE_UP = 0x1.0p+600;

        /**
         * {@inheritDoc}
         */
        @Override
        public double applyAsDouble(final double[] v) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the low part of the double length number {@code (z,zz)} for the exact
         * square of {@code x} using Dekker's mult12 algorithm. The standard
         * precision product {@code x*x} must be provided. The number {@code x}
         * is split into high and low parts using Dekker's algorithm.
         *
         * <p>Warning: This method does not perform scaling in Dekker's split and large
         * finite numbers can create NaN results.
         *
         * @param x The factor.
         * @param xx Square of the factor (x * x).
         * @return the low part of the product double length number
         */
        private static double productLowUnscaled(double x, double xx) {
            // Split the numbers using Dekker's algorithm without scaling
            final double hx = DoublePrecision.highPartUnscaled(x);
            final double lx = x - hx;
            return DoublePrecision.productLow(hx, lx, hx, lx, xx);
        }

        /**
         * Compute the extended precision square root from the split number
         *  {@code x, xx}.
         * This is a modification of Dekker's sqrt2 algorithm to ignore the
         * roundoff of the square root.
         *
         * @param x the high part
         * @param xx the low part
         * @return the double
         */
        private static double sqrt2(final double x, final double xx) {
            if (x > 0) {
                double c = Math.sqrt(x);
                double u = c * c;
                //double uu = ExtendedPrecision.productLow(c, c, u);
                // Here we use the optimised version:
                double uu = productLowUnscaled(c, u);
                double cc = (x - u - uu + xx) * 0.5 / c;
                // Extended precision sqrt (y, yy)
                // y = c + cc
                // yy = c - y + cc (ignored)
                return c + cc;
            }
            return x;
        }
    }
}
