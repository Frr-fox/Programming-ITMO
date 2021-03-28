package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;

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
        super("remove_lower", "Удаляет из коллекции все элементы, меньшие, чем заданный", " {element}");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#removeLower(Object[] args}
     * @param socketChannel обозначает сокет для передачи данных
     * @throws IOException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(Object[] args) {
        console.removeLower(args);
    }

}
