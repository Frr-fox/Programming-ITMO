package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.WrongAmountOfArgsException;

/**
 * Класс для задания конкретной команды update при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class Update extends AbstractCommand {
    Console console;

    /**
     * Конструктор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public Update(Console console){
        this.setCommandName("update");
        this.setText("Обновляет значение элемента коллекции, id которого равен заданному");
        this.setArguments(" id {element}");
        this.console = console;
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#update(String...)}
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(String... args) throws WrongAmountOfArgsException {
        console.update(args);
    }

}
