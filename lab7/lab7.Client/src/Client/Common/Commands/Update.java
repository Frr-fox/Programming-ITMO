package Client.Common.Commands;

import Client.Common.CommandManager;
import Client.WrongAmountOfArgsException;
import Common.AbstractCommand;
import Common.WorkWithObjects;

import java.io.*;

/**
 * Класс для задания конкретной команды update при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class Update extends AbstractCommand {
    transient private WorkWithObjects workWithObjects = new WorkWithObjects();
    transient private BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    transient private BufferedWriter writer = new BufferedWriter(new PrintWriter(System.out));

    /**
     * Конструктор для создания нового объекта
     */
    public Update(){
        super("update", "Обновляет значение элемента коллекции, id которого равен заданному", " id {element}");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     * @throws WrongAmountOfArgsException при неверном введенном количестве аргументов
     * @throws NumberFormatException при введенном некорректном значении
     */
    @Override
    public void execute(Object[] args) throws WrongAmountOfArgsException, NumberFormatException {
        if (args.length != 1) {
            throw new WrongAmountOfArgsException();
        } else {
            int key = Integer.parseInt(args[0].toString());
            try {
                CommandManager.setArgument(new Object[]{key, workWithObjects.addStudyGroup(scanner, writer)});
            } catch (IOException e) {
                System.out.println("Возникли проблемы при выводе данных: " + e.getMessage());
            }
        }
    }
}
