package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.IsEmptyException;
import Managers.WrongAmountOfArgsException;

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
        this.setCommandName("filter_contains_name");
        this.setText("Выводит элементы, значение поля name которых содержит заданную подстроку");
        this.setArguments(" name");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#filterContainsName(String...)} ()}
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     * @throws IsEmptyException если коллекция пуста
     */
    @Override
    public void execute(String... args) throws WrongAmountOfArgsException, IsEmptyException {
        console.filterContainsName(args);
    }
}
