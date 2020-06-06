package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды show при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Show extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Show(Console console){
        super("show", "Выводит в стандартный поток вывода все элементы коллекции в строковом представлении", "");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#show()}
     */
    @Override
    public void execute(Object[] args) {
        console.show();
    }
}
