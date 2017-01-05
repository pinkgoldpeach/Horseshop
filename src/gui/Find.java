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
public class Find implements GUI {

    private static Logger log = LogManager.getLogger(Find.class);
    private static Stage primaryStage;

    public Find(Stage ps) {
        primaryStage = ps;
    }

    @Override
    public void start()  {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxmls/find.fxml"));
        } catch (IOException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        Scene create = new Scene(parent);
        primaryStage = primaryStage;
        primaryStage.setTitle("Find a pony or everything!");
        primaryStage.setScene(create);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
