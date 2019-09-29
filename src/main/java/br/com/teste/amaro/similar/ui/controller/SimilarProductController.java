package br.com.teste.amaro.similar.ui.controller;

import br.com.teste.amaro.similar.domain.model.Product;
import br.com.teste.amaro.similar.domain.model.Similar;
import br.com.teste.amaro.similar.domain.usecase.ProductSimilarityCalculator;
import br.com.teste.amaro.similar.ui.model.SearchViewModel;
import br.com.teste.amaro.similar.ui.model.SimilarViewModel;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api to calculate similarity between products
 * @see br.com.teste.amaro.similar.domain.service.SimilarityCalculator
 */
@RestController
@RequestMapping("similar-product")
public class SimilarProductController {

  private ProductSimilarityCalculator productSimilarityCalculator;

  @Autowired
  public SimilarProductController(ProductSimilarityCalculator productSimilarityCalculator) {
    this.productSimilarityCalculator = productSimilarityCalculator;
  }

  @PostMapping("/")
  public List<SimilarViewModel> similar(
      @Valid SearchViewModel searchViewModel) {

    Pair<Product, List<Product>> products = searchViewModel.toModel();

    List<Similar> similarProducts =
        productSimilarityCalculator.calculate(products.getLeft(), products.getRight());

    return similarProducts
        .stream()
        .map(SimilarViewModel::new)
        .collect(Collectors.toList());
  }
}
