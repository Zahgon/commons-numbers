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
package org.apache.commons.numbers.examples.jmh.complex;

import org.apache.commons.numbers.complex.Complex;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.ZigguratNormalizedGaussianSampler;
import org.apache.commons.rng.simple.RandomSource;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Executes a benchmark to measure the speed of operations in the {@link Complex} class.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = { "-server", "-Xms512M", "-Xmx512M" })
public class ComplexPerformance {

    /**
     * An array of edge numbers that will produce edge case results from functions:
     * {@code +/-inf, +/-max, +/-min, +/-0, nan}.
     */
    private static final double[] EDGE_NUMBERS = { Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.MAX_VALUE, -Double.MAX_VALUE, Double.MIN_VALUE, -Double.MIN_VALUE, 0.0, -0.0, Double.NaN };

    /**
     * The range to use for uniform random numbers.
     */
    private static final double RANGE = 3.456789;

    /**
     * Contains the size of numbers.
     */
    @State(Scope.Benchmark)
    public static class ComplexNumberSize {

        /**
         * The size of the data.
         */
        @Param({ "10000" })
        private int size;

        /**
         * Gets the size.
         *
         * @return the size
         */
        public int getSize() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Contains an array of complex numbers.
     */
    @State(Scope.Benchmark)
    public static class ComplexNumbers extends ComplexNumberSize {

        /**
         * The numbers.
         */
        protected Complex[] numbers;

        /**
         * The type of the data.
         */
        @Param({ "cis", "vector", "log-uniform", "uniform", "edge" })
        private String type;

        /**
         * Gets the numbers.
         *
         * @return the numbers
         */
        public Complex[] getNumbers() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the complex numbers.
         */
        @Setup
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates the numbers.
         *
         * @param rng Random number generator.
         * @return the random complex number
         */
        Complex[] createNumbers(UniformRandomProvider rng) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Contains two arrays of complex numbers.
     */
    @State(Scope.Benchmark)
    public static class TwoComplexNumbers extends ComplexNumbers {

        /**
         * The numbers.
         */
        private Complex[] numbers2;

        /**
         * Gets the second set of numbers.
         *
         * @return the numbers
         */
        public Complex[] getNumbers2() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the complex numbers.
         */
        @Override
        @Setup
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Contains an array of complex numbers and an array of real numbers.
     */
    @State(Scope.Benchmark)
    public static class ComplexAndRealNumbers extends ComplexNumbers {

        /**
         * The numbers.
         */
        private double[] numbers2;

        /**
         * Gets the second set of numbers.
         *
         * @return the numbers
         */
        public double[] getNumbers2() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the complex numbers.
         */
        @Override
        @Setup
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Define a function between a complex and real number.
     */
    private interface ComplexRealFunction {

        /**
         * Applies this function to the given arguments.
         *
         * @param z the complex argument
         * @param x the real argument
         * @return the function result
         */
        Complex apply(Complex z, double x);
    }

    /**
     * Creates a random double number with a random sign and mantissa and a large range for
     * the exponent. The numbers will not be uniform over the range. This samples randomly
     * using the components of a double. The limiting distribution is the log-uniform distribution.
     *
     * @param rng Random number generator.
     * @return the random number
     * @see <a href="https://en.wikipedia.org/wiki/Reciprocal_distribution">Reciprocal (log-uniform) distribution</a>
     */
    private static double createLogUniformNumber(UniformRandomProvider rng) {
        // Create random doubles using random bits in the sign bit and the mantissa.
        // Then create an exponent in the range -64 to 64. Thus the sum product
        // of 4 max or min values will not over or underflow.
        final long mask = ((1L << 52) - 1) | 1L << 63;
        final long bits = rng.nextLong() & mask;
        // The exponent must be unsigned so + 1023 to the signed exponent
        final long exp = rng.nextInt(129) - 64 + 1023;
        return Double.longBitsToDouble(bits | (exp << 52));
    }

    /**
     * Creates a random double number with a random sign and uniform range.
     *
     * @param rng Random number generator.
     * @return the random number
     */
    private static double createUniformNumber(UniformRandomProvider rng) {
        // Note: [0, 1) - 1 is [-1, 0).
        // Since the 1 is a 50/50 sample the result is the interval [-1, 1)
        // using the 2^54 dyadic rationals in the interval.
        // The range is not critical. The numbers will have approximately 50%
        // with the same exponent, max, matching that of RANGE and the rest smaller
        // exponents down to (max - 53) since the uniform deviate is limited to 2^-53.
        return (rng.nextDouble() - rng.nextInt(1)) * RANGE;
    }

    /**
     * Creates a random double number that will be an edge case:
     * {@code +/-inf, +/-max, +/-min, +/-0, nan}.
     *
     * @param rng Random number generator.
     * @return the random number
     */
    private static double createEdgeNumber(UniformRandomProvider rng) {
        return EDGE_NUMBERS[rng.nextInt(EDGE_NUMBERS.length)];
    }

    /**
     * Apply the function to all the numbers.
     *
     * @param numbers Numbers.
     * @param fun Function.
     * @param bh Data sink.
     */
    private static void apply(Complex[] numbers, Predicate<Complex> fun, Blackhole bh) {
        for (int i = 0; i < numbers.length; i++) {
            bh.consume(fun.test(numbers[i]));
        }
    }

