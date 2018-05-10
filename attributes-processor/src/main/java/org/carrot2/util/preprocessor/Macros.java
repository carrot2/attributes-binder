package org.carrot2.util.preprocessor;

import org.apache.commons.lang3.StringEscapeUtils;

public final class Macros {
  public String defaultIfEmpty(String val, String defaultValue) {
    if (val == null || val.trim().isEmpty()) {
      return defaultValue;
    } else {
      return val;
    }
  }

  public String java(String in) {
    return StringEscapeUtils.escapeJava(in);
  }
}
