package br.com.test.amaro.vector.domain.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TagTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void requireAName() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("Name is required");

    new Tag(null, 1);
  }

  @Test
  public void requireAPositiveIndex() {
    expectedException.expect(IllegalArgumentException.class);
    expectedException.expectMessage("Index must be zero or positive");

    new Tag("teste", -1);
  }
}
