package br.com.test.amaro.vector.domain.usecase;

import br.com.test.amaro.vector.domain.service.TagVectorCreator;
import br.com.test.amaro.vector.domain.repository.Tags;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>Use case that creates a tag vector
 * <p>A tag vector is an array where each position corresponds to
 * a characteristic and has a value equal to 1 if the product has the characteristic, otherwise the value is
 * equal to zero.</p>
 *
 * <p><strong>Ex:</strong> If the product has only the characteristics "neutral" (index 0), "veludo" (index 1) and
 * "balada" (index 9), its tag vector will be:
 * (1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0)
 */
@Service
public class ProductVectorCreator {

  private TagVectorCreator tagVectorCreator;
  private Tags tags;

  @Autowired
  public ProductVectorCreator(TagVectorCreator tagVectorCreator, Tags tags) {
    this.tagVectorCreator = tagVectorCreator;
    this.tags = tags;
  }

  /**
   * Creates a tag vector
   * @param tagNames valid names of product tags
   * @return a vector containing ones and zeros
   */
  public int[] create(Collection<String> tagNames) {
    return tagVectorCreator.create(tags.count(), tags.list(tagNames));
  }
}
