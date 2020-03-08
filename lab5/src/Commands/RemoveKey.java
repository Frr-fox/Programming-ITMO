package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.WrongAmountOfArgsException;

/**
 * Класс для задания конкретной команды remove_key при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class RemoveKey extends AbstractCommand {
    Console console;

    /**
     * Конструтор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public RemoveKey(Console console){
        this.setCommandName("remove_key");
        this.setText("Удаляет элемент из коллекции по его ключу");
        this.setArguments(" key");
        this.console = console;
    }

    /**
     * Переопредленный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#removeKey(String...)}
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(String... args) throws WrongAmountOfArgsException {
        console.removeKey(args);
    }
}
