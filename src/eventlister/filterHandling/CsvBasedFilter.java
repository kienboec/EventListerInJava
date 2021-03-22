package eventlister.filterHandling;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvBasedFilter implements FilterHandler {
    @Override
    public List<String> Filter(List<String> content, String filter) {
        var filterItems = Arrays.stream(filter.split(","))
                        .map(x -> x.trim())
                        .collect(Collectors.toList());

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
