package br.com.test.amaro.similar.domain.service;

import br.com.test.amaro.similar.domain.usecase.ProductSimilarityCalculator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

/**
 * @see ProductSimilarityCalculator
 */
@Service
public class SimilarityCalculator {

  /**
   * Calculate the similarity between two vectors
   * @return the similarity between the two vectors
   */
  public BigDecimal calculate(int[] vector1, int[] vector2) {
    double similarity = 1 / (1 + calculateDistance(vector1, vector2));

    return BigDecimal.valueOf(similarity).setScale(2, RoundingMode.HALF_UP);
  }

  private double calculateDistance(int[] vector1, int[] vector2) {
    validateLength(vector1, vector2);

    double sum =
        IntStream
            .range(0, vector1.length)
            .mapToDouble(i -> Math.pow(vector1[i] - vector2[i], 2))
            .reduce(0, Double::sum);

    return Math.sqrt(sum);
  }

  private void validateLength(int[] vector1, int[] vector2) {
    int v1Length = vector1.length;
    int v2Length = vector2.length;

    Validate.isTrue(v1Length == v2Length,
        String.format("The vectors have different length: %s and %s", v1Length, v2Length));
  }
}
