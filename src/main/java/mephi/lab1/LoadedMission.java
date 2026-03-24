package mephi.lab1;

public class LoadedMission{
    private final String wayToFile;
    private final FileFormat format;
    private final Mission mission;

    public LoadedMission(String wayToFile, FileFormat format, Mission mission){
        this.wayToFile = wayToFile;
        this.format = format;
        this.mission = mission;
    }

    public FileFormat getFormat(){
        return format;
    }

    public Mission getMission(){
        return this.mission;
    }
}
