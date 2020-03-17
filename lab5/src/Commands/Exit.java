package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.WrongAmountOfArgsException;

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
        this.setCommandName("exit");
        this.setText("Завершает программу");
        this.setArguments("");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#exit()} ()}
     * @param args задает аргументы команды
     */
    @Override
    public void execute(String... args) {
        try {
            if (args.length == 0) {
                console.exit();
            } else {
                throw new WrongAmountOfArgsException();
            }
        } catch (WrongAmountOfArgsException e) {
            System.out.println(e.getMessage());
        }
    }
}
