package br.com.teste.amaro.vector.domain.usecase;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductVectorCreatorTest {

  @Autowired
  private ProductVectorCreator productVectorCreator;

  @Test
  public void createAVector() {
    // Given

    // The expected vector
    Integer[] expectedVector = new Integer[]{0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0};

    // The tags to create the vector
    List<String> tagNames = Arrays.asList("veludo", "festa");

    // When
    // Create de vector
    int[] vector = productVectorCreator.create(tagNames);

    // Then
    // The created vector should match the expected one
    assertThat(
        ArrayUtils.toObject(vector),
        Matchers.arrayContaining(expectedVector));
  }
}
