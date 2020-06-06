package Client;

public class NoCommandException extends RuntimeException {
    public NoCommandException(String commandName){super("Команды " + commandName + " нет в списке");}
}
