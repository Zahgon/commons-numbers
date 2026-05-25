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
package org.apache.commons.numbers.examples.jmh.arrays;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Support for creating {@link DoubleDataTransformer} implementations.
 *
 * @since 1.2
 */
final class DoubleDataTransformers {

    /**
     * No instances.
     */
    private DoubleDataTransformers() {
    }

    /**
     * Creates a factory to supply a {@link DoubleDataTransformer} based on the
     * {@code nanPolicy} and data {@code copy} policy.
     *
     * <p>The factory will supply instances that may be reused on the same thread.
     * Multi-threaded usage should create an instance per thread.
     *
     * @param nanPolicy NaN policy.
     * @param copy Set to {@code true} to use a copy of the data.
     * @return the factory
     */
    static Supplier<DoubleDataTransformer> createFactory(NaNPolicy nanPolicy, boolean copy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * A transformer that moves {@code NaN} to the upper end of the array.
     * Signed zeros are counted.
     */
    private abstract static class ReplaceSignedZerosTransformer implements DoubleDataTransformer {

        /**
         * Count of negative zeros.
         */
        protected int negativeZeroCount;

        @Override
        public void postProcess(double[] data, int[] k, int n) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void postProcess(double[] data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A transformer that moves {@code NaN} to the upper end of the array.
     * Signed zeros are counted.
     */
    private static final class SortTransformer extends ReplaceSignedZerosTransformer {

        /**
         * Set to {@code true} to include NaN in the size of the data.
         */
        private final boolean includeNaN;

        /**
         * Set to {@code true} to use a copy of the data.
         */
        private final boolean copy;

        /**
         * Size of the data.
         */
        private int size;

        /**
         * Length of data to partition.
         */
        private int len;

        /**
         * @param includeNaN Set to {@code true} to include NaN in the size of the data.
         * @param copy Set to {@code true} to use a copy of the data.
         */
        private SortTransformer(boolean includeNaN, boolean copy) {
            this.includeNaN = includeNaN;
            this.copy = copy;
        }

        @Override
        public double[] preProcess(double[] data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int length() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A transformer that errors on {@code NaN}.
     * Signed zeros are counted and restored.
     */
    private static final class NaNErrorTransformer extends ReplaceSignedZerosTransformer {

        /**
         * Set to {@code true} to use a copy of the data.
         */
        private final boolean copy;

        /**
         * Size of the data.
         */
        private int size;

        /**
         * @param copy Set to {@code true} to use a copy of the data.
         */
        private NaNErrorTransformer(boolean copy) {
            this.copy = copy;
        }

        @Override
        public double[] preProcess(double[] data) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int length() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
