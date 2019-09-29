package br.com.teste.amaro.vector.domain.usecase;

import br.com.teste.amaro.vector.domain.service.TagVectorCreator;
import br.com.teste.amaro.vector.domain.repository.Tags;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Use case to create a tag vector
 * @see TagVectorCreator
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
