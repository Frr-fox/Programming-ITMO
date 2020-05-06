package Client;

import Client.Common.CommandManager;
import Common.ConcreteCommand;
import Client.Common.Connection;
import Common.Response;

import static Common.ConcreteCommand.*;
import  static Client.Common.DataChange.*;

import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager = new CommandManager();
        String enteredLine = "";

        Connection connection = new Connection();

        while (connection.getSocket() != null && connection.getSocket().isConnected()) {
            System.out.print(">> ");
            enteredLine = scanner.nextLine().trim();
            if (enteredLine.equals("")) continue;
            ConcreteCommand concreteCommand = new ConcreteCommand(parseCommandName(enteredLine), parseCommandArg(enteredLine));
            try {
                CommandManager.execute(concreteCommand.getCommandName(), concreteCommand.getArgument());
                commandManager.run(concreteCommand);
                sendObject(concreteCommand); //отправка объекта ConcreteCommand на сервер
                Response[] objects = receiveAll();
                if (objects != null) {
                    for (Response response : objects) {
                        if (response != null) CommandManager.executeResponse(response);
                        else break;
                    }
                } else System.out.println("Возникла ошибка при передаче данных. Ответ от сервера не распознан");
            } catch (WrongAmountOfArgsException | NoCommandException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Введено некорректное значение");
            } catch (IOException e) {
                System.out.println("Сервер разорвал существующее соединение");
                System.exit(0);
            }
        }
    }
}