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
package org.apache.commons.numbers.combinatorics;

import org.apache.commons.numbers.core.ArithmeticUtils;

/**
 * Representation of the <a href="https://mathworld.wolfram.com/BinomialCoefficient.html">
 * binomial coefficient</a>.
 * It is "{@code n choose k}", the number of {@code k}-element subsets that
 * can be selected from an {@code n}-element set.
 */
public final class BinomialCoefficient {

    /**
     * The maximum m that can be computed without overflow of a long.
     * {@code C(68, 34) > 2^63}.
     */
    private static final int MAX_M = 33;

    /**
     * The maximum n that can be computed without intermediate overflow for any m.
     * {@code C(61, 30) * 30 < 2^63}.
     */
    private static final int SMALL_N = 61;

    /**
     * The maximum n that can be computed without overflow of a long for any m.
     * {@code C(66, 33) < 2^63}.
     */
    private static final int LIMIT_N = 66;

    /**
     * Private constructor.
     */
    private BinomialCoefficient() {
        // intentionally empty.
    }

    /**
     * Computes the binomial coefficient.
     *
     * <p>The largest value of {@code n} for which <em>all</em> coefficients can
     * fit into a {@code long} is 66. Larger {@code n} may result in an
     * {@link ArithmeticException} depending on the value of {@code k}.
     *
     * <p>Any {@code min(k, n - k) >= 34} cannot fit into a {@code long}
     * and will result in an {@link ArithmeticException}.
     *
     * @param n Size of the set.
     * @param k Size of the subsets to be counted.
     * @return {@code n choose k}.
     * @throws IllegalArgumentException if {@code n < 0}, {@code k < 0} or {@code k > n}.
     * @throws ArithmeticException if the result is too large to be
     * represented by a {@code long}.
     */
    public static long value(int n, int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check binomial preconditions.
     *
     * <p>For convenience in implementations this returns the smaller of
     * {@code k} or {@code n - k} allowing symmetry to be exploited in
     * computing the binomial coefficient.
     *
     * @param n Size of the set.
     * @param k Size of the subsets to be counted.
     * @return min(k, n - k)
     * @throws IllegalArgumentException if {@code n < 0}.
     * @throws IllegalArgumentException if {@code k > n} or {@code k < 0}.
     */
    static int checkBinomial(int n, int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
