package Managers;

public class ParseException extends RuntimeException {
    public ParseException(){
        super("Xml файл записан неправильно");
    }
}
