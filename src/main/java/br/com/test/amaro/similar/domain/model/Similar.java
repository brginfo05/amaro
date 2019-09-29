package br.com.test.amaro.similar.domain.model;

import java.math.BigDecimal;

public class Similar {

  private long productId;
  private String productName;
  private BigDecimal similarity;

  public Similar(long productId, String productName, BigDecimal similarity) {
    this.productId = productId;
    this.productName = productName;
    this.similarity = similarity;
  }

  public long getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public BigDecimal getSimilarity() {
    return similarity;
  }
}
