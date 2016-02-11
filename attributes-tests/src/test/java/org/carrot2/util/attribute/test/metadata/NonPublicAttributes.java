package org.carrot2.util.attribute.test.metadata;

import org.carrot2.util.attribute.Attribute;
import org.carrot2.util.attribute.Bindable;

@Bindable 
public class NonPublicAttributes {
  @Attribute
  int packageScope;
  
  @Attribute
  private int privateScope;

  @Attribute
  protected int protectedScope;  
}