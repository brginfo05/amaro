package br.com.teste.vector.domain.service;

import br.com.teste.vector.domain.model.Tag;
import java.util.Collection;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

/**
 * <p>A tag vector is an array where each position corresponds to
 * a characteristic and has a value equal to 1 if the product has the characteristic, otherwise the value is
 * equal to zero.</p>
 *
 * <p><strong>Ex:</strong> If the product has only the characteristics "neutral" (index 0), "veludo" (index 1) and
 * "balada" (index 9), its tag vector will be:
 * (1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0)
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
