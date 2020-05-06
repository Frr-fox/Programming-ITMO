package Server;

import Common.ConcreteCommand;
import Server.Common.CommandManager;
import Server.Common.Connection;

import java.io.*;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Server.Common.Connection.*;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("Начата работа сервера.");
        CollectionManager.load(args);
        CommandManager commandManager = new CommandManager();
        Connection connection = new Connection();
        Scanner scanner = new Scanner(System.in);

        while (true){
            connection.connectToClient();
            while (connection.getSocketChannel().isConnected()) {
                ConcreteCommand concreteCommand = receiveObject(); //чтение запроса
                if (concreteCommand != null) {
                    CommandManager.execute(concreteCommand.getCommandName(), concreteCommand.getArgument());
                    sendObject(getResponse()); //отправка ответа
                    getResponse().clearAll(); //ошибки обрабатывать здесь
                }
                if (!connection.getSocketChannel().isConnected()) logger.info("Соединение с клиентом остановлено. ");
            }
            while (!connection.getSocketChannel().isConnected()){
                System.out.println("Для сохранения коллекции введите команду save, для закрытия приложения exit");
                String readLine = scanner.nextLine().trim().toLowerCase();
                switch (readLine){
                    case "save":
                        new Console().save();
                        System.out.println("Сохранено");
                        break;
                    case "exit":
                        System.exit(0);
                    default:
                        break;
                }
                if (readLine.equals("")) break;
            }
        }
    }
}
