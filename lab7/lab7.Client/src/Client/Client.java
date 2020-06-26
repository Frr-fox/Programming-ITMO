package Client;

import Client.Common.CommandManager;
import Client.Common.Connection;
import Common.ConcreteCommand;
import Common.Request;
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
        boolean started = false;
        Connection connection   = new Connection();
        while (connection.getSocket() != null && connection.getSocket().isConnected()) {
            try {
                if(!started) {
                    Request request = new Request(null, connection.getLOGIN(), connection.getPASS(), connection.isCommand(), !connection.isRegistered(), connection.isRegistered());
                    sendObject(request);
                    Response response = receiveObject();
                    started = true;
                    if (response != null && !response.isAccess()) {
                        System.out.println("Такого пользователя нет в системе, войдите для выполнения команд");
                        connection.login();
                    } else if (response != null && response.isAccess()){
                        CommandManager.executeResponse(response);
                    } else break;
                }
                System.out.print(">> ");
                enteredLine = scanner.nextLine().trim();
                if (enteredLine.equals("")) continue;
                ConcreteCommand concreteCommand = new ConcreteCommand(parseCommandName(enteredLine), parseCommandArg(enteredLine));
                CommandManager.execute(concreteCommand.getCommandName(), concreteCommand.getArgument());
                commandManager.run(concreteCommand);
                Request command = new Request(concreteCommand,connection.getLOGIN(),connection.getPASS(),true,false,false);
                sendObject(command);
                Response[] objects = receiveAll();
                if (objects != null) {
                    for (Response response : objects) {
                        if (response != null) CommandManager.executeResponse(response);
                        else break;
                    }
                }
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