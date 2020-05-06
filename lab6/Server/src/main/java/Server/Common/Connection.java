package Server.Common;

import Common.ConcreteCommand;
import Common.Response;
import Server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Connection {
    private ServerSocketChannel serverSocketChannel;
    private static SocketChannel socketChannel;
    private int port;
    private static Response response = new Response();
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public Connection() {
        try {
            port = 1444;
            logger.info("Подключение к серверу доступно на порту {}", port);
            SocketAddress socketAddress = new InetSocketAddress(port);
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(socketAddress);
        } catch (IOException e){
            response.addLineToAnswer("Ошибка подключения: " + e.getMessage());
            logger.error("Ошибка подключения: {}", e.getMessage());
        }
    }

    public void connectToClient() {
        try {
            logger.info("Ожидание подключения на порт {}", getPort());
            socketChannel = getServerSocketChannel().accept();
            socketChannel.configureBlocking(false);
            logger.info("Соединение с клиентским приложением установлено");
        } catch (IOException e) {
            response.addLineToAnswer("Ошибка подключения: " + e.getMessage());
            logger.error("Ошибка подключения: {}: {}", e.getClass().toString().substring(6), e.getMessage());
        }
    }

    public static void sendObject(Response obj) {
        try {
            logger.info("Осуществляется сериализация и отправка объекта");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outStream = new ObjectOutputStream(byteArrayOutputStream);
            outStream.writeObject(obj);
            outStream.flush();
            ByteBuffer buf = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            if (socketChannel.isConnected() && buf.limit()!=0) {
                socketChannel.write(buf); //отправка
            }
        } catch (IOException e) {
            logger.error("Возникла ошибка при отправке ответа клиенту: {}", e.getMessage());
        }
    }

    public static ConcreteCommand receiveObject() {
        try {
            ByteBuffer buf = ByteBuffer.allocate(800000000);
            int n = 0;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((n = socketChannel.read(buf)) > 0) {
                logger.info("Осуществляется чтение запроса и десериализация объекта");
                buf.flip();
                outputStream.write(buf.array(), 0, n);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                ObjectInputStream inStream = new ObjectInputStream(inputStream);
                ConcreteCommand obj = (ConcreteCommand) inStream.readObject();
                buf.clear();
                return obj;
            }
            return null;
        } catch (IOException e) {
            logger.error("Возникла ошибка при чтении запроса с клиента: {}: {}", e.getClass().toString().substring(6),  e.getMessage());
            closeSocketChannel();
            return null;
        } catch (ClassNotFoundException e) {
            logger.error("Возникла ошибка сериализации: нужный класс не найден");
            return null;
        }
    }

    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public int getPort() {
        return port;
    }

    public static Response getResponse() {
        return response;
    }

    public static void closeSocketChannel(){
        try {
            logger.info("Закрытие сокета");
            socketChannel.close();
        } catch (IOException e) {
            logger.error("Не удалось закрыть сокет");
        }
    }
}
