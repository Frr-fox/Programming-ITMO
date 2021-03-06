package Managers;

/**
 * Абстрактный класс, расширяющий функционал интерфейса Command
 * полями commandName, text, arguments.
 * Классы, реализующие данный абстрактный класс, являются конкретными командами
 * при работе с поведенческим шаблоном Command
 * @author Нечкасова Олеся
 */
abstract public class AbstractCommand implements Command{
    /**Поле названия команды**/
    private String commandName;
    /**Поле описания действия команды**/
    private String text;
    /**Поле описания аргуметов для команды**/
    private String arguments;


    public String getCommandName(){
        return commandName;
    }

    public String getText(){
        return text;
    }

    public String getArguments() {
        return arguments;
    }

    protected void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    protected void setText(String text) {
        this.text = text;
    }

    protected void setArguments(String arguments) {
        this.arguments = arguments;
    }
}
