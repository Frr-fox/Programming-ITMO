package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды clear при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Clear extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Clear(Console console){
        super("clear", "Очищает коллекцию", "");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#clear()}
     */
    @Override
    public void execute(Object[] args) {
        console.clear(args);
    }
}
