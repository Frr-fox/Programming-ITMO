package Commands;

import Managers.*;
import java.io.IOException;

/**
 * Класс для задания конкретной команды remove_lower при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class RemoveLower extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public RemoveLower(Console console){
        this.setCommandName("remove_lower");
        this.setText("Удаляет из коллекции все элементы, меньшие, чем заданный");
        this.setArguments(" {element}");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#removeLower(String[])}
     * @param args задает аргументы команды
     * @throws IOException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(String... args) throws IOException {
        console.removeLower(args);
    }

}
