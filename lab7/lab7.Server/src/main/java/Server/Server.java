package Server;

import Common.ConcreteCommand;
import Server.Common.CommandManager;
import Server.Common.Connection;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Server.Common.Connection.*;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("Начата работа сервера.");
        CommandManager commandManager = new CommandManager();
        Connection connection = new Connection();
        Scanner scanner = new Scanner(System.in);

        while (true){
            while (connection.getSocketChannel() != null) {
                ConcreteCommand concreteCommand = receiveObject();
                if (concreteCommand != null) {
                    CommandManager.execute(concreteCommand.getCommandName(), concreteCommand.getArgument());
                    if (connection.getSocketChannel() != null) sendObject(getResponse());
                    getResponse().clearAll();
                }
            }
            while (connection.getSocketChannel()==null){
                System.out.println("Введите команду: \nsave - для сохранения состояние коллекции\nexit - для закрытия сервера\nНажмите ENTER для ожидания подключений");
                String readLine = scanner.nextLine().trim().toLowerCase();
                switch (readLine){
                    case "save":
                        new Console(commandManager.getCollectionManager()).save();
                        System.out.println("Сохранено");
                        break;
                    case "exit":
                        System.exit(0);
                    default:
                        break;
                }
                connection.connectToClient();
            }
        }
    }
}
