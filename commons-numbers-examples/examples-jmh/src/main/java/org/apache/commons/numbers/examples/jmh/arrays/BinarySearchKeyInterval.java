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
 * A {@link SearchableInterval} backed by an array of ordered keys. The interval is searched using
 * a binary search.
 *
 * @since 1.2
 */
final class BinarySearchKeyInterval implements SearchableInterval, SearchableInterval2 {

    /**
     * The ordered keys for descending search.
     */
    private final int[] keys;

    /**
     * The original number of keys - 1. This is more convenient to store for the use cases.
     */
    private final int nm1;

    /**
     * Create an instance with the provided keys.
     *
     * @param indices Indices.
     * @param n Number of indices.
     */
    BinarySearchKeyInterval(int[] indices, int n) {
        nm1 = n - 1;
        keys = indices;
    }

    /**
     * Initialise an instance with the {@code indices}. The indices are used in place.
     *
     * @param indices Indices.
     * @param n Number of indices.
     * @return the interval
     * @throws IllegalArgumentException if the indices are not unique and ordered;
     * or {@code n <= 0}
     */
    static BinarySearchKeyInterval of(int[] indices, int n) {
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

    // Use case for previous/next is when left/right is within
    // a partition pivot [p0, p1]. Most likely case is p0 == p1
    // and a scan is faster.
    @Override
    public int previous(int i, int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int next(int i, int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int split(int lo, int hi, int ka, int kb, int[] upper) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
