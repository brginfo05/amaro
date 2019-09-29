package br.com.test.amaro.vector.domain.service;

import br.com.test.amaro.vector.domain.model.Tag;
import br.com.test.amaro.vector.domain.usecase.ProductVectorCreator;
import java.util.Collection;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

/**
 * @see ProductVectorCreator
 */
@Service
public class TagVectorCreator {

  /**
   * Creates a tag vector
   * @param length the length of the vector
   * @param tags the tags to create the vector
   * @return a vector containing ones and zeros
   */
  public int[] create(int length, Collection<Tag> tags) {
    int[] vector = new int[length];

    for (Tag tag : tags) {
      validateIndexBoundary(length, tag);
      vector[tag.getIndex()] = 1;
    }

    return vector;
  }

  private void validateIndexBoundary(int length, Tag tag) {
    Validate.isTrue(tag.getIndex() < length, "Tag index out of bounds: " + tag.getIndex());
  }
}
