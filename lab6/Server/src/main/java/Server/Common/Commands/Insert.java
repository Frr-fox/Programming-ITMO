package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды insert при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Insert extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Insert(Console console){
        super("insert", "Добавляет новый элемент с заданным ключом", " key {element}");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#insert(Object[] args)}
     */
    @Override
    public void execute(Object[] args) {
        console.insert(args);
    }
}
