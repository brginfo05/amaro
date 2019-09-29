package br.com.test.amaro.similar.ui.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import br.com.test.amaro.similar.domain.model.Product;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.hamcrest.Matchers;
import org.junit.Test;

public class SearchViewModelTest {

  @Test
  public void createBaseProductModel() {
    // Given

    // The expected base product view model
    long expectedBaseProductId = 1L;
    ProductViewModel productViewModel = createProductViewModel(expectedBaseProductId);

    // The product view models
    List<ProductViewModel> products = Arrays.asList(
        createProductViewModel(2L),
        productViewModel,
        createProductViewModel(3L));

    // The search parameters
    SearchViewModel searchViewModel = createSearchViewModel(expectedBaseProductId, products);

    // When
    // Convert to model
    Pair<Product, List<Product>> model = searchViewModel.toModel();

    // Then
    // The values of the base product should match the view model
    Product baseProduct = model.getLeft();
    assertModel(productViewModel, baseProduct);
  }

  @Test
  public void createAProductModel() {
    // Given

    // The product view model
    long productId = 1L;
    ProductViewModel productViewModel =
        createProductViewModel(productId, "teste1", new int[]{0});

    List<ProductViewModel> products =
        Collections.singletonList(productViewModel);

    // The search parameters
    SearchViewModel searchViewModel = createSearchViewModel(productId, products);

    // When
    // Convert to model
    Pair<Product, List<Product>> model = searchViewModel.toModel();

    // Then
    // The values of the created model should match the ones of the view model
    Product product = model.getRight().get(0);

    assertModel(productViewModel, product);
  }

  private void assertModel(ProductViewModel productViewModel, Product product) {
    assertEquals(productViewModel.getId().longValue(), product.getId());
    assertEquals(productViewModel.getName(), product.getName());
    assertThat(
        ArrayUtils.toObject(product.getTagsVector()),
        Matchers.arrayContaining(ArrayUtils.toObject(productViewModel.getTagsVector())));
  }

  private SearchViewModel createSearchViewModel(
      long expectedBaseProductId,
      List<ProductViewModel> products) {

    SearchViewModel searchViewModel = new SearchViewModel();
    searchViewModel.setId(expectedBaseProductId);
    searchViewModel.setProducts(products);

    return searchViewModel;
  }

  private ProductViewModel createProductViewModel(Long id) {
    return createProductViewModel(id, "teste", new int[]{0});
  }

  private ProductViewModel createProductViewModel(Long id, String name, int[] tagsVector) {
    ProductViewModel productViewModel = new ProductViewModel();
    productViewModel.setId(id);
    productViewModel.setName(name);
    productViewModel.setTagsVector(tagsVector);

    return productViewModel;
  }
}
