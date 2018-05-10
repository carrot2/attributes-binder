
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

package org.carrot2.util.preprocessor;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

/**
 * Information about a nested bindable field. Needs to be public for Velocity.
 */
public class BindableFieldInfo
{
    /**
     * The nested bindable field declaration.
     */
    private final VariableElement field;
    
    /**
     * {@link #field}'s declared {@Bindable} type.
     */
    private final Element element;
    
    private final String descriptorClass;    

    public BindableFieldInfo(VariableElement field, Element element, String descriptorClass)
    {
        this.field = field;
        this.element = element;
        this.descriptorClass = descriptorClass;
    }
    
    public VariableElementAccessWrapper getField()
    {
        return new VariableElementAccessWrapper(field);
    }
    
    public String getFieldElement()
    {
        return element.toString();
    }
    
    public String getDescriptorClass()
    {
        return descriptorClass;
    }
}
