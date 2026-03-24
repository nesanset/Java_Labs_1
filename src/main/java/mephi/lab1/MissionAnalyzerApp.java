package mephi.lab1;

import java.util.Scanner;

public class MissionAnalyzerApp{
    public static void main(String[] args) {
        MissionLoader missionLoader = new MissionLoader();
        MissionPrinter missionPrinter = new MissionPrinter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в анализатор миссий!");
        while (true){
            System.out.println("\nВведите путь к файлу (A-выход):");
            String wayToFile = scanner.nextLine();

            if ("A".equals(wayToFile)) {
                System.out.println("Завершение работы.");
                return;
            }
            if (wayToFile.isEmpty()) {
                continue;
            }
            try {
                LoadedMission loadedMission = missionLoader.load(wayToFile);
                if (loadedMission != null) {
                    missionPrinter.print(loadedMission);
                }
            } catch (Exception exception){
                System.out.println("Ошибка: не удалось обработать файл");
            }
        }
    }
}

