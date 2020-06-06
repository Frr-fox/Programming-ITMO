package Client.Common;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
    private Socket socket;
    private DataChange dataChange = new DataChange(this);
    private String LOGIN;
    private String PASS;

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите логин: ");
        LOGIN = scanner.nextLine();
        System.out.println("Введите пароль: ");
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
}
