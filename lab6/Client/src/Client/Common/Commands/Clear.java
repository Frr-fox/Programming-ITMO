package Client.Common.Commands;

import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;

/**
 * Класс для задания конкретной команды clear при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class Clear extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public Clear(){
        this.setCommandName("clear");
        this.setText("Очищает коллекцию");
        this.setArguments("");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     */
    @Override
    public void execute(Object[] args) {
        if (args.length != 0) throw new WrongAmountOfArgsException();
    }
}
