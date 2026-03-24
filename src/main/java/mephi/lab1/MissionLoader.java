package mephi.lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MissionLoader{
    private final MissionFormatDetector formatDetector = new MissionFormatDetector();
    private final MissionParser textParser = new TextMissionParser();
    private final MissionParser jsonParser = new JsonMissionParser();
    private final MissionParser xmlParser = new XmlMissionParser();

    public LoadedMission load(String wayToFile){
        try {
            File sourceFile = new File(wayToFile);
            if (!sourceFile.exists()){
                throw new IOException("Файл не найден " + sourceFile.getAbsolutePath());
            }
            String content = readContent(sourceFile);
            FileFormat format = formatDetector.detect(wayToFile);
            MissionParser parser = getParser(format);
            Mission mission = parser.parse(content);
            LoadedMission loadedMission = new LoadedMission(sourceFile.getAbsolutePath(), format, mission);
            return loadedMission;

        }catch (IOException exception){
            System.out.println("Ошибка чтения файла: " + exception.getMessage());
        }catch (MissionParseException exception){
            System.out.println("Ошибка разбора файла: " + exception.getMessage());
        }
        return null;
    }

    private MissionParser getParser(FileFormat format){
        if (format == FileFormat.JSON){
            return jsonParser;
        }
        if (format == FileFormat.XML){
            return xmlParser;
        }
        return textParser;
    }

    private String readContent(File file) throws IOException{
        try (FileInputStream input = new FileInputStream(file)){
            byte[] fileBytes = input.readAllBytes();
            String content = new String(fileBytes, StandardCharsets.UTF_8);
            return content;
        }
    }
}