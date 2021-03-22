package eventlister.argumentHandling;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DefaultArgumentHandler implements ArgumentHandler{
    protected final List<String> _filterCriterias = new ArrayList<String>();
    protected final String _dataSourceAddress = "https://www.technikum-wien.at/newsroom/veranstaltungen/";

    @Override
    public List<String> getFilterCriterias() {
        return this._filterCriterias;
    }

    @Override
    public String getDataSourceAddress() {
        return _dataSourceAddress;
    }
}
