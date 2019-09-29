package br.com.test.amaro.vector.domain.service;

import static org.junit.Assert.assertThat;

import br.com.test.amaro.vector.domain.model.Tag;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TagVectorCreatorTest {

  private TagVectorCreator tagVectorCreator = new TagVectorCreator();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void createAVectorWithTags() {
    // Given

    // The expected vector
    Integer[] expectedVector = new Integer[]{1, 0, 1, 0, 0};

    // The tags that going to be used to generate the vector
    Collection<Tag> tags = Arrays.asList(
        new Tag("neutro", 0),
        new Tag("couro", 2));

    // When
    // Create the vector
    int[] vector = tagVectorCreator.create(5, tags);

    // Then
    // The created vector should match the expected one
    assertThat(
        ArrayUtils.toObject(vector),
        Matchers.arrayContaining(expectedVector));
  }

  @Test
  public void validateWhetherTagIndexIsInsideBoundary() {
    // Given
    // The tag that has the index out of bounds
    Tag tag = new Tag("neutro", 6);
    Collection<Tag> tags = Collections.singletonList(tag);

    // Then
    // It should throw an exception
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Tag index out of bounds: " + tag.getIndex());

    // When
    // Create the vector
    tagVectorCreator.create(5, tags);
  }
}
