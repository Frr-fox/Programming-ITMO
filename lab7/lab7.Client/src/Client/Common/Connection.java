package Client.Common;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
    private Socket socket;
    private DataChange dataChange = new DataChange(this);
    private String LOGIN;
    private String PASS;
    private boolean isRegistered;
    private boolean isCommand;
    private String registered;

    public Connection() {
        int port = 1444;
        try {
            this.socket = new Socket("localhost", port);
            System.out.println("Соединение с сервером успешно установлено");
            login();
        } catch (IOException e){
            System.out.println("Не удалось установить соединение: " + e.getMessage());
        }
    }

    public void login(){
        System.out.println("Для работы с базой данных необходима авторизация.");
        System.out.println("Выберите один из вариантов:");
        System.out.println("( 1 ) Я новенький");
        System.out.println("( 2 ) Я уже смешарик");
        Scanner scanner = new Scanner(System.in);
        registered = scanner.nextLine().trim();
        switch (registered){
            case "1":
                setRegistered(false);
                setCommand(false);
                System.out.println("Тогда нужно зарегистрироваться");
                break;
            case "2":
                setRegistered(true);
                setCommand(false);
                System.out.println("Тогда нужно залогиниться");
                break;
            default:
                System.out.println("Введите 1 или 2");
                login();
        }
        System.out.print("Введите логин: ");
        LOGIN = scanner.nextLine();
        System.out.print("Введите пароль: ");
        PASS = scanner.nextLine();
    }

    public Socket getSocket() {
        return socket;
    }

    public DataChange getDataChange() {
        return dataChange;
    }

    public void setDataChange(DataChange dataChange) {
        this.dataChange = dataChange;
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getPASS() {
        return PASS;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isCommand() {
        return isCommand;
    }

    public void setCommand(boolean command) {
        isCommand = command;
    }
}
