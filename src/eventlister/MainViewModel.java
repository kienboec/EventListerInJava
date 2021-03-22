package eventlister;

import eventlister.argumentHandling.ArgumentHandler;
import eventlister.communicationHandling.CommunicationHandler;
import eventlister.contentInterpretation.ContentInterpreter;
import eventlister.filterHandling.FilterHandler;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainViewModel implements Initializable {
    private final StringProperty _filterText = new SimpleStringProperty("");

    public StringProperty filterTextProperty() {
        return this._filterText;
    }

    private ObservableList<String> _filteredData = FXCollections.observableArrayList();

    public ObservableList<String> getFilteredData(){
        return _filteredData;
    }

    // this dependency is based on an interface with its implementation defined as @Component
    @Autowired
    private CommunicationHandler _comHandler;

    @Autowired
    private ContentInterpreter _interpreter;

    @Autowired
    private FilterHandler _filter;

    @Autowired
    private ArgumentHandler _argumentHandler;

    public void filter(){
        var data = _comHandler.getContent();
        var interpretedData = _interpreter.Interpret(data);
        var filteredData = _filter.Filter(interpretedData, _filterText.get());
        _filteredData.clear();
        for (String filteredDataItem:filteredData) {
            _filteredData.add(filteredDataItem);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        _filterText.set(String.join(", ",_argumentHandler.getFilterCriterias()));
    }
}
