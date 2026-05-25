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

/**
 * Support class for sorting arrays.
 *
 * <p>Optimal sorting networks are used for small fixed size array sorting.
 *
 * <p>Note: Requires that the floating-point data contains no NaN values; sorting
 * does not respect the order of signed zeros imposed by {@link Double#compare(double, double)}.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Sorting_network">Sorting network (Wikipedia)</a>
 * @see <a href="https://bertdobbelaere.github.io/sorting_networks.html">Sorting Networks (Bert Dobbelaere)</a>
 * @since 1.2
 */
final class Sorting {

    /**
     * The upper threshold to use a modified insertion sort to find unique indices.
     */
    private static final int UNIQUE_INSERTION_SORT = 20;

    /**
     * No instances.
     */
    private Sorting() {
    }

    /**
     * Sorts an array using an insertion sort.
     *
     * <p>This method is fast up to approximately 40 - 80 values.
     *
     * <p>The {@code internal} flag indicates that the value at {@code data[begin - 1]}
     * is sorted.
     *
     * @param data Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @param internal Internal flag.
     */
    static void sort(double[] data, int left, int right, boolean internal) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts an array using an insertion sort.
     *
     * <p>This method is fast up to approximately 40 - 80 values.
     *
     * @param data Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    static void sort(double[] data, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts an array using an insertion sort.
     *
     * <p>This method is fast up to approximately 40 - 80 values.
     *
     * @param data Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    static void sortb(double[] data, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts an array using a paired insertion sort.
     *
     * <p>Warning: It is assumed that the value at {@code data[begin - 1]} is sorted.
     *
     * @param data Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    static void sortPairedInternal1(double[] data, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts an array using a paired insertion sort.
     *
     * <p>Warning: It is assumed that the value at {@code data[begin - 1]} is sorted.
     *
     * @param data Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    static void sortPairedInternal2(double[] data, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts an array using a paired insertion sort.
     *
     * <p>Warning: It is assumed that the value at {@code data[begin - 1]} is sorted.
     *
     * @param data Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    static void sortPairedInternal3(double[] data, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts an array using a paired insertion sort.
     *
     * <p>Warning: It is assumed that the value at {@code data[begin - 1]} is sorted.
     *
     * @param data Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    static void sortPairedInternal4(double[] data, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the minimum of 3 elements in {@code a}; and the larger
     * two elements in {@code b, c}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     */
    static void min3(double[] x, int a, int b, int c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the maximum of 3 elements in {@code c}; and the smaller
     * two elements in {@code a, b}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     */
    static void max3(double[] x, int a, int b, int c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     * <pre>{@code
     * i0 != i1 != i2
     * data[i0] < data[i1] < data[i2]
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     */
    static void sort3(double[] data, int i0, int i1, int i2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array.
     *
     * <p>Note: Requires that the range contains no NaN values. It does not respect the
     * order of signed zeros.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     * <pre>{@code
     * a != b != c
     * data[a] < data[b] < data[c]
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     */
    static void sort3b(double[] data, int i0, int i1, int i2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array.
     *
     * <p>Note: Requires that the range contains no NaN values. It does not respect the
     * order of signed zeros.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     *
     * <pre>{@code
     * a != b != c
     * data[a] < data[b] < data[c]
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially
     * ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     */
    static void sort3c(double[] data, int i0, int i1, int i2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array using an insertion sort.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     * <pre>{@code
     * i0 != i1 != i2 != i3
     * data[i0] < data[i1] < data[i2] < data[i3]
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     * @param i3 Index.
     */
    static void sort4(double[] data, int i0, int i1, int i2, int i3) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     * <pre>{@code
     * i0 != i1 != i2 != i3 != i4
     * data[i0] < data[i1] < data[i2] < data[i3] < data[i4]
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     * @param i3 Index.
     * @param i4 Index.
     */
    static void sort5(double[] data, int i0, int i1, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     * <pre>{@code
     * i0 != i1 != i2 != i3 != i4
     * data[i0] < data[i1] < data[i2] < data[i3] < data[i4]
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     * @param i3 Index.
     * @param i4 Index.
     */
    static void sort5b(double[] data, int i0, int i1, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     * <pre>{@code
     * i0 != i1 != i2 != i3 != i4
     * data[i0] < data[i1] < data[i2] < data[i3] < data[i4]
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     * @param i3 Index.
     * @param i4 Index.
     */
    static void sort5c(double[] data, int i0, int i1, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the lower median of 4 elements in {@code b}; the smaller element in
     * {@code a}; and the larger two elements in {@code c, d}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     */
    static void lowerMedian4(double[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the lower median of 4 elements in {@code b}; the smaller element in
     * {@code a}; and the larger two elements in {@code c, d}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     */
    static void lowerMedian4b(double[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the lower median of 4 elements in {@code b}; the smaller element in
     * {@code a}; and the larger two elements in {@code c, d}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     */
    static void lowerMedian4c(double[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the lower median of 4 elements in {@code b}; the smaller element in
     * {@code a}; and the larger two elements in {@code c, d}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     */
    static void lowerMedian4d(double[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the lower median of 4 elements in {@code b}; the smaller element in
     * {@code a}; and the larger two elements in {@code c, d}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     */
    static void lowerMedian4e(double[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the upper median of 4 elements in {@code c}; the smaller two elements in
     * {@code a,b}; and the larger element in {@code d}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     */
    static void upperMedian4(double[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the upper median of 4 elements in {@code c}; the smaller two elements in
     * {@code a,b}; and the larger element in {@code d}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     */
    static void upperMedian4c(double[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the upper median of 4 elements in {@code c}; the smaller two elements in
     * {@code a,b}; and the larger element in {@code d}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     */
    static void upperMedian4d(double[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the median of a continuous block of 5 elements.
     * Data may be partially reordered.
     *
     * @param a Values
     * @param i1 First index.
     * @return the median index
     */
    static int median5(double[] a, int i1) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the median of 5 elements. Data may be partially reordered.
     *
     * @param a Values
     * @param i1 Index.
     * @param i2 Index.
     * @param i3 Index.
     * @param i4 Index.
     * @param i5 Index.
     * @return the median index
     */
    static int median5(double[] a, int i1, int i2, int i3, int i4, int i5) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the median of a continuous block of 5 elements.
     * Data may be partially reordered.
     *
     * @param a Values
     * @param i1 First index.
     * @return the median index
     */
    static int median5b(double[] a, int i1) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the median of a continuous block of 5 elements.
     * Data may be partially reordered.
     *
     * @param a Values
     * @param i1 First index.
     * @return the median index
     */
    static int median5c(double[] a, int i1) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Place the median of 5 elements in {@code c}; the smaller 2 elements in
     * {@code a, b}; and the larger two elements in {@code d, e}.
     *
     * @param x Values
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     * @param e Index.
     */
    static void median5d(double[] x, int a, int b, int c, int d, int e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     * <pre>{@code
     * i0 != i1 != i2 != i3 != i4 != i5 != i6
     * data[i0] < data[i1] < data[i2] < data[i3] < data[i4] < data[i5] < data[i6]
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     * @param i3 Index.
     * @param i4 Index.
     * @param i5 Index.
     * @param i6 Index.
     */
    static void sort7(double[] data, int i0, int i1, int i2, int i3, int i4, int i5, int i6) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     * <pre>{@code
     * i0 != i1 != i2 != i3 != i4 != i5 != i6 != i7
     * data[i0] < data[i1] < data[i2] < data[i3] < data[i4] < data[i5] < data[i6] < data[i7]
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     * @param i3 Index.
     * @param i4 Index.
     * @param i5 Index.
     * @param i6 Index.
     * @param i7 Index.
     */
    static void sort8(double[] data, int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the given indices in an array.
     *
     * <p>Assumes all indices are valid and distinct.
     *
     * <p>Data are arranged such that:
     * <pre>{@code
     * data[i] <= data[i + i] <= data[i + 2] ...
     * }</pre>
     *
     * <p>If indices are duplicated elements will <em>not</em> be correctly ordered.
     * However in this case data will contain the same values and may be partially ordered.
     *
     * @param data Data array.
     * @param i0 Index.
     * @param i1 Index.
     * @param i2 Index.
     * @param i3 Index.
     * @param i4 Index.
     * @param i5 Index.
     * @param i6 Index.
     * @param i7 Index.
     * @param i8 Index.
     * @param i9 Index.
     * @param i10 Index.
     */
    static void sort11(double[] data, int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sort the unique indices in-place to the start of the array. Duplicates are moved
     * to the end of the array and set to negative. For convenience the maximum
     * index is set into the final position in the array. If this is a duplicate it is
     * set to negative using the twos complement representation:
     *
     * <pre>{@code
     * int[] indices = ...
     * IndexSet sortUnique(indices);
     * int min = indices[0];
     * int max = indices[indices.length - 1]
     * if (max < 0) {
     *     max = ~max;
     * }
     * }</pre>
     *
     * <p>A small number of indices is sorted in place. A large number will use an
     * IndexSet which is returned for reuse by the caller. The threshold for this
     * switch is provided by the caller. An index set is used when
     * {@code indices.length > countThreshold} and there is more than 1 index.
     *
     * <p>This method assumes the {@code data} contains only positive integers.
     *
     * @param countThreshold Threshold to use an IndexSet.
     * @param data Indices.
     * @param n Number of indices.
     * @return the index set (or null if not used)
     */
    static IndexSet sortUnique(int countThreshold, int[] data, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sort the unique indices in-place to the start of the array. Duplicates are moved
     * to the end of the array and set to negative. For convenience the maximum
     * index is set into the final position in the array. If this is a duplicate it is
     * set to negative using the twos complement representation:
     *
     * <pre>{@code
     * int[] indices = ...
     * IndexSet sortUnique(indices);
     * int min = indices[0];
     * int max = indices[indices.length - 1]
     * if (max < 0) {
     *     max = ~max;
     * }
     * }</pre>
     *
     * <p>Uses an IndexSet which is returned to the caller. Assumes the indices
     * are non-zero in length.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the index set
     */
    private static IndexSet sortUnique(int[] data, int n) {
        final IndexSet set = IndexSet.of(data, n);
        // Iterate
        final int[] unique = { 0 };
        set.forEach(i -> data[unique[0]++] = i);
        if (unique[0] < n) {
            for (int i = unique[0]; i < n; i++) {
                data[i] = -1;
            }
            // Set the max value at the end, bit flipped
            data[n - 1] = ~data[unique[0] - 1];
        }
        return set;
    }

    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * indices is returned.
     *
     * <pre>{@code
     * int[] indices = ...
     * int n sortIndices(indices, indices.length);
     * int min = indices[0];
     * int max = indices[n - 1]
     * }</pre>
     *
     * <p>This method assumes the {@code data} contains only positive integers.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of indices
     */
    static int sortIndices(int[] data, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * indices is returned.
     *
     * <pre>{@code
     * int[] indices = ...
     * int n sortIndices(indices, indices.length);
     * int min = indices[0];
     * int max = indices[n - 1]
     * }</pre>
     *
     * <p>This method assumes the {@code data} contains only positive integers;
     * and that {@code n} is small relative to the range of indices {@code [min, max]} such
     * that storing all indices in an {@link IndexSet} is not memory efficient.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of indices
     */
    static int sortIndices2(int[] data, int n) {
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
     * Test the data is in ascending order: {@code data[i] <= data[i+1]} for all {@code i}.
     * Data is assumed to be at least length 1.
     *
     * @param data Data.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @return true if ascending
     */
    static boolean isAscending(double[] data, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // The following methods all perform the same function and are present
    // for performance testing.
    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * indices is returned.
     *
     * <p>Uses an insertion sort modified to ignore duplicates.
     *
     * <p>Warning: Requires {@code n > 0}.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of indices
     */
    static int sortIndicesInsertionSort(int[] data, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * indices is returned.
     *
     * <p>Uses a binary search to find the insert point.
     *
     * <p>Warning: Requires {@code n > 1}.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of indices
     */
    static int sortIndicesBinarySearch(int[] data, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * indices is returned.
     *
     * <p>Uses a heap sort modified to ignore duplicates.
     *
     * <p>Warning: Requires {@code n > 0}.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of indices
     */
    static int sortIndicesHeapSort(int[] data, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sift the top element down the min heap.
     *
     * <p>Note this creates the min heap in descending sequence so the
     * heap is positioned below the root.
     *
     * @param a Heap data.
     * @param offset Offset of the heap in the data.
     * @param root Root of the heap.
     * @param n Size of the heap.
     */
    private static void minHeapSiftDown(int[] a, int offset, int root, int n) {
        // For node i:
        // left child: 2i + 1
        // right child: 2i + 2
        // parent: floor((i-1) / 2)
        // Value to sift
        int p = root;
        final int v = a[offset - p];
        // Left child of root: p * 2 + 1
        int c = (p << 1) + 1;
        while (c < n) {
            // Left child value
            int cv = a[offset - c];
            // Use the right child if less
            if (c + 1 < n && cv > a[offset - c - 1]) {
                cv = a[offset - c - 1];
                c++;
            }
            // Min heap requires parent <= child
            if (v <= cv) {
                // Less than smallest child - done
                break;
            }
            // Swap and descend
            a[offset - p] = cv;
            p = c;
            c = (p << 1) + 1;
        }
        a[offset - p] = v;
    }

    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * indices is returned.
     *
     * <p>Uses a full sort and a second-pass to ignore duplicates.
     *
     * <p>Warning: Requires {@code n > 0}.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of indices
     */
    static int sortIndicesSort(int[] data, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
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
     * Sort the unique indices in-place to the start of the array. The number of
     * indices is returned.
     *
     * <p>Uses an {@link IndexSet} to ignore duplicates. The sorted array is
     * extracted from the {@link IndexSet} storage in order.
     *
     * <p>Warning: Requires {@code n > 0}.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of indices
     * @see IndexSet#toArray(int[])
     */
    static int sortIndicesIndexSet(int[] data, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * indices is returned.
     *
     * <p>Uses an {@link IndexSet} to ignore duplicates. The sorted array is
     * extracted from the {@link IndexSet} storage in order.
     *
     * <p>Warning: Requires {@code n > 0}.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of indices
     * @see IndexSet#toArray2(int[])
     */
    static int sortIndicesIndexSet2(int[] data, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * indices is returned.
     *
     * <p>Uses a {@link HashIndexSet} to ignore duplicates and then performs
     * a full sort of the unique values.
     *
     * <p>Warning: Requires {@code n > 0}.
     *
     * @param data Indices.
     * @param n Number of indices.
     * @return the number of indices
     */
    static int sortIndicesHashIndexSet(int[] data, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
