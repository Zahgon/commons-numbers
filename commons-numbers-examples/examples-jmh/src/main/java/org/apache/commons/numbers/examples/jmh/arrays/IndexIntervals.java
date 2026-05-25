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
 * Support for creating {@link SearchableInterval}, {@link SearchableInterval2} and
 * {@link UpdatingInterval} implementations.
 *
 * @since 1.2
 */
final class IndexIntervals {

    /**
     * Size to perform key analysis. This avoids key analysis for a small number of keys.
     */
    private static final int KEY_ANALYSIS_SIZE = 10;

    /**
     * The upper threshold to use a modified insertion sort to find unique indices.
     */
    private static final int INDICES_INSERTION_SORT_SIZE = 20;

    /**
     * Size to use a {@link BinarySearchKeyInterval}. Note that the
     * {@link ScanningKeyInterval} uses points within the range to fast-forward
     * scanning which improves performance significantly for a few hundred indices.
     * Performance is similar when indices are in the thousands. Binary search is
     * much faster when there are multiple thousands of indices.
     */
    private static final int BINARY_SEARCH_SIZE = 2048;

    /**
     * No instances.
     */
    private IndexIntervals() {
    }

    /**
     * Returns an interval that covers all indices ({@code [0, MAX_VALUE)}).
     *
     * <p>When used with a partition algorithm will cause a full sort
     * of the range between the bounds {@code [ka, kb]}.
     *
     * @return the interval
     */
    static SearchableInterval anyIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an interval that covers all indices ({@code [0, MAX_VALUE)}).
     *
     * <p>When used with a partition algorithm will cause a full sort
     * of the range between the bounds {@code [ka, kb]}.
     *
     * @return the interval
     */
    static SearchableInterval2 anyIndex2() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an interval that covers a single index {@code k}. The interval cannot
     * be split or the bounds updated.
     *
     * @param k Index.
     * @return the interval
     */
    static UpdatingInterval interval(int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an interval that covers all indices {@code [left, right]}.
     * This method will sort the input bound to ensure {@code left <= right}.
     *
     * <p>When used with a partition algorithm will cause a full sort
     * of the range between the bounds {@code [left, right]}.
     *
     * @param left Left bound (inclusive).
     * @param right Right bound (inclusive).
     * @return the interval
     */
    static UpdatingInterval interval(int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an interval that covers the specified indices {@code k}.
     *
     * @param k Indices.
     * @param n Count of indices (must be strictly positive).
     * @return the interval
     */
    static SearchableInterval createSearchableInterval(int[] k, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an interval that covers the specified indices {@code k}.
     *
     * @param k Indices.
     * @param n Count of indices (must be strictly positive).
     * @return the interval
     */
    static UpdatingInterval createUpdatingInterval(int[] k, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Test the data is in ascending order: {@code data[i] <= data[i+1]} for all {@code i}.
     * Data is assumed to be at least length 1.
     *
     * @param data Data.
     * @param n Length of data.
     * @return true if ascending
     */
    private static boolean isAscending(int[] data, int n) {
        for (int i = 0; ++i < n; ) {
            if (data[i] < data[i - 1]) {
                // descending
                return false;
            }
        }
        return true;
    }

    /**
     * Compress duplicates in the ascending data.
     *
     * <p>Warning: Requires {@code n > 0}.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of unique indices
     */
    private static int compressDuplicates(int[] data, int n) {
        // Compress to remove duplicates
        int last = 0;
        int top = data[0];
        for (int i = 0; ++i < n; ) {
            final int v = data[i];
            if (v == top) {
                continue;
            }
            top = v;
            data[++last] = v;
        }
        return last + 1;
    }

    /**
     * Compute {@code ceil(log2(x))}. This is valid for all strictly positive {@code x}.
     *
     * <p>Returns -1 for {@code x = 0} in place of -infinity.
     *
     * @param x Value.
     * @return {@code ceil(log2(x))}
     */
    private static int ceilLog2(int x) {
        return 32 - Integer.numberOfLeadingZeros(x - 1);
    }

    /**
     * {@link SearchableInterval} for range {@code [0, MAX_VALUE)}.
     */
    private static final class AnyIndex implements SearchableInterval, SearchableInterval2 {

        /**
         * Singleton instance.
         */
        static final AnyIndex INSTANCE = new AnyIndex();

        @Override
        public int left() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int right() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int previousIndex(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int nextIndex(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int split(int ka, int kb, int[] upper) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        // IndexInterval2
        // This is exactly the same as IndexInterval as the pointers i are the same as the keys k
        @Override
        public int start() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int end() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int index(int i) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int previous(int i, int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int next(int i, int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int split(int i1, int i2, int ka, int kb, int[] upper) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * {@link UpdatingInterval} for a single {@code index}.
     */
    static final class PointInterval implements UpdatingInterval {

        /**
         * Left/right bound of the interval.
         */
        private final int index;

        /**
         * @param k Left/right bound.
         */
        PointInterval(int k) {
            this.index = k;
        }

        @Override
        public int left() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int right() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        // Note: An UpdatingInterval is only required to update when a target index
        // is within [left, right]. This is not possible for a single point.
        @Override
        public int updateLeft(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int updateRight(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public UpdatingInterval splitLeft(int ka, int kb) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public UpdatingInterval splitRight(int ka, int kb) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * {@link UpdatingInterval} for range {@code [left, right]}.
     */
    static final class RangeInterval implements UpdatingInterval {

        /**
         * Left bound of the interval.
         */
        private int left;

        /**
         * Right bound of the interval.
         */
        private int right;

        /**
         * @param left Left bound.
         * @param right Right bound.
         */
        RangeInterval(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int left() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int right() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int updateLeft(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int updateRight(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public UpdatingInterval splitLeft(int ka, int kb) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public UpdatingInterval splitRight(int ka, int kb) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
