package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Rebeka on 2015.04.02..
 */
public class ResultSim implements GUI {

    private static Logger log = LogManager.getLogger(ResultSim.class);
    Stage primaryStage;

    public ResultSim(Stage ps) {
        primaryStage = ps;
    }

    public void start()  {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxmls/simResult.fxml"));
        } catch (IOException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        Scene mainMenu = new Scene(parent);
        primaryStage.setTitle("The best scores:D");
        primaryStage.setScene(mainMenu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
