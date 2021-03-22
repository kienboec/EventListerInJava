package eventlister.filterHandling;

import java.util.List;

public interface FilterHandler {
    List<String> Filter(List<String> content, String filter);
}
