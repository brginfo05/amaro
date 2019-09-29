package br.com.teste.amaro.similar.ui.model;

import br.com.teste.amaro.similar.domain.model.Product;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductViewModel {

  @NotNull
  private Long id;
  @NotNull
  private String name;
  @NotEmpty
  private int[] tagsVector;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int[] getTagsVector() {
    return tagsVector;
  }

  public void setTagsVector(int[] tagsVector) {
    this.tagsVector = tagsVector;
  }

  public Product toModel() {
    return new Product(id, name, tagsVector);
  }
}
