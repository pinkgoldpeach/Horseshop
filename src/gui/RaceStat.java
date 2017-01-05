package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Rebeka on 2015.04.06..
 */
public class RaceStat implements GUI {

    private static Logger log = LogManager.getLogger(RaceStat.class);
    Stage primaryStage;

    public RaceStat(Stage ps) {
        primaryStage = ps;
    }

    public void start()  {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxmls/raceStatResult.fxml"));
        } catch (IOException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        Scene mainMenu = new Scene(parent);
        primaryStage.setTitle("Exciting races");
        primaryStage.setScene(mainMenu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
