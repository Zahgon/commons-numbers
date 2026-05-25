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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.ToDoubleFunction;

/**
 * Class used to evaluate the accuracy of different norm computation
 * methods.
 */
public class EuclideanNormEvaluator {

    /**
     * Map of names to norm computation methods.
     */
    private final Map<String, ToDoubleFunction<double[]>> methods = new LinkedHashMap<>();

    /**
     * Add a computation method to be evaluated.
     * @param name method name
     * @param method computation method
     * @return this instance
     */
    public EuclideanNormEvaluator addMethod(final String name, final ToDoubleFunction<double[]> method) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Evaluate the configured computation methods against the given array of input vectors.
     * @param inputs array of input vectors
     * @return map of evaluation results keyed by method name
     */
    public Map<String, Stats> evaluate(final double[][] inputs) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the exact double value of the vector norm using BigDecimals
     * with a math context of {@link MathContext#DECIMAL128}.
     * @param vec input vector
     * @return euclidean norm
     */
    private static double computeExact(final double[] vec) {
        final MathContext ctx = MathContext.DECIMAL128;
        BigDecimal sum = BigDecimal.ZERO;
        for (final double v : vec) {
            sum = sum.add(new BigDecimal(v).pow(2), ctx);
        }
        return sum.sqrt(ctx).doubleValue();
    }

    /**
     * Compute the ulp difference between two values of the same sign.
     * @param a first input
     * @param b second input
     * @return ulp difference between the arguments
     */
    private static int computeUlpDifference(final double a, final double b) {
        return (int) (Double.doubleToLongBits(a) - Double.doubleToLongBits(b));
    }

    /**
     * Class containing evaluation statistics for a single computation method.
     */
    public static final class Stats {

        /**
         * Mean ulp error.
         */
        private final double ulpErrorMean;

        /**
         * Ulp error standard deviation.
         */
        private final double ulpErrorStdDev;

        /**
         * Ulp error minimum value.
         */
        private final double ulpErrorMin;

        /**
         * Ulp error maximum value.
         */
        private final double ulpErrorMax;

        /**
         * Number of failed computations.
         */
        private final int failCount;

        /**
         * Construct a new instance.
         * @param ulpErrorMean ulp error mean
         * @param ulpErrorStdDev ulp error standard deviation
         * @param ulpErrorMin ulp error minimum value
         * @param ulpErrorMax ulp error maximum value
         * @param failCount number of failed computations
         */
        Stats(final double ulpErrorMean, final double ulpErrorStdDev, final double ulpErrorMin, final double ulpErrorMax, final int failCount) {
            this.ulpErrorMean = ulpErrorMean;
            this.ulpErrorStdDev = ulpErrorStdDev;
            this.ulpErrorMin = ulpErrorMin;
            this.ulpErrorMax = ulpErrorMax;
            this.failCount = failCount;
        }

        /**
         * Get the ulp error mean.
         * @return ulp error mean
         */
        public double getUlpErrorMean() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get the ulp error standard deviation.
         * @return ulp error standard deviation
         */
        public double getUlpErrorStdDev() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get the ulp error minimum value.
         * @return ulp error minimum value
         */
        public double getUlpErrorMin() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get the ulp error maximum value.
         * @return ulp error maximum value
         */
        public double getUlpErrorMax() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Get the number of failed computations, meaning the number of
         * computations that overflowed or underflowed.
         * @return number of failed computations
         */
        public int getFailCount() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Class used to accumulate statistics during a norm evaluation run.
     */
    private static final class StatsAccumulator {

        /**
         * Sample index.
         */
        private int sampleIdx;

        /**
         * Array of ulp errors for each sample.
         */
        private final double[] ulpErrors;

        /**
         * Construct a new instance.
         * @param count number of samples to be accumulated
         */
        StatsAccumulator(final int count) {
            ulpErrors = new double[count];
        }

        /**
         * Report a computation result.
         * @param expected expected result
         * @param actual actual result
         */
        public void report(final double expected, final double actual) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Compute the final statistics for the run.
         * @return statistics object
         */
        public Stats computeStats() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
