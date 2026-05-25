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
package org.apache.commons.numbers.primes;

import java.util.List;

/**
 * Methods related to prime numbers in the range of {@code int}.
 * <ul>
 * <li>primality test</li>
 * <li>prime number generation</li>
 * <li>factorization</li>
 * </ul>
 */
public final class Primes {

    /**
     * Exception message format when an argument is too small.
     */
    static final String NUMBER_TOO_SMALL = "%d is smaller than the minimum (%d)";

    /**
     * Utility class.
     */
    private Primes() {
    }

    /**
     * Primality test: tells if the argument {@code n} is a (provable) prime or not.
     * <p>
     * It uses the Miller-Rabin probabilistic test in such a way that a result is guaranteed:
     * it uses the firsts prime numbers as successive base (see Handbook of applied cryptography
     * by Menezes, table 4.1).
     *
     * @param n Number to test.
     * @return true if {@code n} is prime. All numbers {@code < 2} return false.
     */
    public static boolean isPrime(int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Return the smallest prime greater than or equal to {@code n}.
     *
     * @param n Positive number.
     * @return the smallest prime greater than or equal to {@code n}.
     * @throws IllegalArgumentException if {@code n} is negative.
     */
    public static int nextPrime(int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Prime factors decomposition.
     *
     * @param n Number to factorize: must be {@code >= 2}.
     * @return the list of prime factors of {@code n}.
     * @throws IllegalArgumentException if n {@code < 2}.
     */
    public static List<Integer> primeFactors(int n) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
