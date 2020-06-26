package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды exit при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Exit extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Exit(Console console){
        super("exit", "Завершает программу", "");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#exit(Object[] args)} ()}
     */
    @Override
    public void execute(Object[] args) {
        console.exit(args);
    }
}
