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
 * Support for creating {@link PivotCache} implementations.
 *
 * @since 1.2
 */
final class PivotCaches {

    /**
     * Default value for an unset upper floating pivot.
     * Set as a value higher than any valid array index.
     */
    private static final int UPPER_DEFAULT = Integer.MAX_VALUE;

    /**
     * No instances.
     */
    private PivotCaches() {
    }

    /**
     * Return a {@link PivotCache} for a single {@code k}.
     *
     * @param k Index.
     * @return the pivot cache
     */
    static PivotCache ofIndex(int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return a {@link PivotCache} for a single {@code k},
     * or a pair of indices {@code (k, k+1)}. A pair is
     * signalled using the sign bit.
     *
     * @param k Paired index.
     * @return the pivot cache
     */
    static PivotCache ofPairedIndex(int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return a {@link PivotCache} for the range {@code [left, right]}.
     *
     * <p>If the range contains internal indices, the {@link PivotCache} will not
     * store them and will be {@link PivotCache#sparse() sparse}.
     *
     * <p>The range returned instance may implement {@link ScanningPivotCache}.
     * It should only be cast to a {@link ScanningPivotCache} and used for scanning
     * if it reports itself as non-{@link PivotCache#sparse() sparse}.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @return the pivot cache
     * @see #ofFullRange(int, int)
     */
    static PivotCache ofRange(int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return a {@link PivotCache} for the full-range {@code [left, right]}.
     * The returned implementation will be non-{@link PivotCache#sparse() sparse}.
     *
     * <p>The range returned instance may implement {@link ScanningPivotCache}.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @return the pivot cache
     */
    static PivotCache ofFullRange(int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Validate the range {@code left <= right}.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    private static void validateRange(int left, int right) {
        if (right < left) {
            throw new IllegalArgumentException("Invalid range");
        }
    }

    /**
     * PivotCache for range {@code [left, right]} consisting of a single point.
     */
    private static class PointPivotCache implements ScanningPivotCache {

        /**
         * The target point.
         */
        private final int target;

        /**
         * The upstream pivot closest to the left bound of the support.
         * Provides a lower search bound for the range [left, right].
         */
        private int lowerPivot = -1;

        /**
         * The downstream pivot closest to the right bound of the support.
         * Provides an upper search bound for the range [left, right].
         */
        private int upperPivot = UPPER_DEFAULT;

        /**
         * @param index Index defining {@code [left, right]}.
         */
        PointPivotCache(int index) {
            this.target = index;
        }

        @Override
        public void add(int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void add(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException("STUB: not implemented");
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
        public boolean sparse() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean moveLeft(int newLeft) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int previousPivot(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        // Do not override: int nextPivot(int k)
        @Override
        public int nextPivotOrElse(int k, int other) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int nextNonPivot(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int previousNonPivot(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * PivotCache for range {@code [left, right]} consisting of a bracketing range
     * {@code lower <= left < right <= upper}.
     *
     * <p>Behaviour is undefined if {@code left == right}. This cache is intended to
     * bracket a range [left, right] that can be entirely sorted, e.g. if the separation
     * between left and right is small.
     */
    private static class RangePivotCache implements ScanningPivotCache {

        /**
         * Left bound of the support.
         */
        private final int left;

        /**
         * Right bound of the support.
         */
        private final int right;

        /**
         * The upstream pivot closest to the left bound of the support.
         * Provides a lower search bound for the range [left, right].
         */
        private int lowerPivot = -1;

        /**
         * The downstream pivot closest to the right bound of the support.
         * Provides an upper search bound for the range [left, right].
         */
        private int upperPivot = UPPER_DEFAULT;

        /**
         * @param left Lower bound (inclusive).
         * @param right Upper bound (inclusive).
         */
        RangePivotCache(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void add(int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void add(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException("STUB: not implemented");
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
        public boolean sparse() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean moveLeft(int newLeft) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int previousPivot(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        // Do not override: int nextPivot(int k)
        @Override
        public int nextPivotOrElse(int k, int other) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int nextNonPivot(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int previousNonPivot(int k) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
