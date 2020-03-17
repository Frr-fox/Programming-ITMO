package Managers;

import StudyGroup.StudyGroup;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import static Managers.CollectionManager.studyGroupMap;
import static Managers.CommandManager.queue;
import StudyGroup.FormOfEducation;

/**
 * Класс, предназначенный для вызова команд. В шаблоне Command выполняющий роль receiver'a
 * @author Нечкасова Олеся
 */
public class Console {
    WorkWithObjects workWithObjects = new WorkWithObjects();
    BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    CollectionManager collectionManager = new CollectionManager();

    /**
     * Метод, описывающий конкретную реализацию команды help
     */
    public void help(){
        for (Map.Entry<String, AbstractCommand> entry:CommandManager.commandMap.entrySet()) {
            System.out.printf("/%s%s: %s\n", entry.getKey(), entry.getValue().getArguments(), entry.getValue().getText());
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды info
     */
    public void info(){
        System.out.println("Тип коллекции: " + studyGroupMap.getClass()); //TODO: поменять
        System.out.println("Время инициализации коллекции: " + CollectionManager.getDateIn());
        System.out.println("Количество элементов: " + studyGroupMap.size());
    }

    /**
     * Метод, описывающий конкретную реализацию команды show
     */
    public void show(){
        if (studyGroupMap.isEmpty()) {
            System.out.println("Коллекция пуста. ");
        } else {
            for (Map.Entry<Integer, StudyGroup> entry: studyGroupMap.entrySet()){
                System.out.printf("Key: %s, Value: %s\n", entry.getKey(), entry.getValue().toString());
                System.out.println();
            }
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды insert
     */
    public void insert(String... args) {
        try {
            try {
                if (args.length > 1) {
                    throw new WrongAmountOfArgsException();
                } else {
                    if (!studyGroupMap.containsKey(Integer.parseInt(args[0]))) {
                        StudyGroup st = workWithObjects.addStudyGroup(scanner);
                        st.setNextId();
                        st.setThisKey(Integer.parseInt(args[0]));
                        studyGroupMap.put(Integer.parseInt(args[0]), st);
                        System.out.println("Новый элемент успешно добавлен в коллекцию! ");
                    } else {
                        System.out.println("Элемент с таким ключом уже существует.");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new WrongAmountOfArgsException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WrongAmountOfArgsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e){
            System.out.println("Неправильный формат. Введите целое число в качестве аргумента. ");
        }
        CollectionManager.sort(); //TODO: mark
    }

    /**
     * Метод, описывающий конкретную реализацию команды insert для выполнения скрипта
     */
    public void insert(BufferedReader scanner, String... args) {
        try {
            try {
                if (args.length > 1) {
                    throw new WrongAmountOfArgsException();
                } else {
                    if (!studyGroupMap.containsKey(Integer.parseInt(args[0]))) {
                        StudyGroup st = workWithObjects.addStudyGroup(scanner);
                        st.setNextId();
                        st.setThisKey(Integer.parseInt(args[0]));
                        studyGroupMap.put(Integer.parseInt(args[0]), st);
                        System.out.println("Новый элемент успешно добавлен в коллекцию! ");
                    } else {
                        System.out.println("Элемент с таким ключом уже существует.");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new WrongAmountOfArgsException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Неправильный формат. Введите целое число в качестве аргумента. ");
        }
        CollectionManager.sort();
    }

    /**
     * Метод, описывающий конкретную реализацию команды update
     */
    public void update(String... args) throws WrongAmountOfArgsException{
        boolean flag = false;
        try {
            try {
                if (args.length > 1) {
                    throw new WrongAmountOfArgsException();
                } else {
                    Integer key = 0;
                    for (Map.Entry<Integer, StudyGroup> entry: studyGroupMap.entrySet()){
                        if (Integer.parseInt(args[0]) == entry.getValue().getId()) {
                            key = entry.getKey();
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        StudyGroup st = workWithObjects.addStudyGroup(scanner);
                        st.setThisKey(key);
                        st.setId(Integer.parseInt(args[0]));
                        studyGroupMap.put(key, st);
                        System.out.println("Значение элемента коллекции с ID " + args[0] + " успешно обновлено! ");
                    } else {
                        System.out.println("Элемента с таким ID нет в коллекции. ");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new WrongAmountOfArgsException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Неправильный формат. Введите целое число в качестве аргумента. ");
        }
        CollectionManager.sort();
    }

    /**
     * Метод, описывающий конкретную реализацию команды update для выполнения скрипта
     */
    public void update(BufferedReader scanner, String... args) throws WrongAmountOfArgsException{
        boolean flag = false;
        try {
            try {
                if (args.length > 1) {
                    throw new WrongAmountOfArgsException();
                } else {
                    Integer key = 0;
                    for (Map.Entry<Integer, StudyGroup> entry: studyGroupMap.entrySet()){
                        if (Integer.parseInt(args[0]) == entry.getValue().getId()) {
                            key = entry.getKey();
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        StudyGroup st = workWithObjects.addStudyGroup(scanner);
                        st.setThisKey(key);
                        st.setId(Integer.parseInt(args[0]));
                        studyGroupMap.put(key, st);
                        System.out.println("Значение элемента коллекции с ID " + args[0] + " успешно обновлено! ");
                    } else {
                        System.out.println("Элемента с таким ID нет в коллекции. ");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new WrongAmountOfArgsException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Неправильный формат. Введите целое число в качестве аргумента. ");
        }
        CollectionManager.sort();
    }

    /**
     * Метод, описывающий конкретную реализацию команды remove_key
     */
    public void removeKey(String... args) throws WrongAmountOfArgsException {
        try {
            try {
                if (args.length > 1) {
                    throw new WrongAmountOfArgsException();
                } else {
                    if (studyGroupMap.containsKey(Integer.parseInt(args[0]))) {
                        studyGroupMap.remove(Integer.parseInt(args[0]));
                        System.out.println("Элемент коллекции с ключом " + args[0] + " успешно удален! ");
                    } else {
                        System.out.println("Элемента с таким ключом нет в коллекции. ");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new WrongAmountOfArgsException();
            }
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Неправильный формат. Введите целое число в качестве аргумента. ");
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды clear
     */
    public void clear(){
        studyGroupMap.clear();
        System.out.println("Коллекция очищена! ");
    }

    /**
     * Метод, описывающий конкретную реализацию команды save
     */
    public void save(){
            JAXBWorker.save(collectionManager, CollectionManager.filepath);
        System.out.println("Коллекция сохранена!");
    }

    /**
     * Метод, описывающий конкретную реализацию команды execute_script
     */
    public void executeScript(String[] args) throws IOException {
        try {
            String filepath = Arrays.toString(args).replaceAll("]", "");
            filepath = filepath.replaceAll("\\[", "");
//            String filepath = "C:\\Users\\olesy\\IdeaProjects\\lab5\\src\\script.txt";
            String[] userCommand;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                userCommand = line.replaceAll(" +", " ").split(" ");
                String commandName = userCommand[0].toLowerCase();
                String[] arguments = new String[userCommand.length-1];
                for (int i = 0; i<userCommand.length-1; i++) {
                    arguments[i] = userCommand[i+1];
                }
                switch (commandName){
                    case "insert":
                        this.insert(reader, arguments);
                        break;
                    case "update":
                        this.update(reader, arguments);
                        break;
                    case "remove_lower":
                        this.removeLower(reader, arguments);
                        break;
                    case "execute_script":
                        throw new StackOverflowError();
                    default:
                        CommandManager.execute(commandName, arguments);
                        break;
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e){
            System.out.println("Указанный файл не найден");
        }
        catch (StackOverflowError e){
            System.out.println("Рекурсия вызова скрипта");
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды exit
     */
    public void exit(){
            System.exit(0);
    }

    /**
     * Метод, описывающий конкретную реализацию команды remove_lower
     */
    public void removeLower(String[] args) throws IOException {
        StudyGroup newObject = workWithObjects.addStudyGroup(scanner);
        boolean flag=false;
            try {
                if (args.length != 0) {
                    throw new WrongAmountOfArgsException();
                } else {
                    for (Map.Entry<Integer, StudyGroup> entry : studyGroupMap.entrySet()) {
                        if (newObject.compareTo(entry.getValue()) > 0) {
                            flag=true;
                            break;
                        }
                    }
                    if (flag) {
                        System.out.print("Элементы коллекции с ключами ");
                        for (Iterator<Map.Entry<Integer, StudyGroup>> entries = studyGroupMap.entrySet().iterator(); entries.hasNext();) {
                            Map.Entry<Integer, StudyGroup> entry = entries.next();
                            if (newObject.compareTo(entry.getValue()) > 0) {
                                entries.remove();
                                System.out.print(entry.getKey() + " ");
                            }
                        }
                        System.out.println("успешно удалены! ");
                    } else {
                        System.out.println("Элементов, меньших чем данный, в коллекции нет");
                    }
                }
            } catch (WrongAmountOfArgsException e) {
                System.out.println(e.getMessage());
            }
    }

    /**
     * Метод, описывающий конкретную реализацию команды remove_lower для выполнения скрипта
     */
    public void removeLower(BufferedReader scanner, String[] args) throws IOException {
        StudyGroup newObject = workWithObjects.addStudyGroup(scanner);
        boolean flag=false;
        try {
            if (args.length != 0) {
                throw new WrongAmountOfArgsException();
            } else {
                for (Map.Entry<Integer, StudyGroup> entry : studyGroupMap.entrySet()) {
                    if (newObject.compareTo(entry.getValue()) > 0) {
                        flag=true;
                        break;
                    }
                }
                if (flag) {
                    System.out.print("Элементы коллекции с ключами ");
                    for (Iterator<Map.Entry<Integer, StudyGroup>> entries = studyGroupMap.entrySet().iterator(); entries.hasNext();) {
                        Map.Entry<Integer, StudyGroup> entry = entries.next();
                        if (newObject.compareTo(entry.getValue()) > 0) {
                            entries.remove();
                            System.out.print(entry.getKey() + " ");
                        }
                    }
                    System.out.println("успешно удалены! ");
                } else {
                    System.out.println("Элементов, меньших чем данный, в коллекции нет");
                }
            }
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды history
     */
    public void history(){
        System.out.println(queue);
    }

    /**
     * Метод, описывающий конкретную реализацию команды remove_lower_key
     */
    public void removeLowerKey(String... args) throws WrongAmountOfArgsException {
        boolean flag=false;
        try {
            try {
                if (args.length > 1) {
                    throw new WrongAmountOfArgsException();
                } else {
                    for (Map.Entry<Integer, StudyGroup> entry : studyGroupMap.entrySet()) {
                        if (args[0].compareTo(String.valueOf(entry.getKey())) > 0) {
                            flag=true;
                            break;
                        }
                    }
                    if (flag) {
                        System.out.print("Элементы коллекции с ключами ");
                        for (Iterator<Map.Entry<Integer, StudyGroup>> entries = studyGroupMap.entrySet().iterator(); entries.hasNext();) {
                            Map.Entry<Integer, StudyGroup> entry = entries.next();
                            if (args[0].compareTo(String.valueOf(entry.getKey())) > 0) {
                                entries.remove();
                                System.out.print(entry.getKey() + " ");
                            }
                        }
                        System.out.println("успешно удалены! ");
                    } else {
                        System.out.println("Элементов, с ключом меньшим " + args[0] + ", нет");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new WrongAmountOfArgsException();
            }
        } catch (WrongAmountOfArgsException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды average_of_students_count
     */
    public void averageOfStudentsCount() throws IsEmptyException {
        long sum = 0;
        try {
            if (!studyGroupMap.isEmpty()) {
                for (Map.Entry<Integer, StudyGroup> entry: studyGroupMap.entrySet()){
                    sum += entry.getValue().getStudentsCount()/studyGroupMap.size();
                }
                System.out.println("Среднее значение поля studentsCount: " + sum);
            } else {
                throw new IsEmptyException();
            }
        } catch (IsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды filter_contains_name
     */
    public void filterContainsName(String... args) throws WrongAmountOfArgsException, IsEmptyException {
        boolean flag = false;
        try {
            try {
                if ((args.length > 1)){
                    throw new WrongAmountOfArgsException();
                } else {
                    if (!studyGroupMap.isEmpty()) {
                        for (Map.Entry<Integer, StudyGroup> entry: studyGroupMap.entrySet()) {
                            if (entry.getValue().getName().contains(args[0])) {
                                flag = true;
                                System.out.printf("Key: %s, Value: %s\n\n", entry.getKey(), entry.getValue().toString());
                            }
                        }
                        if (!flag) {
                            System.out.println("Элементов, значение поля name которых содержит подстроку " + args[0] + ", нет. ");
                        }
                    } else {
                        throw new IsEmptyException();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new WrongAmountOfArgsException();
            }
        } catch (WrongAmountOfArgsException | IsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды filter_greater_than_form_of_education
     */
    public void filterGreaterThanFormOfEducation(String... args) throws WrongAmountOfArgsException{
        boolean flag = false;
        try {
            try {
                if ((args.length > 1)){
                    throw new WrongAmountOfArgsException();
                } else {
                    if (!studyGroupMap.isEmpty()) {
                        for (Map.Entry<Integer, StudyGroup> entry: studyGroupMap.entrySet()) {
                            if (FormOfEducation.parse(args[0]) == null) {
                                System.out.println("Введено некорректное значение. ");
                                flag = true;
                                break;
                            } else {
                                if (entry.getValue().getFormOfEducation().compareTo(FormOfEducation.parse(args[0])) > 0) {
                                    flag = true;
                                    System.out.printf("Key: %s, Value: %s\n", entry.getKey(), entry.getValue().toString());
                                }
                            }
                        }
                        if (!flag) {
                            System.out.println("Элементов, значение поля formOfEducation которых больше \"" + args[0] + "\", нет. ");
                        }
                    } else {
                        throw new IsEmptyException();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new WrongAmountOfArgsException();
            }
        } catch (WrongAmountOfArgsException | IsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
}
