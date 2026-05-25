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
package org.apache.commons.numbers.quaternion;

import java.util.Arrays;
import java.util.function.ToDoubleFunction;
import java.util.function.BiPredicate;
import java.io.Serializable;
import org.apache.commons.numbers.core.Precision;

/**
 * This class implements <a href="https://mathworld.wolfram.com/Quaternion.html">
 * quaternions</a> (Hamilton's hypercomplex numbers).
 *
 * <p>Wherever quaternion components are listed in sequence, this class follows the
 * convention of placing the scalar ({@code w}) component first, e.g. [{@code w, x, y, z}].
 * Other libraries and textbooks may place the {@code w} component last.</p>
 *
 * <p>Instances of this class are guaranteed to be immutable.</p>
 */
public final class Quaternion implements Serializable {

    /**
     * Zero quaternion.
     */
    public static final Quaternion ZERO = of(0, 0, 0, 0);

    /**
     * Identity quaternion.
     */
    public static final Quaternion ONE = new Quaternion(Type.POSITIVE_POLAR_FORM, 1, 0, 0, 0);

    /**
     * i.
     */
    public static final Quaternion I = new Quaternion(Type.POSITIVE_POLAR_FORM, 0, 1, 0, 0);

    /**
     * j.
     */
    public static final Quaternion J = new Quaternion(Type.POSITIVE_POLAR_FORM, 0, 0, 1, 0);

    /**
     * k.
     */
    public static final Quaternion K = new Quaternion(Type.POSITIVE_POLAR_FORM, 0, 0, 0, 1);

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20170118L;

    /**
     * Error message.
     */
    private static final String ILLEGAL_NORM_MSG = "Illegal norm: ";

    /**
     * {@link #toString() String representation}.
     */
    private static final String FORMAT_START = "[";

    /**
     * {@link #toString() String representation}.
     */
    private static final String FORMAT_END = "]";

    /**
     * {@link #toString() String representation}.
     */
    private static final String FORMAT_SEP = " ";

    /**
     * The number of dimensions for the vector part of the quaternion.
     */
    private static final int VECTOR_DIMENSIONS = 3;

    /**
     * The number of parts when parsing a text representation of the quaternion.
     */
    private static final int NUMBER_OF_PARTS = 4;

    /**
     * For enabling specialized method implementations.
     */
    private final Type type;

    /**
     * First component (scalar part).
     */
    private final double w;

    /**
     * Second component (first vector part).
     */
    private final double x;

    /**
     * Third component (second vector part).
     */
    private final double y;

    /**
     * Fourth component (third vector part).
     */
    private final double z;

    /**
     * For enabling optimized implementations.
     */
    private enum Type {

        /**
         * Default implementation.
         */
        DEFAULT(Default.NORMSQ, Default.NORM, Default.IS_UNIT),
        /**
         * Quaternion has unit norm.
         */
        NORMALIZED(Normalized.NORM, Normalized.NORM, Normalized.IS_UNIT),
        /**
         * Quaternion has positive scalar part.
         */
        POSITIVE_POLAR_FORM(Normalized.NORM, Normalized.NORM, Normalized.IS_UNIT);

        /**
         * {@link Quaternion#normSq()}.
         */
        private final ToDoubleFunction<Quaternion> normSq;

        /**
         * {@link Quaternion#norm()}.
         */
        private final ToDoubleFunction<Quaternion> norm;

        /**
         * {@link Quaternion#isUnit(double)}.
         */
        private final BiPredicate<Quaternion, Double> testIsUnit;

        /**
         * Default implementations.
         */
        private static final class Default {

            /**
             * {@link Quaternion#normSq()}.
             */
            static final ToDoubleFunction<Quaternion> NORMSQ = q -> q.w * q.w + q.x * q.x + q.y * q.y + q.z * q.z;

            /**
             * {@link Quaternion#norm()}.
             */
            private static final ToDoubleFunction<Quaternion> NORM = q -> Math.sqrt(NORMSQ.applyAsDouble(q));

            /**
             * {@link Quaternion#isUnit(double)}.
             */
            private static final BiPredicate<Quaternion, Double> IS_UNIT = (q, eps) -> Precision.equals(NORM.applyAsDouble(q), 1d, eps);
        }

