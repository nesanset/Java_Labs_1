package mephi.lab1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonMissionParser implements MissionParser{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mission parse(String content) throws MissionParseException{
        try{
            JsonNode root = objectMapper.readTree(content);
            JsonNode curse = root.get("curse");

            List<String> participants = new ArrayList<>();
            for (JsonNode sorcerer : root.get("sorcerers")) {
                participants.add(sorcerer.get("name").asText() + " (" + sorcerer.get("rank").asText() + ")");
            }

            List<String> techniques = new ArrayList<>();
            for (JsonNode technique : root.get("techniques")){
                techniques.add(technique.get("name").asText() + " | владелец: " + technique.get("owner").asText() + " | тип: " + technique.get("type").asText() + " | урон: " + technique.get("damage").asText());
            }

            String note = null;
            if (root.has("note")) {
                note = root.get("note").asText();
            }
            Mission mission = new Mission(root.get("missionId").asText(), root.get("date").asText(), root.get("location").asText(), root.get("outcome").asText(), root.get("damageCost").asText(), participants, curse.get("name").asText(), curse.get("threatLevel").asText(), techniques, note);
            return mission;
        } catch (Exception exception) {
            throw new MissionParseException("Не удалось прочитать JSON");
        }
    }
}