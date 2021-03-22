package eventlister;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class Controller implements Initializable {

    @Autowired
    private MainViewModel _mainViewModel;

    public Controller()
    {

    }

    public TextField FilterTextField;
    public ListView EventsListView;

    public void filter(ActionEvent actionEvent) {
        _mainViewModel.filter();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        _mainViewModel.initialize(url, resourceBundle);

        FilterTextField.textProperty().bindBidirectional(_mainViewModel.filterTextProperty());
        EventsListView.setItems(_mainViewModel.getFilteredData());
    }
}
