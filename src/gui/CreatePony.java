package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.PferdEditor;
import service.ServiceException;

import java.io.IOException;

/**
 * Created by Rebeka on 2015.03.20..
 */
public class CreatePony implements GUI{

    private static Logger log = LogManager.getLogger(CreatePony.class);
    private Stage primaryStage;

    public CreatePony(Stage ps) {
        primaryStage = ps;
    }

    @Override
    public void start() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxmls/create.fxml"));
        } catch (IOException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        Scene create = new Scene(parent);
        primaryStage.setTitle("Create a pony!");
        primaryStage.setScene(create);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static boolean createPony(String name, String photo, String speedMinText, String speedMaxText) {
        int speedMin, speedMax;
        if (!speedMinText.isEmpty() && speedMinText.matches("\\d+")) {
            speedMin = Integer.parseInt(speedMinText);
        } else {
            return false;
        }
        if (!speedMaxText.isEmpty() && speedMaxText.matches("\\d+")) {
            speedMax = Integer.parseInt(speedMaxText);
        } else {
            return false;
        }
        if (name.isEmpty()) {
            name = "noName";
        }
        if (photo.isEmpty()) {
            photo = "noPhoto";
        }
        try {
            PferdEditor.create(name, speedMin, speedMax, photo);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        return true;
    }
}
