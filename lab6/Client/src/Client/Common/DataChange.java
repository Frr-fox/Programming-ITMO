package Client.Common;

import Client.Common.Commands.Exit;
import Common.ConcreteCommand;
import Common.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataChange {
    private static Connection connection;

    public DataChange(Connection connection){
        DataChange.connection = connection;
    }

    public static void sendObject(ConcreteCommand obj) throws IOException {
        ObjectOutputStream outStream = new ObjectOutputStream(connection.getSocket().getOutputStream());
        outStream.writeObject(obj);
    }

    public static Object receiveObject() throws IOException {
        try {
            ObjectInputStream inStream = new ObjectInputStream(connection.getSocket().getInputStream());
            return inStream.readObject();
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
                Thread.sleep(10);
                if (connection.getSocket().getInputStream().available() > 0) inStream = new ObjectInputStream(connection.getSocket().getInputStream());
            }
            return arg;
        } catch (ClassNotFoundException | InterruptedException e){
            System.out.println("Возникли проблемы с сериализацией: " + e.getMessage());
            return null;
        }
    }
}
