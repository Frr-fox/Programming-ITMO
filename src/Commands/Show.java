package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.WrongAmountOfArgsException;

/**
 * Класс для задания конкретной команды show при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Show extends AbstractCommand {
    Console console;

    /**
     * Конструтор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Show(Console console){
        this.setCommandName("show");
        this.setText("Выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.setArguments("");
        this.console = console;
    }

    /**
     * Переопредленный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#show()}
     * @param args задает аргументы команды
     */
    @Override
    public void execute(String... args) {
        try {
            if (args.length == 0) {
                console.show();
            } else {
                throw new WrongAmountOfArgsException();
            }
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
        }
    }
}
