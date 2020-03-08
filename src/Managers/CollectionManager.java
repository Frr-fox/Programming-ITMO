package Managers;

import java.io.*;
import java.util.*;

import StudyGroup.*;

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

    /**
     * Метод, возвращаюзий значение
     * @return даты инициализации коллекции
     */
    public static String getDateIn() {
        return date;
    }

    /**
     * Метод, предназначенный для считывания введенных команд пользователем в интерактивным режиме,
     * их идентификации и выполнения
     * @throws WrongAmountOfArgsException при неправильном количестве введенных аргументов
     * @throws IsEmptyException если коллекция пуста
     * @throws IOException при ошибках парсинга
     */
    public static void start() throws WrongAmountOfArgsException, IsEmptyException, IOException {
        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager = new CommandManager();
        String enteredLine = "";
        String[] userCommand = new String[0];

        while (true) {
            enteredLine = scanner.nextLine().trim();
            if (enteredLine.equals("")) continue;
            userCommand = enteredLine.replaceAll(" +", " ").split(" ");
            String commandName = userCommand[0].toLowerCase();
            String[] args = new String[userCommand.length-1];
            for (int i = 0; i<userCommand.length-1; i++) {
                args[i] = userCommand[i+1];
            }
            CommandManager.execute(commandName, args);
        }
    }

    /**
     * Метод, для заполнения коллекции данными из указанного файла
     * @param args задает путь до файла
     * @throws IOException при ошибках газрузки файла
     */
    public static void load(String[] args) throws IOException {
        try{
            filepath = System.getenv("FILE_PATH");
            if (filepath == null) {
                filepath = "src/students.xml";
            } else {
                filepath += "students.xml";
            }
            JAXBWorker.load(filepath);
            if (studyGroupMap.isEmpty()){
                System.out.println("Коллекция из файла не загружена. Неправильные данные в Xml файле. ");
                sort();
            } else{
                System.out.println("Коллекция из файла загружена!");
            }
        } catch (IOException e) {
            System.out.println("Не распознана переменная окружения. ");
        }
        sort();
    }

    public static void sort(){
        List<StudyGroup> list = new ArrayList<StudyGroup>(studyGroupMap.values());
        Collections.sort(list);
        studyGroupMap.clear();
        for (StudyGroup studyGroup: list) {
            studyGroupMap.put(studyGroup.getThisKey(), studyGroup);
        }
    }
}
