package br.com.teste.amaro.similar.domain.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;

public class SimilarityCalculatorTest {

  private SimilarityCalculator similarityCalculator = new SimilarityCalculator();

  @Test
  public void calculateTheSimilarity() {
    // Given

    // The expect similarity
    BigDecimal expectedSimilarity = new BigDecimal("0.50");

    // The vectors to have the similarity calculated
    int[] vector1 = new int[]{0, 1, 0, 1};
    int[] vector2 = new int[]{0, 0, 0, 1};

    // When
    // Calculate the similarity
    BigDecimal similarity = similarityCalculator.calculate(vector1, vector2);

    // Then
    // The calculated similarity should match de expected one
    assertEquals(expectedSimilarity, similarity);
  }
}
