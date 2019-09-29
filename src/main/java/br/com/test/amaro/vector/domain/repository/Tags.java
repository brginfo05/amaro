package br.com.test.amaro.vector.domain.repository;

import br.com.test.amaro.vector.domain.model.Tag;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;

@Repository
public class Tags {

  private static Map<String, Tag> tagsSource;

  static {
    // No database was used for this test only static data
    tagsSource = Stream
        .of(new Tag("neutro", 0),
            new Tag("veludo", 1),
            new Tag("couro", 2),
            new Tag("basics", 3),
            new Tag("festa", 4),
            new Tag("workwear", 5),
            new Tag("inverno", 6),
            new Tag("boho", 7),
            new Tag("estampas", 8),
            new Tag("balada", 9),
            new Tag("colorido", 10),
            new Tag("casual", 11),
            new Tag("liso", 12),
            new Tag("moderno", 13),
            new Tag("passeio", 14),
            new Tag("metal", 15),
            new Tag("viagem", 16),
            new Tag("delicado", 17),
            new Tag("descolado", 18),
            new Tag("elastano", 19))
        .collect(
            Collectors.toMap(
                Tag::getName,
                Function.identity()
            ));
  }

  public int count() {
    return tagsSource.values().size();
  }

  public List<Tag> list(Collection<String> names) {
    return names
        .stream()
        .map(tagsSource::get)
        .collect(Collectors.toList());
  }
}
