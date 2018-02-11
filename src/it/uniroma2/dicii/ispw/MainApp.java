package it.uniroma2.dicii.ispw;

import it.uniroma2.dicii.ispw.thread.RunnableThread;
import it.uniroma2.dicii.ispw.thread.SimpleThread;
import it.uniroma2.dicii.ispw.view.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Elephant");
        primaryStage.getIcons().add(new Image("file:resources/images/elephant-128.png"));

        // onCreateView with root layout

        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("view/RootView.fxml"));
            BorderPane rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            // call SceneManager
            SceneManager sm = SceneManager.getSingletonInstance();
            sm.setRootLayout(rootLayout);
            sm.setRootController(loader.getController());
            sm.init();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {

        /*System.out.println("Start thread, see MainApp");

        SimpleThread thread = new SimpleThread();
        thread.start();

        Thread t = new Thread(new RunnableThread());
        t.start();*/

        launch(args);
    }
}
