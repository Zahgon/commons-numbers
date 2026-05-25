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
 *
 * @since 1.2
 */
final class Sorting {

    /**
     * No instances.
     */
    private Sorting() {
    }

    /**
     * Sorts an array using an insertion sort.
     *
     * @param x Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    static void sort(double[] x, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the elements at the given distinct indices in an array.
     *
     * @param x Data array.
     * @param a Index.
     * @param b Index.
     * @param c Index.
     */
    static void sort3(double[] x, int a, int b, int c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the elements at the given distinct indices in an array.
     *
     * @param x Data array.
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     * @param e Index.
     */
    static void sort5(double[] x, int a, int b, int c, int d, int e) {
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
     * Sorts an array using an insertion sort.
     *
     * @param x Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    static void sort(int[] x, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the elements at the given distinct indices in an array.
     *
     * @param x Data array.
     * @param a Index.
     * @param b Index.
     * @param c Index.
     */
    static void sort3(int[] x, int a, int b, int c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the elements at the given distinct indices in an array.
     *
     * @param x Data array.
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     * @param e Index.
     */
    static void sort5(int[] x, int a, int b, int c, int d, int e) {
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
    static void lowerMedian4(int[] x, int a, int b, int c, int d) {
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
    static void upperMedian4(int[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts an array using an insertion sort.
     *
     * @param x Data array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     */
    static void sort(long[] x, int left, int right) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the elements at the given distinct indices in an array.
     *
     * @param x Data array.
     * @param a Index.
     * @param b Index.
     * @param c Index.
     */
    static void sort3(long[] x, int a, int b, int c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sorts the elements at the given distinct indices in an array.
     *
     * @param x Data array.
     * @param a Index.
     * @param b Index.
     * @param c Index.
     * @param d Index.
     * @param e Index.
     */
    static void sort5(long[] x, int a, int b, int c, int d, int e) {
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
    static void lowerMedian4(long[] x, int a, int b, int c, int d) {
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
    static void upperMedian4(long[] x, int a, int b, int c, int d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * unique indices is returned.
     *
     * <p>Uses an insertion sort modified to ignore duplicates. Use on small {@code n}.
     *
     * <p>Warning: Requires {@code n > 0}. The array contents after the count of unique
     * indices {@code c} are unchanged (i.e. {@code [c, n)}. This may change the count of
     * each unique index in the entire array.
     *
     * @param x Indices.
     * @param n Number of indices.
     * @return the number of unique indices
     */
    static int insertionSortIndices(int[] x, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sort the unique indices in-place to the start of the array. The number of
     * unique indices is returned.
     *
     * <p>Uses an Order(1) data structure to ignore duplicates.
     *
     * <p>Warning: Requires {@code n > 0}. The array contents after the count of unique
     * indices {@code c} are unchanged (i.e. {@code [c, n)}. This may change the count of
     * each unique index in the entire array.
     *
     * @param x Indices.
     * @param n Number of indices.
     * @return the number of unique indices
     */
    static int sortIndices(int[] x, int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
