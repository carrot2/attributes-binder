package org.carrot2.util.preprocessor;

import javax.lang.model.element.VariableElement;

public class VariableElementAccessWrapper {
  private final VariableElement element;

  public VariableElementAccessWrapper(VariableElement element) {
    this.element = element;
  }

  public String getSimpleName() {
    return element.getSimpleName().toString();
  }

}
