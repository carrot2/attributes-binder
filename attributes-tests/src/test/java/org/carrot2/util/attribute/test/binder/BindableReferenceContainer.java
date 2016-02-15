
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
import org.carrot2.util.attribute.Bindable;
import org.carrot2.util.attribute.Input;
import org.carrot2.util.attribute.TestInit;
import org.carrot2.util.attribute.constraint.ImplementingClasses;

/**
 *
 */
@Bindable
public class BindableReferenceContainer
{
    /**
     * Test Bindable.
     */
    @TestInit
    @Input
    @Attribute
    @ImplementingClasses(classes =
    {
        BindableReferenceImpl1.class, BindableReferenceImpl2.class
    })
    public IBindableReference bindableAttribute;

    /**
     * This is just a field, not a parameter, but if it's implementation is bindable, its
     * descriptors need to be considered as well.
     */
    public IBindableReference bindableField = new BindableReferenceImpl1();
}
