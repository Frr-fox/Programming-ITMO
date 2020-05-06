package Client.Common.Commands;

import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;

/**
 * Класс для задания конкретной команды show при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class Show extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public Show(){
        this.setCommandName("show");
        this.setText("Выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
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
