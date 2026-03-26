package mephi.lab1;

import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMissionParser implements MissionParser{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mission parse(String content) throws MissionParseException{
        try{
            JsonNode root = objectMapper.readTree(content);
            JsonNode curse = root.get("curse");

            List<Sorcerer> participants = new ArrayList<>();
            for (JsonNode sorcerer : root.get("sorcerers")){
                participants.add(new Sorcerer(sorcerer.get("name").asText(), sorcerer.get("rank").asText()));
            }

            List<Technique> techniques = new ArrayList<>();
            for (JsonNode technique : root.get("techniques")){
                techniques.add(new Technique(technique.get("name").asText(),technique.get("owner").asText(), technique.get("type").asText(), technique.get("damage").asText()));
            }

            String note = root.get("comment").asText();
            Curse missionCurse = new Curse(curse.get("name").asText(), curse.get("threatLevel").asText());
            return new Mission(root.get("missionId").asText(), root.get("date").asText(), root.get("location").asText(), root.get("outcome").asText(), root.get("damageCost").asText(), participants, missionCurse, techniques, note);

        }catch (Exception exception){
            throw new MissionParseException("Не удалось прочитать JSON");
        }
    }
}