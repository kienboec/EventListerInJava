package eventlister;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * By adding the @Component, Spring is able to detect the class and register it.
 */
@Component
public class Controller implements Initializable {

    /**
     * This is a dependency on the MainViewModel.
     *
     * Spring will automatically assign ("inject") the registered MainViewModel instance to the @Autowired element when
     * instantiating the Controller. In our case this will be the single MainViewModel.
     *
     * Note that in this class there is no other way to provide this dependency (e.g. via a constructor)! As such this
     * class can only be properly instantiated and configured via Spring.
     */
    @Autowired
    private MainViewModel _mainViewModel;

    // FXML references
    public TextField FilterTextField;
    public ListView EventsListView;

    // Action
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
