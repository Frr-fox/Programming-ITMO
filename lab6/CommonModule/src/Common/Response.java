package Common;

import Common.StudyGroup.StudyGroup;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Response implements Serializable {
    private String answer = "";
    private Map<Integer, StudyGroup> map = new LinkedHashMap<>();

    public boolean isMap(){
        return !map.isEmpty();
    }

    public void addLineToAnswer(String line) {
        answer += line + "\n";
    }

    public void addToAnswer(String line) {
        answer += line + " ";
    }

    public void addElement(int key, StudyGroup st){
        map.put(key, st);
    }

    public Map<Integer, StudyGroup> getMap() {
        return map;
    }

    public void setMap(Map<Integer, StudyGroup> map) {
        this.map = map;
    }

    public String getAnswer() {
        return answer;
    }

    public void clearAnswer(){
        answer = "";
    }

    public void clearMap(){
        map = new HashMap<>();
    }
    public void clearAll(){
        clearAnswer();
        clearMap();
    }
}

