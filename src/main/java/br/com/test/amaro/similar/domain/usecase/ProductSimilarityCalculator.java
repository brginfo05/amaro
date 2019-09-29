package br.com.test.amaro.similar.domain.usecase;

import br.com.test.amaro.similar.domain.model.Product;
import br.com.test.amaro.similar.domain.model.Similar;
import br.com.test.amaro.similar.domain.service.SimilarityCalculator;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Use case that calculates similarity between products
 * <p>The similarity between two products can be calculated taking into account the Euclidean
 * distance
 * between their tag vectors, and the similarity is inversely proportional to the distance. That is,
 * we can define the similarity S between two products p1 and p2, which have vectors of tags v1 and
 * v2 of dimension N, such as:
 * <p>S = 1/(1 + D), where D = sqrt((v1[0] - v2[0])^2 + (v1[1] - v2[1])^2 + .. + (v1[N-1] -
 * v2[N-1])^2) is the distance between the vectors v1 and v2
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
