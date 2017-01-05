package domain;

/**
 * Created by Rebeka on 2015.03.16..
 * Datentyp für Erzeugung neue Objekte basierend auf der Datenbank
 */
public interface Data {

    /**
     * ID in der Datenbank
     * @return
     */
    public Integer getID();

    /**
     * Subtyp PferdData
     * @return
     */
    public boolean istPferd();

    /**
     * Subtyp ReiterData
     * @return
     */
    public boolean istReiter();

    /**
     * Subtyp RennenData
     * @return
     */
    public boolean istRennen();
}
