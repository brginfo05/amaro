package br.com.test.amaro.similar.domain.model;

import java.util.Arrays;

public class Product {

  private long id;
  private String name;
  private int[] tagsVector;

  public Product(long id, String name, int[] tagsVector) {
    this.id = id;
    this.name = name;
    this.tagsVector = tagsVector;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int[] getTagsVector() {
    return Arrays.copyOf(tagsVector, tagsVector.length);
  }
}
