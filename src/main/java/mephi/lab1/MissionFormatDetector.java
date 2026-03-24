package mephi.lab1;

public class MissionFormatDetector{
    public FileFormat detect(String wayToFile){
        if (wayToFile.endsWith(".json")){
            return FileFormat.JSON;
        }
        if (wayToFile.endsWith(".xml")){
            return FileFormat.XML;
        }
        return FileFormat.TEXT;
    }
}
