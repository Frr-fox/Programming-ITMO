package Client.Common.Commands;

import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;

/**
 * Класс для задания конкретной команды average_of_students_count при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class AverageOfStudentsCount extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public AverageOfStudentsCount(){
        this.setCommandName("average_of_students_count");
        this.setText("Выводит среднее значение поля studentsCount для всех элементов коллекции");
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
