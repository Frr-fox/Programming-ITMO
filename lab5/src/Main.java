import java.io.IOException;
import java.util.NoSuchElementException;

import Managers.CollectionManager;
import Managers.IsEmptyException;
import Managers.WrongAmountOfArgsException;

/**
 * Главный класс программы, метод main котрого реализует работу консольного приложения для управления коллекцией объектов
 * в интерактивном режиме
 * @author Нечкасова Олеся
 */
public class Main {
    public static void main(String[] args) throws IOException, WrongAmountOfArgsException, IsEmptyException {
        CollectionManager.load(args);
        try {
            CollectionManager.start();
        } catch (NoSuchElementException e){
            System.exit(0);
        }
    }
}