    /**
     * Apply the function to all the numbers.
     *
     * @param numbers Numbers.
     * @param fun Function.
     * @param bh Data sink.
     */
    private static void apply(Complex[] numbers, ToDoubleFunction<Complex> fun, Blackhole bh) {
        for (int i = 0; i < numbers.length; i++) {
            bh.consume(fun.applyAsDouble(numbers[i]));
        }
    }

    /**
     * Apply the function to all the numbers.
     *
     * @param numbers Numbers.
     * @param fun Function.
     * @param bh Data sink.
     */
    private static void apply(Complex[] numbers, UnaryOperator<Complex> fun, Blackhole bh) {
        for (int i = 0; i < numbers.length; i++) {
            bh.consume(fun.apply(numbers[i]));
        }
    }

    /**
     * Apply the function to the paired numbers.
     *
     * @param numbers First numbers of the pairs.
     * @param numbers2 Second numbers of the pairs.
     * @param fun Function.
     * @param bh Data sink.
     */
    private static void apply(Complex[] numbers, Complex[] numbers2, BiFunction<Complex, Complex, Complex> fun, Blackhole bh) {
        for (int i = 0; i < numbers.length; i++) {
            bh.consume(fun.apply(numbers[i], numbers2[i]));
        }
    }

    /**
     * Apply the function to the paired numbers.
     *
     * @param numbers First numbers of the pairs.
     * @param numbers2 Second numbers of the pairs.
     * @param fun Function.
     * @param bh Data sink.
     */
    private static void apply(Complex[] numbers, double[] numbers2, ComplexRealFunction fun, Blackhole bh) {
        for (int i = 0; i < numbers.length; i++) {
            bh.consume(fun.apply(numbers[i], numbers2[i]));
        }
    }

    /**
     * Identity function. This can be used to measure overhead of object array creation.
     *
     * @param z Complex number.
     * @return the complex number
     */
    private static Complex identity(Complex z) {
        return z;
    }

    /**
     * Copy function. This can be used to measure overhead of object array creation plus
     * new Complex creation.
     *
     * @param z Complex number.
     * @return a copy of the complex number
     */
    private static Complex copy(Complex z) {
        return Complex.ofCartesian(z.real(), z.imag());
    }

    // Benchmark methods.
    //
    // The methods are partially documented as the names are self-documenting.
    // CHECKSTYLE: stop JavadocMethod
    // CHECKSTYLE: stop DesignForExtension
    //
    // Benchmarks use function references to perform different operations on the complex numbers.
    // Tests show that explicit programming of the same benchmarks run in the same time.
    // For reference examples are provided for the fastest operations: real() and conj().
    /**
     * Explicit benchmark without using a method reference.
     * This should run in the same time as {@link #real(ComplexNumbers, Blackhole)}.
     * This is commented out as it exists for reference purposes.
     *
     * @param numbers Numbers.
     * @param bh Data sink.
     */
    //@Benchmark
    public void real2(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Explicit benchmark without using a method reference.
     * This should run in the same time as {@link #conj(ComplexNumbers, Blackhole)}.
     * This is commented out as it exists for reference purposes.
     *
     * @param numbers Numbers.
     * @param bh Data sink.
     */
    //@Benchmark
    public void conj2(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Baseline the JMH overhead for the loop execute to consume Complex objects.
     *
     * @param numbers Numbers.
     * @param bh Data sink.
     */
    @Benchmark
    public void baselineIdentity(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Baseline the creation of a copy complex number. This
     * measures the overhead of creation of new complex numbers including field access
     * to the real and imaginary parts.
     *
     * @param numbers Numbers.
     * @param bh Data sink.
     */
    @Benchmark
    public void baselineCopy(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Unary operations that a boolean
    @Benchmark
    public void isNaN(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void isInfinite(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void isFinite(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Unary operations that a double
    @Benchmark
    public void real(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void imag(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void abs(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void arg(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void norm(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * This test demonstrates that the method used in abs() is not as fast as using square
     * root of the norm. The C99 standard for the abs() function requires over/underflow
     * protection in the intermediate computation and infinity edge case handling. This
     * has a performance overhead.
     *
     * @param numbers Numbers.
     * @param bh Data sink.
     */
    @Benchmark
    public void sqrtNorm(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * This test demonstrates that the {@link Math#hypot(double, double)} method
     * is not as fast as the custom implementation in abs().
     *
     * @param numbers Numbers.
     * @param bh Data sink.
     */
    @Benchmark
    public void absMathHypot(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Unary operations that a complex number
    @Benchmark
    public void conj(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void negate(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void proj(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void cos(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void cosh(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void exp(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void log(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void log10(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void sin(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void sinh(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void sqrt(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void tan(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void tanh(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void acos(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void acosh(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void asin(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void asinh(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void atan(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void atanh(ComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Binary operations on two complex numbers.
    @Benchmark
    public void pow(TwoComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void multiply(TwoComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void divide(TwoComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void add(TwoComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void subtract(TwoComplexNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Binary operations on a complex and a real number.
    // These only benchmark methods on the real component as the
    // following are expected to be the same speed as the real-only operations
    // given the equivalent primitive operations:
    // - multiplyImaginary
    // - divideImaginary
    // - addImaginary
    // - subtractImaginary
    // - subtractFrom
    // - subtractFromImaginary
    @Benchmark
    public void powReal(ComplexAndRealNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void multiplyReal(ComplexAndRealNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void divideReal(ComplexAndRealNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void addReal(ComplexAndRealNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Benchmark
    public void subtractReal(ComplexAndRealNumbers numbers, Blackhole bh) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
