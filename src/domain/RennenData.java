package domain;

/**
 * Created by Rebeka on 2015.03.16..
 */
public class RennenData implements Data, Comparable<RennenData> {

    private Integer pID;
    private Integer rID;
    private Integer id;
    private Double ergebnis;
    private Double luck;
    private Double skill;
    private Integer rank;
    private Double tempo;

    public RennenData(Integer id, Integer pID, Integer rID, double ergebnis) {
        this.id = id;
        this.pID = pID;
        this.rID = rID;
        this.ergebnis =ergebnis;
        luck = null;
        skill = null;
        rank = null;
        tempo = null;
    }

    public RennenData(Integer id, Integer pID, Integer rID, double ergebnis, double luck, double skill, int rank, double tempo) {
        this.id = id;
        this.pID = pID;
        this.rID = rID;
        this.ergebnis =ergebnis;
        this.luck = luck;
        this.skill = skill;
        this.rank = rank;
        this.tempo = tempo;
    }

    public String toString() {
        return "RaceID: " + id + "; PonyID: " + pID + "; JockeyID: " + rID + "; Score: " + ergebnis + "; " + "Luck: " + luck + "; Skill: " + skill + "; Tempo: " + tempo + "; Rank: " + rank + ";" ;
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
        return false;
    }

    @Override
    public boolean istRennen() {
        return true;
    }

    public Integer getPID() {
        return pID;
    }

    public Integer getRID() {
        return rID;
    }

    public double getErgebnis() {
        return ergebnis;
    }

    public Double getLuck() {
        return luck;
    }

    public void setLuck(Double luck) {
        this.luck = luck;
    }

    public Double getSkill() {
        return skill;
    }

    public void setSkill(Double skill) {
        this.skill = skill;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    @Override
    public int compareTo(RennenData o) {
        return Double.compare(ergebnis, o.getErgebnis());
    }
}
