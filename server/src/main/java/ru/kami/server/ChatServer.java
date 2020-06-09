package ru.kami.server;

import lombok.extern.slf4j.Slf4j;
import ru.kami.api.Properties;
import ru.kami.entity.ClientTcpConnection;
import ru.kami.model.ChatModel;
import ru.kami.network.TcpConnection;
import ru.kami.network.TcpConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static ru.kami.code.CodeEnum.OFFLINE_USER;
import static ru.kami.code.CodeEnum.USER;

/**
 * Сервер чата
 *
 * @author Kami
 */
@Slf4j
public class ChatServer implements TcpConnectionListener {
    private ChatModel chatModel = new ChatModel(this);

    public static void main(String[] args) {
        new ChatServer();
    }

    private final List<ClientTcpConnection> connections = new CopyOnWriteArrayList<>();

    private ChatServer() {
        try (ServerSocket serverSocket = new ServerSocket(Properties.getPort())) {
            log.info("Server running on port {}", Properties.getPort());
            while (true) {
                try {
                    new ClientTcpConnection(this, serverSocket.accept());
                } catch (IOException ex) {
                    log.error("TCP connection exception: ", ex);
                }
            }
        } catch (IOException ex) {
            log.error("Server socket exception: ", ex);
        }
    }

    /**
     * Метод на подключение клиента к серверу
     * Добавляет клиента в пулл клиентов
     *
     * @param client конект клиента
     */
    @Override
    public synchronized void onConnectionReady(TcpConnection client) {
        connections.add((ClientTcpConnection) client);
        log.info("Client connected: {}", client);
    }

    /**
     * Метод для передачи строки клиентом серверу
     *
     * @param client конект клиента
     * @param value  строка которую клиент отправил серверу
     */
    @Override
    public synchronized void onReceiveString(TcpConnection client, String value) {
        ClientTcpConnection clientTcpConnection = (ClientTcpConnection) client;
        chatModel.processMessage(clientTcpConnection, value);

    }

    /**
     * Отправить клиенту список подключенных к серверу клиентов
     *
     * @param client конект клиента
     */
    private void sendClientsList(ClientTcpConnection client) {
        connections.stream()
                .filter(connection -> connection != client)
                .forEach(connection -> client.sendString(USER.wrapString(connection.getNickName())));
    }

    /**
     * Метод на дисконект клиента от сервера
     *
     * @param client конект клиента
     */
    @Override
    public synchronized void onDisconnect(TcpConnection client) {
        connections.remove(client);
        sendToAllConnections(OFFLINE_USER.wrapString(((ClientTcpConnection) client).getNickName()));
        log.info("Client disconnected: {}", client);
    }

    /**
     * Метод на ошибку клиента
     *
     * @param client конект клиента
     * @param ex     ошибка клиента
     */
    @Override
    public void onException(TcpConnection client, Exception ex) {
        log.error("Client {}, exception: ", client, ex);
    }

    /**
     * Метод для отправки сообщения пользователю
     *
     * @param messageString сообщение которое надо отправить пользователю
     */
    public synchronized void sendToConnection(ClientTcpConnection connection, String messageString) {
        connection.sendString(messageString);
    }

    /**
     * Метод для отправки сообщения всем пользователям
     *
     * @param messageString сообщение которое надо отправить всем пользователям
     */
    public synchronized void sendToAllConnections(String messageString) {
        connections.forEach(x -> x.sendString(messageString));
    }

    /**
     * Проверяет занят ли никнейм
     *
     * @param nickName проверяемый никнейм
     * @return true если существует, false если нет
     */
    public boolean isExistNickName(String nickName) {
        return connections.stream().anyMatch(connection -> connection.getNickName().equals(nickName));
    }

    /**
     * Удалить конект клиента из пулла конектов
     *
     * @param connection конект клиента
     */
    public void removeConnection(ClientTcpConnection connection) {
        connections.remove(connection);
    }

    /**
     * Авторизовать пользователя
     *
     * @param connection конект клиента
     */
    public void authorizeUser(ClientTcpConnection connection, String nickName) {
        connection.setHasAuthorization(true);
        connection.setNickName(nickName);
        sendClientsList(connection);
    }
}
