package mephi.lab1;

import java.util.List;

public class MissionPrinter{
    public void print(LoadedMission loadedMission) {
        Mission mission = loadedMission.getMission();

        System.out.println("-----АНАЛИЗ МИССИИ------");
        System.out.println("Распознанный формат: " + loadedMission.getFormat().getViewName() + "\n");
        printMission(mission);
        printParticipants(mission.getParticipants());
        printCurse(mission.getCurse());
        printTechniques(mission.getTechniques());
    }

    private void printParticipants(List<Sorcerer> participants) {
        System.out.println("УЧАСТНИКИ:");
        for (Sorcerer participant : participants){
            System.out.println("-" + participant.getName() + " (" + participant.getRank() + ")");
        }
        System.out.println();
    }

    private void printTechniques(List<Technique> techniques){
        System.out.println("ТЕХНИКИ:");
        for (Technique technique : techniques){
            System.out.println("-" + technique.getName() + "\n   -Владелец: " + technique.getOwner() + "\n   -Тип: " + technique.getType() + "\n   -Урон: " + technique.getDamage());
        }
    }

    private void printCurse(Curse curse){
        System.out.println("ПРОКЛЯТИЕ: \n-Имя: " + curse.getName() + "\n-Уровень угрозы: " + curse.getThreatLevel() + "\n");
    }

    private void printMission(Mission mission){
        System.out.println("МИССИЯ:");
        System.out.println("-Статус: " + mission.getOutcome());
        System.out.println("-ID миссии: " + mission.getMissionId());
        System.out.println("-Дата: " + mission.getDate());
        System.out.println("-Локация: " + mission.getLocation());
        System.out.println("-Ущерб: " + mission.getDamageCost());
        if (mission.getNote() == null){
            System.out.println("-Примечание: не задано\n");
        }else{
            System.out.println("-Примечание: " + mission.getNote() + "\n");
        }
    }
}