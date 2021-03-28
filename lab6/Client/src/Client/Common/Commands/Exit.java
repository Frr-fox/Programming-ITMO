package Client.Common.Commands;

import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;
import Common.ConcreteCommand;

import java.io.IOException;

import  static Client.Common.DataChange.*;

/**
 * Класс для задания конкретной команды exit при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class Exit extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public Exit(){
        super("exit", "Завершает программу", "");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     */
    @Override
    public void execute(Object... args) {
        if (args.length != 0) {
            throw new WrongAmountOfArgsException();
        } else {
            System.out.println("Работа завершена. Соединение с сервером прервано");
            ConcreteCommand concreteCommand = new ConcreteCommand(ConcreteCommand.parseCommandName("exit"), ConcreteCommand.parseCommandArg(""));
            try {
                sendObject(concreteCommand);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
}
