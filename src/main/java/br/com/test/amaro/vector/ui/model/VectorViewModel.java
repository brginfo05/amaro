package br.com.test.amaro.vector.ui.model;

import java.util.List;

public class VectorViewModel {

  private Long id;
  private String name;
  private List<String> tags;
  private int[] tagsVector;

  public VectorViewModel(Long id, String name, List<String> tags, int[] tagsVector) {
    this.id = id;
    this.name = name;
    this.tags = tags;
    this.tagsVector = tagsVector;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<String> getTags() {
    return tags;
  }

  public int[] getTagsVector() {
    return tagsVector;
  }
}
