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
 * An {@link UpdatingInterval} and {@link SplittingInterval} backed by an array of ordered keys.
 *
 * @since 1.2
 */
final class KeyUpdatingInterval implements UpdatingInterval, SplittingInterval {

    /**
     * Size to use a scan of the keys when splitting instead of binary search.
     * Note binary search has an overhead on small size due to the random left/right
     * branching per iteration. It is much faster on very large sizes.
     */
    private static final int SCAN_SIZE = 256;

    /**
     * The ordered keys.
     */
    private final int[] keys;

    /**
     * Index of the left key.
     */
    private int l;

    /**
     * Index of the right key.
     */
    private int r;

    /**
     * Create an instance with the provided {@code indices}.
     * Indices must be sorted.
     *
     * @param indices Indices.
     * @param n Number of indices.
     */
    KeyUpdatingInterval(int[] indices, int n) {
        this(indices, 0, n - 1);
    }

    /**
     * @param indices Indices.
     * @param l Index of left key.
     * @param r Index of right key.
     */
    private KeyUpdatingInterval(int[] indices, int l, int r) {
        keys = indices;
        this.l = l;
        this.r = r;
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
    static KeyUpdatingInterval of(int[] indices, int n) {
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

    /**
     * Return the current number of indices in the interval.
     * This is undefined when {@link #empty()}.
     *
     * @return the size
     */
    int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean empty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SplittingInterval split(int ka, int kb) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Invalidate the interval and mark as empty.
     */
    private void invalidate() {
        r = -1;
    }
}
