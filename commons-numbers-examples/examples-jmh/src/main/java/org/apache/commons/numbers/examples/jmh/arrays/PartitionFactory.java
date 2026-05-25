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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.numbers.examples.jmh.arrays.Partition.AdaptMode;
import org.apache.commons.numbers.examples.jmh.arrays.Partition.EdgeSelectStrategy;
import org.apache.commons.numbers.examples.jmh.arrays.Partition.ExpandStrategy;
import org.apache.commons.numbers.examples.jmh.arrays.Partition.KeyStrategy;
import org.apache.commons.numbers.examples.jmh.arrays.Partition.LinearStrategy;
import org.apache.commons.numbers.examples.jmh.arrays.Partition.PairedKeyStrategy;
import org.apache.commons.numbers.examples.jmh.arrays.Partition.SPStrategy;
import org.apache.commons.numbers.examples.jmh.arrays.Partition.StopperStrategy;

/**
 * Create instances of partition algorithms. The configuration of the algorithm
 * is obtained by harvesting parameters from the name.
 *
 * @see Partition
 * @see KthSelector
 * @since 1.2
 */
final class PartitionFactory {

    /**
     * Pattern for the minimum quickselect size.
     */
    private static final Pattern QS_PATTERN = Pattern.compile("QS(\\d+)");

    /**
     * Pattern for the edgeselect constant.
     */
    private static final Pattern EC_PATTERN = Pattern.compile("EC(\\d+)");

    /**
     * Pattern for the edgeselect constant for linear select.
     */
    private static final Pattern LC_PATTERN = Pattern.compile("LC(\\d+)");

    /**
     * Pattern for the sub-sampling size.
     */
    private static final Pattern SU_PATTERN = Pattern.compile("SU(\\d+)");

    /**
     * Pattern for the recursion multiple (simple float format).
     */
    private static final Pattern RM_PATTERN = Pattern.compile("RM(\\d+\\.?\\d*)");

    /**
     * Pattern for the recursion constant.
     */
    private static final Pattern RC_PATTERN = Pattern.compile("RC(\\d+)");

    /**
     * Pattern for the compression level.
     */
    private static final Pattern CL_PATTERN = Pattern.compile("CL(\\d+)");

    /**
     * Pattern for the control flags. Allow negative flags.
     */
    private static final Pattern CF_PATTERN = Pattern.compile("CF(-?\\d+)");

    /**
     * Pattern for the option flags.
     */
    private static final Pattern OF_PATTERN = Pattern.compile("OF(-?\\d+)");

    /**
     * No instances.
     */
    private PartitionFactory() {
    }

