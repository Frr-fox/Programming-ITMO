package Client.Common.Commands;

import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;

/**
 * Класс для задания конкретной команды info при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class Info extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public Info(){
        super("info", "Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)", "");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * @param args задает аргументы команды
     */
    @Override
    public void execute(Object[] args) {
        if (args.length != 0) throw new WrongAmountOfArgsException();
    }
}
