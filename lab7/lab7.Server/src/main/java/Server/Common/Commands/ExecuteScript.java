package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

/**
 * Класс для задания конкретной команды execute_script при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class ExecuteScript extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public ExecuteScript(Console console){
        super("execute_script", "Считывает и исполняет скрипт из указанного файла", " file_name");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#executeScript(Object[] args)} ()}
     */
    @Override
    public void execute(Object[] args){
        console.executeScript(args);
    }
}
