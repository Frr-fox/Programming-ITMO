package Common;

import java.io.Serializable;

/**
 * Абстрактный класс, расширяющий функционал интерфейса Client.Common.Command
 * полями commandName, text, arguments.
 * Классы, реализующие данный абстрактный класс, являются конкретными командами
 * при работе с поведенческим шаблоном Client.Common.Command
 * @author Нечкасова Олеся
 */
abstract public class AbstractCommand implements Command, Serializable {
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
