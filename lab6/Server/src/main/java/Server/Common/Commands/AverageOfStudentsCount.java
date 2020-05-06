package Server.Common.Commands;
import Common.AbstractCommand;
import Server.Console;
import Server.IsEmptyException;

/**
 * Класс для задания конкретной команды average_of_students_count при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class AverageOfStudentsCount extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public AverageOfStudentsCount(Console console){
        this.setCommandName("average_of_students_count");
        this.setText("Выводит среднее значение поля studentsCount для всех элементов коллекции");
        this.setArguments("");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#averageOfStudentsCount()}
     * @param args аргументы
     * @throws IsEmptyException если коллекция пуста
     */
    @Override
    public void execute(Object[] args) throws IsEmptyException {
        console.averageOfStudentsCount();
    }
}
