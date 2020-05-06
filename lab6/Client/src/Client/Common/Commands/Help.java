package Client.Common.Commands;

import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;

/**
 * Класс для задания конкретной команды help при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class Help extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public Help(){
        this.setCommandName("help");
        this.setText("Эта команда выводит справку по доступным командам");
        this.setArguments("");
    }

    /**
     * Переопредлеенный из абстрактного класса AbstractCommand метод,
     * @param args задает аргументы команды
     */
    @Override
    public void execute(Object[] args) {
        if (args.length != 0) throw new WrongAmountOfArgsException();
    }
}
