package Client.Common.Commands;

import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;

/**
 * Класс для задания конкретной команды history при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class History extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public History(){
        super("history", "Выводит последние 15 команд ", "");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     */
    @Override
    public void execute(Object[] args) {
        if (args.length != 0) throw new WrongAmountOfArgsException();
    }
}
