
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2012, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.util.attribute;

import java.util.HashMap;
import java.util.Map;

import org.carrot2.util.attribute.test.filtering.*;
import org.fest.assertions.Assertions;
import org.junit.Test;

import com.carrotsearch.randomizedtesting.RandomizedTest;

@SuppressWarnings("unchecked")
public class BindableDescriptorTest extends RandomizedTest
{
    private final BindableDescriptor descriptor = BindableDescriptorBuilder
        .buildDescriptor(new FilteringSubClass());

    @Bindable
    public static class DefaultValuesTestClass
    {
        @Input
        @Attribute(key = "optionalNullValue")
        public String optional;

        @Input
        @Attribute(key = "requiredNullValue")
        @Required
        public String required;

        @Input
        @Attribute(key = "nonNull")
        public String nonNull = "nonNull";

        @Output
        @Attribute(key = "output")
        public String output = "value";
    }

    @Test
    public void testDefaultValues()
    {
        HashMap<String, Object> values = 
            BindableDescriptorBuilder
                .buildDescriptor(new DefaultValuesTestClass())
                .getDefaultValues();
        assertFalse(values.containsKey("optionalNullValue"));
        assertFalse(values.containsKey("output"));
        assertTrue(values.containsKey("requiredNullValue"));
        assertEquals(null, values.get("requiredNullValue"));
        assertEquals("nonNull", values.get("nonNull"));
    }
    
    @Test
    public void testOnlyWithNoCriteria()
    {
        final BindableDescriptor filteredDescriptor = descriptor.only();
        assertEquals(descriptor, filteredDescriptor);
    }

