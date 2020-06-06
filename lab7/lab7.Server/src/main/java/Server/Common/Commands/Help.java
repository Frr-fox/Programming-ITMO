package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды help при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Help extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Help(Console console){
        super("help", "Эта команда выводит справку по доступным командам", "");
        this.console = console;
    }

    /**
     * Переопредлеенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#help()}
     */
    @Override
    public void execute(Object[] args) {
        console.help();
    }
}
