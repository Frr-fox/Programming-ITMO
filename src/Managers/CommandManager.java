package Managers;

import Commands.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Класс, предназначенный для вызова команд. В шаблоне Command выаолняющий роль invoker'a
 * @author Нечкасова Олеся
 */
public class CommandManager {
    public static HashMap<String, AbstractCommand> commandMap = new LinkedHashMap<>();
    public static LimitedQueue<String> queue = new LimitedQueue<>(15);

    {
        Console console = new Console();
        this.register(new Help(console));
        this.register(new Info(console));
        this.register(new Show(console));
        this.register(new Insert(console));
        this.register(new Update(console));
        this.register(new RemoveKey(console));
        this.register(new Clear(console));
        this.register(new Save(console));
        this.register(new ExecuteScript(console));
        this.register(new Exit(console));
        this.register(new RemoveLower(console));
        this.register(new History(console));
        this.register(new RemoveLowerKey(console));
        this.register(new AverageOfStudentsCount(console));
        this.register(new FilterContainsName(console));
        this.register(new FilterGreaterThanFormOfEducation(console));
    }

    /**
     * Метод для добавления команды в LinkedHashMap {@link CommandManager#commandMap}
     * @param command задает команду для регистрации
     */
     protected void register(AbstractCommand command){
        commandMap.put(command.getCommandName(), command);
     }

    /**
     * Метод для идентификации команды и ее выполнения
     * @param commandName определяет название команды
     * @param args определяет аргумента команды
     * @throws WrongAmountOfArgsException при непральном введенном количестве аргументов
     * @throws IsEmptyException , если коллекция пуста
     * @throws IOException при ошибках парсинга
     */
     protected static void execute(String commandName, String ... args) throws WrongAmountOfArgsException, IsEmptyException, IOException {
         if (commandName != null) queue.add(commandName);
         AbstractCommand command = commandMap.get(commandName);
        if (command == null) {
            System.out.println("Команды " + commandName + " нет в списке");
        } else {
            command.execute(args);
        }
     }

}
