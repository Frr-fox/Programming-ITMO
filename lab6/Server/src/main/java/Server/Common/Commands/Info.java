package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды info при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Info extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Info(Console console){
        this.setCommandName("info");
        this.setText("Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов)");
        this.setArguments("");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#info()}
     */
    @Override
    public void execute(Object[] args) {
        console.info();
    }
}
