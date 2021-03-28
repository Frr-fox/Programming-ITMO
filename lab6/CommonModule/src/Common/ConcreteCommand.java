package Common;

import java.io.Serializable;
import Common.AbstractCommand;

public class ConcreteCommand extends AbstractCommand implements Serializable{
    private Object[] argument;

    public ConcreteCommand(String commandName, Object[] arg){
        super(commandName, "", "");
        this.argument = arg;
    }

    @Override
    public void execute(Object[] args) {}

    public Object[] getArgument() {
        return argument;
    }

    public void setArgument(Object[] argument) {
        this.argument = argument;
    }

    public static String parseCommandName(String enteredLine){
        String[] userCommand = enteredLine.replaceAll("\\s+", " ").split(" ");
        return userCommand[0].toLowerCase();
    }

    public static Object[] parseCommandArg(String enteredLine){
        String[] userCommand = enteredLine.replaceAll("\\s+", " ").split(" ");
        Object[] argument = new String[userCommand.length - 1];
        System.arraycopy(userCommand, 1, argument, 0, userCommand.length - 1);
        return argument;
    }
}
