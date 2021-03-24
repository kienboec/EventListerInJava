package eventlister;

import eventlister.argumentHandling.ArgumentHandler;
import eventlister.communicationHandling.CommunicationHandler;
import eventlister.contentInterpretation.ContentInterpreter;
import eventlister.filterHandling.FilterHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * By adding the @Component, Spring is able to detect the class and register it.
 */
@Component
public class MainViewModel implements Initializable {
    private final StringProperty _filterText = new SimpleStringProperty("");
    private final ObservableList<String> _filteredData = FXCollections.observableArrayList();

    /**
     * These are the dependencies. They are based on interfaces with their implementations defined as @Components
     *
     * Spring will automatically assign ("inject") the registered instances to @Autowired elements when instantiating
     * the MainViewModel. In our case this will be the single CommandLineArgumentHandler, HTTPOutputInterpreter,
     * CsvBasedFilter, and CommandLineArgumentHandler.
     *
     * Note that in this class there is no other way to provide these dependencies (e.g. via a constructor)! As such it
     * can only be properly instantiated and configured via Spring.
     */
    @Autowired
    private CommunicationHandler _comHandler;
    @Autowired
    private ContentInterpreter _interpreter;
    @Autowired
    private FilterHandler _filter;
    @Autowired
    private ArgumentHandler _argumentHandler;

    public StringProperty filterTextProperty() {
        return this._filterText;
    }

    public ObservableList<String> getFilteredData() {
        return _filteredData;
    }

    public void filter() {
        var data = _comHandler.getContent();
        var interpretedData = _interpreter.Interpret(data);
        var filteredData = _filter.Filter(interpretedData, _filterText.get());
        _filteredData.clear();
        for (String filteredDataItem : filteredData) {
            _filteredData.add(filteredDataItem);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        _filterText.set(String.join(", ", _argumentHandler.getFilterCriterias()));
    }
}
