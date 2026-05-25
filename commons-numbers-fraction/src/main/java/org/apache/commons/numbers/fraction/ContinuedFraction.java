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
package org.apache.commons.numbers.fraction;

import java.util.function.Supplier;
import org.apache.commons.numbers.fraction.GeneralizedContinuedFraction.Coefficient;

/**
 * Provides a generic means to evaluate
 * <a href="https://mathworld.wolfram.com/ContinuedFraction.html">continued fractions</a>.
 *
 * <p>The continued fraction uses the following form for the numerator ({@code a}) and
 * denominator ({@code b}) coefficients:
 * <pre>
 *              a1
 * b0 + ------------------
 *      b1 +      a2
 *           -------------
 *           b2 +    a3
 *                --------
 *                b3 + ...
 * </pre>
 *
 * <p>Subclasses must provide the {@link #getA(int,double) a} and {@link #getB(int,double) b}
 * coefficients to evaluate the continued fraction.
 *
 * <p>This class allows evaluation of the fraction for a specified evaluation point {@code x};
 * the point can be used to express the values of the coefficients.
 * Evaluation of a continued fraction from a generator of the coefficients can be performed using
 * {@link GeneralizedContinuedFraction}. This may be preferred if the coefficients can be computed
 * with updates to the previous coefficients.
 */
public abstract class ContinuedFraction {

    /**
     * Create an instance.
     */
    public ContinuedFraction() {
    }

    /**
     * Defines the <a href="https://mathworld.wolfram.com/ContinuedFraction.html">
     * {@code n}-th "a" coefficient</a> of the continued fraction.
     *
     * @param n Index of the coefficient to retrieve.
     * @param x Evaluation point.
     * @return the coefficient <code>a<sub>n</sub></code>.
     */
    protected abstract double getA(int n, double x);

    /**
     * Defines the <a href="https://mathworld.wolfram.com/ContinuedFraction.html">
     * {@code n}-th "b" coefficient</a> of the continued fraction.
     *
     * @param n Index of the coefficient to retrieve.
     * @param x Evaluation point.
     * @return the coefficient <code>b<sub>n</sub></code>.
     */
    protected abstract double getB(int n, double x);

    /**
     * Evaluates the continued fraction.
     *
     * @param x the evaluation point.
     * @param epsilon Maximum relative error allowed.
     * @return the value of the continued fraction evaluated at {@code x}.
     * @throws ArithmeticException if the algorithm fails to converge.
     * @throws ArithmeticException if the maximal number of iterations is reached
     * before the expected convergence is achieved.
     *
     * @see #evaluate(double,double,int)
     */
    public double evaluate(double x, double epsilon) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Evaluates the continued fraction.
     * <p>
     * The implementation of this method is based on the modified Lentz algorithm as described
     * on page 508 in:
     * </p>
     *
     * <ul>
     *   <li>
     *   I. J. Thompson,  A. R. Barnett (1986).
     *   "Coulomb and Bessel Functions of Complex Arguments and Order."
     *   Journal of Computational Physics 64, 490-509.
     *   <a target="_blank" href="https://www.fresco.org.uk/papers/Thompson-JCP64p490.pdf">
     *   https://www.fresco.org.uk/papers/Thompson-JCP64p490.pdf</a>
     *   </li>
     * </ul>
     *
     * @param x Point at which to evaluate the continued fraction.
     * @param epsilon Maximum relative error allowed.
     * @param maxIterations Maximum number of iterations.
     * @return the value of the continued fraction evaluated at {@code x}.
     * @throws ArithmeticException if the algorithm fails to converge.
     * @throws ArithmeticException if the maximal number of iterations is reached
     * before the expected convergence is achieved.
     */
    public double evaluate(double x, double epsilon, int maxIterations) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
