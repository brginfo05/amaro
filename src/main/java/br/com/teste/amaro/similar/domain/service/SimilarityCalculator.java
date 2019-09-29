package br.com.teste.amaro.similar.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

/**
 * <p>The similarity between two products can be calculated taking into account the Euclidean
 * distance
 * between their tag vectors, and the similarity is inversely proportional to the distance. That is,
 * we can define the similarity S between two products p1 and p2, which have vectors of tags v1 and
 * v2 of dimension N, such as:
 * <p>S = 1/(1 + D), where D = sqrt((v1[0] - v2[0])^2 + (v1[1] - v2[1])^2 + .. + (v1[N-1] -
 * v2[N-1])^2) is the distance between the vectors v1 and v2
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
