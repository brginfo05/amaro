package br.com.test.amaro.similar.ui.controller;

import br.com.test.amaro.similar.ui.model.SearchViewModel;
import br.com.test.amaro.similar.ui.model.SimilarViewModel;
import br.com.test.amaro.similar.domain.model.Product;
import br.com.test.amaro.similar.domain.model.Similar;
import br.com.test.amaro.similar.domain.usecase.ProductSimilarityCalculator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api to calculate similarity between products
 * @see ProductSimilarityCalculator
 */
@Api(tags = "Similar Products")
@RestController
@RequestMapping("/v1/products/similar")
public class SimilarProductController {

  private ProductSimilarityCalculator productSimilarityCalculator;

  @Autowired
  public SimilarProductController(ProductSimilarityCalculator productSimilarityCalculator) {
    this.productSimilarityCalculator = productSimilarityCalculator;
  }

  @ApiOperation(value = "Calculate the similarity between products")
  @PostMapping
  public List<SimilarViewModel> similar(
      @Valid @RequestBody SearchViewModel searchViewModel) {

    Pair<Product, List<Product>> products = searchViewModel.toModel();

    List<Similar> similarProducts =
        productSimilarityCalculator.calculate(products.getLeft(), products.getRight());

    return similarProducts
        .stream()
        .map(SimilarViewModel::new)
        .collect(Collectors.toList());
  }
}
