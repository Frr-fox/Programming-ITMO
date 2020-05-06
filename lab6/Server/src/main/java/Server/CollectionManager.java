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
    static Map<Integer, StudyGroup> studyGroupMap = new LinkedHashMap<>();
    /**Поле даты инициализации коллекции**/
    private static String date = new Date().toString();
    /**Поле для хранения пути к файлу с данными**/
    protected static String filepath = "";
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    /**
     * Метод, возвращающий значение
     * @return даты инициализации коллекции
     */
    public static String getDateIn() {
        return date;
    }

    /**
     * Метод, для заполнения коллекции данными из указанного файла
     * @param args задает путь до файла
     * @throws IOException при ошибках газрузки файла
     */
    public static void load(String[] args) {
        logger.info("Выполняется загрузка коллекции...");
        try{
            filepath = System.getenv("FILE_PATH");
            if (filepath == null) {
                filepath = "src/main/java/students.xml";
            } else {
                filepath += "students.xml";
            }
            JAXBWorker.load(filepath);
            if (studyGroupMap.isEmpty()){
                logger.warn("Коллекция из файла не загружена. Неправильные данные в xml файле. ");
                sort();
            } else{
                logger.info("Коллекция из файла загружена!");
            }
        } catch (IOException e) {
            logger.warn("Не распознана переменная окружения. ");
        }
        sort();
    }

    public static void sort(){
        logger.info("Выполняется сортировка коллекции");
        List<StudyGroup> list = new ArrayList<StudyGroup>(studyGroupMap.values());
        Collections.sort(list);
        studyGroupMap.clear();
        for (StudyGroup studyGroup: list) {
            studyGroupMap.put(studyGroup.getThisKey(), studyGroup);
        }
    }
}
