package domain;

/**
 * Created by Rebeka on 2015.03.16..
 */
public class PferdData implements Data {

    private Integer id;
    private int speedMin;
    private int speedMax;
    private boolean deleted;
    private String foto;
    private String name;

    public PferdData(Integer id, String name, int speedMin, int speedMax, String foto) {
        this.id = id;
        this.name = name;
        this.speedMin = speedMin;
        this.speedMax = speedMax;
        this.foto = foto;
        deleted = false;
    }

    public String toString() {
        return "ID: " + id + "; Name: " +  name + "; Min Speed: " + speedMin + "; Max Speed: " + speedMax + "; Photo: " + foto;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public boolean istPferd() {
        return true;
    }

    @Override
    public boolean istReiter() {
        return false;
    }

    @Override
    public boolean istRennen() {
        return false;
    }

    public int getSpeedMin() {
        return speedMin;
    }

    public int getSpeedMax() {
        return speedMax;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getFoto() {
        return foto;
    }

    public String getName() {
        return name;
    }

    public void delete() {
        deleted = true;
    }
}
