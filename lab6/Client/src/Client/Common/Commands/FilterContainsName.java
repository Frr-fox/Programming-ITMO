package Client.Common.Commands;

import Client.Common.CommandManager;
import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;

/**
 * Класс для задания конкретной команды filter_contains_name при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class FilterContainsName extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public FilterContainsName() {
        this.setCommandName("filter_contains_name");
        this.setText("Выводит элементы, значение поля name которых содержит заданную подстроку");
        this.setArguments(" name");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(Object[] args) throws WrongAmountOfArgsException {
        if (args.length != 1) throw new WrongAmountOfArgsException();
        else CommandManager.setArgument(new Object[]{args[0].toString()});
    }
}
