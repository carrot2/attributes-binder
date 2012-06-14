
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

package org.carrot2.util.attribute.constraint;

import org.junit.Test;

/**
 * Test cases for {@link PassesValueOf} constraint.
 */
public class PassesValueOfConstraintTest extends ConstraintTestBase<PassesValueOf>
{
    public static class ValueOfClass
    {
        public static Object valueOf(String v)
        {
            return Long.valueOf(v);
        }
    }

    static class AnnotationContainer
    {
        @PassesValueOf(valueOfClass = ValueOfClass.class)
        Object field;
    }

    @Override
    Class<?> getAnnotationContainerClass()
    {
        return AnnotationContainer.class;
    }

    @Override
    Class<PassesValueOf> getAnnotationType()
    {
        return PassesValueOf.class;
    }

    @Test
    public void testInvalidType() throws Exception
    {
        assertNotMet("abc");
    }
    
    @Test
    public void testValid() throws Exception
    {
        assertMet("1");
    }    
}
