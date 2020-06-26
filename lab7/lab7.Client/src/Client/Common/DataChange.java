package Client.Common;

import Common.Request;
import Common.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataChange {
    private static Connection connection;

    public DataChange(Connection connection){
        DataChange.connection = connection;
    }

    public static void sendObject(Request obj) throws IOException {
        ObjectOutputStream outStream = new ObjectOutputStream(connection.getSocket().getOutputStream());
        outStream.writeObject(obj);
    }

    public static Response receiveObject() throws IOException {
        try {
            ObjectInputStream inStream = new ObjectInputStream(connection.getSocket().getInputStream());
            return (Response) inStream.readObject();
        } catch (ClassNotFoundException e){
            System.out.println("Возникли проблемы с сериализацией: " + e.getMessage());
            return null;
        }
    }

    public static Response[] receiveAll() throws IOException {
        Response[] arg = new Response[20];
        try {
            ObjectInputStream inStream = new ObjectInputStream(connection.getSocket().getInputStream());
            for (int i=0; connection.getSocket().getInputStream().available() > 0; i++) {
                arg[i] = (Response) inStream.readObject();
                Thread.sleep(450);
                if (connection.getSocket().getInputStream().available() > 0) inStream = new ObjectInputStream(connection.getSocket().getInputStream());
            }
            return arg;
        } catch (ClassNotFoundException | InterruptedException e){
            System.out.println("Возникли проблемы с сериализацией: " + e.getMessage());
            return null;
        }
    }
}
