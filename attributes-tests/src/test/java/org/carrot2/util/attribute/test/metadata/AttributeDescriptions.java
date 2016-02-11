
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

package org.carrot2.util.attribute.test.metadata;

import org.carrot2.util.attribute.Attribute;
import org.carrot2.util.attribute.Bindable;
import org.carrot2.util.attribute.Input;
import org.carrot2.util.attribute.Label;
import org.carrot2.util.attribute.TestInit;

/**
 *
 */
@Bindable
public class AttributeDescriptions
{
    @TestInit
    @Input
    @Attribute
    @Label("label")
    public int noDescriptionNoTitle;

    /**
     * Title.
     */
    @TestInit
    @Input
    @Attribute
    @Label("label")
    public int noDescription;

    /**
     * Title. Single sentence description.
     */
    @TestInit
    @Input
    @Attribute
    public int singleSentenceDescription;

    /**
     * Title. Description sentence 1. Description sentence 2.
     */
    @TestInit
    @Input
    @Attribute
    public int twoSentenceDescription;

    /**
     * Title. Description
     *
     * with     extra
     * space.
     */
    @TestInit
    @Input
    @Attribute
    public int descriptionWithExtraSpace;
}
