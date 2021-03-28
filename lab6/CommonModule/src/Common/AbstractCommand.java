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
    private final String commandName;
    /**Поле описания действия команды**/
    private final String text;
    /**Поле описания аргуметов для команды**/
    private final String arguments;

    public AbstractCommand(String commandName, String text, String arguments) {
        this.commandName = commandName;
        this.text = text;
        this.arguments = arguments;
    }

    public String getCommandName(){
        return commandName;
    }

    public String getText(){
        return text;
    }

    public String getArguments() {
        return arguments;
    }
}
