package Client.Common.Commands;

import Common.AbstractCommand;
import Client.Common.CommandManager;
import Client.WrongAmountOfArgsException;

/**
 * Класс для задания конкретной команды execute_script при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class ExecuteScript extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public ExecuteScript(){
        this.setCommandName("execute_script");
        this.setText("Считывает и исполняет скрипт из указанного файла");
        this.setArguments(" file_name");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     */
    @Override
    public void execute(Object[] args) {
        if (args.length != 1) throw new WrongAmountOfArgsException();
        else {
            CommandManager.setArgument(new Object[]{args[0].toString()});
        }
    }
}
