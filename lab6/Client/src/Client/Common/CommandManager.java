package Client.Common;

import Client.NoCommandException;
import Client.WrongAmountOfArgsException;
import Client.Common.Commands.*;
import Common.ConcreteCommand;

import Common.AbstractCommand;
import Common.Response;
import Common.StudyGroup.StudyGroup;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс, предназначенный для вызова команд. В шаблоне Common.Command выаолняющий роль invoker'a
 * @author Нечкасова Олеся
 */
public class CommandManager implements Serializable {
    public static HashMap<String, AbstractCommand> commandMap = new LinkedHashMap<>();
    private static Object[] argument;

    {
        this.register(new Help());
        this.register(new Info());
        this.register(new Show());
        this.register(new Insert());
        this.register(new Update());
        this.register(new RemoveKey());
        this.register(new Clear());
        this.register(new ExecuteScript());
        this.register(new Exit());
        this.register(new RemoveLower());
        this.register(new History());
        this.register(new RemoveLowerKey());
        this.register(new AverageOfStudentsCount());
        this.register(new FilterContainsName());
        this.register(new FilterGreaterThanFormOfEducation());
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
     */
     public static void execute(String commandName, Object[] args) throws WrongAmountOfArgsException, NoCommandException {
         AbstractCommand command = commandMap.get(commandName);
        if (command == null) {
            throw new NoCommandException(commandName);
        } else {
            command.execute(args);
        }
     }

     public static void executeResponse(Response response) {
        if (response.isMap()) {
            response.getMap().entrySet().stream().map(entry -> "Key: " + entry.getKey() + ", Value: " + entry.getValue().toString() + "\n\n").forEach(System.out::println);
        } else {
            System.out.println(response.getAnswer());
        }
     }

    public static void setArgument(Object[] argument) {
        CommandManager.argument = argument;
    }

    public void run(ConcreteCommand command) {
        command.setArgument(argument);
    }
}
