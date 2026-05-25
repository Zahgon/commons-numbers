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

import org.apache.commons.numbers.gamma.LogBeta;

/**
 * Natural logarithm of the <a href="https://mathworld.wolfram.com/BinomialCoefficient.html">
 * binomial coefficient</a>.
 * It is "{@code n choose k}", the number of {@code k}-element subsets that
 * can be selected from an {@code n}-element set.
 */
public final class LogBinomialCoefficient {

    /**
     * The maximum n that can be computed without overflow of a long for any m.
     * {@code C(66, 33) < 2^63}.
     */
    private static final int LIMIT_N_LONG = 66;

    /**
     * The maximum n that can be computed without overflow of a double for an m.
     * C(1029, 514) ~ 1.43e308.
     */
    private static final int LIMIT_N_DOUBLE = 1029;

    /**
     * The maximum m that can be computed without overflow of a double for any n.
     * C(2147483647, 37) ~ 1.39e302.
     */
    private static final int LIMIT_M_DOUBLE = 37;

    /**
     * Private constructor.
     */
    private LogBinomialCoefficient() {
        // intentionally empty.
    }

    /**
     * Computes the logarithm of the binomial coefficient.
     *
     * <p>This returns a finite result for any valid {@code n choose k}.
     *
     * @param n Size of the set.
     * @param k Size of the subsets to be counted.
     * @return {@code log(n choose k)}.
     * @throws IllegalArgumentException if {@code n < 0}, {@code k < 0} or {@code k > n}.
     */
    public static double value(int n, int k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
