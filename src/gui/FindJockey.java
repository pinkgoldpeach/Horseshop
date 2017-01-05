package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Rebeka on 2015.03.30..
 */
public class FindJockey implements GUI {

    private static Logger log = LogManager.getLogger(FindJockey.class);
    private static Stage primaryStage;

    public FindJockey(Stage ps) {
        primaryStage = ps;
    }

    @Override
    public void start()  {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxmls/findJockey.fxml"));
        } catch (IOException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        Scene create = new Scene(parent);
        primaryStage.setTitle("Find your new best friend!");
        primaryStage.setScene(create);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
