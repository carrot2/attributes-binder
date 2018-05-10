package org.carrot2.util.preprocessor;

import javax.lang.model.element.TypeElement;

// So that velocity doesn't attempt to access properties of non-accessible implementation classes
public class TypeElementAccessWrapper {
  private final TypeElement type;

  public TypeElementAccessWrapper(TypeElement type) {
    this.type = type;
  }

  public String getQualifiedName() {
    return type.getQualifiedName().toString();
  }
  
  public String getSimpleName() {
    return type.getSimpleName().toString();
  }  
}
