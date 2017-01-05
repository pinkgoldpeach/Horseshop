package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReiterEditor;
import service.ServiceException;

import java.io.IOException;

/**
 * Created by Rebeka on 2015.03.23..
 */
public class CreateJockey implements GUI {

   private static Logger log = LogManager.getLogger(CreateJockey.class);
   private Stage primaryStage;

    public CreateJockey(Stage ps) {
        primaryStage = ps;
    }

    public void start()  {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("fxmls/createJockey.fxml"));
        } catch (IOException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        Scene create = new Scene(parent);
        primaryStage.setTitle("Create a jockey!");
        primaryStage.setScene(create);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static boolean createJockey(String name, String skillText) {
        if (name.isEmpty()) {
            name = "noName";
        }
        int skill = 0;
        if (skillText.isEmpty()) {
            skill = 0;
        } else if (skillText.matches("\\d+")) {
            skill = Integer.parseInt(skillText);
        } else if (skillText.charAt(0) == '-') {
            String absSkill = skillText.substring(1);
            if (absSkill.matches("\\d+")) {
                skill = Integer.parseInt(absSkill);
                skill = -1 * skill;
            } else {
                return false;
            }
        } else {
            return false;
        }
        try {
            ReiterEditor.create(name, skill);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        return true;
    }
}
