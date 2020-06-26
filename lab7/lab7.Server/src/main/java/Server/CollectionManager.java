package Server;

import java.util.*;

import Common.StudyGroup.StudyGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Класс, предназначенный для управления коллекцией и обладающий свойствами
 * studyGroup, date, filepath
 * @author Нечкасова Олеся
 */
public class CollectionManager {
    /**Поле объекта коллекции типа LinkedHashMap**/
    private Map<Integer, StudyGroup> studyGroupMap = new LinkedHashMap<>();
    /**Поле даты инициализации коллекции**/
    private String date = new Date().toString();
    /**Поле для хранения пути к файлу с данными**/
    private final Logger logger = LoggerFactory.getLogger(Server.class);
    private DataBaseManager dataBaseManager;

    public CollectionManager() {}

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

    public void setDataBaseManager(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }
}
