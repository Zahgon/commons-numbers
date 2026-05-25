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

/**
 * Representation of the <a href="https://mathworld.wolfram.com/BinomialCoefficient.html">
 * binomial coefficient</a>, as a {@code double}.
 * It is "{@code n choose k}", the number of {@code k}-element subsets that
 * can be selected from an {@code n}-element set.
 */
public final class BinomialCoefficientDouble {

    /**
     * The maximum factorial that can be represented as a double.
     */
    private static final int MAX_FACTORIAL = 170;

    /**
     * The maximum n that can be computed without overflow of a long for any m.
     * {@code C(66, 33) < 2^63}.
     */
    private static final int LIMIT_N_LONG = 66;

    /**
     * The maximum m that can be computed without overflow of a double.
     * C(1030, 515) ~ 2.85e308.
     */
    private static final int MAX_M = 514;

    /**
     * The maximum n that can be computed without intermediate overflow for any m.
     * C(1020, 510) * 510 ~ 1.43e308.
     */
    private static final int SMALL_N = 1020;

    /**
     * The maximum m that can be computed without intermediate overflow for any n.
     * C(2147483647, 37) * 37 ~ 5.13e303.
     */
    private static final int SMALL_M = 37;

    /**
     * Private constructor.
     */
    private BinomialCoefficientDouble() {
        // intentionally empty.
    }

    /**
     * Computes the binomial coefficient.
     *
     * <p>The largest value of {@code n} for which <em>all</em> coefficients can
     * fit into a {@code double} is 1029. Larger {@code n} may result in
     * infinity depending on the value of {@code k}.
     *
     * <p>Any {@code min(k, n - k) >= 515} cannot fit into a {@code double}
     * and will result in infinity.
     *
     * @param n Size of the set.
     * @param k Size of the subsets to be counted.
     * @return {@code n choose k}.
     * @throws IllegalArgumentException if {@code n < 0}, {@code k < 0} or {@code k > n}.
     */
    public static double value(int n, int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
