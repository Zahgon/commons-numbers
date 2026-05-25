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
package org.apache.commons.numbers.examples.jmh.core;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.rng.UniformRandomProvider;

/**
 * Utility class to create data for linear combinations.
 */
final class LinearCombinationUtils {

    /**
     * ln(2).
     */
    private static final double LN_2 = Math.log(2);

    /**
     * No construction.
     */
    private LinearCombinationUtils() {
    }

    /**
     * Generates ill conditioned dot products.
     * See {@link #genDot(double, UniformRandomProvider, double[], double[], double[], MathContext)}.
     *
     * <p>The exact dot product is computed using {@link MathContext#UNLIMITED}. This
     * will not scale to large lengths.
     *
     * @param c anticipated condition number of x’*y
     * @param rng source of randomness
     * @param x output array vector (length at least 6)
     * @param y output array vector (length at least 6)
     * @param computeC if not null compute the condition number and place at index 0
     * @return the exact dot product
     * @throws IllegalArgumentException If the vector length is below 6
     */
    static double genDot(double c, UniformRandomProvider rng, double[] x, double[] y, double[] computeC) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Generates ill conditioned dot products. The length of the vectors should be
     * {@code n>=6}.
     *
     * <p>The condition number is defined as the inverse of the cosine of the angle between
     * the two vectors:
     *
     * <pre>
     * C = 1 / cos angle(x, y)
     *   = ||x'|| ||y|| / |x'*y|
     * </pre>
     * <p>where |x'*y| is the absolute of the dot product and ||.|| is the 2-norm (i.e. the
     * Euclidean length of each vector).
     *
     * <p>A high condition number means that small perturbations in the values result in
     * a large change in the final result. This occurs when the cosine of the angle approaches
     * 0 and the vectors are close to orthogonal.
     *
     * <p>Computation of the actual dot product requires an exact method to avoid cancellation
     * floating-point errors. BigDecimal is used to compute the exact result to the accuracy
     * defined by the {@link MathContext}. The dot product result is created in the range [-1, 1].
     * The actual condition number can be obtained by passing an array {@code computeC}. The
     * routine has been tested with anticipated condition numbers up to 1e300 to generate data
     * where the standard precision dot product will not overflow.
     *
     * <p>Uses the GenDot algorithm 6.1 from <a
     * href="https://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita, Siegfried M. Rump, and
     * Shin'ichi Oishi published in <em>SIAM J. Sci. Comput</em>.
     *
     * <p>Note: Ogita et al state "in general the actual condition number is a
     * little larger than the anticipated one with quite some variation". This
     * implementation is computing a condition number approximately equal to the
     * requested for the parameters that have been tested.
     *
     * @param c anticipated condition number of x’*y
     * @param rng source of randomness
     * @param x output array vector (length at least 6)
     * @param y output array vector (length at least 6)
     * @param computeC if not null compute the condition number and place at index 0
     * @param context the context to use for rounding the exact sum
     * @return the exact dot product
     * @throws IllegalArgumentException If the vector length is below 6
     * @see <a href="https://en.wikipedia.org/wiki/Condition_number">Condition number</a>
     * @see <a href="https://en.wikipedia.org/wiki/Dot_product">Dot product</a>
     */
    static double genDot(double c, UniformRandomProvider rng, double[] x, double[] y, double[] computeC, MathContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create a double in the range [-1, 1).
     *
     * @param rng source of randomness
     * @return the double
     */
    private static double m1p1(UniformRandomProvider rng) {
        // Create in the range [0, 1) then randomly subtract 1.
        // This samples the 2^54 dyadic rationals in the range.
        return rng.nextDouble() - rng.nextInt(1);
    }

    /**
     * Swaps the two specified elements in the array.
     *
     * @param array Array.
     * @param i First index.
     * @param j Second index.
     */
    private static void swap(double[] array, int i, int j) {
        final double tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
