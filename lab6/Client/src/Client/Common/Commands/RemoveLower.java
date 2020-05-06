package Client.Common.Commands;

import Client.Common.CommandManager;
import Common.AbstractCommand;
import Client.WrongAmountOfArgsException;
import Common.WorkWithObjects;

import java.io.*;

/**
 * Класс для задания конкретной команды remove_lower при работе с шаблоном Common.Command
 * @author Нечкасова Олеся
 */
public class RemoveLower extends AbstractCommand {
    transient private WorkWithObjects workWithObjects = new WorkWithObjects();
    transient private BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    transient private BufferedWriter writer = new BufferedWriter(new PrintWriter(System.out));

    /**
     * Конструктор для создания нового объекта
     */
    public RemoveLower(){
        this.setCommandName("remove_lower");
        this.setText("Удаляет из коллекции все элементы, меньшие, чем заданный");
        this.setArguments(" {element}");
    }

    /**
     * Переопределенный из абстрактного класса AbstractCommand метод
     * @param args задает аргументы команды
     */
    @Override
    public void execute(Object[] args) {
        if (args.length != 0) throw new WrongAmountOfArgsException();
        else {
            try {
                CommandManager.setArgument(new Object[]{workWithObjects.addStudyGroup(scanner, writer)});
            } catch (IOException e) {
                System.out.println("Возникли проблемы при выводе данных: " + e.getMessage());
            }
        }
    }
}
