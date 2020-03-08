package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.IsEmptyException;

import java.io.IOException;

/**
 * Класс для задания конкретной команды execute_script при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class ExecuteScript extends AbstractCommand {
    Console console;

    /**
     * Конструтор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public ExecuteScript(Console console){
        this.setCommandName("execute_script");
        this.setText("Считывает и исполняет скрипт из указанного файла");
        this.setArguments(" file_name");
        this.console = console;
    }

    /**
     * Переопредленный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#executeScript(String[])} ()}
     * @param args задает аргументы команды
     * @throws IOException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(String... args) throws IOException {
        console.executeScript(args);
    }
}
