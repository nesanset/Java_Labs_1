package mephi.lab1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TextMissionParser implements MissionParser{
    @Override
    public Mission parse(String content){
        Map<String, String> values = new LinkedHashMap<>();
        Map<Integer, String> sorcererNames = new TreeMap<>();
        Map<Integer, String> sorcererRanks = new TreeMap<>();
        Map<Integer, TechniqueData> techniquesByIndex = new TreeMap<>();

        for (String chooseLine : content.split("\\R")){
            String line = chooseLine.trim();
            if (line.isEmpty()) {
                continue;
            }
            int separatorIndex = line.indexOf(':');
            String key = line.substring(0, separatorIndex);
            String value = line.substring(separatorIndex + 1);

            if (key.startsWith("sorcerer[")){
                int index = readIndex(key);
                String field = readFieldName(key);

                if ("name".equals(field)){
                    sorcererNames.put(index, value);
                }else{
                    sorcererRanks.put(index, value);
                }
            }else if (key.startsWith("technique[")){
                int index = readIndex(key);
                String field = readFieldName(key);
                TechniqueData technique = techniquesByIndex.get(index);

                if (technique == null){
                    technique = new TechniqueData();
                    techniquesByIndex.put(index, technique);
                }

                if ("name".equals(field)){
                    technique.name = value;
                }else if ("owner".equals(field)){
                    technique.owner = value;
                }else if ("type".equals(field)){
                    technique.type = value;
                }else{
                    technique.damage = value;
                }
            }else{
                values.put(key, value);
            }
        }

        List<String> participants = new ArrayList<>();
        for (Integer index : sorcererNames.keySet()){
            participants.add(sorcererNames.get(index) + " (" + sorcererRanks.get(index) + ")");
        }

        List<String> techniques = new ArrayList<>();
        for (TechniqueData technique : techniquesByIndex.values()) {
            techniques.add(formatTechnique(technique));
        }

        String note = values.get("note");
        Mission mission = new Mission(values.get("missionid"), values.get("date"), values.get("location"), values.get("outcome"), values.get("damagecost"), participants, values.get("curse.name"), values.get("curse.threatlevel"), techniques, note);

        return mission;
    }

    private int readIndex(String key){
        int openBracketIndex = key.indexOf('[');
        int closeBracketIndex = key.indexOf(']');
        return Integer.parseInt(key.substring(openBracketIndex + 1, closeBracketIndex));
    }

    private String readFieldName(String key){
        int dotIndex = key.indexOf('.');
        return key.substring(dotIndex + 1);
    }

    private String formatTechnique(TechniqueData technique){
        return technique.name + "\n   -Владелец: " + technique.owner + "\n   -Тип: " + technique.type + "\n   -Урон: " + technique.damage;
    }

    private static class TechniqueData{
        private String name;
        private String owner;
        private String type;
        private String damage;
    }
}