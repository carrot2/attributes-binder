
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
public class AttributeTitles
{
    @TestInit
    @Input
    @Attribute
    @Label("label")
    public int noTitle;

    /**
     * . Description follows.
     */
    @TestInit
    @Input
    @Attribute
    @Label("label")
    public int emptyTitle;

    /**
     * Title with period.
     */
    @TestInit
    @Input
    @Attribute
    public int titleWithPeriod;

    /**
     * Title    with
     *
     *
     * extra    space.
     */
    @TestInit
    @Input
    @Attribute
    public int titleWithExtraSpace;

    /**
     * Title without period
     */
    @TestInit
    @Input
    @Attribute
    public int titleWithoutPeriod;

    /**
     * Title with exclamation mark! and something more. Description.
     */
    @TestInit
    @Input
    @Attribute
    public int titleWithExclamationMark;

    /**
     * Title with extra periods (e.g. www.carrot2.org). Description.
     */
    @TestInit
    @Input
    @Attribute
    public int titleWithExtraPeriods;

    /**
     * Title with link to {@link AttributeTitles#titleWithLink}. Description.
     */
    @TestInit
    @Input
    @Attribute
    public int titleWithLink;

    /**
     * Title. Description with {@link #descriptionWithLinks} and {@link String} links and <b>markup</b>.
     */
    @TestInit
    @Input
    @Attribute
    public int descriptionWithLinks;
    
    /**
     * Title. Description with &#160;.
     */
    @TestInit
    @Input
    @Attribute
    public int descriptionWithNumericEntities;
    
    /**
     * Title with description. Description follows.
     */
    @TestInit
    @Input
    @Attribute
    public int titleWithDescription;

    /**
     * Title with label.
     */
    @TestInit
    @Input
    @Attribute
    @Label("label")
    public int titleWithLabel;
}
