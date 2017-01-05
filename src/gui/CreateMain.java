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
public class CreateMain implements GUI {

    private static Logger log = LogManager.getLogger(CreateMain.class);
    public Stage primaryStage;

    public CreateMain(Stage ps) {
      primaryStage = ps;
    }

    @Override
    public void start() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxmls/createMain.fxml"));
        } catch (IOException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        Scene create = new Scene(parent);
        primaryStage.setTitle("Create something lovely!");
        primaryStage.setScene(create);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
