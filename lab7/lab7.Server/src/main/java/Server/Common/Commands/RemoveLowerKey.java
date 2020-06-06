package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды remove_lower_key при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class RemoveLowerKey extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public RemoveLowerKey(Console console){
        super("remove_lower_key", "Удаляет из коллекции все элементы, ключ которых меньше, чем заданный", " key");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#removeLowerKey(Object[] args)}
     */
    @Override
    public void execute(Object[] args) {
        console.removeLowerKey(args);
    }
}
