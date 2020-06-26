package Common;

import java.io.Serializable;

public class Request implements Serializable {
    private ConcreteCommand concreteCommand;
    private String password;
    private String login;
    private boolean isCommand;
    private boolean isRegister;
    private boolean isLogin;
    public Request(ConcreteCommand concreteCommand,String log, String pass, boolean isCommand, boolean isRegister, boolean isLogin){
        setConcreteCommand(concreteCommand);
        password = pass;
        login = log;
        if(concreteCommand!=null) concreteCommand.addArgument(login);
        this.setCommand(isCommand);
        this.setRegister(isRegister);
        this.setLogin(isLogin);

    }

    public ConcreteCommand getConcreteCommand() {
        return concreteCommand;
    }

    public void setConcreteCommand(ConcreteCommand concreteCommand) {
        this.concreteCommand = concreteCommand;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        concreteCommand.addArgument(login);
    }

    public boolean isCommand() {
        return isCommand;
    }

    public void setCommand(boolean command) {
        isCommand = command;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
