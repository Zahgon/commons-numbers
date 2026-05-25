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

import java.math.MathContext;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.IntFunction;
import org.apache.commons.numbers.core.Sum;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.FourD;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.ND;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.ThreeD;
import org.apache.commons.numbers.examples.jmh.core.LinearCombination.TwoD;
import org.apache.commons.rng.UniformRandomProvider;
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

/**
 * Executes a benchmark to measure the speed of operations in the {@link LinearCombination} class.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = { "-server", "-Xms512M", "-Xmx512M" })
public class LinearCombinationPerformance {

    /**
     * The seed to use to create the factors.
     * Using a fixed seed ensures the same factors are created for the variable
     * length arrays as for the small fixed size arrays.
     */
    private static final long SEED = System.currentTimeMillis();

    /**
     * The factors to multiply.
     */
    @State(Scope.Benchmark)
    public static class Factors {

        /**
         * The condition number of the generated data.
         */
        @Param({ "1e20" })
        private double c;

        /**
         * The number of factors.
         */
        @Param({ "1000" })
        private int size;

        /**
         * Factors a.
         */
        private double[][] a;

        /**
         * Factors b.
         */
        private double[][] b;

        /**
         * Gets the length of the array of factors.
         * This exists to be overridden by factors of a specific length.
         * The default is to create factors of length 4 for use in the inlined
         * scalar product methods.
         *
         * @return the length
         */
        public int getLength() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Gets the number of scalar products to compute.
         *
         * @return the size
         */
        public int getSize() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Gets the a factors.
         *
         * @param index the index
         * @return Factors b.
         */
        public double[] getA(int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Gets the b factors.
         *
         * @param index the index
         * @return Factors b.
         */
        public double[] getB(int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the factors.
         */
        @Setup
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The factors to multiply of a specific length.
     */
    @State(Scope.Benchmark)
    public static class LengthFactors extends Factors {

        /**
         * The length of each factors array.
         */
        @Param({ "2", "3", "4", "8", "16", "32", "64" })
        private int length;

        /**
         * {@inheritDoc}
         */
        @Override
        public int getLength() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The {@link LinearCombination} implementation.
     */
    @State(Scope.Benchmark)
    public static class Calculator {

        /**
         * The implementation name.
         */
        @Param({ "standard", "current", "dekker", "dot2s", "dot2", "dot3", "dot4", "dot5", "dot6", "dot7", "exact", "extended", "extended2", "extended_exact", "extended_exact2", // Cached working double[] array.
        // Only faster when 'length' is >16. Below this the array
        // is small enough to be allocated locally
        // (Search for Thread Local Allocation Buffer (TLAB))
        "dot3c", "extendedc" })
        private String name;

        /**
         * The 2D implementation.
         */
        private TwoD twod;

        /**
         * The 3D implementation.
         */
        private ThreeD threed;

        /**
         * The 4D implementation.
         */
        private FourD fourd;

        /**
         * The ND implementation.
         */
        private ND nd;

        /**
         * @return the 2D implementation
         */
        public TwoD getTwoD() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the 3D implementation
         */
        public ThreeD getThreeD() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the 4D implementation
         */
        public FourD getFourD() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * @return the ND implementation
         */
        public ND getND() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Setup the implementation.
         */
        @Setup
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Create or return a cached array.
     */
    static final class CachedArrayFactory implements IntFunction<double[]> {

        /**
         * An empty double array.
         */
        private static final double[] EMPTY = new double[0];

        /**
         * The cached array.
         */
        private double[] array = EMPTY;

        @Override
        public double[] apply(int value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Compute the 2D scalar product for all the factors.
     *
     * @param factors Factors.
     * @param bh Data sink.
     * @param calc Scalar product calculator.
     */
    @Benchmark
    public void twoD(Factors factors, Blackhole bh, Calculator calc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the 3D scalar product for all the factors.
     *
     * @param factors Factors.
     * @param bh Data sink.
     * @param calc Scalar product calculator.
     */
    @Benchmark
    public void threeD(Factors factors, Blackhole bh, Calculator calc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the 4D scalar product for all the factors.
     *
     * @param factors Factors.
     * @param bh Data sink.
     * @param calc Scalar product calculator.
     */
    @Benchmark
    public void fourD(Factors factors, Blackhole bh, Calculator calc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Compute the ND scalar product for all the factors.
     *
     * @param factors Factors.
     * @param bh Data sink.
     * @param calc Scalar product calculator.
     */
    @Benchmark
    public void nD(LengthFactors factors, Blackhole bh, Calculator calc) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
