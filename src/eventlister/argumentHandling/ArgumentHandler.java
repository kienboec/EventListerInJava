package eventlister.argumentHandling;

import java.util.List;

public interface ArgumentHandler {
    List<String> getFilterCriterias();

    String getDataSourceAddress();
}
