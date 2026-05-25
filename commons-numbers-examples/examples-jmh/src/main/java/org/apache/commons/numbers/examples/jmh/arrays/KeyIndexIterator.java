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
 * An {@link IndexIterator} backed by an array of ordered keys.
 *
 * @since 1.2
 */
final class KeyIndexIterator implements IndexIterator {

    /**
     * The ordered keys.
     */
    private final int[] keys;

    /**
     * The original number of keys minus 1.
     */
    private final int nm1;

    /**
     * Iterator left.
     */
    private int left;

    /**
     * Iterator right position. Never advanced beyond {@code n - 1}.
     */
    private int hi = -1;

    /**
     * Create an instance with the provided keys.
     *
     * @param indices Indices.
     * @param n Number of indices.
     */
    KeyIndexIterator(int[] indices, int n) {
        keys = indices;
        this.nm1 = n - 1;
        next();
    }

    /**
     * Initialise an instance with {@code n} initial {@code indices}. The indices are used in place.
     *
     * @param indices Indices.
     * @param n Number of indices.
     * @return the iterator
     * @throws IllegalArgumentException if the indices are not unique and ordered; or not
     * in the range {@code [0, 2^31-1)}; or {@code n <= 0}
     */
    static KeyIndexIterator of(int[] indices, int n) {
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
    public int end() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean positionAfter(int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean nextAfter(int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
