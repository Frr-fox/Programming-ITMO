package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.WrongAmountOfArgsException;

/**
 * Класс для задания конкретной команды insert при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Insert extends AbstractCommand {
    Console console;

    /**
     * Конструтор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Insert(Console console){
        this.setCommandName("insert");
        this.setText("Добавляет новый элемент с заданным ключом");
        this.setArguments(" key {element}");
        this.console = console;
    }

    /**
     * Переопредленный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#insert(String...)}
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(String... args) throws WrongAmountOfArgsException {
        console.insert(args);
    }

}
