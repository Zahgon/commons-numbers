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
package org.apache.commons.numbers.complex.streams;

import org.apache.commons.numbers.complex.Complex;

/**
 * Static implementations of common {@link Complex} utilities functions.
 */
public final class ComplexUtils {

    /**
     * Dimension X.
     */
    private static final int DIM_X = 0;

    /**
     * Dimension Y.
     */
    private static final int DIM_Y = 1;

    /**
     * Dimension Z.
     */
    private static final int DIM_Z = 2;

    /**
     * Utility class.
     */
    private ComplexUtils() {
    }

    /**
     * Creates a complex number from the given polar representation.
     * <p>
     * If either {@code r} or {@code theta} is NaN, or {@code theta} is
     * infinite, {@code Complex(NaN, NaN)} is returned.
     * <p>
     * If {@code r} is infinite and {@code theta} is finite, infinite or NaN
     * values may be returned in parts of the result, following the rules for
     * double arithmetic.
     *
     * Examples:
     * <pre>
     * {@code
     * polar2Complex(INFINITY, \(\pi\)) = INFINITY + INFINITY i
     * polar2Complex(INFINITY, 0) = INFINITY + NaN i
     * polar2Complex(INFINITY, \(-\frac{\pi}{4}\)) = INFINITY - INFINITY i
     * polar2Complex(INFINITY, \(5\frac{\pi}{4}\)) = -INFINITY - INFINITY i }
     * </pre>
     *
     * @param r the modulus of the complex number to create
     * @param theta the argument of the complex number to create
     * @return {@code Complex}
     * @throws IllegalArgumentException if {@code r} is negative
     */
    public static Complex polar2Complex(double r, double theta) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates {@code Complex[]} array given {@code double[]} arrays of r and
     * theta.
     *
     * @param r {@code double[]} of moduli
     * @param theta {@code double[]} of arguments
     * @return {@code Complex[]}
     * @throws IllegalArgumentException if any element in {@code r} is negative
     */
    public static Complex[] polar2Complex(double[] r, double[] theta) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates {@code Complex[][]} array given {@code double[][]} arrays of r
     * and theta.
     *
     * @param r {@code double[]} of moduli
     * @param theta {@code double[]} of arguments
     * @return {@code Complex[][]}
     * @throws IllegalArgumentException if any element in {@code r} is negative
     */
    public static Complex[][] polar2Complex(double[][] r, double[][] theta) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates {@code Complex[][][]} array given {@code double[][][]} arrays of
     * r and theta.
     *
     * @param r array of moduli
     * @param theta array of arguments
     * @return {@code Complex}
     * @throws IllegalArgumentException if any element in {@code r} is negative
     */
    public static Complex[][][] polar2Complex(double[][][] r, double[][][] theta) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns double from array {@code real[]} at entry {@code index} as a
     * {@code Complex}.
     *
     * @param real array of real numbers
     * @param index location in the array
     * @return {@code Complex}.
     */
    public static Complex extractComplexFromRealArray(double[] real, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns float from array {@code real[]} at entry {@code index} as a
     * {@code Complex}.
     *
     * @param real array of real numbers
     * @param index location in the array
     * @return {@code Complex} array
     */
    public static Complex extractComplexFromRealArray(float[] real, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns double from array {@code imaginary[]} at entry {@code index} as a
     * {@code Complex}.
     *
     * @param imaginary array of imaginary numbers
     * @param index location in the array
     * @return {@code Complex} array
     */
    public static Complex extractComplexFromImaginaryArray(double[] imaginary, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns float from array {@code imaginary[]} at entry {@code index} as a
     * {@code Complex}.
     *
     * @param imaginary array of imaginary numbers
     * @param index location in the array
     * @return {@code Complex} array
     */
    public static Complex extractComplexFromImaginaryArray(float[] imaginary, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns real component of Complex from array {@code Complex[]} at entry
     * {@code index} as a {@code double}.
     *
     * @param complex array of complex numbers
     * @param index location in the array
     * @return {@code double}.
     */
    public static double extractRealFromComplexArray(Complex[] complex, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns real component of array {@code Complex[]} at entry {@code index}
     * as a {@code float}.
     *
     * @param complex array of complex numbers
     * @param index location in the array
     * @return {@code float}.
     */
    public static float extractRealFloatFromComplexArray(Complex[] complex, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns imaginary component of Complex from array {@code Complex[]} at
     * entry {@code index} as a {@code double}.
     *
     * @param complex array of complex numbers
     * @param index location in the array
     * @return {@code double}.
     */
    public static double extractImaginaryFromComplexArray(Complex[] complex, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns imaginary component of array {@code Complex[]} at entry
     * {@code index} as a {@code float}.
     *
     * @param complex array of complex numbers
     * @param index location in the array
     * @return {@code float}.
     */
    public static float extractImaginaryFloatFromComplexArray(Complex[] complex, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a Complex object from interleaved {@code double[]} array at entry
     * {@code index}.
     *
     * @param d array of interleaved complex numbers alternating real and imaginary values
     * @param index location in the array This is the location by complex number, e.g. index number 5 in the
     *      array will return {@code Complex.ofCartesian(d[10], d[11])}
     * @return {@code Complex}.
     */
    public static Complex extractComplexFromInterleavedArray(double[] d, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a Complex object from interleaved {@code float[]} array at entry
     * {@code index}.
     *
     * @param f float array of interleaved complex numbers alternating real and imaginary values
     * @param index location in the array This is the location by complex number, e.g. index number 5
     *      in the {@code float[]} array will return new {@code Complex(d[10], d[11])}
     * @return {@code Complex}.
     */
    public static Complex extractComplexFromInterleavedArray(float[] f, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns values of Complex object from array {@code Complex[]} at entry
     * {@code index} as a size 2 {@code double} of the form {real, imag}.
     *
     * @param complex array of complex numbers
     * @param index location in the array
     * @return size 2 array.
     */
    public static double[] extractInterleavedFromComplexArray(Complex[] complex, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns Complex object from array {@code Complex[]} at entry
     * {@code index} as a size 2 {@code float} of the form {real, imag}.
     *
     * @param complex {@code Complex} array
     * @param index location in the array
     * @return size 2 {@code float[]}.
     */
    public static float[] extractInterleavedFloatFromComplexArray(Complex[] complex, int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code double[]} array to a {@code Complex[]} array.
     *
     * @param real array of numbers to be converted to their {@code Complex} equivalent
     * @return {@code Complex} array
     */
    public static Complex[] real2Complex(double[] real) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code float[]} array to a {@code Complex[]} array.
     *
     * @param real array of numbers to be converted to their {@code Complex} equivalent
     * @return {@code Complex} array
     */
    public static Complex[] real2Complex(float[] real) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D real {@code double[][]} array to a 2D {@code Complex[][]}
     * array.
     *
     * @param d 2D array
     * @return 2D {@code Complex} array
     */
    public static Complex[][] real2Complex(double[][] d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D real {@code float[][]} array to a 2D {@code Complex[][]}
     * array.
     *
     * @param d 2D array
     * @return 2D {@code Complex} array
     */
    public static Complex[][] real2Complex(float[][] d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D real {@code double[][][]} array to a {@code Complex [][][]}
     * array.
     *
     * @param d 3D complex interleaved array
     * @return 3D {@code Complex} array
     */
    public static Complex[][][] real2Complex(double[][][] d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D real {@code float[][][]} array to a {@code Complex [][][]}
     * array.
     *
     * @param d 3D complex interleaved array
     * @return 3D {@code Complex} array
     */
    public static Complex[][][] real2Complex(float[][][] d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 4D real {@code double[][][][]} array to a {@code Complex [][][][]}
     * array.
     *
     * @param d 4D complex interleaved array
     * @return 4D {@code Complex} array
     */
    public static Complex[][][][] real2Complex(double[][][][] d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts real component of {@code Complex[]} array to a {@code double[]}
     * array.
     *
     * @param c {@code Complex} array
     * @return array of the real component
     */
    public static double[] complex2Real(Complex[] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts real component of {@code Complex[]} array to a {@code float[]}
     * array.
     *
     * @param c {@code Complex} array
     * @return {@code float[]} array of the real component
     */
    public static float[] complex2RealFloat(Complex[] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts real component of a 2D {@code Complex[][]} array to a 2D
     * {@code double[][]} array.
     *
     * @param c 2D {@code Complex} array
     * @return {@code double[][]} of real component
     */
    public static double[][] complex2Real(Complex[][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts real component of a 2D {@code Complex[][]} array to a 2D
     * {@code float[][]} array.
     *
     * @param c 2D {@code Complex} array
     * @return {@code float[][]} of real component
     */
    public static float[][] complex2RealFloat(Complex[][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts real component of a 3D {@code Complex[][][]} array to a 3D
     * {@code double[][][]} array.
     *
     * @param c 3D complex interleaved array
     * @return array of real component
     */
    public static double[][][] complex2Real(Complex[][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts real component of a 3D {@code Complex[][][]} array to a 3D
     * {@code float[][][]} array.
     *
     * @param c 3D {@code Complex} array
     * @return {@code float[][][]} of real component
     */
    public static float[][][] complex2RealFloat(Complex[][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts real component of a 4D {@code Complex[][][][]} array to a 4D
     * {@code double[][][][]} array.
     *
     * @param c 4D complex interleaved array
     * @return array of real component
     */
    public static double[][][][] complex2Real(Complex[][][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts real component of a 4D {@code Complex[][][][]} array to a 4D
     * {@code float[][][][]} array.
     *
     * @param c 4D {@code Complex} array
     * @return {@code float[][][][]} of real component
     */
    public static float[][][][] complex2RealFloat(Complex[][][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code double[]} array to an imaginary {@code Complex[]}
     * array.
     *
     * @param imaginary array of numbers to be converted to their {@code Complex} equivalent
     * @return {@code Complex} array
     */
    public static Complex[] imaginary2Complex(double[] imaginary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code float[]} array to an imaginary {@code Complex[]} array.
     *
     * @param imaginary array of numbers to be converted to their {@code Complex} equivalent
     * @return {@code Complex} array
     */
    public static Complex[] imaginary2Complex(float[] imaginary) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D imaginary array {@code double[][]} to a 2D
     * {@code Complex[][]} array.
     *
     * @param i 2D array
     * @return 2D {@code Complex} array
     */
    public static Complex[][] imaginary2Complex(double[][] i) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D imaginary array {@code double[][][]} to a {@code Complex[]}
     * array.
     *
     * @param i 3D complex imaginary array
     * @return 3D {@code Complex} array
     */
    public static Complex[][][] imaginary2Complex(double[][][] i) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 4D imaginary array {@code double[][][][]} to a 4D {@code Complex[][][][]}
     * array.
     *
     * @param i 4D complex imaginary array
     * @return 4D {@code Complex} array
     */
    public static Complex[][][][] imaginary2Complex(double[][][][] i) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts imaginary part of a {@code Complex[]} array to a
     * {@code double[]} array.
     *
     * @param c {@code Complex} array.
     * @return array of the imaginary component
     */
    public static double[] complex2Imaginary(Complex[] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts imaginary component of a {@code Complex[]} array to a
     * {@code float[]} array.
     *
     * @param c {@code Complex} array.
     * @return {@code float[]} array of the imaginary component
     */
    public static float[] complex2ImaginaryFloat(Complex[] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts imaginary component of a 2D {@code Complex[][]} array to a 2D
     * {@code double[][]} array.
     *
     * @param c 2D {@code Complex} array
     * @return {@code double[][]} of imaginary component
     */
    public static double[][] complex2Imaginary(Complex[][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts imaginary component of a 2D {@code Complex[][]} array to a 2D
     * {@code float[][]} array.
     *
     * @param c 2D {@code Complex} array
     * @return {@code float[][]} of imaginary component
     */
    public static float[][] complex2ImaginaryFloat(Complex[][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts imaginary component of a 3D {@code Complex[][][]} array to a 3D
     * {@code double[][][]} array.
     *
     * @param c 3D complex interleaved array
     * @return 3D {@code Complex} array
     */
    public static double[][][] complex2Imaginary(Complex[][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts imaginary component of a 3D {@code Complex[][][]} array to a 3D
     * {@code float[][][]} array.
     *
     * @param c 3D {@code Complex} array
     * @return {@code float[][][]} of imaginary component
     */
    public static float[][][] complex2ImaginaryFloat(Complex[][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts imaginary component of a 4D {@code Complex[][][][]} array to a 4D
     * {@code double[][][][]} array.
     *
     * @param c 4D complex interleaved array
     * @return 4D {@code Complex} array
     */
    public static double[][][][] complex2Imaginary(Complex[][][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts imaginary component of a 4D {@code Complex[][][][]} array to a 4D
     * {@code float[][][][]} array.
     *
     * @param c 4D {@code Complex} array
     * @return {@code float[][][][]} of imaginary component
     */
    public static float[][][][] complex2ImaginaryFloat(Complex[][][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // INTERLEAVED METHODS
    /**
     * Converts a complex interleaved {@code double[]} array to a
     * {@code Complex[]} array.
     *
     * @param interleaved array of numbers to be converted to their {@code Complex} equivalent
     * @return {@code Complex} array
     */
    public static Complex[] interleaved2Complex(double[] interleaved) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a complex interleaved {@code float[]} array to a
     * {@code Complex[]} array.
     *
     * @param interleaved float[] array of numbers to be converted to their {@code Complex} equivalent
     * @return {@code Complex} array
     */
    public static Complex[] interleaved2Complex(float[] interleaved) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code Complex[]} array to an interleaved complex
     * {@code double[]} array.
     *
     * @param c Complex array
     * @return complex interleaved array alternating real and
     *         imaginary values
     */
    public static double[] complex2Interleaved(Complex[] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a {@code Complex[]} array to an interleaved complex
     * {@code float[]} array.
     *
     * @param c Complex array
     * @return complex interleaved {@code float[]} alternating real and
     *         imaginary values
     */
    public static float[] complex2InterleavedFloat(Complex[] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D {@code Complex[][]} array to an interleaved complex
     * {@code double[][]} array.
     *
     * @param c 2D Complex array
     * @param interleavedDim Depth level of the array to interleave
     * @return complex interleaved array alternating real and
     *         imaginary values
     * @throws IllegalArgumentException if {@code interleavedDim} is not 0 or 1
     */
    public static double[][] complex2Interleaved(Complex[][] c, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D {@code Complex[][]} array to an interleaved complex
     * {@code double[][]} array. The second d level of the array is assumed
     * to be interleaved.
     *
     * @param c 2D Complex array
     * @return complex interleaved array alternating real and
     *         imaginary values
     */
    public static double[][] complex2Interleaved(Complex[][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D {@code Complex[][][]} array to an interleaved complex
     * {@code double[][][]} array.
     *
     * @param c 3D Complex array
     * @param interleavedDim Depth level of the array to interleave
     * @return complex interleaved array alternating real and
     *         imaginary values
     * @throws IllegalArgumentException if {@code interleavedDim} is not 0, 1, or 2
     */
    public static double[][][] complex2Interleaved(Complex[][][] c, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 4D {@code Complex[][][][]} array to an interleaved complex
     * {@code double[][][][]} array.
     *
     * @param c 4D Complex array
     * @param interleavedDim Depth level of the array to interleave
     * @return complex interleaved array alternating real and
     *         imaginary values
     * @throws IllegalArgumentException if {@code interleavedDim} is not in the range {@code [0, 3]}
     */
    public static double[][][][] complex2Interleaved(Complex[][][][] c, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D {@code Complex[][][]} array to an interleaved complex
     * {@code double[][][]} array. The third level of the array is
     * interleaved.
     *
     * @param c 3D Complex array
     * @return complex interleaved array alternating real and
     *         imaginary values
     */
    public static double[][][] complex2Interleaved(Complex[][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 4D {@code Complex[][][][]} array to an interleaved complex
     * {@code double[][][][]} array. The fourth level of the array is
     * interleaved.
     *
     * @param c 4D Complex array
     * @return complex interleaved array alternating real and
     *         imaginary values
     */
    public static double[][][][] complex2Interleaved(Complex[][][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D {@code Complex[][]} array to an interleaved complex
     * {@code float[][]} array.
     *
     * @param c 2D Complex array
     * @param interleavedDim Depth level of the array to interleave
     * @return complex interleaved {@code float[][]} alternating real and
     *         imaginary values
     * @throws IllegalArgumentException if {@code interleavedDim} is not 0 or 1
     */
    public static float[][] complex2InterleavedFloat(Complex[][] c, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D {@code Complex[][]} array to an interleaved complex
     * {@code float[][]} array. The second d level of the array is assumed
     * to be interleaved.
     *
     * @param c 2D Complex array
     *
     * @return complex interleaved {@code float[][]} alternating real and
     *         imaginary values
     */
    public static float[][] complex2InterleavedFloat(Complex[][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D {@code Complex[][][]} array to an interleaved complex
     * {@code float[][][]} array.
     *
     * @param c 3D Complex array
     * @param interleavedDim Depth level of the array to interleave
     * @return complex interleaved {@code float[][][]} alternating real and
     *         imaginary values
     * @throws IllegalArgumentException if {@code interleavedDim} is not 0, 1, or 2
     */
    public static float[][][] complex2InterleavedFloat(Complex[][][] c, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D {@code Complex[][][]} array to an interleaved complex
     * {@code float[][][]} array. The third d level of the array is
     * interleaved.
     *
     * @param c 2D Complex array
     *
     * @return complex interleaved {@code float[][][]} alternating real and
     *         imaginary values
     */
    public static float[][][] complex2InterleavedFloat(Complex[][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D interleaved complex {@code double[][]} array to a
     * {@code Complex[][]} array.
     *
     * @param i 2D complex interleaved array
     * @param interleavedDim Depth level of the array to interleave
     * @return 2D {@code Complex} array
     * @throws IllegalArgumentException if {@code interleavedDim} is not 0 or 1
     */
    public static Complex[][] interleaved2Complex(double[][] i, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D interleaved complex {@code double[][]} array to a
     * {@code Complex[][]} array. The second d level of the array is assumed
     * to be interleaved.
     *
     * @param d 2D complex interleaved array
     * @return 2D {@code Complex} array
     */
    public static Complex[][] interleaved2Complex(double[][] d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D interleaved complex {@code double[][][]} array to a
     * {@code Complex[][][]} array.
     *
     * @param i 3D complex interleaved array
     * @param interleavedDim Depth level of the array to interleave
     * @return 3D {@code Complex} array
     * @throws IllegalArgumentException if {@code interleavedDim} is not 0, 1, or 2
     */
    public static Complex[][][] interleaved2Complex(double[][][] i, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 4D interleaved complex {@code double[][][][]} array to a
     * {@code Complex[][][][]} array.
     *
     * @param i 4D complex interleaved array
     * @param interleavedDim Depth level of the array to interleave
     * @return 4D {@code Complex} array
     * @throws IllegalArgumentException if {@code interleavedDim} is not in the range {@code [0, 3]}
     */
    public static Complex[][][][] interleaved2Complex(double[][][][] i, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D interleaved complex {@code double[][][]} array to a
     * {@code Complex[][][]} array. The third d level is assumed to be
     * interleaved.
     *
     * @param d 3D complex interleaved array
     * @return 3D {@code Complex} array
     */
    public static Complex[][][] interleaved2Complex(double[][][] d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D interleaved complex {@code float[][]} array to a
     * {@code Complex[][]} array.
     *
     * @param i 2D complex interleaved float array
     * @param interleavedDim Depth level of the array to interleave
     * @return 2D {@code Complex} array
     * @throws IllegalArgumentException if {@code interleavedDim} is not 0 or 1
     */
    public static Complex[][] interleaved2Complex(float[][] i, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D interleaved complex {@code float[][]} array to a
     * {@code Complex[][]} array. The second d level of the array is assumed
     * to be interleaved.
     *
     * @param d 2D complex interleaved float array
     * @return 2D {@code Complex} array
     */
    public static Complex[][] interleaved2Complex(float[][] d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D interleaved complex {@code float[][][]} array to a
     * {@code Complex[][][]} array.
     *
     * @param i 3D complex interleaved float array
     * @param interleavedDim Depth level of the array to interleave
     * @return 3D {@code Complex} array
     * @throws IllegalArgumentException if {@code interleavedDim} is not 0, 1, or 2
     */
    public static Complex[][][] interleaved2Complex(float[][][] i, int interleavedDim) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D interleaved complex {@code float[][][]} array to a
     * {@code Complex[]} array. The third level of the array is assumed to
     * be interleaved.
     *
     * @param d 3D complex interleaved float array
     * @return 3D {@code Complex} array
     */
    public static Complex[][][] interleaved2Complex(float[][][] d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // SPLIT METHODS
    /**
     * Converts a split complex array {@code double[] r, double[] i} to a
     * {@code Complex[]} array.
     *
     * @param real real component
     * @param imag imaginary component
     * @return {@code Complex} array
     */
    public static Complex[] split2Complex(double[] real, double[] imag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D split complex array {@code double[][] r, double[][] i} to a
     * 2D {@code Complex[][]} array.
     *
     * @param real real component
     * @param imag imaginary component
     * @return 2D {@code Complex} array
     */
    public static Complex[][] split2Complex(double[][] real, double[][] imag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D split complex array {@code double[][][] r, double[][][] i}
     * to a 3D {@code Complex[][][]} array.
     *
     * @param real real component
     * @param imag imaginary component
     * @return 3D {@code Complex} array
     */
    public static Complex[][][] split2Complex(double[][][] real, double[][][] imag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 4D split complex array {@code double[][][][] r, double[][][][] i}
     * to a 4D {@code Complex[][][][]} array.
     *
     * @param real real component
     * @param imag imaginary component
     * @return 4D {@code Complex} array
     */
    public static Complex[][][][] split2Complex(double[][][][] real, double[][][][] imag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a split complex array {@code float[] r, float[] i} to a
     * {@code Complex[]} array.
     *
     * @param real real component
     * @param imag imaginary component
     * @return {@code Complex} array
     */
    public static Complex[] split2Complex(float[] real, float[] imag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 2D split complex array {@code float[][] r, float[][] i} to a
     * 2D {@code Complex[][]} array.
     *
     * @param real real component
     * @param imag imaginary component
     * @return 2D {@code Complex} array
     */
    public static Complex[][] split2Complex(float[][] real, float[][] imag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Converts a 3D split complex array {@code float[][][] r, float[][][] i} to
     * a 3D {@code Complex[][][]} array.
     *
     * @param real real component
     * @param imag imaginary component
     * @return 3D {@code Complex} array
     */
    public static Complex[][][] split2Complex(float[][][] real, float[][][] imag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // MISC
    /**
     * Initializes a {@code Complex[]} array to zero, to avoid
     * NullPointerExceptions.
     *
     * @param c Complex array
     * @return c
     */
    public static Complex[] initialize(Complex[] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Initializes a {@code Complex[][]} array to zero, to avoid
     * NullPointerExceptions.
     *
     * @param c {@code Complex} array
     * @return c
     */
    public static Complex[][] initialize(Complex[][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Initializes a {@code Complex[][][]} array to zero, to avoid
     * NullPointerExceptions.
     *
     * @param c {@code Complex} array
     * @return c
     */
    public static Complex[][][] initialize(Complex[][][] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code double[]} containing absolute values (magnitudes) of a
     * {@code Complex[]} array.
     *
     * @param c {@code Complex} array
     * @return {@code double[]}
     */
    public static double[] abs(Complex[] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code double[]} containing arguments (phase angles) of a
     * {@code Complex[]} array.
     *
     * @param c {@code Complex} array
     * @return {@code double[]} array
     */
    public static double[] arg(Complex[] c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Exception to be throw when a negative value is passed as the modulus.
     */
    private static class NegativeModulusException extends IllegalArgumentException {

        /**
         * Serializable version identifier.
         */
        private static final long serialVersionUID = 20181205L;

        /**
         * @param r Wrong modulus.
         */
        NegativeModulusException(double r) {
            super("Modulus is negative: " + r);
        }
    }

    /**
     * Exception to be throw when an out-of-range index value is passed.
     */
    private static class IndexOutOfRangeException extends IllegalArgumentException {

        /**
         * Serializable version identifier.
         */
        private static final long serialVersionUID = 20181205L;

        /**
         * @param i Wrong index.
         */
        IndexOutOfRangeException(int i) {
            super("Out of range: " + i);
        }
    }
}
