
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

package org.carrot2.util.attribute.test.filtering;

import org.carrot2.util.attribute.Attribute;
import org.carrot2.util.attribute.AttributeLevel;
import org.carrot2.util.attribute.Bindable;
import org.carrot2.util.attribute.Group;
import org.carrot2.util.attribute.Input;
import org.carrot2.util.attribute.Level;
import org.carrot2.util.attribute.Output;
import org.carrot2.util.attribute.TestInit;
import org.carrot2.util.attribute.TestProcessing;

/**
 *
 */
@Bindable
public class FilteringSubClass extends FilteringSuperClass
{
    @TestInit
    @TestProcessing
    @Input
    @Attribute
    @Level(AttributeLevel.BASIC)
    @Group("Group B")
    public int initProcessingInput = 10;

    @TestInit
    @TestProcessing
    @Output
    @Attribute
    @Level(AttributeLevel.MEDIUM)    
    @Group("Group B")
    public int initProcessingOutput = 10;

    @TestInit
    @TestProcessing
    @Input
    @Output
    @Attribute
    @Group("Group A")
    public int initProcessingInputOutput = 10;
}
