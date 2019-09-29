package br.com.teste.amaro.similar.domain.usecase;

import br.com.teste.amaro.similar.domain.model.Product;
import br.com.teste.amaro.similar.domain.model.Similar;
import br.com.teste.amaro.similar.domain.service.SimilarityCalculator;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Use case that calculates similarity between products
 * @see br.com.teste.amaro.similar.domain.service.SimilarityCalculator
 */
@Service
public class ProductSimilarityCalculator {

  private static final long LIMIT = 3;
  private SimilarityCalculator similarityCalculator;

  @Autowired
  public ProductSimilarityCalculator(SimilarityCalculator similarityCalculator) {
    this.similarityCalculator = similarityCalculator;
  }

  /**
   * For the supplied product calculates the similarity to all products
   * @param product the base product to calculate the similarity
   * @param products the products to have their similarity calculated
   * @return the most similar products
   */
  public List<Similar> calculate(Product product, Collection<Product> products) {
    return products
        .stream()
        .filter(p -> p.getId() != product.getId())
        .map(v -> calculateSimilar(product, v))
        .sorted(Comparator.comparing(Similar::getSimilarity).reversed())
        .limit(LIMIT)
        .collect(Collectors.toList());
  }

  private Similar calculateSimilar(Product product1, Product product2) {
    return new Similar(
        product2.getId(),
        product2.getName(),
        similarityCalculator.calculate(product1.getTagsVector(), product2.getTagsVector()));
  }
}
