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

import java.util.concurrent.TimeUnit;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;
import org.apache.commons.numbers.core.ArithmeticUtils;
import org.apache.commons.rng.simple.RandomSource;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Executes a benchmark to measure the speed of arithmetic operations.
 *
 * <p>Contains copy implementations of {@code o.a.c.numbers.core.ArithmeticUtils}.
 * Do not call {@code ArithmeticUtils} directly to ensure we are benchmarking the
 * original method and not an updated version that may for example delegate to JDK functions.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = { "-server", "-Xms512M", "-Xmx512M" })
public class ArithmeticPerformance {

    /**
     * Method to compute the divide using unsigned arithmetic.
     */
    private static final String DIVIDE_UNSIGNED_1_0 = "divideUnsigned_1.0";

    /**
     * Method to compute the remainder using unsigned arithmetic.
     */
    private static final String REMAINDER_UNSIGNED_1_0 = "remainderUnsigned_1.0";

    /**
     * Method to compute the divide using unsigned arithmetic.
     */
    private static final String DIVIDE_UNSIGNED_1_1 = "divideUnsigned_1.1";

    /**
     * Method to compute the remainder using unsigned arithmetic.
     */
    private static final String REMAINDER_UNSIGNED_1_1 = "remainderUnsigned_1.1";

    /**
     * Source of {@code long} array data.
     */
    @State(Scope.Benchmark)
    public static class LongDataSource {

        /**
         * Data length.
         */
        @Param({ "1024" })
        private int length;

        /**
         * Data.
         */
        private long[] data;

        /**
         * @return the data
         */
        public long[] getData() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data.
         * Data will be randomized per iteration.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of {@code int} array data.
     */
    @State(Scope.Benchmark)
    public static class IntDataSource {

        /**
         * Data length.
         */
        @Param({ "1024" })
        private int length;

        /**
         * Data.
         */
        private int[] data;

        /**
         * @return the data
         */
        public int[] getData() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the data.
         * Data will be randomized per iteration.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of a {@link LongBinaryOperator}.
     */
    @State(Scope.Benchmark)
    public static class LongFunctionSource {

        /**
         * Name of the source.
         */
        @Param({ "Long.divideUnsigned", DIVIDE_UNSIGNED_1_0, DIVIDE_UNSIGNED_1_1, "Long.remainderUnsigned", REMAINDER_UNSIGNED_1_0, REMAINDER_UNSIGNED_1_1 })
        private String name;

        /**
         * The action.
         */
        private LongBinaryOperator function;

        /**
         * @return the function
         */
        public LongBinaryOperator getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Source of an {@link IntBinaryOperator}.
     */
    @State(Scope.Benchmark)
    public static class IntFunctionSource {

        /**
         * Name of the source.
         */
        @Param({ "Integer.divideUnsigned", DIVIDE_UNSIGNED_1_0, DIVIDE_UNSIGNED_1_1, "Integer.remainderUnsigned", REMAINDER_UNSIGNED_1_0, REMAINDER_UNSIGNED_1_1 })
        private String name;

        /**
         * The action.
         */
        private IntBinaryOperator function;

        /**
         * @return the function
         */
        public IntBinaryOperator getFunction() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Create the function.
         */
        @Setup(Level.Iteration)
        public void setup() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Returns the unsigned remainder from dividing the first argument
     * by the second where each argument and the result is interpreted
     * as an unsigned value.
     * <p>This method does not use the {@code long} datatype.</p>
     *
     * <p>This is a copy of the original method in {@code o.a.c.numbers.core.ArithmeticUtils} v1.0.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned remainder of the first argument divided by
     * the second argument.
     */
    public static int remainderUnsigned(int dividend, int divisor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the unsigned remainder from dividing the first argument
     * by the second where each argument and the result is interpreted
     * as an unsigned value.
     * <p>This method does not use the {@code BigInteger} datatype.</p>
     *
     * <p>This is a copy of the original method in {@code o.a.c.numbers.core.ArithmeticUtils} v1.0.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned remainder of the first argument divided by
     * the second argument.
     */
    public static long remainderUnsigned(long dividend, long divisor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the unsigned quotient of dividing the first argument by
     * the second where each argument and the result is interpreted as
     * an unsigned value.
     * <p>Note that in two's complement arithmetic, the three other
     * basic arithmetic operations of add, subtract, and multiply are
     * bit-wise identical if the two operands are regarded as both
     * being signed or both being unsigned. Therefore separate {@code
     * addUnsigned}, etc. methods are not provided.</p>
     * <p>This method does not use the {@code long} datatype.</p>
     *
     * <p>This is a copy of the original method in {@code o.a.c.numbers.core.ArithmeticUtils}.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned quotient of the first argument divided by
     * the second argument
     */
    public static int divideUnsigned(int dividend, int divisor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the unsigned quotient of dividing the first argument by
     * the second where each argument and the result is interpreted as
     * an unsigned value.
     * <p>Note that in two's complement arithmetic, the three other
     * basic arithmetic operations of add, subtract, and multiply are
     * bit-wise identical if the two operands are regarded as both
     * being signed or both being unsigned. Therefore separate {@code
     * addUnsigned}, etc. methods are not provided.</p>
     * <p>This method does not use the {@code BigInteger} datatype.</p>
     *
     * <p>This is a copy of the original method in {@code o.a.c.numbers.core.ArithmeticUtils}.
     *
     * @param dividend the value to be divided
     * @param divisor the value doing the dividing
     * @return the unsigned quotient of the first argument divided by
     * the second argument.
     */
    public static long divideUnsigned(long dividend, long divisor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Benchmark methods.
    /**
     * Benchmark a {@link LongBinaryOperator}.
     *
     * @param data Data source.
     * @param function Function source.
     * @return the long
     */
    @Benchmark
    public long longOp(LongDataSource data, LongFunctionSource function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Benchmark a {@link IntBinaryOperator}.
     *
     * @param data Data source.
     * @param function Function source.
     * @return the int
     */
    @Benchmark
    public int intOp(IntDataSource data, IntFunctionSource function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
