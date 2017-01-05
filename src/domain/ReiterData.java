package domain;

/**
 * Created by Rebeka on 2015.03.16..
 */
public class ReiterData implements Data {

    private Integer id;
    private double skill;
    private boolean deleted;
    private String name;

    public ReiterData(Integer id, String name, double skill) {
        this.id = id;
        this.name = name;
        this.skill = skill;
        deleted = false;
    }

    public String toString() {
        return "ID: " + id + "; Name: " +  name + "; Skill: " + skill;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public boolean istPferd() {
        return false;
    }

    @Override
    public boolean istReiter() {
        return true;
    }

    @Override
    public boolean istRennen() {
        return false;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public double getSkill() {
        return skill;
    }

    public String getName() {
        return name;
    }

    public void setSkill(double skill) {
        this.skill = skill;
    }

    public void delete() {
        deleted = true;
    }

    public void setName(String name) {
        this.name = name;
    }
}
