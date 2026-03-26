package mephi.lab1;

import java.util.Scanner;

public class MissionAnalyzerApp{
    public static void main(String[] args) {
        MissionLoader missionLoader = new MissionLoader();
        MissionPrinter missionPrinter = new MissionPrinter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в анализатор миссий!");
        while (true){
            System.out.println("\nВведите путь к файлу (или <выход> для выхода из программы):");
            String wayToFile = scanner.nextLine();

            if ("выход".equals(wayToFile)){
                System.out.println("Анализ файлов завершен!");
                return;
            }
            if (wayToFile.isEmpty()){
                continue;
            }
            try{
                LoadedMission loadedMission = missionLoader.load(wayToFile);
                if (loadedMission != null){
                    missionPrinter.print(loadedMission);
                }
            }catch (Exception exception){
                System.out.println("Не удалось обработать файл");
            }
        }
    }
}