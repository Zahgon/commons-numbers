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
package org.apache.commons.numbers.examples.jmh.arrays;

/**
 * A strategy to pick two pivot indices of an array for partitioning.
 *
 * <p>An ideal strategy will pick the tertiles across a variety of data so
 * to divide the data into [1/3, 1/3, 1/3].
 *
 * @see <a href="https://en.wiktionary.org/wiki/tertile">Tertile (Wiktionary)</a>
 * @since 1.2
 */
enum DualPivotingStrategy {

    /**
     * Pivot around the medians at 1/3 and 2/3 of the range.
     *
     * <p>Requires {@code right - left >= 2}.
     *
     * <p>On sorted data the tertiles are: 0.3340 0.6670
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0000   0.9970   0.3327   0.2357   0.2920   0.5654
     * [2]  0.0020   1.0000   0.3346   0.2356   0.2940   0.5675
     * [3]  0.0000   0.9970   0.3328   0.2356   0.2920   0.5656
     * </pre>
     */
    MEDIANS {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 2nd and 4th values from 5 approximately uniformly spaced within the range.
     * Uses points +/- sixths from the median: 1/6, 1/3, 1/2, 2/3, 5/6.
     *
     * <p>Requires {@code right - left >= 4}.
     *
     * <p>Warning: This has the side effect that the 5 values are also sorted.
     *
     * <p>On sorted data the tertiles are: 0.3290 0.6710
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0010   0.9820   0.3327   0.1778   0.3130   0.4650
     * [2]  0.0030   0.9760   0.3348   0.1778   0.3150   0.4665
     * [3]  0.0010   0.9870   0.3325   0.1779   0.3130   0.4698
     * </pre>
     */
    SORT_5 {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 2nd and 4th values from 5 approximately uniformly spaced within the range.
     * Uses points +/- sevenths from the median: 3/14, 5/14, 1/2, 9/14, 11/14.
     *
     * <p>Requires {@code right - left >= 4}.
     *
     * <p>Warning: This has the side effect that the 5 values are also sorted.
     *
     * <p>On sorted data the tertiles are: 0.3600 0.6400
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0010   0.9790   0.3330   0.1780   0.3140   0.4665
     * [2]  0.0030   0.9800   0.3348   0.1778   0.3150   0.4681
     * [3]  0.0010   0.9770   0.3322   0.1777   0.3130   0.4677
     * </pre>
     */
    SORT_5B {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * This strategy is the same as {@link #SORT_5B} with the exception that it
     * returns identical pivots if the data at the chosen pivots is equal.
     *
     * <p>This allows testing switching to a single pivot strategy against using
     * a dual pivot partitioning with effectively only 1 pivot. This requires
     * the dual pivot partition function to check pivot1 == pivot2. If the
     * dual pivot partition function checks data[pivot1] == data[pivot2] then
     * the switching choice cannot be enabled/disabled by changing pivoting strategy
     * and must use another mechanism.
     *
     * <p>This specific strategy has been selected for single-pivot switching as
     * {@link #SORT_5B} benchmarks as consistently fast across all data input.
     */
    SORT_5B_SP {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 2nd and 4th values from 5 approximately uniformly spaced within the range.
     * Uses points +/- eights from the median: 1/4, 3/8, 1/2, 5/8, 3/4.
     *
     * <p>Requires {@code right - left >= 4}.
     *
     * <p>Warning: This has the side effect that the 5 values are also sorted.
     *
     * <p>On sorted data the tertiles are: 0.3750 0.6250
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0010   0.9790   0.3324   0.1779   0.3130   0.4666
     * [2]  0.0030   0.9850   0.3348   0.1778   0.3150   0.4686
     * [3]  0.0010   0.9720   0.3327   0.1779   0.3130   0.4666
     * </pre>
     */
    SORT_5C {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 2nd and 4th values from 5 medians approximately uniformly spaced within
     * the range. The medians are from 3 samples. The 5 samples of 3 do not overlap thus this
     * method requires {@code right - left >= 14}. The samples can be visualised as 5 sorted
     * columns:
     *
     * <pre>
     * v w x y z
     * 1 2 3 4 5
     * a b c d e
     * </pre>
     *
     * <p>The pivots are points 2 and 4. The other points are either known to be below or
     * above the pivots; or potentially below or above the pivots.
     *
     * <p>Pivot 1: below {@code 1,a,b}; potentially below {@code v,c,d,e}. This ranks
     * pivot 1 from 4/15 to 8/15 and exactly 5/15 if the input data is sorted/reverse sorted.
     *
     * <p>Pivot 2: above {@code 5,y,z}; potentially above {@code e,v,w,x}. This ranks
     * pivot 2 from 7/15 to 11/15 and exactly 10/15 if the input data is sorted/reverse sorted.
     *
     * <p>Warning: This has the side effect that the 15 samples values are partially sorted.
     *
     * <p>On sorted data the tertiles are: 0.3140 0.6860
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0090   0.9170   0.3783   0.1320   0.3730   0.2107
     * [2]  0.0030   0.8950   0.2438   0.1328   0.2270   0.6150
     * [3]  0.0110   0.9140   0.3779   0.1319   0.3730   0.2114
     * </pre>
     * <p>Note the bias towards the outer regions.
     */
    SORT_5_OF_3 {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 2nd and 3rd values from 4 medians approximately uniformly spaced within
     * the range. The medians are from 3 samples. The 4 samples of 3 do not overlap thus this
     * method requires {@code right - left >= 11}. The samples can be visualised as 4 sorted
     * columns:
     *
     * <pre>
     * w x y z
     * 1 2 3 4
     * a b c d
     * </pre>
     *
     * <p>The pivots are points 2 and 3. The other points are either known to be below or
     * above the pivots; or potentially below or above the pivots.
     *
     * <p>Pivot 1: below {@code 1,a,b}; potentially below {@code w,c,d}. This ranks
     * pivot 1 from 4/12 to 7/12 and exactly 5/12 if the input data is sorted/reverse sorted.
     *
     * <p>Pivot 2: above {@code 4,y,z}; potentially above {@code d,w,x}. This ranks
     * pivot 2 from 5/15 to 8/12 and exactly 7/12 if the input data is sorted/reverse sorted.
     *
     * <p>Warning: This has the side effect that the 12 samples values are partially sorted.
     *
     * <p>On sorted data the tertiles are: 0.3850 0.6160
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0160   0.9580   0.4269   0.1454   0.4230   0.1366
     * [2]  0.0020   0.8270   0.1467   0.1193   0.1170   1.1417
     * [3]  0.0140   0.9560   0.4264   0.1453   0.4230   0.1352
     * </pre>
     * <p>Note the large bias towards the outer regions.
     */
    SORT_4_OF_3 {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 1st and 3rd values from 3 medians approximately uniformly spaced within
     * the range. The medians are from 3 samples. The 3 samples of 3 do not overlap thus this
     * method requires {@code right - left >= 8}. The samples can be visualised as 3 sorted
     * columns:
     *
     * <pre>
     * x y z
     * 1 2 3
     * a b c
     * </pre>
     *
     * <p>The pivots are points 1 and 3. The other points are either known to be below or
     * above the pivots; or potentially below or above the pivots.
     *
     * <p>Pivot 1: below {@code a}; potentially below {@code b, c}. This ranks
     * pivot 1 from 2/9 to 4/9 and exactly 2/9 if the input data is sorted/reverse sorted.
     *
     * <p>Pivot 2: above {@code z}; potentially above {@code x,y}. This ranks
     * pivot 2 from 6/9 to 8/9 and exactly 8/9 if the input data is sorted/reverse sorted.
     *
     * <p>Warning: This has the side effect that the 9 samples values are partially sorted.
     *
     * <p>On sorted data the tertiles are: 0.1280 0.8720
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0010   0.9460   0.3062   0.1560   0.2910   0.4455
     * [2]  0.0030   0.9820   0.3875   0.1813   0.3780   0.2512
     * [3]  0.0010   0.9400   0.3063   0.1558   0.2910   0.4453
     * </pre>
     * <p>Note the bias towards the central region.
     */
    SORT_3_OF_3 {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 2nd and 4th values from 5 medians approximately uniformly spaced within
     * the range. The medians are from 5 samples. The 5 samples of 5 do not overlap thus this
     * method requires {@code right - left >= 24}. The samples can be visualised as 5 sorted
     * columns:
     *
     * <pre>
     * v w x y z
     * q r s t u
     * 1 2 3 4 5
     * f g h i j
     * a b c d e
     * </pre>
     *
     * <p>The pivots are points 2 and 4. The other points are either known to be below or
     * above the pivots; or potentially below or above the pivots.
     *
     * <p>Pivot 1: below {@code 1,a,b,f,g}; potentially below {@code q,v,c,d,e,h,i,j}. This ranks
     * pivot 1 from 6/25 to 14/25 and exactly 8/25 if the input data is sorted/reverse sorted.
     *
     * <p>Pivot 2 by symmetry from 12/25 to 20/25 and exactly 18/25 for sorted data.
     *
     * <p>Warning: This has the side effect that the 25 samples values are partially sorted.
     *
     * <p>On sorted data the tertiles are: 0.3050 0.6950
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0270   0.8620   0.3996   0.1093   0.3970   0.1130
     * [2]  0.0030   0.8100   0.2010   0.1106   0.1860   0.6691
     * [3]  0.0270   0.8970   0.3994   0.1093   0.3970   0.1147
     * </pre>
     * <p>Note the bias towards the outer regions on random data but the inner region on
     * sorted data.
     */
    SORT_5_OF_5 {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 3rd and 5th values from 7 approximately uniformly spaced within the range.
     * Uses points +/- eights from the median: 1/8, 1/4, 3/8, 1/2, 5/8, 3/4, 7/8.
     *
     * <p>Requires {@code right - left >= 6}.
     *
     * <p>Warning: This has the side effect that the 7 values are also sorted.
     *
     * <p>On sorted data the tertiles are: 0.3760 0.6240
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0020   0.9600   0.3745   0.1609   0.3640   0.3092
     * [2]  0.0030   0.9490   0.2512   0.1440   0.2300   0.6920
     * [3]  0.0030   0.9620   0.3743   0.1609   0.3640   0.3100
     * </pre>
     * <p>Note the bias towards the outer regions.
     */
    SORT_7 {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 3rd and 6th values from 8 approximately uniformly spaced within the range.
     * Uses points +/- ninths from the median: m - 4/9, m - 3/9, m - 2/9, m - 1/9; m + 1 + 1/9,
     * m + 1 + 2/9, m + 1 + 3/9, m + 1 + 4/9.
     *
     * <p>Requires {@code right - left >= 7}.
     *
     * <p>Warning: This has the side effect that the 8 values are also sorted.
     *
     * <p>On sorted data the tertiles are: 0.3380 0.6630
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0030   0.9480   0.3327   0.1485   0.3200   0.4044
     * [2]  0.0050   0.9350   0.3345   0.1485   0.3220   0.4056
     * [3]  0.0020   0.9320   0.3328   0.1485   0.3200   0.4063
     * </pre>
     */
    SORT_8 {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ,
    /**
     * Pivot around the 4th and 8th values from 11 approximately uniformly spaced within the range.
     * Uses points +/- twelfths from the median: ..., m - 1/12, m, m + 1/12, ... .
     *
     * <p>Requires {@code right - left >= 10}.
     *
     * <p>Warning: This has the side effect that the 11 values are also sorted.
     *
     * <p>On sorted data the tertiles are: 0.3460 0.6540
     * <p>On random data the tertiles are:
     * <pre>
     *         min      max     mean       sd   median     skew
     * [1]  0.0060   0.9000   0.3328   0.1301   0.3230   0.3624
     * [2]  0.0100   0.9190   0.3345   0.1299   0.3250   0.3643
     * [3]  0.0060   0.8970   0.3327   0.1302   0.3230   0.3653
     * </pre>
     */
    SORT_11 {

        @Override
        int pivotIndex(double[] data, int left, int right, int[] pivot2) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int[] getSampledIndices(int left, int right) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        int samplingEffect() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ;

    /**
     * Sampled points are unchanged.
     */
    static final int UNCHANGED = 0;

    /**
     * Sampled points are partially sorted.
     */
    static final int PARTIAL_SORT = 0x1;

    /**
     * Sampled points are sorted.
     */
    static final int SORT = 0x2;

    /**
     * Find two pivot indices of the array so that partitioning into 3-regions can be made.
     *
     * <pre>{@code
     * left <= p1 <= p2 <= right
     * }</pre>
     *
     * <p>Returns two pivots so that {@code data[p1] <= data[p2]}.
     *
     * @param data Array.
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @param pivot2 Second pivot.
     * @return first pivot
     */
    abstract int pivotIndex(double[] data, int left, int right, int[] pivot2);

    // The following methods allow the strategy and side effects to be tested
    /**
     * Get the indices of points that will be sampled.
     *
     * @param left Lower bound (inclusive).
     * @param right Upper bound (inclusive).
     * @return the indices
     */
    abstract int[] getSampledIndices(int left, int right);

    /**
     * Get the effect on the sampled points.
     * <ul>
     * <li>0 - Unchanged</li>
     * <li>1 - Partially sorted</li>
     * <li>2 - Sorted</li>
     * </ul>
     *
     * @return the effect
     */
    abstract int samplingEffect();
}
