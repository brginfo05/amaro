package br.com.teste.amaro.similar.ui.model;

import br.com.teste.amaro.similar.domain.model.Product;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.tuple.Pair;

public class SearchViewModel {

  @NotNull
  private Long id;
  @Valid
  @NotEmpty
  private List<ProductViewModel> products;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<ProductViewModel> getProducts() {
    return products;
  }

  public void setProducts(List<ProductViewModel> products) {
    this.products = products;
  }

  public Pair<Product, List<Product>> toModel() {
    AtomicReference<Product> vectorReference = new AtomicReference<>();

    List<Product> products =
        this.products
            .stream()
            .map(ProductViewModel::toModel)
            .peek(product -> {
              if (product.getId() == id) {
                vectorReference.set(product);
              }
            }).collect(Collectors.toList());

    return Pair.of(vectorReference.get(), products);
  }
}
