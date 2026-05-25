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
import java.util.function.IntConsumer;

/**
 * A fixed size set of indices within an inclusive range {@code [left, right]}.
 *
 * <p>This is a specialised class to implement a reduced API similar to a
 * {@link java.util.BitSet}. It uses no bounds range checks and supports only a
 * fixed size. It contains the methods required to store and look-up intervals of indices.
 *
 * <p>An offset is supported to allow the fixed size to cover a range of indices starting
 * above 0 with the most efficient usage of storage.
 *
 * <p>The class has methods to directly set and get bits in the range.
 * Implementations of the {@link PivotCache} interface use range checks and maintain
 * floating pivots flanking the range to allow bracketing any index within the range.
 *
 * <p>Stores all pivots between the support {@code [left, right]}. Uses two
 * floating pivots which are the closest known pivots surrounding this range.
 *
 * <p>See the BloomFilter code in Commons Collections for use of long[] data to store
 * bits.
 *
 * @since 1.2
 */
final class IndexSet implements PivotCache, SearchableInterval, SearchableInterval2 {

    /**
     * All 64-bits bits set.
     */
    private static final long LONG_MASK = -1L;

    /**
     * A bit shift to apply to an integer to divided by 64 (2^6).
     */
    private static final int DIVIDE_BY_64 = 6;

    /**
     * Default value for an unset upper floating pivot.
     * Set as a value higher than any valid array index.
     */
    private static final int UPPER_DEFAULT = Integer.MAX_VALUE;

    /**
     * Bit indexes.
     */
    private final long[] data;

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
     * Create an instance to store indices within the range {@code [left, right]}.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    private IndexSet(int left, int right) {
        this.left = left;
        this.right = right;
        // Allocate storage to store index==right
        // Note: This may allow directly writing to index > right if there
        // is extra capacity. Ranges checks to prevent this are provided by
        // the PivotCache.add(int) method rather than using set(int).
        data = new long[getLongIndex(right - left) + 1];
    }

