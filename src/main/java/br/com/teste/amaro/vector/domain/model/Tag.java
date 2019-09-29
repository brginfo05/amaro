package br.com.teste.amaro.vector.domain.model;

import org.apache.commons.lang3.Validate;

public class Tag {

  private String name;
  private int index;

  public Tag(String name, int index) {
    Validate.notEmpty(name, "Name is required");
    Validate.isTrue(index >= 0, "Index must be zero or positive");

    this.name = name;
    this.index = index;
  }

  public String getName() {
    return name;
  }

  public int getIndex() {
    return index;
  }
}
