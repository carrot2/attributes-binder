
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

package org.carrot2.util.attribute.test.binder;

import org.carrot2.util.attribute.Attribute;
import org.carrot2.util.attribute.AttributeLevel;
import org.carrot2.util.attribute.Bindable;
import org.carrot2.util.attribute.Group;
import org.carrot2.util.attribute.Input;
import org.carrot2.util.attribute.Label;
import org.carrot2.util.attribute.Level;
import org.carrot2.util.attribute.TestInit;
import org.carrot2.util.attribute.TestProcessing;

/**
 *
 */
@Bindable
@SuppressWarnings("unused")
public class SingleClass
{
    /**
     * Init input int attribute.
     */
    @TestInit
    @Input
    @Attribute
    @Level(AttributeLevel.BASIC)
    @Group("Group A")
    @Label("Init Input Int")
    public int initInputInt = 10;

    /**
     * Processing input string attribute. Some description.
     */
    @TestProcessing
    @Input
    @Attribute
    @Level(AttributeLevel.ADVANCED)
    @Group("Group B")
    @Label("Processing Input String")
    public String processingInputString = "test";

    /**
     * This is not an attribute.
     */
    public String notAnAttribute;
}
