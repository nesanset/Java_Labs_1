package mephi.lab1;

import java.util.List;

public class Mission{
    private final String missionId;
    private final String date;
    private final String location;
    private final String outcome;
    private final String damageCost;
    private final List<String> participants;
    private final String curseName;
    private final String curseLevel;
    private final List<String> techniques;
    private final String note;

    public Mission(String missionId, String date, String location, String outcome, String damageCost, List<String> participants, String curseName, String curseLevel, List<String> techniques, String note){
        this.missionId = missionId;
        this.date = date;
        this.location = location;
        this.outcome = outcome;
        this.damageCost = damageCost;
        this.participants = participants;
        this.curseName = curseName;
        this.curseLevel = curseLevel;
        this.techniques = techniques;
        this.note = note;
    }


    public String getMissionId(){
        return missionId;
    }

    public String getDamageCost(){
        return damageCost;
    }

    public List<String> getParticipants(){
        return participants;
    }

    public String getCurseName(){
        return curseName;
    }

    public String getCurseLevel(){
        return curseLevel;
    }

    public List<String> getTechniques(){
        return techniques;
    }

    public String getNote(){
        return note;
    }

    public String getOutcome(){
        return outcome;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
