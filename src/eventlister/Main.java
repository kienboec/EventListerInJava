package eventlister;

import eventlister.argumentHandling.CommandLineArgumentStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    /**
     * This is the IoC container. It accepts annotated classes as configuration input.
     */
    private static AnnotationConfigApplicationContext _context;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

        // By default the loader instantiates controller of the FXML (fx:controller) via its default constructor.
        // You can override this behavior with custom instantiation logic by providing your own instantiation
        // implementation using the setControllerFactory method. This implementation receives the requested class as
        // parameter and returns a (hopefully) matching and properly instantiated object.
        //
        // In our case, we use the Spring context to instantiate a previously registered class.
        loader.setControllerFactory(Controller -> _context.getBean(Controller.class));

        Parent root = loader.load();

        primaryStage.setTitle("Events");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        _context = new AnnotationConfigApplicationContext();

        // register the CommandLineArgumentStore class and provide the required (constructor) parameters for
        // instantiation manually
        _context.registerBean(CommandLineArgumentStore.class, args);

        // register all other classes via the IoCContainerConfig via class annotations (see class "implementation")
        _context.register(IoCContainerConfig.class);

        // finish the configuration by (re-)building the context. After that the context is able to instantiate the
        // registered classes
        _context.refresh();

        launch(args);
    }
}
