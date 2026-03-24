package mephi.lab1;

public interface MissionParser{
    Mission parse(String content) throws MissionParseException;
}
