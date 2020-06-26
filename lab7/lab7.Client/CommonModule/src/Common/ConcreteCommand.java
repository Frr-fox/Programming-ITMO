package Common;

import java.io.Serializable;
import java.lang.reflect.Array;

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
    public void addArgument(Object arguments){
        if (argument != null){
            argument = concatenate(argument,new Object[]{arguments});
        } else argument = new Object[]{arguments};
    }
    public <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
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
