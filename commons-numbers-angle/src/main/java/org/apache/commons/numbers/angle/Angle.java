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
package org.apache.commons.numbers.angle;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

/**
 * Represents the <a href="https://en.wikipedia.org/wiki/Angle">angle</a> concept.
 */
public abstract class Angle implements DoubleSupplier {

    /**
     * 2&pi;.
     */
    public static final double TWO_PI = 2 * Math.PI;

    /**
     * &pi;/2.
     */
    public static final double PI_OVER_TWO = 0.5 * Math.PI;

    /**
     * Turns to degrees conversion factor.
     */
    private static final double TURN_TO_DEG = 360d;

    /**
     * Radians to degrees conversion factor.
     */
    private static final double RAD_TO_DEG = 180.0 / Math.PI;

    /**
     * Degrees to radians conversion factor.
     */
    private static final double DEG_TO_RAD = Math.PI / 180.0;

    /**
     * Value (unit depends on concrete instance).
     */
    private final double value;

    /**
     * @param value Value in turns.
     */
    Angle(final double value) {
        this.value = value;
    }

    /**
     * @return the value.
     */
    @Override
    public double getAsDouble() {
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
     * Test for equality with another object.
     * Objects are considered to be equal if their concrete types are
     * equal and their values are exactly the same (or both are {@code Double.NaN}).
     *
     * @param other Object to test for equality with this instance.
     * @return {@code true} if the objects are equal, {@code false} if
     * {@code other} is {@code null}, not of the same type as this instance,
     * or not equal to this instance.
     */
    @Override
    public boolean equals(final Object other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Convert to a {@link Turn}.
     *
     * @return the angle in <a href="https://en.wikipedia.org/wiki/Turn_%28geometry%29">turns</a>.
     */
    public abstract Turn toTurn();

    /**
     * Convert to a {@link Rad}.
     *
     * @return the angle in <a href="https://en.wikipedia.org/wiki/Radian">radians</a>.
     */
    public abstract Rad toRad();

    /**
     * Convert to a {@link Deg}.
     *
     * @return the angle in <a href="https://en.wikipedia.org/wiki/Degree_%28angle%29">degrees</a>.
     */
    public abstract Deg toDeg();

    /**
     * Unit: <a href="https://en.wikipedia.org/wiki/Turn_%28geometry%29">turns</a>.
     */
    public static final class Turn extends Angle {

        /**
         * Zero.
         */
        public static final Turn ZERO = of(0d);

        /**
         * Normalizing operator (result will be within the {@code [0, 1[} interval).
         */
        public static final DoubleUnaryOperator WITHIN_0_AND_1 = normalizer(0d);

        /**
         * Create an instance.
         *
         * @param angle (in turns).
         */
        private Turn(final double angle) {
            super(angle);
        }

        /**
         * Create an instance.
         *
         * @param angle (in turns).
         * @return a new instance.
         */
        public static Turn of(final double angle) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Turn toTurn() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Rad toRad() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Deg toDeg() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates an operator for normalizing/reducing an angle.
         * The output will be within the {@code [lo, lo + 1[} interval.
         *
         * @param lo Lower bound of the normalized interval.
         * @return the normalization operator.
         */
        public static DoubleUnaryOperator normalizer(final double lo) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Unit: <a href="https://en.wikipedia.org/wiki/Radian">radians</a>.
     */
    public static final class Rad extends Angle {

        /**
         * Zero.
         */
        public static final Rad ZERO = of(0d);

        /**
         * &pi;.
         */
        public static final Rad PI = of(Math.PI);

        /**
         * 2&pi;.
         */
        public static final Rad TWO_PI = of(Angle.TWO_PI);

        /**
         * Normalizing operator (result will be within the <code>[0, 2&pi;[</code> interval).
         */
        public static final DoubleUnaryOperator WITHIN_0_AND_2PI = normalizer(0d);

        /**
         * Normalizing operator (result will be within the <code>[-&pi;, &pi;[</code> interval).
         */
        public static final DoubleUnaryOperator WITHIN_MINUS_PI_AND_PI = normalizer(-Math.PI);

        /**
         * Create an instance.
         *
         * @param angle (in radians).
         */
        private Rad(final double angle) {
            super(angle);
        }

        /**
         * Create an instance.
         *
         * @param angle (in radians).
         * @return a new instance.
         */
        public static Rad of(final double angle) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Turn toTurn() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Rad toRad() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Deg toDeg() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates an operator for normalizing/reducing an angle.
         * The output will be within the <code> [lo, lo + 2&pi;[</code> interval.
         *
         * @param lo Lower bound of the normalized interval.
         * @return the normalization operator.
         */
        public static DoubleUnaryOperator normalizer(final double lo) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Unit: <a href="https://en.wikipedia.org/wiki/Degree_%28angle%29">degrees</a>.
     */
    public static final class Deg extends Angle {

        /**
         * Zero.
         */
        public static final Deg ZERO = of(0d);

        /**
         * Normalizing operator (result will be within the {@code [0, 360[} interval).
         */
        public static final DoubleUnaryOperator WITHIN_0_AND_360 = normalizer(0d);

        /**
         * Create an instance.
         *
         * @param angle (in degrees).
         */
        private Deg(final double angle) {
            super(angle);
        }

        /**
         * Create an instance.
         *
         * @param angle (in degrees).
         * @return a new instance.
         */
        public static Deg of(final double angle) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Turn toTurn() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Rad toRad() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Deg toDeg() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates an operator for normalizing/reducing an angle.
         * The output will be within the {@code [c, c + 360[} interval.
         *
         * @param lo Lower bound of the normalized interval.
         * @return the normalization operator.
         */
        public static DoubleUnaryOperator normalizer(final double lo) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Normalizes an angle around a center value.
     */
    private static final class Normalizer implements DoubleUnaryOperator {

        /**
         * Lower bound.
         */
        private final double lo;

        /**
         * Upper bound.
         */
        private final double hi;

        /**
         * Period.
         */
        private final double period;

        /**
         * Normalizer.
         */
        private final Reduce reduce;

        /**
         * Note: It is assumed that both arguments have the same unit.
         *
         * @param lo Lower bound of the desired interval.
         * @param period Circonference of the circle.
         */
        Normalizer(final double lo, final double period) {
            this.period = period;
            this.lo = lo;
            this.hi = lo + period;
            reduce = new Reduce(lo, period);
        }

        /**
         * @param a Angle.
         * @return {@code = a - k} where {@code k} is an integer that satisfies
         * {@code lo <= a - k < lo + period}.
         */
        @Override
        public double applyAsDouble(final double a) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
