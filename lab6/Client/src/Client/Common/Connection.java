package Client.Common;

import java.io.IOException;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private DataChange dataChange = new DataChange(this);//Вроде не за чем

    public Connection() {
        int port = 1444;
        try {
            this.socket = new Socket("localhost", port);
            System.out.println("Соединение с сервером успешно установлено");
        } catch (IOException e){
            System.out.println("Не удалось установить соединение: " + e.getMessage());
        }
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
}