    /**
     * Creates the {@link KthSelector}. Parameters are derived from the {@code name}.
     *
     * <p>After parameters are harvested the only allowed characters are underscores,
     * otherwise an exception is thrown. This ensures the parameters in the name were
     * correct.
     *
     * @param name Name.
     * @param prefix Method prefix.
     * @return the {@link KthSelector} instance
     */
    static KthSelector createKthSelector(String name, String prefix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates the {@link KthSelector}. Parameters are derived from the {@code name}. This
     * uses regex matching or enum name matching. Regex uses a prefix of two characters
     * and then a number. Enum name matching finds the longest enum name match from all
     * enum values. Ideally enum names from different enums that can be used together
     * should be distinct. Enum names in the {@code name} must be prefixed using an underscore.
     *
     * <p>Any matches are removed from the {@code name}. After parameters are harvested
     * the only allowed characters are underscores, otherwise an exception is thrown. This
     * ensures the parameters in the {@code name} were correct.
     *
     * <p>Harvests:
     * <ul>
     * <li>PivotingStrategy : enum name</li>
     * <li>QSnn : minimum quickselect size of n</li>
     * </ul>
     *
     * @param name Name.
     * @param prefix Method prefix.
     * @param qs Minimum quickselect size (if non-zero).
     * @return the {@link KthSelector} instance
     */
    static KthSelector createKthSelector(String name, String prefix, int qs) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates the {@link Partition}. Parameters are derived from the {@code name}.
     *
     * <p>After parameters are harvested the only allowed characters are underscores,
     * otherwise an exception is thrown. This ensures the parameters in the name were
     * correct.
     *
     * @param name Name.
     * @param prefix Method prefix.
     * @return the {@link Partition} instance
     * @see #createPartition(String, String, int, int)
     */
    static Partition createPartition(String name, String prefix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates the {@link Partition}. Parameters are derived from the {@code name}. This
     * uses regex matching or enum name matching. Regex uses a prefix of two characters
     * and then a number. Enum name matching finds the longest enum name match from all
     * enum values. Ideally enum names from different enums that can be used together
     * should be distinct. Enum names in the {@code name} must be prefixed using an underscore.
     *
     * <p>Any matches are removed from the {@code name}. After parameters are harvested
     * the only allowed characters are underscores, otherwise an exception is thrown. This
     * ensures the parameters in the {@code name} were correct.
     *
     * <p>Harvests:
     * <ul>
     * <li>PivotingStrategy : enum name</li>
     * <li>DualPivotingStrategy : enum name</li>
     * <li>QSn : minimum quickselect size of n</li>
     * <li>ECn : minimum edgeselect constant of n</li>
     * <li>LCn : minimum sortselect constant of n for linear selection algorithms</li>
     * <li>SUn : minimum sub-sampling size of n (for the Floyd-Rivest algorithm)</li>
     * <li>KeyStrategy : enum name</li>
     * <li>PairedKeyStrategy : enum name</li>
     * <li>RMn.n : recursion multiple of n.n (optional floating point) used to set recursion limit for introspection</li>
     * <li>RCn : recursion constant of n used to set recursion limit for introspection</li>
     * <li>CLn : compression level of n for compressed keys</li>
     * <li>CFn : control flags of n</li>
     * <li>SPStrategy : enum name</li>
     * <li>ExpandStrategy : enum name</li>
     * <li>LinearStrategy : enum name</li>
     * <li>EdgeSelectStrategy : enum name</li>
     * <li>StopperStrategy : enum name</li>
     * <li>AdaptMode : enum name</li>
     * </ul>
     *
     * @param name Name.
     * @param prefix Method prefix.
     * @param qs Minimum quickselect size (if non-zero).
     * @param ec Minimum edgeselect constant (if non-zero); also used for linear sort select size.
     * @return the {@link Partition} instance
     */
    static Partition createPartition(String name, String prefix, int qs, int ec) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the minimum size for the recursive quickselect partition algorithm.
     * Below this size the algorithm will change strategy for partitioning,
     * e.g. change to a full sort.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @return the minimum quickselect size
     */
    static int getMinQuickSelectSize(String[] name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the constant for the edgeselect distance-from-end computation.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @return the edgeselect constant
     */
    static int getEdgeSelectConstant(String[] name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the constant for the sortselect distance-from-end computation for linearselect.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @return the sortselect constant
     */
    static int getLinearSortSelectConstant(String[] name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the minimum size for single-pivot sub-sampling (using the Floyd-Rivest algorithm).
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @return the sub-sampling size
     */
    static int getSubSamplingSize(String[] name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the recursion multiplication factor.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @return the recursion multiple
     */
    static double getRecursionMultiple(String[] name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the recursion constant.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @return the recursion constant
     */
    static int getRecursionConstant(String[] name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the compression level for {@link CompressedIndexSet}.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @return the compression
     */
    static int getCompressionLevel(String[] name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the control flags. These are used to enable additional features, for example
     * random sampling in the Floyd-Rivest algorithm.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @return the control flags
     */
    static int getControlFlags(String[] name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the control flags. These are used to enable additional features, for example
     * random sampling in the Floyd-Rivest algorithm.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @param defaultValue Default value.
     * @return the control flags
     */
    static int getControlFlags(String[] name, int defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the option flags. These are used to enable additional features, and can be
     * used separately to the control flags.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @return the option flags
     */
    static int getOptionFlags(String[] name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the option flags. These are used to enable additional features, and can be
     * used separately to the control flags.
     *
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @param defaultValue Default value.
     * @return the option flags
     */
    static int getOptionFlags(String[] name, int defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the enum from the name. The enum name must be prefixed with an underscore.
     *
     * @param <E> Enum type.
     * @param name Algorithm name (updated in-place to remove the parameter).
     * @param cls Class of the enum.
     * @param defaultValue Default value.
     * @return the enum value
     */
    static <E extends Enum<E>> E getEnumOrElse(String[] name, Class<E> cls, E defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
