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
        System.out.println("УЧАСТНИКИ:\n");
        for (Sorcerer participant : participants){
            System.out.println("-" + participant.getName() + " (" + participant.getRank() + ")\n");
        }
    }

    private void printTechniques(List<Technique> techniques){
        System.out.println("ТЕХНИКИ:");
        for (Technique technique : techniques){
            System.out.println("-" + technique.getName() + "\n   -Владелец: " + technique.getOwner() + "\n   -Тип: " + technique.getType() + "\n   -Урон: " + technique.getDamage()+ "\n");
        }
    }

    private void printCurse(Curse curse){
        System.out.println("ПРОКЛЯТИЕ: \n-Имя: " + curse.getName() + "\n-Уровень угрозы: " + curse.getThreatLevel() + "\n");
    }

    private void printMission(Mission mission){
        System.out.println("МИССИЯ:");
        System.out.println("\n-Статус: " + mission.getOutcome());
        System.out.println("\n-ID миссии: " + mission.getMissionId());
        System.out.println("\n-Дата: " + mission.getDate());
        System.out.println("\n-Локация: " + mission.getLocation());
        System.out.println("\n-Ущерб: " + mission.getDamageCost());
        if (mission.getNote() == null){
            System.out.println("-Примечание: не задано\n");
        }else{
            System.out.println("-Примечание: " + mission.getNote() + "\n");
        }
    }
}