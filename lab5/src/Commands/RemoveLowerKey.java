package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.WrongAmountOfArgsException;

/**
 * Класс для задания конкретной команды remove_lower_key при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class RemoveLowerKey extends AbstractCommand {
    Console console;

    /**
     * Конструтор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public RemoveLowerKey(Console console){
        this.setCommandName("remove_lower_key");
        this.setText("Удаляет из коллекции все элементы, ключ которых меньше, чем заданный");
        this.setArguments(" key");
        this.console = console;
    }

    /**
     * Переопредленный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#removeLowerKey(String...)}
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(String... args) throws WrongAmountOfArgsException {
        console.removeLowerKey(args);
    }
}
