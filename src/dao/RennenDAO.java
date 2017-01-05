package dao;

import domain.Data;
import domain.RennenData;

import java.util.ArrayList;

/**
 * Created by Rebeka on 2015.04.12..
 */
public interface RennenDAO  {

    /**
     *Daten von der Datenbank lesen
     * @return gelesene Daten
     */
    public ArrayList<RennenData> empfangen() throws DaoException;

    /**
     *Daten in die Datenbank schreiben
     * @param daten zum Senden
     * @return erfolg?
     */
    public boolean senden(ArrayList<RennenData> daten) throws DaoException;

    /**
     *Daten in der Datenbank modifizieren
     * @param daten zu modifizieren
     * @return erfolg?
     */
    public boolean update(ArrayList<RennenData> daten);

    /**
     *Daten von der Datenbank loeschen
     * (per Flag)
     * @param daten zu loeschen
     * @return erfolg?
     */
    public boolean delete(ArrayList<RennenData> daten);
}
