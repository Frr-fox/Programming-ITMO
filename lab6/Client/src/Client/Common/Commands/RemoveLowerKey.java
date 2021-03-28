package Client.Common.Commands;

import Client.Common.CommandManager;
import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;

/**
 * Класс для задания конкретной команды remove_lower_key при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class RemoveLowerKey extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public RemoveLowerKey(){
        super("remove_lower_key", "Удаляет из коллекции все элементы, ключ которых меньше, чем заданный", " key");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(Object[] args) throws WrongAmountOfArgsException {
        if (args.length != 1) {
            throw new WrongAmountOfArgsException();
        } else {
            int key = Integer.parseInt(args[0].toString());
            CommandManager.setArgument(new Object[]{key});
        }
    }
}
