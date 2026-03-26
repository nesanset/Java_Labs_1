package mephi.lab1;

import java.util.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public class XmlMissionParser implements MissionParser{
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public Mission parse(String content) throws MissionParseException{
        try{
            MissionXml missionXml = xmlMapper.readValue(content, MissionXml.class);

            List<Sorcerer> participants = new ArrayList<>();
            for (SorcererXml sorcerer : missionXml.sorcerers){
                participants.add(new Sorcerer(sorcerer.name, sorcerer.rank));
            }

            List<Technique> techniques = new ArrayList<>();
            for (TechniqueXml technique : missionXml.techniques){
                techniques.add(new Technique(technique.name, technique.owner, technique.type, technique.damage));
            }

            Curse curse = new Curse(missionXml.curse.name, missionXml.curse.threatLevel);

            return new Mission(missionXml.missionId, missionXml.date, missionXml.location, missionXml.outcome, missionXml.damageCost, participants, curse, techniques, missionXml.comment);
        }catch (Exception exception){
            throw new MissionParseException("Не удалось прочитать XML");
        }
    }

    @JacksonXmlRootElement(localName = "mission")
    private static class MissionXml{
        public String missionId;
        public String date;
        public String location;
        public String outcome;
        public String damageCost;
        public CurseXml curse;

        @JacksonXmlElementWrapper(localName = "sorcerers")
        @JacksonXmlProperty(localName = "sorcerer")
        public List<SorcererXml> sorcerers;

        @JacksonXmlElementWrapper(localName = "techniques")
        @JacksonXmlProperty(localName = "technique")
        public List<TechniqueXml> techniques;

        public String comment;
    }

    private static class CurseXml{
        public String name;
        public String threatLevel;
    }

    private static class SorcererXml{
        public String name;
        public String rank;
    }

    private static class TechniqueXml{
        public String name;
        public String owner;
        public String type;
        public String damage;
    }
}