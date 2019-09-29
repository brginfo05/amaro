package br.com.test.amaro.similar.ui.model;

import br.com.test.amaro.similar.domain.model.Similar;
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
