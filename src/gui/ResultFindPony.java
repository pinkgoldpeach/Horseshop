package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Rebeka on 2015.03.23..
 */
public class ResultFindPony implements GUI {

    private static Logger log = LogManager.getLogger(ResultFindPony.class);
    Stage primaryStage;


    public ResultFindPony(Stage ps) {
    primaryStage = ps;
    }

    public void start() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxmls/resultFindPony.fxml"));
        } catch (IOException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        Scene create = new Scene(parent);
        primaryStage.setTitle("Results");
        primaryStage.setScene(create);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
