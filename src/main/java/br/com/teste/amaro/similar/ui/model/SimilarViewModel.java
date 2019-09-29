package br.com.teste.amaro.similar.ui.model;

import br.com.teste.amaro.similar.domain.model.Similar;
import java.math.BigDecimal;

public class SimilarViewModel {

  private Similar similar;

  public SimilarViewModel(Similar similar) {
    this.similar = similar;
  }

  public long getId() {
    return similar.getProductId();
  }

  public String getName() {
    return similar.getProductName();
  }

  public BigDecimal getSimilarity() {
    return similar.getSimilarity();
  }
}
