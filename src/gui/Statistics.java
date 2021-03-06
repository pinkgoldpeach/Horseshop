package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Rebeka on 2015.03.20..
 */
public class Statistics implements GUI {

    private static Logger log = LogManager.getLogger(Statistics.class);
    private static Stage primaryStage;

    public Statistics(Stage ps) {
        primaryStage = ps;
    }

    public void start(){
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxmls/statistics.fxml"));
        } catch (IOException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        Scene mainMenu = new Scene(parent);
        primaryStage.setTitle("Statistics of the happy ponies");
        primaryStage.setScene(mainMenu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
