package mephi.lab1;

public enum FileFormat{
    TEXT("Текст"),JSON("JSON"),XML("XML");
    private final String viewName;

    FileFormat(String viewName){
        this.viewName = viewName;
    }

    public String getViewName(){
        return viewName;
    }
}
