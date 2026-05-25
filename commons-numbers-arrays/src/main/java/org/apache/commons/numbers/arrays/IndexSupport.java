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
package org.apache.commons.numbers.arrays;

/**
 * Support for creating {@link UpdatingInterval} implementations and validating indices.
 *
 * @since 1.2
 */
final class IndexSupport {

    /**
     * The upper threshold to use a modified insertion sort to find unique indices.
     */
    private static final int INSERTION_SORT_SIZE = 20;

    /**
     * No instances.
     */
    private IndexSupport() {
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
        for (int i = 1; i < n; i++) {
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
        for (int i = 1; i < n; i++) {
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
     * Returns an interval that covers the specified indices {@code k}.
     * The indices must be sorted.
     *
     * @param k Indices.
     * @param n Count of indices (must be strictly positive).
     * @throws IndexOutOfBoundsException if any index {@code k} is not within the
     * sub-range {@code [left, right]}
     * @return the interval
     */
    private static UpdatingInterval newUpdatingInterval(int[] k, int n) {
        return new KeyUpdatingInterval(k, n);
    }

    /**
     * Count the number of indices. Returns a negative value if the indices are sorted.
     *
     * @param keys Keys.
     * @param n Count of indices.
     * @return the count of (sorted) indices
     */
    static int countIndices(UpdatingInterval keys, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if the sub-range from fromIndex (inclusive) to toIndex (exclusive) is
     * within the bounds of range from 0 (inclusive) to length (exclusive).
     *
     * <p>This function provides the functionality of
     * {@code java.utils.Objects.checkFromToIndex} introduced in JDK 9. The <a
     * href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Objects.html#checkFromToIndex(int,int,int)">Objects</a>
     * javadoc has been reproduced for reference. The return value has been changed
     * to void.
     *
     * <p>The sub-range is defined to be out of bounds if any of the following
     * inequalities is true:
     * <ul>
     * <li>{@code fromIndex < 0}</li>
     * <li>{@code fromIndex > toIndex}</li>
     * <li>{@code toIndex > length}</li>
     * <li>{@code length < 0}, which is implied from the former inequalities</li>
     * </ul>
     *
     * @param fromIndex Lower-bound (inclusive) of the sub-range.
     * @param toIndex Upper-bound (exclusive) of the sub-range.
     * @param length Upper-bound (exclusive) of the range.
     * @throws IndexOutOfBoundsException if the sub-range is out of bounds
     */
    static void checkFromToIndex(int fromIndex, int toIndex, int length) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if the {@code index} is within the half-open interval {@code [fromIndex, toIndex)}.
     *
     * @param fromIndex Lower-bound (inclusive) of the sub-range.
     * @param toIndex Upper-bound (exclusive) of the sub-range.
     * @param k Indices.
     * @throws IndexOutOfBoundsException if any index is out of bounds
     */
    static void checkIndices(int fromIndex, int toIndex, int[] k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if the {@code index} is within the half-open interval {@code [fromIndex, toIndex)}.
     *
     * @param fromIndex Lower-bound (inclusive) of the sub-range.
     * @param toIndex Upper-bound (exclusive) of the sub-range.
     * @param index Index.
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    static void checkIndex(int fromIndex, int toIndex, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Message formatting moved to separate methods to assist inlining of the validation methods.
    /**
     * Format a message when range [from, to) is not entirely within the length.
     *
     * @param fromIndex Lower-bound (inclusive) of the sub-range.
     * @param toIndex Upper-bound (exclusive) of the sub-range.
     * @param length Upper-bound (exclusive) of the range.
     * @return the message
     */
    private static String msgRangeOutOfBounds(int fromIndex, int toIndex, int length) {
        return String.format("Range [%d, %d) out of bounds for length %d", fromIndex, toIndex, length);
    }

    /**
     * Format a message when index is not within range [from, to).
     *
     * @param fromIndex Lower-bound (inclusive) of the sub-range.
     * @param toIndex Upper-bound (exclusive) of the sub-range.
     * @param index Index.
     * @return the message
     */
    private static String msgIndexOutOfBounds(int fromIndex, int toIndex, int index) {
        return String.format("Index %d out of bounds for range [%d, %d)", index, fromIndex, toIndex);
    }
}
