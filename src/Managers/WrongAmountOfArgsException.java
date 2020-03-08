package Managers;

/**
 * Исключение, возникающее при введении неверного количества аргументов
 * @author Нечкасова Олеся
 */
public class WrongAmountOfArgsException extends RuntimeException{
    public WrongAmountOfArgsException(){
        super("Введено неверное количество аргументов команды. ");
    }
}
