package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды history при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class History extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public History(Console console){
        this.setCommandName("history");
        this.setText("Выводит последние 15 команд ");
        this.setArguments("");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#history()}
     */
    @Override
    public void execute(Object[] args) {
        console.history();
    }
}
