package Server;

import java.io.*;
import java.util.*;

import Common.StudyGroup.StudyGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс, предназначенный для управления коллекцией и обладающий свойствами
 * studyGroup, date, filepath
 * @author Нечкасова Олеся
 */
@XmlRootElement(name = "StudyGroupMap")
public class CollectionManager {
    /**Поле объекта коллекции типа LinkedHashMap**/
    @XmlElementWrapper(name = "studyGroup")
    @XmlElement
    private Map<Integer, StudyGroup> studyGroupMap = new LinkedHashMap<>();
    /**Поле даты инициализации коллекции**/
    private String date = new Date().toString();
    /**Поле для хранения пути к файлу с данными**/
    protected final String filepath;
    private final Logger logger = LoggerFactory.getLogger(Server.class);

    public CollectionManager() {
        String filepath1;
        filepath1 = System.getenv("FILE_PATH");
        if (filepath1 == null) {
            filepath1 = "src/main/resources/students.xml";
        } else {
            filepath1 += "students.xml";
        }
        filepath = filepath1;
    }

    /**
     * Метод, возвращающий значение
     * @return даты инициализации коллекции
     */
    public String getDateIn() {
        return date;
    }


    public void sort(){
        logger.info("Выполняется сортировка коллекции");
        List<StudyGroup> list = new ArrayList<StudyGroup>(studyGroupMap.values());
        Collections.sort(list);
        studyGroupMap.clear();
        for (StudyGroup studyGroup: list) {
            studyGroupMap.put(studyGroup.getThisKey(), studyGroup);
        }
    }

    public Map<Integer, StudyGroup> getStudyGroupMap() {
        return studyGroupMap;
    }

    public String getFilepath() {
        return filepath;
    }
}