    /**
     * Create an instance to store indices within the range {@code [left, right]}.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @return the index set
     * @throws IllegalArgumentException if {@code right < left}
     */
    static IndexSet ofRange(int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Initialise an instance with the {@code indices}. The capacity is defined by the
     * range required to store the minimum and maximum index.
     *
     * @param indices Indices.
     * @return the index set
     * @throws IllegalArgumentException if {@code indices.length == 0}
     */
    static IndexSet of(int[] indices) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Initialise an instance with the {@code indices}. The capacity is defined by the
     * range required to store the minimum and maximum index.
     *
     * @param indices Indices.
     * @param n Number of indices.
     * @return the index set
     * @throws IllegalArgumentException if {@code n == 0}
     */
    static IndexSet of(int[] indices, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the memory footprint in bytes. This is always a multiple of 64.
     *
     * <p>The result is {@code 8 * ceil((right - left + 1) / 64)}.
     *
     * <p>This method is intended to provide information to choose if the data structure
     * is memory efficient.
     *
     * <p>Warning: It is assumed {@code 0 <= left <= right}. Use with the min/max index
     * that is to be stored.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @return the memory footprint
     */
    static long memoryFootprint(int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the filter index for the specified bit index assuming the filter is using
     * 64-bit longs to store bits starting at index 0.
     *
     * <p>The index is assumed to be positive. For a positive index the result will match
     * {@code bitIndex / 64}.</p>
     *
     * <p><em>The divide is performed using bit shifts. If the input is negative the
     * behavior is not defined.</em></p>
     *
     * @param bitIndex the bit index (assumed to be positive)
     * @return the index of the bit map in an array of bit maps.
     */
    private static int getLongIndex(final int bitIndex) {
        // An integer divide by 64 is equivalent to a shift of 6 bits if the integer is
        // positive.
        // We do not explicitly check for a negative here. Instead we use a
        // signed shift. Any negative index will produce a negative value
        // by sign-extension and if used as an index into an array it will throw an
        // exception.
        return bitIndex >> DIVIDE_BY_64;
    }

    /**
     * Gets the filter bit mask for the specified bit index assuming the filter is using
     * 64-bit longs to store bits starting at index 0. The returned value is a
     * {@code long} with only 1 bit set.
     *
     * <p>The index is assumed to be positive. For a positive index the result will match
     * {@code 1L << (bitIndex % 64)}.</p>
     *
     * <p><em>If the input is negative the behavior is not defined.</em></p>
     *
     * @param bitIndex the bit index (assumed to be positive)
     * @return the filter bit
     */
    private static long getLongBit(final int bitIndex) {
        // Bit shifts only use the first 6 bits. Thus it is not necessary to mask this
        // using 0x3f (63) or compute bitIndex % 64.
        // Note: If the index is negative the shift will be (64 - (bitIndex & 0x3f)) and
        // this will identify an incorrect bit.
        return 1L << bitIndex;
    }

    // Compressed cardinality methods
    /**
     * Returns the number of bits set to {@code true} in this {@code IndexSet} using a
     * compression of 2 to 1. This counts as enabled <em>all</em> bits of each consecutive
     * 2 bits if <em>any</em> of the consecutive 2 bits are set to {@code true}.
     * <pre>
     * 0010100011000101000100
     * 0 2 2 0 2 0 2 2 0 2 0
     * </pre>
     * <p>This method can be used to assess the saturation of the indices in the range.
     *
     * @return the number of bits set to {@code true} in this {@code IndexSet} using a
     * compression of 2 to 1
     */
    public int cardinality2() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of bits set to {@code true} in this {@code IndexSet} using a
     * compression of 4 to 1. This counts as enabled <em>all</em> bits of each consecutive
     * 4 bits if <em>any</em> of the consecutive 4 bits are set to {@code true}.
     * <pre>
     * 0010000011000101000100
     * 4   0   4   4   4   0
     * </pre>
     * <p>This method can be used to assess the saturation of the indices in the range.
     *
     * @return the number of bits set to {@code true} in this {@code IndexSet} using a compression
     * of 8 to 1
     */
    public int cardinality4() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of bits set to {@code true} in this {@code IndexSet} using a
     * compression of 8 to 1. This counts as enabled <em>all</em> bits of each consecutive
     * 8 bits if <em>any</em> of the consecutive 8 bits are set to {@code true}.
     * <pre>
     * 0010000011000101000000
     * 8       8       0
     * </pre>
     * <p>This method can be used to assess the saturation of the indices in the range.
     *
     * @return the number of bits set to {@code true} in this {@code IndexSet} using a compression
     * of 8 to 1
     */
    public int cardinality8() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of bits set to {@code true} in this {@code IndexSet} using a
     * compression of 16 to 1. This counts as enabled <em>all</em> bits of each consecutive
     * 16 bits if <em>any</em> of the consecutive 16 bits are set to {@code true}.
     * <pre>
     * 0010000011000101000000
     * 16              0
     * </pre>
     * <p>This method can be used to assess the saturation of the indices in the range.
     *
     * @return the number of bits set to {@code true} in this {@code IndexSet} using a compression
     * of 16 to 1
     */
    public int cardinality16() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of bits set to {@code true} in this {@code IndexSet} using a
     * compression of 32 to 1. This counts as enabled <em>all</em> bits of each consecutive
     * 32 bits if <em>any</em> of the consecutive 32 bits are set to {@code true}.
     * <pre>
     * 0010000011000101000000
     * 32
     * </pre>
     * <p>This method can be used to assess the saturation of the indices in the range.
     *
     * @return the number of bits set to {@code true} in this {@code IndexSet} using a compression
     * of 32 to 1
     */
    public int cardinality32() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of bits set to {@code true} in this {@code IndexSet} using a
     * compression of 64 to 1. This counts as enabled <em>all</em> bits of each consecutive
     * 64 bits if <em>any</em> of the consecutive 64 bits are set to {@code true}.
     * <pre>
     * 0010000011000101000000
     * 64
     * </pre>
     * <p>This method can be used to assess the saturation of the indices in the range.
     *
     * @return the number of bits set to {@code true} in this {@code IndexSet} using a compression
     * of 64 to 1
     */
    public int cardinality64() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Adapt method API from BitSet
    /**
     * Returns the number of bits set to {@code true} in this {@code IndexSet}.
     *
     * @return the number of bits set to {@code true} in this {@code IndexSet}
     */
    public int cardinality() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the value of the bit with the specified index.
     *
     * @param bitIndex the bit index (assumed to be positive)
     * @return the value of the bit with the specified index
     */
    public boolean get(int bitIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the bit at the specified index to {@code true}.
     *
     * <p>Warning: This has no range checks. Use {@link #add(int)} to add an index that
     * may be outside the support.
     *
     * @param bitIndex the bit index (assumed to be positive)
     */
    public void set(int bitIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the bits from the specified {@code leftIndex} (inclusive) to the specified
     * {@code rightIndex} (inclusive) to {@code true}.
     *
     * <p><em>If {@code rightIndex - leftIndex < 0} the behavior is not defined.</em></p>
     *
     * <p>Note: In contrast to the BitSet API, this uses an <em>inclusive</em> end as this
     * is the main use case for the class.
     *
     * <p>Warning: This has no range checks. Use {@link #add(int, int)} to range that
     * may be outside the support.
     *
     * @param leftIndex the left index
     * @param rightIndex the right index
     */
    public void set(int leftIndex, int rightIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the index of the nearest bit that is set to {@code true} that occurs on or
     * before the specified starting index. If no such bit exists, then {@code -1} is returned.
     *
     * @param fromIndex Index to start checking from (inclusive).
     * @return the index of the previous set bit, or {@code -1} if there is no such bit
     */
    public int previousSetBit(int fromIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the index of the nearest bit that is set to {@code true} that occurs on or
     * before the specified starting index. If no such bit exists, then
     * {@code defaultValue} is returned.
     *
     * @param fromIndex Index to start checking from (inclusive).
     * @param defaultValue Default value.
     * @return the index of the previous set bit, or {@code defaultValue} if there is no such bit
     */
    int previousSetBitOrElse(int fromIndex, int defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the index of the first bit that is set to {@code true} that occurs on or
     * after the specified starting index. If no such bit exists then {@code -1} is
     * returned.
     *
     * @param fromIndex Index to start checking from (inclusive).
     * @return the index of the next set bit, or {@code -1} if there is no such bit
     */
    public int nextSetBit(int fromIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the index of the first bit that is set to {@code true} that occurs on or
     * after the specified starting index. If no such bit exists then {@code defaultValue} is
     * returned.
     *
     * @param fromIndex Index to start checking from (inclusive).
     * @param defaultValue Default value.
     * @return the index of the next set bit, or {@code defaultValue} if there is no such bit
     */
    int nextSetBitOrElse(int fromIndex, int defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the index of the first bit that is set to {@code false} that occurs on or
     * after the specified starting index <em>within the supported range</em>. If no such
     * bit exists then the {@code capacity} is returned where {@code capacity = index + 1}
     * with {@code index} the largest index that can be added to the set without an error.
     *
     * <p>If the starting index is less than the supported range the result is {@code fromIndex}.
     *
     * @param fromIndex Index to start checking from (inclusive).
     * @return the index of the next unset bit, or the {@code capacity} if there is no such bit
     */
    public int nextClearBit(int fromIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the index of the first bit that is set to {@code false} that occurs on or
     * before the specified starting index <em>within the supported range</em>. If no such
     * bit exists then {@code -1} is returned.
     *
     * <p>If the starting index is less than the supported range the result is {@code fromIndex}.
     * This can return {@code -1} only if the support begins at {@code index == 0}.
     *
     * @param fromIndex Index to start checking from (inclusive).
     * @return the index of the previous unset bit, or {@code -1} if there is no such bit
     */
    public int previousClearBit(int fromIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Perform the {@code action} for each index in the set.
     *
     * @param action Action.
     */
    public void forEach(IntConsumer action) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Write each index in the set into the provided array.
     * Returns the number of indices.
     *
     * <p>The caller must ensure the output array has sufficient capacity.
     * For example the array used to construct the IndexSet.
     *
     * @param a Output array.
     * @return count of indices
     * @see #of(int[])
     * @see #of(int[], int)
     */
    public int toArray(int[] a) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Write each index in the set into the provided array.
     * Returns the number of indices.
     *
     * <p>The caller must ensure the output array has sufficient capacity.
     * For example the array used to construct the IndexSet.
     *
     * @param a Output array.
     * @return count of indices
     * @see #of(int[])
     * @see #of(int[], int)
     */
    public int toArray2(int[] a) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // PivotCache interface
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
    public boolean contains(int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
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
    public int previousPivot(int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int nextPivotOrElse(int k, int other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // IndexInterval
    @Override
    public int previousIndex(int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int nextIndex(int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // No override for split.
    // This requires searching for previousIndex(k - 1) and nextIndex(k + 1).
    // The only shared code is getLongIndex(x - left). Since argument indices are 2 apart
    // these will map to a different long with a probability of 1/32.
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

    /**
     * Return a {@link ScanningPivotCache} implementation re-using the same internal storage.
     *
     * <p>Note that the range for the {@link ScanningPivotCache} must fit inside the current
     * supported range of indices.
     *
     * <p>Warning: This operation clears all set bits within the range.
     *
     * <p><strong>Support</strong>
     *
     * <p>The returned {@link ScanningPivotCache} is suitable for storing all pivot points between
     * {@code [left, right]} and the closest bounding pivots outside that range. It can be
     * used for bracketing partition keys processed in a random order by storing pivots
     * found during each successive partition search.
     *
     * <p>The returned {@link ScanningPivotCache} is suitable for use when iterating over
     * partition keys in ascending order.
     *
     * <p>The cache allows incrementing the {@code left} support using
     * {@link ScanningPivotCache#moveLeft(int)}. Any calls to decrement the {@code left} support
     * at any time will result in an {@link UnsupportedOperationException}; this prevents reseting
     * to within the original support used to create the cache. If the {@code left} is
     * moved beyond the {@code right} then the move is rejected.
     *
     * @param lower Lower bound (inclusive).
     * @param upper Upper bound (inclusive).
     * @return the pivot cache
     * @throws IllegalArgumentException if {@code right < left} or the range cannot be
     * supported.
     */
    ScanningPivotCache asScanningPivotCache(int lower, int upper) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return a {@link ScanningPivotCache} implementation to support the range
     * {@code [left, right]}.
     *
     * <p>See {@link #asScanningPivotCache(int, int)} for the details of the cache implementation.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @return the pivot cache
     * @throws IllegalArgumentException if {@code right < left}
     */
    static ScanningPivotCache createScanningPivotCache(int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return a {@link ScanningPivotCache} implementation to support the range
     * {@code [left, right]} re-using the same internal storage.
     *
     * @param lower Lower bound (inclusive).
     * @param upper Upper bound (inclusive).
     * @param initialize Perform validation checks and initialize the storage.
     * @return the pivot cache
     * @throws IllegalArgumentException if {@code right < left} or the range cannot be
     * supported.
     */
    private ScanningPivotCache asScanningPivotCache(int lower, int upper, boolean initialize) {
        if (initialize) {
            checkRange(lower, upper);
            final int capacity = data.length * Long.SIZE + lower;
            if (lower < left || upper >= capacity) {
                throw new IllegalArgumentException(String.format("Unsupported range: [%d, %d] is not within [%d, %d]", lower, upper, left, capacity - 1));
            }
            // Clear existing data
            Arrays.fill(data, 0);
        }
        return new IndexPivotCache(lower, upper);
    }

    /**
     * Return an {@link UpdatingInterval} implementation to support the range
     * {@code [left, right]} re-using the same internal storage.
     *
     * @return the interval
     */
    UpdatingInterval interval() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check the range is valid.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @throws IllegalArgumentException if {@code right < left}
     */
    private static void checkRange(int left, int right) {
        if (right < left) {
            throw new IllegalArgumentException(String.format("Invalid range: [%d, %d]", left, right));
        }
    }

    /**
     * Implementation of the {@link ScanningPivotCache} using the {@link IndexSet}.
     *
     * <p>Stores all pivots between the support {@code [left, right]}. Uses two
     * floating pivots which are the closest known pivots surrounding this range.
     *
     * <p>This class is bound to the enclosing {@link IndexSet} instance to provide
     * the functionality to read, write and search indexes.
     *
     * <p>Note: This duplicates functionality of the parent IndexSet. Differences
     * are that it uses a movable left bound and implements the scanning functionality
     * of the {@link ScanningPivotCache} interface. It can also be created for
     * a smaller {@code [left, right]} range than the enclosing class.
     *
     * <p>Creation of this class typically invalidates the use of the outer class.
     * Creation will zero the underlying storage and the range may be different.
     */
    private class IndexPivotCache implements ScanningPivotCache {

        /**
         * Left bound of the support.
         */
        private int left;

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
        IndexPivotCache(int left, int right) {
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

        @Override
        public void add(int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void add(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Implementation of the {@link UpdatingInterval} using the {@link IndexSet}.
     *
     * <p>This class is bound to the enclosing {@link IndexSet} instance to provide
     * the functionality to search indexes.
     */
    private class IndexSetUpdatingInterval implements UpdatingInterval {

        /**
         * Left bound of the interval.
         */
        private int left;

        /**
         * Right bound of the interval.
         */
        private int right;

        /**
         * @param left Lower bound (inclusive).
         * @param right Upper bound (inclusive).
         */
        IndexSetUpdatingInterval(int left, int right) {
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
