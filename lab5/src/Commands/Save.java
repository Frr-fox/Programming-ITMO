package Commands;

import Managers.AbstractCommand;
import Managers.Console;

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
        this.setCommandName("save");
        this.setText("Сохраняет коллекцию в файл");
        this.setArguments("");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#save()}
     * @param args задает аргументы команды
     */
    @Override
    public void execute(String... args) {
        console.save();
    }
}
