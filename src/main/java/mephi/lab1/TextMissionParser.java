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
        Map<Integer, Sorcerer> sorcerersByIndex = new TreeMap<>();
        Map<Integer, Technique> techniquesByIndex = new TreeMap<>();

        for (String rawLine : content.split("\\R")) {
            String line = rawLine.trim();
            if (line.isEmpty()) {
                continue;
            }

            int separatorIndex = line.indexOf(':');
            String key = line.substring(0, separatorIndex).trim();
            String value = line.substring(separatorIndex + 1).trim();

            if (key.startsWith("sorcerer[")) {
                readSorcererField(sorcerersByIndex, key, value);
            } else if (key.startsWith("technique[")) {
                readTechniqueField(techniquesByIndex, key, value);
            } else {
                values.put(key, value);
            }
        }

        List<Sorcerer> participants = new ArrayList<>(sorcerersByIndex.values());
        List<Technique> techniques = new ArrayList<>(techniquesByIndex.values());
        Curse curse = new Curse(values.get("curse.name"), values.get("curse.threatLevel"));

        return new Mission(values.get("missionId"), values.get("date"), values.get("location"), values.get("outcome"), values.get("damageCost"), participants, curse, techniques, values.get("note"));
    }

    private void readSorcererField(Map<Integer, Sorcerer> sorcerersByIndex, String key, String value) {
        int index = readIndex(key);
        String field = readFieldName(key);
        Sorcerer sorcerer = getSorcerer(sorcerersByIndex, index);

        if ("name".equals(field)){
            sorcerer.setName(value);
        } else if ("rank".equals(field)){
            sorcerer.setRank(value);
        }
    }

    private void readTechniqueField(Map<Integer, Technique> techniquesByIndex, String key, String value) {
        int index = readIndex(key);
        String field = readFieldName(key);
        Technique technique = getTechnique(techniquesByIndex, index);

        if ("name".equals(field)){
            technique.setName(value);
        }else if ("owner".equals(field)){
            technique.setOwner(value);
        }else if ("type".equals(field)){
            technique.setType(value);
        }else if ("damage".equals(field)){
            technique.setDamage(value);
        }
    }

    private Sorcerer getSorcerer(Map<Integer, Sorcerer> sorcerersByIndex, int index){
        Sorcerer sorcerer = sorcerersByIndex.get(index);
        if (sorcerer == null){
            sorcerer = new Sorcerer();
            sorcerersByIndex.put(index, sorcerer);
        }
        return sorcerer;
    }

    private Technique getTechnique(Map<Integer, Technique> techniquesByIndex, int index) {
        Technique technique = techniquesByIndex.get(index);
        if (technique == null){
            technique = new Technique();
            techniquesByIndex.put(index, technique);
        }
        return technique;
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
}