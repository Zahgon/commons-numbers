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

/**
 * A strategy to pick a pivoting index of an array for partitioning.
 *
 * <p>An ideal strategy will pick [1/2, 1/2] across a variety of data.
 *
 * @since 1.2
 */
enum PivotingStrategy {

    /**
     * Pivot around the centre of the range.
     */
    CENTRAL {

        @Override
        int pivotIndex(double[] data, int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the median of 3 values within the range: the first; the centre; and the last.
     */
    MEDIAN_OF_3 {

        @Override
        int pivotIndex(double[] data, int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the median of 9 values within the range.
     * Uses the median of 3 medians of 3. The returned value
     * is ranked 4, 5, or 6 out of the 9 values.
     * This is also known in the literature as Tukey’s "ninther" pivot.
     */
    MEDIAN_OF_9 {

        @Override
        int pivotIndex(double[] data, int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the median of 3 or 9 values within the range.
     *
     * <p>Note: Bentley & McIlroy (1993) choose a size of 40 to pivot around 9 values;
     * and a lower size of 7 to use the central; otherwise the median of 3.
     * This method does not switch to the central method for small sizes.
     */
    DYNAMIC {

        @Override
        int pivotIndex(double[] data, int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the median of 5 values within the range.
     * Requires that {@code right - left >= 4}.
     *
     * <p>Warning: This has the side effect that the 5 values are also partially sorted.
     *
     * <p>Uses the same spacing as {@link DualPivotingStrategy#SORT_5}.
     */
    MEDIAN_OF_5 {

        @Override
        int pivotIndex(double[] data, int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the median of 5 values within the range.
     * Requires that {@code right - left >= 4}.
     *
     * <p>Warning: This has the side effect that the 5 values are also partially sorted.
     *
     * <p>Uses the same spacing as {@link DualPivotingStrategy#SORT_5B}.
     */
    MEDIAN_OF_5B {

        @Override
        int pivotIndex(double[] data, int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right, int ignored) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the target index.
     */
    TARGET {

        @Override
        int pivotIndex(double[] data, int left, int right, int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right, int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ;

    /**
     * Sampled points are unchanged.
     */
    static final int UNCHANGED = 0;

    /**
     * Sampled points are partially sorted.
     */
    static final int PARTIAL_SORT = 0x1;

    /**
     * Sampled points are sorted.
     */
    static final int SORT = 0x2;

    /**
     * Size to pivot around the median of 9.
     */
    private static final int MED_9 = 40;

    /**
     * Compute the median index.
     *
     * <p>Note: This intentionally uses the median as {@code left + (right - left + 1) / 2}.
     * If the median is {@code left + (right - left) / 2} then the median is 1 position lower
     * for even length due to using an inclusive right bound. This median is not as affected
     * by median-of-3 killer sequences. For benchmarking it is useful to maintain the classic
     * median-of-3 behaviour to be able to trigger worst case performance on input
     * used in the literature.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @return the median index
     */
    private static int med(int left, int right) {
        return (left + right + 1) >>> 1;
    }

    /**
     * Find the median index of 3.
     *
     * @param data Values.
     * @param i Index.
     * @param j Index.
     * @param k Index.
     * @return the median index
     */
    private static int med3(double[] data, int i, int j, int k) {
        return med3(data[i], data[j], data[k], i, j, k);
    }

    /**
     * Find the median index of 3 values.
     *
     * @param a Value.
     * @param b Value.
     * @param c Value.
     * @param ia Index of a.
     * @param ib Index of b.
     * @param ic Index of c.
     * @return the median index
     */
    private static int med3(double a, double b, double c, int ia, int ib, int ic) {
        if (a < b) {
            if (b < c) {
                return ib;
            }
            return a < c ? ic : ia;
        }
        if (b > c) {
            return ib;
        }
        return a > c ? ic : ia;
    }

    /**
     * Find a pivot index of the array so that partitioning into 2-regions can be made.
     *
     * <pre>{@code
     * left <= p <= right
     * }</pre>
     *
     * <p>The argument {@code k} is the target index in {@code [left, right]}. Strategies
     * may use this to help select the pivot index. If not available (e.g. selecting a pivot
     * for quicksort) then choose a value in {@code [left, right]} to be safe.
     *
     * @param data Array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @param k Target index.
     * @return pivot
     */
    abstract int pivotIndex(double[] data, int left, int right, int k);

    // The following methods allow the strategy and side effects to be tested
    /**
     * Get the indices of points that will be sampled.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @param k Target index.
     * @return the indices
     */
    abstract int[] getSampledIndices(int left, int right, int k);

    /**
     * Get the effect on the sampled points.
     * <ul>
     * <li>0 - Unchanged</li>
     * <li>1 - Partially sorted</li>
     * <li>2 - Sorted</li>
     * </ul>
     *
     * @return the effect
     */
    abstract int samplingEffect();
}
