package org.carrot2.util.attribute.test.metadata;

import org.carrot2.util.attribute.Attribute;
import org.carrot2.util.attribute.Bindable;

@Bindable 
@SuppressWarnings("unused")
public class NonPublicBindables {
  @Attribute
  public int noJavadoc;
  
  private Object privateReferenceField = new OtherBindable();
  private static Object privateStaticReferenceField = new OtherBindable();
}