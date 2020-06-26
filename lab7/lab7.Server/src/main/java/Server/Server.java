package Server;

import Common.StudyGroup.*;
import Server.Common.CommandManager;
import Server.Common.Connection;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) {
        logger.info("Начата работа сервера.");
        Console console = new Console(new CollectionManager());
        CommandManager commandManager = new CommandManager(console);
        Connection connection = new Connection();
        Scanner scanner = new Scanner(System.in);
        UserData userData = new UserData();
        ExecutorService fixedExecutor = Executors.newFixedThreadPool(5);
        ExecutorService cachedExecutor = Executors.newCachedThreadPool();
        DataBaseManager dataBaseManager = new DataBaseManager(commandManager.getCollectionManager());
        userData.loadUsers();

//        dataBaseManager.addElement(new StudyGroup("name", new Coordinates(2f, 5), 25L, FormOfEducation.DISTANCE_EDUCATION, Semester.FIFTH, new Person("Sasha", 65L, null, Color.YELLOW, Country.SPAIN, null), "log"));

        while (true){
            connection.connectToClient();
            if (connection.getSocketChannel() != null)
                fixedExecutor.execute(new ProcessingRequest(connection, userData, connection.getSocketChannel(), cachedExecutor));
        }
    }
}
