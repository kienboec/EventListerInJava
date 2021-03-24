package eventlister.filterHandling;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * By adding the @Component, Spring is able to detect the class and register it.
 *
 * Filters a list of strings according to a comma-separated list of filter criteria and returns a new list.
 */
@Component
public class CsvBasedFilter implements FilterHandler {
    @Override
    public List<String> Filter(List<String> content, String filter) {
        // fetch the individual, comma-separated filter terms
        var filterItems = Arrays.stream(filter.split(","))
                .map(x -> x.trim())
                .collect(Collectors.toList());

        // only keep those content items, which contain all filter terms
        return content.stream().filter(x ->
        {
            for (String filterItem : filterItems) {
                if (!x.contains(filterItem)) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }
}
