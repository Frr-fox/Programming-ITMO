package Commands;

import Managers.AbstractCommand;
import Managers.Console;
import Managers.WrongAmountOfArgsException;

/**
 * Класс для задания конкретной команды filter_greater_than_form_of_education при работе с шаблоном Command
 * @author Нечкасова Олеся
 */
public class FilterGreaterThanFormOfEducation extends AbstractCommand {
    Console console;

    /**
     * Конструтор для создания нового объекта
     * @param console объект, выполняющий роль receiver'a
     */
    public FilterGreaterThanFormOfEducation(Console console) {
        this.setCommandName("filter_greater_than_form_of_education");
        this.setText("\nВыводит элементы, значение поля formOfEducation которых больше заданного");
        this.setArguments(" formOfEducation(DISTANCE_EDUCATION, FULL_TIME_EDUCATION, EVENING_CLASSES)");
        this.console = console;
    }

    /**
     * Переопредленный из абстрактного класса AbstractCommand метод,
     * позволяющий перенести конечную реализацию команды в метод {@link Console#filterGreaterThanFormOfEducation(String...)}
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     */
    @Override
    public void execute(String... args) throws WrongAmountOfArgsException {
        console.filterGreaterThanFormOfEducation(args);
    }
}
