package br.com.teste.amaro.vector.ui.controller;

import br.com.teste.amaro.vector.domain.service.TagVectorCreator;
import br.com.teste.amaro.vector.ui.model.ProductViewModel;
import br.com.teste.amaro.vector.ui.model.VectorViewModel;
import br.com.teste.amaro.vector.domain.usecase.ProductVectorCreator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api to create a tag vector
 * @see TagVectorCreator
 */
@Api(tags = "Products Vector")
@RestController
@RequestMapping("/v1/products/vector")
public class ProductVectorController {

  private ProductVectorCreator productVectorCreator;

  @Autowired
  public ProductVectorController(ProductVectorCreator productVectorCreator) {
    this.productVectorCreator = productVectorCreator;
  }

  @ApiOperation(value = "Creates a tag vector")
  @PostMapping
  public List<VectorViewModel> vector(@Valid @RequestBody List<ProductViewModel> products) {
    return products
        .stream()
        .map(this::createVectorViewModel)
        .collect(Collectors.toList());
  }

  private VectorViewModel createVectorViewModel(ProductViewModel product) {
    return new VectorViewModel(
        product.getId(),
        product.getName(),
        product.getTags(),
        createVector(product));
  }

  private int[] createVector(ProductViewModel productVm) {
    return productVectorCreator.create(productVm.getTags());
  }

}
