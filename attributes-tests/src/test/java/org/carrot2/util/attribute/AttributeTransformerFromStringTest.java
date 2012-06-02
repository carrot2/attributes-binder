
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

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;

import org.carrot2.util.attribute.AttributeBinder.AttributeTransformerFromString;
import org.carrot2.util.attribute.constraint.ImplementingClasses;
import org.fest.assertions.Assertions;
import org.junit.Test;

import com.carrotsearch.randomizedtesting.RandomizedTest;

/**
 * Test cases for {@link AttributeTransformerFromString}.
 */
@SuppressWarnings("unused")
public class AttributeTransformerFromStringTest extends RandomizedTest
{
    private Integer integerField;

    private String stringField;

    enum TestEnum
    {
        VALUE1, VALUE2;
    }

    private TestEnum enumField;

    private AttributeTransformerFromStringTest loadableClassField;

    @ImplementingClasses(classes =
    {
        String.class, Integer.class
    }, strict = true)
    private Object stringStrictlyAssignable;

    @ImplementingClasses(classes =
    {
        Integer.class, Double.class
    }, strict = false)
    private Object stringNonStrictlyAssignable;

    @ImplementingClasses(classes =
    {
        Integer.class, Double.class
    }, strict = true)
    private Object stringNotAssignable;

    @Test
    public void testNonStringValue()
    {
        final Integer integer = Integer.valueOf(10);

        Assertions.assertThat(
            AttributeTransformerFromString.INSTANCE.transform(integer, null, null))
            .isSameAs(integer);
    }

    @Test
    public void testStringField() throws Exception
    {
        final String string = "test";
        check("stringField", string, string);
    }

    @Test
    public void testExistingValueOfInteger() throws Exception
    {
        final Integer integer = Integer.valueOf(10);
        check("integerField", integer.toString(), integer);
    }

    @Test
    public void testExistingValueOfEnum() throws Exception
    {
        final TestEnum eenum = TestEnum.VALUE1;
        check("enumField", eenum.name(), eenum);
    }

    @Test
    public void testLoadableClass() throws Exception
    {
        check("loadableClassField", AttributeTransformerFromStringTest.class.getName(),
            AttributeTransformerFromStringTest.class);
    }

    @Test
    public void testNonLoadableClass() throws Exception
    {
        final String value = "x" + AttributeTransformerFromStringTest.class.getName();
        check("loadableClassField", value, value);
    }

    @Test
    public void testStringStrictlyAssignable() throws Exception
    {
        final String string = "test";
        check("stringStrictlyAssignable", string, string);
    }

    @Test
    public void testStringNonStrictlyAssignable() throws Exception
    {
        final String string = "test";
        check("stringNonStrictlyAssignable", string, string);
    }

    @Test
    public void testStringNotAssignable() throws Exception
    {
        final String string = Object.class.getName();
        check("stringNotAssignable", string, Object.class);
    }

    private void check(String fieldName, String stringValue,
        Object expectedTransformedValue) throws Exception
    {
        final Field field = AttributeTransformerFromStringTest.class
            .getDeclaredField(fieldName);
        Assertions.assertThat(
            AttributeTransformerFromString.INSTANCE.transform(stringValue, null, field)).isEqualTo(expectedTransformedValue);
    }
}
