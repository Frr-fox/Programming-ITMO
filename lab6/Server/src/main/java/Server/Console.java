package Server;

import Server.Common.CommandManager;
import Common.StudyGroup.StudyGroup;
import Common.WorkWithObjects;
import Common.StudyGroup.FormOfEducation;
import Server.Common.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

import static Server.Common.CommandManager.*;
import static Server.Common.Connection.getResponse;
import static Server.Common.Connection.*;


/**
 * Класс, предназначенный для вызова команд. В шаблоне Command выполняющий роль receiver'a
 * @author Нечкасова Олеся
 */
public class Console {
    WorkWithObjects workWithObjects = new WorkWithObjects();
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    CollectionManager collectionManager;

    public Console() {
    }

    public Console(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Метод, описывающий конкретную реализацию команды help
     */
    public void help() {
        CommandManager.commandMap.forEach((key, value) -> getResponse().addLineToAnswer("/" + key + value.getArguments() + ": " + value.getText()));
    }

    /**
     * Метод, описывающий конкретную реализацию команды info
     */
    public void info() {
        getResponse().addLineToAnswer("Тип коллекции: " + collectionManager.getStudyGroupMap().getClass());
        getResponse().addLineToAnswer("Время инициализации коллекции: " + collectionManager.getDateIn());
        getResponse().addLineToAnswer("Количество элементов: " + collectionManager.getStudyGroupMap().size());
    }

    /**
     * Метод, описывающий конкретную реализацию команды show
     */
    public void show() {
        if (collectionManager.getStudyGroupMap().isEmpty()) {
            getResponse().addLineToAnswer("Коллекция пуста.");
        } else {
            getResponse().setMap(collectionManager.getStudyGroupMap());
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды insert
     */
    public void insert(Object[] args) {
        int key = (int) args[0];
        StudyGroup st = (StudyGroup) args[1];
        Counter counter = new Counter(collectionManager);
        if (!collectionManager.getStudyGroupMap().containsKey(key)) {
            st.setNextId(counter.generate());
            st.setThisKey(key);
            collectionManager.getStudyGroupMap().put(key, st);
            getResponse().addLineToAnswer("Новый элемент успешно добавлен в коллекцию!");
        } else {
            getResponse().addLineToAnswer("Элемент с таким ключом уже существует.");
        }
        collectionManager.sort();
    }

    /**
     * Метод, описывающий конкретную реализацию команды insert для выполнения скрипта
     */
    public void insert(BufferedReader scanner, StringWriter writer, Object[] args) {
        int key = Integer.parseInt(args[0].toString());
        Counter counter = new Counter(collectionManager);
        try {
            if (!collectionManager.getStudyGroupMap().containsKey(key)) {
                StudyGroup st = workWithObjects.addStudyGroup(scanner, writer);
                writer.flush();
                getResponse().addLineToAnswer(writer.getBuffer().toString());
                st.setNextId(counter.generate());
                st.setThisKey(key);
                collectionManager.getStudyGroupMap().put(key, st);
                getResponse().addLineToAnswer("Новый элемент успешно добавлен в коллекцию!");
            } else {
                getResponse().addLineToAnswer("Элемент с таким ключом уже существует.");
            }
        } catch (NumberFormatException e){
            getResponse().addLineToAnswer("Неправильный формат. Введите целое число в качестве аргумента.");
        } catch (IOException e) {
            getResponse().addLineToAnswer("Возникли проблемы при выводе данных: " + e.getMessage());
        }
        collectionManager.sort();
    }

    /**
     * Метод, описывающий конкретную реализацию команды update
     */
    public void update(Object[] args) {
        int arg = (int) args[0];
        StudyGroup st = (StudyGroup) args[1];
        boolean flag = false;
        Integer key = 0;
        for (Map.Entry<Integer, StudyGroup> entry : collectionManager.getStudyGroupMap().entrySet()) {
            if (arg == entry.getValue().getId()) {
                key = entry.getKey();
                flag = true;
                break;
            }
        }
        if (flag) {
            st.setThisKey(key);
            st.setId(arg);
            collectionManager.getStudyGroupMap().put(key, st);
            getResponse().addLineToAnswer("Значение элемента коллекции с ID " + arg + " успешно обновлено!");
        } else {
            getResponse().addLineToAnswer("Элемента с таким ID нет в коллекции.");
        }
        collectionManager.sort();
    }

    /**
     * Метод, описывающий конкретную реализацию команды update для выполнения скрипта
     */
    public void update(BufferedReader scanner, StringWriter writer, Object[] args) {
        boolean flag = false;
        int arg = (int) args[0];
        try {
            Integer key = 0;
            for (Map.Entry<Integer, StudyGroup> entry: collectionManager.getStudyGroupMap().entrySet())
                if (arg == entry.getValue().getId()) {
                    key = entry.getKey();
                    flag = true;
                    break;
                }
            if (flag) {
                StudyGroup st = workWithObjects.addStudyGroup(scanner, writer);
                st.setThisKey(key);
                st.setId(arg);
                collectionManager.getStudyGroupMap().put(key, st);
                getResponse().addLineToAnswer("Значение элемента коллекции с ID " + arg + " успешно обновлено!");
            } else {
                getResponse().addLineToAnswer("Элемента с таким ID нет в коллекции.");
            }
        } catch (NumberFormatException e) {
            getResponse().addLineToAnswer("Неправильный формат. Введите целое число в качестве аргумента.");
        } catch (IOException e) {
            getResponse().addLineToAnswer("Возникли проблемы при выводе данных: " + e.getMessage());
        }
        collectionManager.sort();
    }


    /**
     * Метод, описывающий конкретную реализацию команды remove_key
     */
    public void removeKey(Object[] args) {
        int arg = (int) args[0];
        if (collectionManager.getStudyGroupMap().containsKey(arg)) {
            collectionManager.getStudyGroupMap().remove(arg);
            getResponse().addLineToAnswer("Элемент коллекции с ключом " + arg + " успешно удален!");
        } else getResponse().addLineToAnswer("Элемента с таким ключом нет в коллекции.");
    }

    /**
     * Метод, описывающий конкретную реализацию команды clear
     */
    public void clear() {
        collectionManager.getStudyGroupMap().clear();
        getResponse().addLineToAnswer("Коллекция очищена! ");
    }

    /**
     * Метод, описывающий конкретную реализацию команды save
     */
    public void save(){
        JAXBWorker.save(collectionManager, collectionManager.filepath);
        getResponse().addLineToAnswer("Коллекция сохранена!");
    }

    /**
     * Метод, описывающий конкретную реализацию команды execute_script
     */
    public void executeScript(Object[] args) {
        String filepath = (String) args[0];
        try {
//            String filepath = "C:\\Users\\olesy\\IdeaProjects\\lab5\\src\\script.txt";
            String[] userCommand;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            StringWriter writer = new StringWriter();
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                userCommand = line.replaceAll(" +", " ").split(" ");
                String commandName = userCommand[0].toLowerCase();
                String[] arguments = new String[userCommand.length-1];
                System.arraycopy(userCommand, 1, arguments, 0, userCommand.length - 1);
                switch (commandName){
                    case "insert":
                        queue.add(commandName);
                        logger.info("Начато выполнение команды {}", commandName);
                        this.insert(reader, writer, arguments);
                        break;
                    case "update":
                        queue.add(commandName);
                        logger.info("Начато выполнение команды {}", commandName);
                        this.update(reader, writer, arguments);
                        break;
                    case "execute_script":
                        logger.info("Начато выполнение команды {}", commandName);
                        throw new StackOverflowError();
                    default:
                        CommandManager.execute(commandName, arguments);
                        break;
                }
                sendObject(getResponse()); //отправка ответа
                getResponse().clearAll();
                line = reader.readLine();
            }
            writer.close();
        } catch (FileNotFoundException e){
            getResponse().addLineToAnswer("Указанный файл не найден");
        } catch (StackOverflowError e){
            getResponse().addLineToAnswer("Рекурсия вызова скрипта");
        } catch (IOException e) {
            getResponse().addLineToAnswer("Возникли проблемы с чтением данных из консоли: " + e.getMessage());
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды exit
     */
    public void exit() {
        save();
        Connection.closeSocketChannel();
    }

    /**
     * Метод, описывающий конкретную реализацию команды remove_lower
     */
    public void removeLower(Object[] args) {
        StudyGroup newObject = (StudyGroup) args[0];
        boolean flag = collectionManager.getStudyGroupMap().entrySet().stream().anyMatch(entry -> newObject.compareTo(entry.getValue()) > 0);
        if (flag) {
            getResponse().addToAnswer("Элементы коллекции с ключами");
            for (Iterator<Map.Entry<Integer, StudyGroup>> entries = collectionManager.getStudyGroupMap().entrySet().iterator(); entries.hasNext();) {
                Map.Entry<Integer, StudyGroup> entry = entries.next();
                if (newObject.compareTo(entry.getValue()) > 0) {
                    entries.remove();
                    getResponse().addToAnswer(entry.getKey().toString());
                }
            }
            getResponse().addToAnswer("успешно удалены!");
        } else getResponse().addLineToAnswer("Элементов, меньших чем данный, в коллекции нет");
    }

    /**
     * Метод, описывающий конкретную реализацию команды history
     */
    public void history() {
        getResponse().addLineToAnswer(queue.toString());
    }

    /**
     * Метод, описывающий конкретную реализацию команды remove_lower_key
     */
    public void removeLowerKey(Object[] args) {
        int arg = (int) args[0];
        boolean flag= collectionManager.getStudyGroupMap().entrySet().stream().anyMatch(entry -> entry.getKey() < arg);
        if (flag) {
            getResponse().addToAnswer("Элементы коллекции с ключами");
            for (Iterator<Map.Entry<Integer, StudyGroup>> entries = collectionManager.getStudyGroupMap().entrySet().iterator(); entries.hasNext();) {
                Map.Entry<Integer, StudyGroup> entry = entries.next();
                if (entry.getKey() < arg) {
                    entries.remove();
                    getResponse().addToAnswer(entry.getKey().toString());
                }
            }
            getResponse().addToAnswer("успешно удалены!");
        } else {
            getResponse().addLineToAnswer("Элементов, с ключом меньшим " + arg + ", нет");
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды average_of_students_count
     */
    public void averageOfStudentsCount() {
        long sum;
        try {
            if (!collectionManager.getStudyGroupMap().isEmpty()) {
                sum = collectionManager.getStudyGroupMap().values().stream().mapToLong(studyGroup -> studyGroup.getStudentsCount() / collectionManager.getStudyGroupMap().size()).sum();
                getResponse().addLineToAnswer("Среднее значение поля studentsCount: " + sum);
            } else {
                throw new IsEmptyException();
            }
        } catch (IsEmptyException e) {
            getResponse().addLineToAnswer(e.getMessage());
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды filter_contains_name
     */
    public void filterContainsName(Object[] args) {
        String arg = (String) args[0];
        boolean flag = false;
        try {
            if (!collectionManager.getStudyGroupMap().isEmpty()) {
                for (Map.Entry<Integer, StudyGroup> entry: collectionManager.getStudyGroupMap().entrySet()) {
                    if (entry.getValue().getName().contains(arg)) {
                        flag = true;
                        getResponse().addElement(entry.getKey(), entry.getValue());
                    }
                }
                if (!flag) {
                    getResponse().addLineToAnswer("Элементов, значение поля name которых содержит подстроку " + arg + ", нет.");
                }
            } else {
                throw new IsEmptyException();
            }
        } catch (IsEmptyException e) {
            getResponse().addLineToAnswer(e.getMessage());
        }
    }

    /**
     * Метод, описывающий конкретную реализацию команды filter_greater_than_form_of_education
     */
    public void filterGreaterThanFormOfEducation(Object[] args) {
        FormOfEducation formOfEducation = (FormOfEducation) args[0];
        boolean flag = false;
        try {
            if (!collectionManager.getStudyGroupMap().isEmpty()) {
                for (Map.Entry<Integer, StudyGroup> entry: collectionManager.getStudyGroupMap().entrySet()) {
                        if (entry.getValue().getFormOfEducation().compareTo(formOfEducation) > 0) {
                            flag = true;
                            getResponse().addElement(entry.getKey(), entry.getValue());
                        }
                }
                if (!flag) {
                    getResponse().addLineToAnswer("Элементов, значение поля formOfEducation которых больше \"" + formOfEducation.getTitle() + "\", нет.");
                }
            } else {
                throw new IsEmptyException();
            }
        } catch (IsEmptyException e) {
            getResponse().addLineToAnswer(e.getMessage());
        }
    }
}
