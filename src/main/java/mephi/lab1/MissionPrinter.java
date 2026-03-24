package mephi.lab1;

import java.util.List;

public class MissionPrinter{
    public void print(LoadedMission loadedMission){
        Mission mission = loadedMission.getMission();

        System.out.println("-----АНАЛИЗ МИССИИ------");
        System.out.println("Распознанный формат: " + loadedMission.getFormat().getViewName() + "\n");
        System.out.println("МИССИЯ:");
        printResult(mission);
        printList("УЧАСТНИКИ", mission.getParticipants());
        System.out.println("ПРОКЛЯТИЕ: " + buildCurseText(mission));
        printList("ТЕХНИКИ", mission.getTechniques());
    }

    private void printList(String title, List<String> values){
        System.out.println(title + ":");
        for (String value : values) {
            System.out.println("-" + value);
        }
        System.out.println();
    }

    private String buildCurseText(Mission mission){
        return "\n-Имя: " + mission.getCurseName() + "\n-Уровень угрозы: " + mission.getCurseLevel() + "\n";
    }

    private void printResult(Mission mission){
        System.out.println("-Статус: " + mission.getOutcome());
        System.out.println("-ID миссии: " + mission.getMissionId());
        System.out.println("-Дата: " + mission.getDate());
        System.out.println("-Локация: " + mission.getLocation());
        System.out.println("-Ущерб: " + mission.getDamageCost());
        if (mission.getNote() == null){
            System.out.println("-Примечание: не задано");
        }else{
            System.out.println("-Примечание: " + mission.getNote());
        }
    }
}