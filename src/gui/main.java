package gui;

import dao.Connector;
import dao.DaoException;
import domain.PferdData;
import domain.ReiterData;
import domain.RennenData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rebeka on 2015.03.19..
 * Controller Class fuer GUI
 */
public class main extends Application {

    private static Logger log = LogManager.getLogger(main.class);

    public static void main(String[] args) {
        launch(args);
        try {
            Connector.closeConnection();
        } catch (DaoException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
    }

    private static Stage ps;
    /**
     * schon geoeffnete Fenster
     */
    private static HashMap<String, GUI> windows_10 = new HashMap<String, GUI>();
    /**
     * Ergebnis von der Pferd-Suche
     */
    private static ObservableList<PferdData> resultFindPony;
    /**
     * Ergebnis von der Reiter-Suche
     */
    private static ObservableList<ReiterData> resultFindJockey;
    /**
     * Ergebnis von der Rennen-Suche
     */
    private static ObservableList<RennenData> resultFindRace;
    /**
     * Erfebnis von der Simulation
     */
    private static ObservableList<RennenData> resultSim = FXCollections.observableArrayList();
    /**
     * Ergebnis von den Statistiken
     */
    private static ObservableList<RennenData> resultStatRace = FXCollections.observableArrayList();
    /**
     * Ergebnis von den Rankings
     */
    private static ObservableList<String> resultStatRanking = FXCollections.observableArrayList();
    /**
     * Nicht verfuegbare (schon gewaehlte) Pferde
     */
    private static ArrayList<PferdData> inPonyTable = new ArrayList<>();
    /**
     * Nicht verfuegbare (schon gewaehlte) Reiter
     */
    private static ArrayList<ReiterData> inJockeyTable = new ArrayList<>();

    @FXML
    private TextField pony_name;
    @FXML
    private TextField pony_photo;
    @FXML
    private TextField pony_minSpeed;
    @FXML
    private TextField pony_maxSpeed;
    @FXML
    private TextField jockey_name;
    @FXML
    private TextField jockey_skill;
    @FXML
    private CheckBox findFastest_pony;
    @FXML
    private CheckBox findSlowest_pony;
    @FXML
    private TextField findPony_name;
    @FXML
    private TextField findPony_photo;
    @FXML
    private TableView tableView_resultFindPony;
    @FXML
    private TableColumn ID_resultFindPony;
    @FXML
    private TableColumn name_resultFindPony;
    @FXML
    private TableColumn minSpeed_resultFindPony;
    @FXML
    private TableColumn maxSpeed_resultFindPony;
    @FXML
    private TableColumn photo_resultFindPony;
    @FXML
    private TextField findJockey_name;
    @FXML
    private CheckBox findJockey_best;
    @FXML
    private CheckBox findJockey_worst;
    @FXML
    private TableView tableView_resultFindJockey;
    @FXML
    private TableColumn ID_resultFindJockey;
    @FXML
    private TableColumn name_resultFindJockey;
    @FXML
    private TableColumn skill_resultFindJockey;
    @FXML
    private ChoiceBox<PferdData> pony_findRace;
    @FXML
    private ChoiceBox<ReiterData> jockey_findRace;
    @FXML
    private TextField id_findRace;
    @FXML
    private TableView tableView_resultFindRace;
    @FXML
    private TableColumn ID_resultFindRace;
    @FXML
    private TableColumn pony_resultFindRace;
    @FXML
    private TableColumn jockey_resultFindRace;
    @FXML
    private TableColumn score_resultFindRace;
    @FXML
    private TableColumn skill_resultFindRace;
    @FXML
    private TableColumn tempo_resultFindRace;
    @FXML
    private TableColumn rank_resultFindRace;
    @FXML
    private TableColumn luck_resultFindRace;
    @FXML
    private ChoiceBox<PferdData> pony_race;
    @FXML
    private ChoiceBox<ReiterData> jockey_race;
    @FXML
    private TableView tableViewPony_race;
    @FXML
    private TableView tableViewJockey_race;
    @FXML
    private TableColumn pony_tableView;
    @FXML
    private TableColumn jockey_tableView;
    @FXML
    private TableView tableView_simResult;
    @FXML
    private TableColumn pony_simResult;
    @FXML
    private TableColumn jockey_simResult;
    @FXML
    private TableColumn result_simResult;
    @FXML
    private TableColumn luck_simResult;
    @FXML
    private TableColumn tempo_simResult;
    @FXML
    private TableColumn skill_simResult;
    @FXML
    private TableColumn rank_simResult;
    @FXML
    private CheckBox fastest_Stat;
    @FXML
    private CheckBox slowest_Stat;
    @FXML
    private  ChoiceBox<PferdData> pony_Stat;
    @FXML
    private ChoiceBox<ReiterData> jockey_Stat;
    @FXML
    private TableView tableView_raceStat;
    @FXML
    private TableColumn raceID_raceStat;
    @FXML
    private TableColumn ponyID_raceStat;
    @FXML
    private TableColumn jockeyID_raceStat;
    @FXML
    private TableColumn score_raceStat;
    @FXML
    private TableColumn skill_raceStat;
    @FXML
    private TableColumn luck_raceStat;
    @FXML
    private TableColumn tempo_raceStat;
    @FXML
    private TableColumn rank_raceStat;
    @FXML
    private ListView listView_Ranking;
    @FXML
    private ComboBox<PferdData> pony_update;
    @FXML
    private ComboBox<ReiterData> jockey_update;
    @FXML
    private TextField ponyName_update;
    @FXML
    private TextField minSpeed_update;
    @FXML
    private TextField maxSpeed_update;
    @FXML
    private TextField photo_update;
    @FXML
    private TextField nameJockey_update;
    @FXML
    private TextField skill_update;
    @FXML
    private ComboBox<PferdData> pony_gallery;
    @FXML
    private ImageView image_gallery;


    /**
     * Oeffnet den Fenster
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("fxmls/menu.fxml"));
        Scene mainMenu = new Scene(parent);
        ps = primaryStage;
        primaryStage.setTitle("Wendy's Main Menu");
        primaryStage.setScene(mainMenu);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Befuellt die Tabellen (falls schon geoeffnet)
     */
    public void initialize() {
        if (tableView_resultFindPony != null && resultFindPony != null) {
            ID_resultFindPony.setCellValueFactory(new PropertyValueFactory<PferdData, Integer>("ID"));
            name_resultFindPony.setCellValueFactory(new PropertyValueFactory<PferdData, String>("name"));
            minSpeed_resultFindPony.setCellValueFactory(new PropertyValueFactory<PferdData, Integer>("speedMin"));
            maxSpeed_resultFindPony.setCellValueFactory(new PropertyValueFactory<PferdData, Integer>("speedMax"));
            photo_resultFindPony.setCellValueFactory(new PropertyValueFactory<PferdData, String>("foto"));
            tableView_resultFindPony.setItems(resultFindPony);
        }
       if (tableView_resultFindJockey != null && resultFindJockey != null) {
            ID_resultFindJockey.setCellValueFactory(new PropertyValueFactory<ReiterData, Integer>("ID"));
            name_resultFindJockey.setCellValueFactory(new PropertyValueFactory<ReiterData, String>("name"));
            skill_resultFindJockey.setCellValueFactory(new PropertyValueFactory<ReiterData, Double>("skill"));
            tableView_resultFindJockey.setItems(resultFindJockey);
        }
        if (pony_findRace != null && jockey_findRace != null) {
            try {
                pony_findRace.setItems(FXCollections.observableArrayList(Suchen.allHorses()));
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
            try {
                jockey_findRace.setItems(FXCollections.observableArrayList(Suchen.allJockeys()));
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        }
        if (tableView_resultFindRace != null && resultFindRace != null) {
            ID_resultFindRace.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("ID"));
            pony_resultFindRace.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("PID"));
            jockey_resultFindRace.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("RID"));
            score_resultFindRace.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("ergebnis"));
            luck_resultFindRace.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("luck"));
            skill_resultFindRace.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("skill"));
            tempo_resultFindRace.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("tempo"));
            rank_resultFindRace.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("rank"));
            tableView_resultFindRace.setItems(resultFindRace);
          }
        if (pony_race != null && jockey_race != null) {
            try {
                pony_race.setItems(FXCollections.observableArrayList(Suchen.availableHorses(inPonyTable)));
                jockey_race.setItems(FXCollections.observableArrayList(Suchen.availableJockey(inJockeyTable)));
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        }
        if (tableView_simResult != null && resultSim != null) {
            pony_simResult.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("pID"));
            jockey_simResult.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("rID"));
            result_simResult.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("ergebnis"));
            luck_simResult.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("luck"));
            skill_simResult.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("skill"));
            tempo_simResult.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("tempo"));
            rank_simResult.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("rank"));
            tableView_simResult.setItems(resultSim);
        }
        if (tableView_raceStat != null && resultStatRace != null) {
            raceID_raceStat.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("ID"));
            ponyID_raceStat.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("pID"));
            jockeyID_raceStat.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("rID"));
            score_raceStat.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("ergebnis"));
            luck_raceStat.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("luck"));
            skill_raceStat.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("skill"));
            tempo_raceStat.setCellValueFactory(new PropertyValueFactory<RennenData, Double>("tempo"));
            rank_raceStat.setCellValueFactory(new PropertyValueFactory<RennenData, Integer>("rank"));
            tableView_raceStat.setItems(resultStatRace);
        }
        if (pony_Stat != null && jockey_Stat != null) {
            try {
                pony_Stat.setItems(FXCollections.observableArrayList(Suchen.allHorses()));
                jockey_Stat.setItems(FXCollections.observableArrayList(Suchen.allJockeys()));
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        }
        if (listView_Ranking != null && resultStatRanking != null) {
            listView_Ranking.setItems(resultStatRanking);
        }
        if (pony_update != null && jockey_update != null) {
            try {
                pony_update.setItems(FXCollections.observableArrayList(Suchen.allHorses()));
                jockey_update.setItems(FXCollections.observableArrayList(Suchen.allJockeys()));
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
            pony_update.setOnAction((event) -> {
                ponyName_update.setText(pony_update.getValue().getName());
                minSpeed_update.setText("" + pony_update.getValue().getSpeedMin());
                maxSpeed_update.setText("" + pony_update.getValue().getSpeedMax());
                photo_update.setText(pony_update.getValue().getFoto());
            });
            jockey_update.setOnAction((event) -> {
                nameJockey_update.setText(jockey_update.getValue().getName());
                skill_update.setText("" + jockey_update.getValue().getSkill());
            });
        }
        if (pony_gallery != null) {
            try {
                pony_gallery.setItems(FXCollections.observableArrayList(Suchen.allHorses()));
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void create(ActionEvent event) {
        if (!windows_10.containsKey("CreateMain")) {
            CreateMain c = new CreateMain(ps);
            windows_10.put("CreateMain", c);
            c.start();
        } else {
            windows_10.get("CreateMain").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void createHorse(ActionEvent event) {
        if (!windows_10.containsKey("CreatePony")) {
            CreatePony c = new CreatePony(ps);
            windows_10.put("CreatePony", c);
            c.start();
        } else {
            windows_10.get("CreatePony").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void createJockey(ActionEvent event) {
        if (!windows_10.containsKey("CreateJockey")) {
            CreateJockey c = new CreateJockey(ps);
            windows_10.put("CreateJockey", c);
            c.start();
        } else {
            windows_10.get("CreateJockey").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
     public void find(ActionEvent event) {
        try {
            resultFindPony = FXCollections.observableArrayList(Suchen.allHorses());
            resultFindJockey =  FXCollections.observableArrayList(Suchen.allJockeys());
            resultFindRace= FXCollections.observableArrayList(Suchen.allRaces());
        } catch (ServiceException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
        if (!windows_10.containsKey("Find")) {
            Find c = new Find(ps);
            windows_10.put("Find", c);
            c.start();
        } else {
            windows_10.get("Find").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void update(ActionEvent event) {
        if (!windows_10.containsKey("Update")) {
            Update c = new Update(ps);
            windows_10.put("Update", c);
            c.start();
        } else {
            windows_10.get("Update").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void gallery(ActionEvent event) {
        if (!windows_10.containsKey("Gallery")) {
            Gallery c = new Gallery(ps);
            windows_10.put("Gallery", c);
            c.start();
        } else {
            windows_10.get("Gallery").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findJockey(ActionEvent event) {
        if (!windows_10.containsKey("FindJockey")) {
            FindJockey c = new FindJockey(ps);
            windows_10.put("FindJockey", c);
            c.start();
        } else {
            windows_10.get("FindJockey").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findJockeyName(ActionEvent event) {
       checkNameJockey();
        if (!windows_10.containsKey("FindJockeyResult")) {
            FindJockeyResult c = new FindJockeyResult(ps);
            windows_10.put("FindJockeyResult", c);
            c.start();
        } else {
            windows_10.get("FindJockeyResult").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findJockeyBest(ActionEvent event) {
        boolean best = findJockey_best.isSelected();
        boolean worst = findJockey_worst.isSelected();
        if (best != worst) {
            checkBest(best);
            if (!windows_10.containsKey("FindJockeyResult")) {
                FindJockeyResult c = new FindJockeyResult(ps);
                windows_10.put("FindJockeyResult", c);
                c.start();
            } else {
                windows_10.get("FindJockeyResult").start();
            }
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void stat(ActionEvent event) {
        if (!windows_10.containsKey("Statistics")) {
            Statistics c = new Statistics(ps);
            windows_10.put("Statistics", c);
            c.start();
        } else {
            windows_10.get("Statistics").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void statRace(ActionEvent event) {
        boolean fastest = fastest_Stat.isSelected();
        boolean slowest = slowest_Stat.isSelected();
        if (fastest != slowest) {
            checkFastestRace(slowest);
            if (!windows_10.containsKey("RaceStat")) {
                RaceStat c = new RaceStat(ps);
                windows_10.put("RaceStat", c);
                c.start();
            } else {
                windows_10.get("RaceStat").start();
            }
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void statRanking(ActionEvent event) {
       if (pony_Stat.getValue() != null && jockey_Stat.getValue() != null) {
           PferdData pID = pony_Stat.getValue();
           ReiterData jID = jockey_Stat.getValue();
           checkRanking(pID, jID);
           if (!windows_10.containsKey("RankingStat")) {
               RankingStat c = new RankingStat(ps);
               windows_10.put("RankingStat", c);
               c.start();
           } else {
               windows_10.get("RankingStat").start();
           }
       }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void sim(ActionEvent event) {
       if (!windows_10.containsKey("Sim")) {
            Sim c = new Sim(ps);
            windows_10.put("Sim", c);
            c.start();
        } else {
            windows_10.get("Sim").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void simResult(ActionEvent event) {
        if (!inPonyTable.isEmpty() && !inJockeyTable.isEmpty()) {
            simulate();
            if (!windows_10.containsKey("SimResult")) {
                ResultSim c = new ResultSim(ps);
                windows_10.put("SimResult", c);
                c.start();
            } else {
                windows_10.get("SimResult").start();
            }
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findPony(ActionEvent event) {
        if (!windows_10.containsKey("FindPony")) {
            FindPony c = new FindPony(ps);
            windows_10.put("FindPony", c);
            c.start();
        } else {
            windows_10.get("FindPony").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findPonyName(ActionEvent event) {
       checkName();
       if  (!windows_10.containsKey("FindPonyResult")) {
            ResultFindPony c = new ResultFindPony(ps);
            windows_10.put("FindPonyResult", c);
            c.start();
        } else {
            windows_10.get("FindPonyResult").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findPonyPhoto(ActionEvent event) {
        checkPhoto();
        if  (!windows_10.containsKey("FindPonyResult")) {
            ResultFindPony c = new ResultFindPony(ps);
            windows_10.put("FindPonyResult", c);
            c.start();
        } else {
            windows_10.get("FindPonyResult").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findPonyFastest(ActionEvent event) {
        boolean fastest = findFastest_pony.isSelected();
        boolean slowest = findSlowest_pony.isSelected();
        if (fastest != slowest) {
            checkFastest(fastest);
            if (!windows_10.containsKey("FindPonyResult")) {
                ResultFindPony c = new ResultFindPony(ps);
                windows_10.put("FindPonyResult", c);
                c.start();
            } else {
                windows_10.get("FindPonyResult").start();
            }
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findRace(ActionEvent event) {
        if  (!windows_10.containsKey("FindRace")) {
            FindRace c = new FindRace(ps);
            windows_10.put("FindRace", c);
            c.start();
        } else {
            windows_10.get("FindRace").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findRaceResult(ActionEvent event) {
        checkRace();
        if  (!windows_10.containsKey("FindRaceResult")) {
            FindRaceResult c = new FindRaceResult(ps);
            windows_10.put("FindRaceResult", c);
            c.start();
        } else {
            windows_10.get("FindRaceResult").start();
        }
    }

    /**
     * Oeffnet verschiedenen Fenster
     * @param event
     */
    @FXML
    public void findRaceIDResult(ActionEvent event) {
        if (checkRaceID()) {
            if  (!windows_10.containsKey("FindRaceResult")) {
                FindRaceResult c = new FindRaceResult(ps);
                windows_10.put("FindRaceResult", c);
                c.start();
            } else {
                windows_10.get("FindRaceResult").start();
            }
        }
    }

    /**
     * Kehrt zu einem schon geoffneten Fenster zurueck
     */
    @FXML
    public void back() {
        try {
            start(ps);
            inJockeyTable.clear();
            inPonyTable.clear();
        } catch (Exception e) {
            log.error("Error back button", e);
            Emily.alertMessage(e);
        }
    }

    /**
     * Kehrt zu einem schon geoffneten Fenster zurueck
     */
    @FXML
    public void backCreate() {
       windows_10.get("CreateMain").start();
    }

    /**
     *K ehrt zu einem schon geoffneten Fenster zurueck
     */
    @FXML
    public void backFind() {
       windows_10.get("Find").start();
    }

    /**
     * Kehrt zu einem schon geoffneten Fenster zurueck
     */
    @FXML
    void backSim() {
        tableView_simResult.getItems().clear();
        inPonyTable.clear();
        inJockeyTable.clear();
        windows_10.get("Sim").start();
    }

    /**
     * Kehrt zu einem schon geoffneten Fenster zurueck
     */
    @FXML
    public void backStat() {
        windows_10.get("Statistics").start();
    }

    /**
     * Uebreprueft die Pony-Parameters, und gegebenfalls erzeugt einen neuen
     */
    @FXML
    public void createPony() {
        String name = pony_name.getText();
        String photo = pony_photo.getText();
        String speedMinText = pony_minSpeed.getText();
        String speedMaxText = pony_maxSpeed.getText();
        if (!CreatePony.createPony(name, photo, speedMinText, speedMaxText)) {
            pony_minSpeed.setText("I need an Integer");
            pony_maxSpeed.setText("but I love you");
            return;
        }
        windows_10.get("CreatePony").start();
    }

    /**
     * Uebreprueft die Reiter-Parameters, und gegebenfalls erzeugt einen neuen
     */
    @FXML
    public void createNewJockey() {
        String name = jockey_name.getText();
        String skillText = jockey_skill.getText();
        if (!CreateJockey.createJockey(name, skillText)) {
            jockey_skill.setText("An Integer pls^^");
            return;
        }
        windows_10.get("CreateJockey").start();
    }

    /**
     * Findet einen Pony nach Name
     */
    @FXML
    public void checkName() {
        String name = findPony_name.getText();
        if (name.isEmpty()) {
            try {
                resultFindPony = FXCollections.observableArrayList(Suchen.allHorses());
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        } else {
            try {
                resultFindPony = FXCollections.observableArrayList(Suchen.findHorse(name));
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        }
    }

    /**
     * Findet einen Pony nach Foto
     */
    @FXML
    public void checkPhoto() {
        String name = findPony_photo.getText();
        if (name.isEmpty()) {
            try {
                resultFindPony = FXCollections.observableArrayList(Suchen.allHorses());
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        } else {
            try {
                resultFindPony = FXCollections.observableArrayList(Suchen.findHorsePhoto(name));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Findet den schnellsten/langsamsten Pony
     * @param fastest
     */
    @FXML
    public void checkFastest(boolean fastest) {
        try {
            resultFindPony = FXCollections.observableArrayList(Suchen.findFastestHorse(fastest));
        } catch (ServiceException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
    }

    /**
     * Findet einen Reiter nach Name
     */
    @FXML
    public void checkNameJockey() {
        String name = findJockey_name.getText();
        if (name.isEmpty()) {
            try {
                resultFindJockey = FXCollections.observableArrayList(Suchen.allJockeys());
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        } else {
            try {
                resultFindJockey = FXCollections.observableArrayList(Suchen.findJockey(name));
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        }
    }

    /**
     * Findet den faehigsten/unfaehigsten Reiter
     * @param best
     */
    @FXML
    public void checkBest(boolean best) {
        try {
            resultFindJockey = FXCollections.observableArrayList(Suchen.findBestJockey(best));
        } catch (ServiceException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
    }

    /**
     * Sucht nach Rennen mit der gegebenen Reiter-Pferd-Paar
     */
    @FXML
    public void checkRace() {
        if (pony_findRace.getValue() != null && jockey_findRace.getValue() !=null ) {
            int ponyID = pony_findRace.getValue().getID();
            int jockeyID = jockey_findRace.getValue().getID();
            try {
                resultFindRace = FXCollections.observableArrayList(Suchen.findRennen(jockeyID, ponyID));
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        } else {
            try {
                resultFindRace = FXCollections.observableArrayList(Suchen.allRaces());
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        }
    }

    /**
     * Sucht ein Rennen nach ID
     * @return
     */
    @FXML
    public boolean checkRaceID() {
        if (!"".equals(id_findRace.getText())) {
            if (id_findRace.getText().matches("\\d+")) {
                int id = Integer.parseInt(id_findRace.getText());
                try {
                    resultFindRace = FXCollections.observableArrayList(Suchen.findRaceID(id));
                } catch (ServiceException e) {
                    log.error(e.getMessage());
                    Emily.alertMessage(e);
                }
                return true;
            } else {
                id_findRace.setText("An Integer pls");
                return false;
            }
        } else {
            try {
                resultFindRace = FXCollections.observableArrayList(Suchen.allRaces());
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
            return true;
        }
    }

    /**
     * Findet das schnellsten/langsamsten Rennergebnis
     * @param slowest
     */
    @FXML
    public void checkFastestRace(boolean slowest) {
        try {
            resultStatRace = FXCollections.observableArrayList(Statistik.fastestTurn(slowest));
        } catch (ServiceException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
    }

    /**
     * Gibt die Positionen von dem Pferd-Reiter-Paar zurueck
     * @param p : Pferd
     * @param j : Reiter
     */
    @FXML
    public void checkRanking(PferdData p, ReiterData j) {
        try {
            resultStatRanking = FXCollections.observableArrayList(Statistik.ranking(p, j));
        } catch (ServiceException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
    }

    /**
     * Fuegt Elemente in die Rennsimulationstabelle ein
     */
    @FXML
    public void inputTable() {
        if (pony_race.getValue() != null && jockey_race.getValue() != null) {
            pony_tableView.setCellValueFactory(new PropertyValueFactory<PferdData, Integer>("ID"));
            tableViewPony_race.getItems().addAll(FXCollections.observableArrayList(pony_race.getValue()));
            jockey_tableView.setCellValueFactory(new PropertyValueFactory<ReiterData, Integer>("ID"));
            tableViewJockey_race.getItems().addAll(FXCollections.observableArrayList(jockey_race.getValue()));
            inPonyTable.add(pony_race.getValue());
            inJockeyTable.add(jockey_race.getValue());
        }
        pony_race.setValue(null);
        jockey_race.setValue(null);
        try {
            pony_race.setItems(FXCollections.observableArrayList(Suchen.availableHorses(inPonyTable)));
            jockey_race.setItems(FXCollections.observableArrayList(Suchen.availableJockey(inJockeyTable)));
        } catch (ServiceException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
    }

    /**
     * Simuliert ein Rennen
     */
    @FXML
    public void simulate() {
        try {
            resultSim = FXCollections.observableArrayList(Simulation.simulation(inPonyTable, inJockeyTable));
        } catch (ServiceException e) {
            log.error(e.getMessage());
            Emily.alertMessage(e);
        }
    }

    /**
     * Uberprueft die Update-Parameters (von Pferd und/oder Reiter) und gegebenfalls modifiziert sie
     */
    @FXML
    public void updateData() {
        if (pony_update.getValue() != null) {
            int id = pony_update.getValue().getID();
            String name = pony_update.getValue().getName();
            if (!ponyName_update.getText().isEmpty()) {
                name = ponyName_update.getText();
            }
            int minSpeed = pony_update.getValue().getSpeedMin();
            if (!minSpeed_update.getText().isEmpty() && minSpeed_update.getText().matches("\\d+")) {
                minSpeed = Integer.parseInt(minSpeed_update.getText());
            }
            int maxSpeed = pony_update.getValue().getSpeedMax();
            if (!maxSpeed_update.getText().isEmpty() && maxSpeed_update.getText().matches("\\d+")) {
                maxSpeed = Integer.parseInt(maxSpeed_update.getText());
            }
            String photo = pony_update.getValue().getFoto();
            if (photo_update != null && !photo_update.getText().isEmpty()) {
                photo = photo_update.getText();
            }
            try {
                PferdEditor.update(id, name, minSpeed, maxSpeed, photo);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        if (jockey_update.getValue() != null) {
            int id = jockey_update.getValue().getID();
            String name = jockey_update.getValue().getName();
            if (!nameJockey_update.getText().isEmpty()) {
                name = nameJockey_update.getText();
            }
            double skill = jockey_update.getValue().getSkill();
            if (!skill_update.getText().isEmpty() && skill_update.getText().matches("\\d+")) {
                skill = Integer.parseInt(skill_update.getText());
            }
            try {
                ReiterEditor.update(id, name, skill);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        if (windows_10.containsKey("Gallery")) {
            windows_10.remove(windows_10.get("Gallery"));
        }
        windows_10.get("Update").start();
    }

    /**
     * Loescht ein Pferd und/oder einen Reiter
     */
    @FXML
    public void delete() {
        if (pony_update.getValue() != null) {
            try {
                PferdEditor.delete(pony_update.getValue().getID());
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        }
        if (jockey_update.getValue() != null) {
            try {
                ReiterEditor.delete(jockey_update.getValue().getID());
            } catch (ServiceException e) {
                log.error(e.getMessage());
                Emily.alertMessage(e);
            }
        }
        windows_10.get("Update").start();
    }

    /**
     * Laesst einen File waehlen
     */
    @FXML
    public void pickFile() {
        javafx.stage.FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("chooooose");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File chosen = fileChooser.showOpenDialog(ps);
       /* try {
            FileWriter writer = new FileWriter("/pictures");
            //writer.write(chosen);
        } catch (IOException e) {
            System.out.println("Error FileWriter");
        }*/
        String path = chosen.getPath();
        pony_photo.setText(chosen.getPath());
    }

    /**
     * Laesst einen File waehlen
     */
    @FXML
    public void pickFileUpdate() {
        javafx.stage.FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("chooooose");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File chosen = fileChooser.showOpenDialog(ps);
        photo_update.setText(chosen.getPath());
    }

    /**
     * Zeigt einen Foto an
     */
    @FXML
    public void showPicture() {
        Image img = new Image( pony_gallery.getValue().getFoto());
            image_gallery.setImage(img);

    }
}
