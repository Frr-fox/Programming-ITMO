package Server.Common.Commands;

import Common.AbstractCommand;
import Server.Console;


/**
 * Класс для задания конкретной команды save при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Save extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Save(Console console){
        super("save", "Сохраняет коллекцию в файл", "");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#save()}
     */
    @Override
    public void execute(Object[] args) {
        console.save();
    }
}