    @Test
    public void testOnlyInitProcessing()
    {
        final BindableDescriptor filteredDescriptor = descriptor.only(TestInit.class,
            TestProcessing.class);
        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSubClass("initProcessingInput"),
            keyFromSubClass("initProcessingOutput"),
            keyFromSubClass("initProcessingInputOutput"));
        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, FilteringReferenceClass.class))
            .isNull();
    }

    @Test
    public void testOnlyInputOutput()
    {
        final BindableDescriptor filteredDescriptor = descriptor.only(Input.class,
            Output.class);
        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSuperClass("initInputOutput"),
            keyFromSuperClass("processingInputOutput"),
            keyFromSubClass("initProcessingInputOutput"));
        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, FilteringReferenceClass.class))
            .isNull();
    }

    @Test
    public void testOnlyInputOutputInitProcessing()
    {
        final BindableDescriptor filteredDescriptor = descriptor.only(TestInit.class,
            TestProcessing.class, Input.class, Output.class);
        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSubClass("initProcessingInputOutput"));
        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, FilteringReferenceClass.class))
            .isNull();
    }

    @Test
    public void testOnlyInput()
    {
        final BindableDescriptor filteredDescriptor = descriptor.only(Input.class);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSuperClass("initInput"), keyFromSuperClass("initInputOutput"),
            keyFromSuperClass("processingInput"),
            keyFromSuperClass("processingInputOutput"),
            keyFromSubClass("initProcessingInput"),
            keyFromSubClass("initProcessingInputOutput"),
            keyFromReferenceClass("initInput"), keyFromReferenceClass("processingInput"));
    }

    @Test
    public void testOnlyInputProcessing()
    {
        final BindableDescriptor filteredDescriptor = descriptor.only(Input.class,
            TestProcessing.class);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSuperClass("processingInput"),
            keyFromSuperClass("processingInputOutput"),
            keyFromSubClass("initProcessingInput"),
            keyFromSubClass("initProcessingInputOutput"),
            keyFromReferenceClass("processingInput"));
    }

    @Test
    public void testRepeatedGrouping()
    {
        final BindableDescriptor grouped = descriptor
            .group(BindableDescriptor.GroupingMethod.LEVEL);

        final BindableDescriptor groupedAgain = grouped
            .group(BindableDescriptor.GroupingMethod.LEVEL);

        final BindableDescriptor flattened = groupedAgain.flatten();
        final BindableDescriptor flattenedAgain = flattened.flatten();

        assertSame(grouped, groupedAgain);
        assertNotSame(flattened, groupedAgain);
        assertSame(flattened, flattenedAgain);
    }

    @Test
    public void testNotInput()
    {
        final BindableDescriptor filteredDescriptor = descriptor.not(Input.class);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSuperClass("initOutput"), keyFromSuperClass("processingOutput"),
            keyFromSubClass("initProcessingOutput"), keyFromReferenceClass("initOutput"),
            keyFromReferenceClass("processingOutput"));
    }

    @Test
    public void testNotInputProcessing()
    {
        final BindableDescriptor filteredDescriptor = descriptor.not(Input.class,
            TestProcessing.class);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSuperClass("initOutput"), keyFromReferenceClass("initOutput"));
    }

    @Test
    public void testFlatten()
    {
        final BindableDescriptor filteredDescriptor = descriptor.only(Input.class,
            TestProcessing.class).flatten();

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSuperClass("processingInput"),
            keyFromSuperClass("processingInputOutput"),
            keyFromSubClass("initProcessingInput"),
            keyFromSubClass("initProcessingInputOutput"),
            keyFromReferenceClass("processingInput"));

        Assertions.assertThat(filteredDescriptor.attributeGroups).isEmpty();
    }

    @Test
    public void testAllByStructure()
    {
        final BindableDescriptor filteredDescriptor = descriptor
            .group(BindableDescriptor.GroupingMethod.STRUCTURE);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).isEmpty();

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, FilteringSubClass.class).keySet())
            .containsOnly(keyFromSubClass("initProcessingInput"),
                keyFromSubClass("initProcessingOutput"),
                keyFromSubClass("initProcessingInputOutput"));

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, FilteringSuperClass.class)
                .keySet()).containsOnly(keyFromSuperClass("initInput"),
            keyFromSuperClass("initOutput"), keyFromSuperClass("initInputOutput"),
            keyFromSuperClass("processingInput"), keyFromSuperClass("processingOutput"),
            keyFromSuperClass("processingInputOutput"));

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, FilteringReferenceClass.class)
                .keySet()).containsOnly(keyFromReferenceClass("initInput"),
            keyFromReferenceClass("initOutput"),
            keyFromReferenceClass("processingInput"),
            keyFromReferenceClass("processingOutput"));
    }

    @Test
    public void testEmptyGroupsAfterFiltering()
    {
        final BindableDescriptor filteredDescriptor = descriptor.group(
            BindableDescriptor.GroupingMethod.STRUCTURE).only(Input.class).only(
            Output.class);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).isEmpty();

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, FilteringSubClass.class).keySet())
            .containsOnly(keyFromSubClass("initProcessingInputOutput"));

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, FilteringSuperClass.class)
                .keySet()).containsOnly(keyFromSuperClass("initInputOutput"),
            keyFromSuperClass("processingInputOutput"));

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, FilteringReferenceClass.class))
            .isNull();
    }

    @Test
    public void testAllByLevel()
    {
        final BindableDescriptor filteredDescriptor = descriptor
            .group(BindableDescriptor.GroupingMethod.LEVEL);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSubClass("initProcessingInputOutput"),
            keyFromSuperClass("processingInput"), keyFromSuperClass("processingOutput"),
            keyFromSuperClass("processingInputOutput"),
            keyFromReferenceClass("processingOutput"));

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, AttributeLevel.BASIC).keySet())
            .containsOnly(keyFromSubClass("initProcessingInput"),
                keyFromSuperClass("initInput"), keyFromReferenceClass("initInput"));

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, AttributeLevel.MEDIUM).keySet())
            .containsOnly(keyFromSubClass("initProcessingOutput"),
                keyFromSuperClass("initOutput"), keyFromReferenceClass("initOutput"));

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, AttributeLevel.ADVANCED).keySet())
            .containsOnly(keyFromSuperClass("initInputOutput"),
                keyFromReferenceClass("processingInput"));
    }

    @Test
    public void testInputByLevel()
    {
        final BindableDescriptor filteredDescriptor = descriptor.only(Input.class).group(
            BindableDescriptor.GroupingMethod.LEVEL);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSubClass("initProcessingInputOutput"),
            keyFromSuperClass("processingInput"),
            keyFromSuperClass("processingInputOutput"));

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, AttributeLevel.BASIC).keySet())
            .containsOnly(keyFromSubClass("initProcessingInput"),
                keyFromSuperClass("initInput"), keyFromReferenceClass("initInput"));

        Assertions.assertThat(getDescriptorsFromGroup(filteredDescriptor, AttributeLevel.MEDIUM))
            .isNull();

        Assertions.assertThat(
            getDescriptorsFromGroup(filteredDescriptor, AttributeLevel.ADVANCED).keySet())
            .containsOnly(keyFromSuperClass("initInputOutput"),
                keyFromReferenceClass("processingInput"));
    }

    @Test
    public void testAllByGroup()
    {
        final BindableDescriptor filteredDescriptor = descriptor
            .group(BindableDescriptor.GroupingMethod.GROUP);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).containsOnly(
            keyFromSuperClass("initInput"), keyFromSuperClass("initOutput"),
            keyFromSuperClass("initInputOutput"), keyFromReferenceClass("initInput"),
            keyFromReferenceClass("initOutput"));

        Assertions.assertThat(getDescriptorsFromGroup(filteredDescriptor, "Group A").keySet())
            .containsOnly(keyFromSubClass("initProcessingInputOutput"),
                keyFromReferenceClass("processingOutput"));

        Assertions.assertThat(getDescriptorsFromGroup(filteredDescriptor, "Group B").keySet())
            .containsOnly(keyFromSubClass("initProcessingOutput"),
                keyFromSubClass("initProcessingInput"),
                keyFromSuperClass("processingInputOutput"),
                keyFromReferenceClass("processingInput"));

        Assertions.assertThat(getDescriptorsFromGroup(filteredDescriptor, "Group C").keySet())
            .containsOnly(keyFromSuperClass("processingInput"),
                keyFromSuperClass("processingOutput"));
    }

    @Test
    public void testProcessingByGroup()
    {
        final BindableDescriptor filteredDescriptor = descriptor.only(
            TestProcessing.class).group(BindableDescriptor.GroupingMethod.GROUP);

        Assertions.assertThat(filteredDescriptor.attributeDescriptors.keySet()).isEmpty();

        Assertions.assertThat(getDescriptorsFromGroup(filteredDescriptor, "Group A").keySet())
            .containsOnly(keyFromSubClass("initProcessingInputOutput"),
                keyFromReferenceClass("processingOutput"));

        Assertions.assertThat(getDescriptorsFromGroup(filteredDescriptor, "Group B").keySet())
            .containsOnly(keyFromSubClass("initProcessingOutput"),
                keyFromSubClass("initProcessingInput"),
                keyFromSuperClass("processingInputOutput"),
                keyFromReferenceClass("processingInput"));

        Assertions.assertThat(getDescriptorsFromGroup(filteredDescriptor, "Group C").keySet())
            .containsOnly(keyFromSuperClass("processingInput"),
                keyFromSuperClass("processingOutput"));
    }

    private <T> Map<String, AttributeDescriptor> getDescriptorsFromGroup(
        BindableDescriptor filteredDescriptor, T groupKey)
    {
        return filteredDescriptor.attributeGroups.get(groupKey);
    }

    private String keyFromSuperClass(String fieldName)
    {
        return AttributeUtils.getKey(FilteringSuperClass.class, fieldName);
    }

    private String keyFromSubClass(String fieldName)
    {
        return AttributeUtils.getKey(FilteringSubClass.class, fieldName);
    }

    private String keyFromReferenceClass(String fieldName)
    {
        return AttributeUtils.getKey(FilteringReferenceClass.class, fieldName);
    }
}
