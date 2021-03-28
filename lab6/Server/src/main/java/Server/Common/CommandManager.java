package Server.Common;

import Common.AbstractCommand;
import Server.Common.Commands.*;
import Server.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static Server.Common.Connection.getResponse;

/**
 * Класс, предназначенный для вызова команд. В шаблоне Command выаолняющий роль invoker'a
 * @author Нечкасова Олеся
 */
public class CommandManager {
    public static HashMap<String, AbstractCommand> commandMap = new LinkedHashMap<>();
    public static LimitedQueue<String> queue = new LimitedQueue<String>(15);
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private CollectionManager collectionManager;
    private Console console;

    {
        CollectionManager collectionManager = new CollectionManager();
        this.collectionManager = JAXBWorker.load(collectionManager.getFilepath());
        console = new Console();
        console.setCollectionManager(this.collectionManager);
        logger.info("Инициализация команд");
        this.register(new Help(console));
        this.register(new Info(console));
        this.register(new Show(console));
        this.register(new Insert(console));
        this.register(new Update(console));
        this.register(new RemoveKey(console));
        this.register(new Clear(console));
//        this.register(new Save(console));
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
     * @param args обозначает аргументы команды
     * @throws IsEmptyException если коллекция пуста
     */
     public static void execute(String commandName, Object[] args) throws IsEmptyException {
         logger.info("Начато выполнение команды {}", commandName);
         queue.add(commandName);
         AbstractCommand command = commandMap.get(commandName);
         if (command == null){
             getResponse().addLineToAnswer("Команды " + commandName + " нет в списке");
         } else command.execute(args);
     }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public Console getConsole() {
        return console;
    }
}
