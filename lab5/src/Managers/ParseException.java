package Managers;

/**
 * Исключение, возникающее при парсинге неправильной структуры xml-файла
 * @author Нечкасова Олеся
 */
public class ParseException extends RuntimeException {
    public ParseException(){
        super("Xml файл записан неправильно");
    }
}
