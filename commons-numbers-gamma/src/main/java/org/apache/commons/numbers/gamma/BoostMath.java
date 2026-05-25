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
//  (C) Copyright John Maddock 2006.
//  Use, modification and distribution are subject to the
//  Boost Software License, Version 1.0. (See accompanying file
//  LICENSE_1_0.txt or copy at http://www.boost.org/LICENSE_1_0.txt)
package org.apache.commons.numbers.gamma;

/**
 * Math functions used by the Boost functions.
 *
 * <p>This code has been adapted from the <a href="https://www.boost.org/">Boost</a>
 * {@code c++} implementations in {@code <boost/math/special_functions/>}.
 * All work is copyright John Maddock 2006 and subject to the Boost Software License.
 */
final class BoostMath {

    /**
     * Private constructor.
     */
    private BoostMath() {
        // intentionally empty.
    }

    /**
     * Returns {@code pow(x, y) - 1}. This function is accurate when {@code x -> 1} or {@code y} is
     * small.
     *
     * <p>Adapted from {@code boost/math/special_functions/powm1.hpp}. Explicit handling of
     * edges cases (overflow, domain error) using the policy has been removed.
     *
     * @param x the x
     * @param y the y
     * @return {@code pow(x, y) - 1}
     */
    static double powm1(double x, double y) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
