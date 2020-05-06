package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды remove_key при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class RemoveKey extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public RemoveKey(Console console){
        this.setCommandName("remove_key");
        this.setText("Удаляет элемент из коллекции по его ключу");
        this.setArguments(" key");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#removeKey(Object[] args)}
     */
    @Override
    public void execute(Object[] args) {
        console.removeKey(args);
    }
}
