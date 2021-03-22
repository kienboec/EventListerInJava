package eventlister;

import eventlister.argumentHandling.CommandLineArgumentStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    private static AnnotationConfigApplicationContext _context;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        loader.setControllerFactory(Controller -> _context.getBean(Controller.class));
        Parent root = loader.load();

        primaryStage.setTitle("Events");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        _context = new AnnotationConfigApplicationContext();
        _context.registerBean(CommandLineArgumentStore.class, args);
        _context.register(IoCContainerConfig.class);
        _context.refresh();

        launch(args);
    }
}
