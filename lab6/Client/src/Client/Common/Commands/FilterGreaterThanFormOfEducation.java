package Client.Common.Commands;

import Client.Common.CommandManager;
import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;
import Common.StudyGroup.FormOfEducation;

/**
 * Класс для задания конкретной команды filter_greater_than_form_of_education при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class FilterGreaterThanFormOfEducation extends AbstractCommand {

    /**
     * Конструктор для создания нового объекта
     */
    public FilterGreaterThanFormOfEducation() {
        super("filter_greater_than_form_of_education", "Выводит элементы, значение поля formOfEducation которых больше заданного", " formOfEducation(DISTANCE_EDUCATION, FULL_TIME_EDUCATION, EVENING_CLASSES)");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     * @throws NumberFormatException при введенном некорректном значении
     */
    @Override
    public void execute(Object[] args) throws WrongAmountOfArgsException {
        if (args.length != 1) throw new WrongAmountOfArgsException();
        else {
            FormOfEducation formOfEducation = FormOfEducation.parse(args[0].toString());
            if (FormOfEducation.parse(args[0].toString()) == null) throw new NumberFormatException();
            CommandManager.setArgument(new Object[]{formOfEducation});
        }
    }
}
