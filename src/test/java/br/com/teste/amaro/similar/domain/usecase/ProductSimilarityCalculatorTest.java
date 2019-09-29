package br.com.teste.amaro.similar.domain.usecase;

import static org.junit.Assert.assertEquals;

import br.com.teste.amaro.similar.domain.model.Product;
import br.com.teste.amaro.similar.domain.model.Similar;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductSimilarityCalculatorTest {

  @Autowired
  private ProductSimilarityCalculator productSimilarityCalculator;

  @Test
  public void calculateTheSimilarity() {
    // Given

    // The product to be compared
    Product product = new Product(1, "teste", new int[]{0, 1, 0, 1});

    // The products that are going to have the similarity calculated
    Collection<Product> products = Arrays.asList(
        new Product(2, "teste2", new int[]{1, 0, 0, 1}),
        product,
        new Product(3, "teste3", new int[]{0, 1, 0, 1})
    );

    // When
    // Calculate de similarity
    List<Similar> similars = productSimilarityCalculator.calculate(product, products);

    // Then
    // It should calculate the similarities of the products
    assertEquals(2, similars.size());
    assertSimilar(3, "1.00", similars.get(0));
    assertSimilar(2, "0.41", similars.get(1));
  }

  public void assertSimilar(long id, String similarity, Similar similar) {
    assertEquals(id, similar.getProductId());
    assertEquals(new BigDecimal(similarity), similar.getSimilarity());
  }
}