        /**
         * Implementations for normalized quaternions.
         */
        private static final class Normalized {

            /**
             * {@link Quaternion#norm()} returns 1.
             */
            static final ToDoubleFunction<Quaternion> NORM = q -> 1;

            /**
             * {@link Quaternion#isUnit(double)} returns 1.
             */
            static final BiPredicate<Quaternion, Double> IS_UNIT = (q, eps) -> true;
        }

        /**
         * @param normSq {@code normSq} method.
         * @param norm {@code norm} method.
         * @param isUnit {@code isUnit} method.
         */
        Type(ToDoubleFunction<Quaternion> normSq, ToDoubleFunction<Quaternion> norm, BiPredicate<Quaternion, Double> isUnit) {
            this.normSq = normSq;
            this.norm = norm;
            this.testIsUnit = isUnit;
        }

        /**
         * @param q Quaternion.
         * @return the norm squared.
         */
        double normSq(Quaternion q) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @param q Quaternion.
         * @return the norm.
         */
        double norm(Quaternion q) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @param q Quaternion.
         * @param eps Tolerance.
         * @return whether {@code q} has unit norm within the allowed tolerance.
         */
        boolean isUnit(Quaternion q, double eps) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Builds a quaternion from its components.
     *
     * @param type Quaternion type.
     * @param w Scalar component.
     * @param x First vector component.
     * @param y Second vector component.
     * @param z Third vector component.
     */
    private Quaternion(Type type, final double w, final double x, final double y, final double z) {
        this.type = type;
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Copies the given quaternion, but change its {@link Type}.
     *
     * @param type Quaternion type.
     * @param q Quaternion whose components will be copied.
     */
    private Quaternion(Type type, Quaternion q) {
        this.type = type;
        w = q.w;
        x = q.x;
        y = q.y;
        z = q.z;
    }

    /**
     * Builds a quaternion from its components.
     *
     * @param w Scalar component.
     * @param x First vector component.
     * @param y Second vector component.
     * @param z Third vector component.
     * @return a quaternion instance.
     */
    public static Quaternion of(final double w, final double x, final double y, final double z) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Builds a quaternion from scalar and vector parts.
     *
     * @param scalar Scalar part of the quaternion.
     * @param v Components of the vector part of the quaternion.
     * @return a quaternion instance.
     *
     * @throws IllegalArgumentException if the array length is not 3.
     */
    public static Quaternion of(final double scalar, final double[] v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Builds a pure quaternion from a vector (assuming that the scalar
     * part is zero).
     *
     * @param v Components of the vector part of the pure quaternion.
     * @return a quaternion instance.
     */
    public static Quaternion of(final double[] v) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the conjugate of this quaternion number.
     * The conjugate of {@code a + bi + cj + dk} is {@code a - bi -cj -dk}.
     *
     * @return the conjugate of this quaternion object.
     */
    public Quaternion conjugate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the Hamilton product of two quaternions.
     *
     * @param q1 First quaternion.
     * @param q2 Second quaternion.
     * @return the product {@code q1} and {@code q2}, in that order.
     */
    public static Quaternion multiply(final Quaternion q1, final Quaternion q2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the Hamilton product of the instance by a quaternion.
     *
     * @param q Quaternion.
     * @return the product of this instance with {@code q}, in that order.
     */
    public Quaternion multiply(final Quaternion q) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Computes the sum of two quaternions.
     *
     * @param q1 Quaternion.
     * @param q2 Quaternion.
     * @return the sum of {@code q1} and {@code q2}.
     */
    public static Quaternion add(final Quaternion q1, final Quaternion q2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Computes the sum of the instance and another quaternion.
     *
     * @param q Quaternion.
     * @return the sum of this instance and {@code q}.
     */
    public Quaternion add(final Quaternion q) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Subtracts two quaternions.
     *
     * @param q1 First Quaternion.
     * @param q2 Second quaternion.
     * @return the difference between {@code q1} and {@code q2}.
     */
    public static Quaternion subtract(final Quaternion q1, final Quaternion q2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Subtracts a quaternion from the instance.
     *
     * @param q Quaternion.
     * @return the difference between this instance and {@code q}.
     */
    public Quaternion subtract(final Quaternion q) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Computes the dot-product of two quaternions.
     *
     * @param q1 Quaternion.
     * @param q2 Quaternion.
     * @return the dot product of {@code q1} and {@code q2}.
     */
    public static double dot(final Quaternion q1, final Quaternion q2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Computes the dot-product of the instance by a quaternion.
     *
     * @param q Quaternion.
     * @return the dot product of this instance and {@code q}.
     */
    public double dot(final Quaternion q) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Computes the norm of the quaternion.
     *
     * @return the norm.
     */
    public double norm() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Computes the square of the norm of the quaternion.
     *
     * @return the square of the norm.
     */
    public double normSq() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Computes the normalized quaternion (the versor of the instance).
     * The norm of the quaternion must not be near zero.
     *
     * @return a normalized quaternion.
     * @throws IllegalStateException if the norm of the quaternion is NaN, infinite,
     *      or near zero.
     */
    public Quaternion normalize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks whether this instance is equal to another quaternion
     * within a given tolerance.
     *
     * @param q Quaternion with which to compare the current quaternion.
     * @param eps Tolerance.
     * @return {@code true} if the each of the components are equal
     * within the allowed absolute error.
     */
    public boolean equals(final Quaternion q, final double eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks whether the instance is a unit quaternion within a given
     * tolerance.
     *
     * @param eps Tolerance (absolute error).
     * @return {@code true} if the norm is 1 within the given tolerance,
     * {@code false} otherwise
     */
    public boolean isUnit(double eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks whether the instance is a pure quaternion within a given
     * tolerance.
     *
     * @param eps Tolerance (absolute error).
     * @return {@code true} if the scalar part of the quaternion is zero.
     */
    public boolean isPure(double eps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the polar form of the quaternion.
     *
     * @return the unit quaternion with positive scalar part.
     */
    public Quaternion positivePolarForm() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the opposite of this instance.
     *
     * @return the quaternion for which all components have an opposite
     * sign to this one.
     */
    public Quaternion negate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the inverse of this instance.
     * The norm of the quaternion must not be zero.
     *
     * @return the inverse.
     * @throws IllegalStateException if the norm (squared) of the quaternion is NaN,
     *      infinite, or near zero.
     */
    public Quaternion inverse() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the first component of the quaternion (scalar part).
     *
     * @return the scalar part.
     */
    public double getW() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the second component of the quaternion (first component
     * of the vector part).
     *
     * @return the first component of the vector part.
     */
    public double getX() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the third component of the quaternion (second component
     * of the vector part).
     *
     * @return the second component of the vector part.
     */
    public double getY() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the fourth component of the quaternion (third component
     * of the vector part).
     *
     * @return the third component of the vector part.
     */
    public double getZ() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the scalar part of the quaternion.
     *
     * @return the scalar part.
     * @see #getW()
     */
    public double getScalarPart() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the three components of the vector part of the quaternion.
     *
     * @return the vector part.
     * @see #getX()
     * @see #getY()
     * @see #getZ()
     */
    public double[] getVectorPart() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Multiplies the instance by a scalar.
     *
     * @param alpha Scalar factor.
     * @return a scaled quaternion.
     */
    public Quaternion multiply(final double alpha) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Divides the instance by a scalar.
     *
     * @param alpha Scalar factor.
     * @return a scaled quaternion.
     */
    public Quaternion divide(final double alpha) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Parses a string that would be produced by {@link #toString()}
     * and instantiates the corresponding object.
     *
     * @param s String representation.
     * @return an instance.
     * @throws NumberFormatException if the string does not conform
     * to the specification.
     */
    public static Quaternion parse(String s) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * See {@link #parse(String)}.
     */
    private static final class QuaternionParsingException extends NumberFormatException {

        /**
         * Serializable version identifier.
         */
        private static final long serialVersionUID = 20181128L;

        /**
         * @param msg Error message.
         */
        QuaternionParsingException(String msg) {
            super(msg);
        }

        /**
         * @param msg Error message.
         * @param cause Cause of the exception.
         */
        QuaternionParsingException(String msg, Throwable cause) {
            super(msg);
            initCause(cause);
        }
    }
}
