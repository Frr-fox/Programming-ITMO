package Server;

import Common.Request;
import Common.ConcreteCommand;
import Server.Common.CommandManager;
import Server.Common.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;

import static Server.Common.Connection.*;
import static Server.Common.Connection.getResponse;

public class ProcessingRequest implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    Connection connection;
    UserData userData;
    SocketChannel socketChannel;
    ExecutorService cachedExecutor;

    public ProcessingRequest(Connection connection, UserData userData, SocketChannel socketChannel, ExecutorService cachedExecutor) {
        this.connection = connection;
        this.userData = userData;
        this.socketChannel = socketChannel;
        this.cachedExecutor = cachedExecutor;
    }


    private synchronized void executeRequest(Request request){
        request.setLogin(request.getLogin());
        Object[] mass = concatenate(request.getConcreteCommand().getArgument(), new Object[]{socketChannel});
        System.out.println(Arrays.toString(mass));
        CommandManager.execute(request.getConcreteCommand().getCommandName(), mass);
        notify();
    }

    private synchronized void sendResult(){
        while (getResponse().getAnswer().equals("") && getResponse().getMap() == null){
            try {
                wait();
            } catch (InterruptedException e){
                logger.warn("Прерывание потока");
            }
        } sendObject(getResponse(), socketChannel);
        getResponse().clearAll();
    }

    @Override
    public void run() {
        logger.info("Начата обработка запросов с клиента");
        while (!Thread.currentThread().isInterrupted()) {
            Request request = receiveObject(socketChannel);
            if (request != null) {
                if (request.isRegister()) {
                    logger.info("Начата регистрация");
                    userData.add(request.getLogin(), request.getPassword());
                    getResponse().addLineToAnswer(userData.answer);
                    getResponse().setAccess(userData.isAccess);
                } else if (request.isLogin()) {
                    logger.info("Начат логин");
                    userData.check(request.getLogin(), request.getPassword());
                    getResponse().addLineToAnswer(userData.answer);
                    getResponse().setAccess(userData.isAccess);
                } else if (request.isCommand() && userData.check(request.getLogin(), request.getPassword())) {
                    logger.info("Начата обработка команды");
                    getResponse().setAccess(userData.isAccess);
                    cachedExecutor.execute(()-> executeRequest(request));
                } else {
                    getResponse().addLineToAnswer("Команда не прошла авторизацию");
                    getResponse().setAccess(userData.isAccess);
                }
                if (socketChannel != null) new Thread(this::sendResult).start();
            }
        }
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
}
