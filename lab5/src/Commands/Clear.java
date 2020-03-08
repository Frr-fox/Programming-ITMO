package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.IsEmptyException;
import Managers.WrongAmountOfArgsException;

/**
 * Класс для задания конкретной команды clear при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Clear extends AbstractCommand {
    Console console;

    /**
     * Конструтор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Clear(Console console){
        this.setCommandName("clear");
        this.setText("Очищает коллекцию");
        this.setArguments("");
        this.console = console;
    }

    /**
     * Переопредленный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#clear()}
     * @param args задает аргументы команды
     */
    @Override
    public void execute(String... args) {
        try {
            if (args.length == 0) {
                console.clear();
            } else {
                throw new WrongAmountOfArgsException();
            }
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
        }
    }
}
