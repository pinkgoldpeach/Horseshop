package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Rebeka on 2015.03.16..
 * Stellt eine Verbindung zur Datenbank vor
 */
public class Connector {

    private static final Logger log = LogManager.getLogger(Connector.class);

    private static String username = "sa";
    private static String passwort = "";
    private static String datenbankName ="jdbc:h2:~/test";
    private static String treiberName = "org.h2.Driver";


    private static Connection connection;

    private static boolean openConnection() throws DaoException {
        try {
            Class.forName(treiberName);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DaoException(e);
        }
        try {
            connection = DriverManager.getConnection(datenbankName, username, passwort);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DaoException(e);
        }
        return true;
    }

    public static Connection getConnection() throws DaoException {
        if(connection == null) {
            try {
                openConnection();
            } catch (DaoException e) {
                log.error(e.getMessage());
                throw new DaoException(e);
            }
        }
        return  connection;
    }

    public static boolean closeConnection() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
           log.error(e.getMessage());
            throw new DaoException(e);
        }
        return true;
    }
}