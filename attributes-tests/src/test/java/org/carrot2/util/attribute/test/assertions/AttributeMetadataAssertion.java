
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

package org.carrot2.util.attribute.test.assertions;

import static org.fest.assertions.Assertions.assertThat;

import org.carrot2.util.attribute.AttributeInfo;

/**
 * Assertions on {@link AttributeMetadata}.
 */
public class AttributeMetadataAssertion
{
    private final AttributeInfo actual;

    public AttributeMetadataAssertion(AttributeInfo actual)
    {
        this.actual = actual;
    }

    public AttributeMetadataAssertion isEquivalentTo(AttributeInfo expected)
    {
        new CommonMetadataAssertion(actual).isEquivalentTo(expected);
        assertThat(actual.getLevel()).as("level").isEqualTo(expected.getLevel());
        assertThat(actual.getGroup()).as("group").isEqualTo(expected.getGroup());
        return this;
    }
}
