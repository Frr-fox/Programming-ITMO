package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;
import Server.IsEmptyException;

/**
 * Класс для задания конкретной команды filter_contains_name при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class FilterContainsName extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public FilterContainsName(Console console) {
        super("filter_contains_name", "Выводит элементы, значение поля name которых содержит заданную подстроку", " name");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#filterContainsName(Object[] args)} ()}
     * @throws IsEmptyException если коллекция пуста
     */
    @Override
    public void execute(Object[] args) throws IsEmptyException {
        console.filterContainsName(args);
    }
}
