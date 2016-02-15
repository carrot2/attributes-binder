package org.carrot2.util.attribute.test.metadata;

import org.carrot2.util.attribute.Attribute;
import org.carrot2.util.attribute.Bindable;

@Bindable
@SuppressWarnings("unused")
public class MixedFields {
  @Attribute
  public int noJavadoc;
  
  private int privateField;
  private static int privateStaticField;
  int packageField;
  static int packageStaticField;
  public int publicField;
  public static int publicStaticField;

  private Object privateReferenceField = new Object();
  private static Object privateStaticReferenceField = new Object();
}