package mephi.lab1;

import java.util.List;

public class Mission{
    private final String missionId;
    private final String date;
    private final String location;
    private final String outcome;
    private final String damageCost;
    private final List<Sorcerer> participants;
    private final Curse curse;
    private final List<Technique> techniques;
    private final String note;

    public Mission(String missionId, String date, String location, String outcome, String damageCost, List<Sorcerer> participants, Curse curse, List<Technique> techniques, String note){
        this.missionId = missionId;
        this.date = date;
        this.location = location;
        this.outcome = outcome;
        this.damageCost = damageCost;
        this.participants = List.copyOf(participants);
        this.curse = curse;
        this.techniques = List.copyOf(techniques);
        this.note = note;
    }

    public String getMissionId(){
        return missionId;
    }

    public String getDate(){
        return date;
    }

    public String getLocation(){
        return location;
    }

    public String getOutcome(){
        return outcome;
    }

    public String getDamageCost(){
        return damageCost;
    }

    public List<Sorcerer> getParticipants(){
        return participants;
    }

    public Curse getCurse(){
        return curse;
    }

    public List<Technique> getTechniques(){
        return techniques;
    }

    public String getNote(){
        return note;
    }
}