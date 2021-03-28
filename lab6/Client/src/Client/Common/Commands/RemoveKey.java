package Client.Common.Commands;

import Client.Common.CommandManager;
import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;

/**
 * Класс для задания конкретной команды remove_key при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class RemoveKey extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public RemoveKey(){
        super("remove_key", "Удаляет элемент из коллекции по его ключу", " key");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     * @throws NumberFormatException при введенном некорректном значении
     */
    @Override
    public void execute(Object[] args) throws WrongAmountOfArgsException, NumberFormatException {
        if (args.length != 1) {
            throw new WrongAmountOfArgsException();
        } else {
            int key = Integer.parseInt(args[0].toString());
            CommandManager.setArgument(new Object[]{key});
        }
    }
}
