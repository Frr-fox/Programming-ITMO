package Server;

/**
 * Исключение, возникающее при обращении к пустой коллекции
 * @author Нечкасова Олеся
 */
public class IsEmptyException extends RuntimeException {
    IsEmptyException(){
        super("Коллекция пуста. Вы не можете выполнить требуемое действие. ");
    }
}
