package ru.kami.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.kami.code.CodeEnum;
import ru.kami.model.api.ChatTime;
import ru.kami.network.TcpConnection;
import ru.kami.network.TcpConnectionListener;
import ru.kami.parser.MessageParser;
import ru.kami.view.ui.ChatView;

import java.io.IOException;

@Slf4j
public class ClientTcpConnectionListener implements TcpConnectionListener {
    private final ChatWindowNotifier chatWindowNotifier = new ChatWindowNotifier();
    private int port;
    private String ipAddress;
    private String nickName;
    private int hourlyDifference;

    @Getter
    private TcpConnection server;

    /**
     * Добавить новый view
     *
     * @param chatView view
     */
    public void attachView(ChatView chatView) {
        chatWindowNotifier.attachView(chatView);
    }

    /**
     * Метод на подлючение к серверу
     *
     * @param tcpConnection конект сервера
     */
    @Override
    public void onConnectionReady(TcpConnection tcpConnection) {
        chatWindowNotifier.notifyViewConnectionReady();
        server.sendString(CodeEnum.NICK_NAME.wrapString(nickName));
        log.info("Connect to server IP address: {} port: {}", ipAddress, port);
    }

    public void sendMessageToServer(String message) {
        if (server != null) {
            server.sendString(CodeEnum.MESSAGE.wrapString(message));
        }
    }

    /**
     * Метод для обработки сообщения перед отправкой в чат
     *
     * @param tcpConnection конект сервера
     * @param value         строка от сервера
     */
    @Override
    public void onReceiveString(TcpConnection tcpConnection, String value) {
        if (server == null) {
            value = CodeEnum.EXCEPTION.wrapString(value);
        }
        MessageParser message = new MessageParser(value);
        switch (message.getCode()) {
            case BUSY_NICK_NAME:
                processCodeBusyNickname();
                break;
            case SUCCESSFUL:
                processCodeSuccessful();
                break;
            case SERVER_DATA_TIME:
                processCodeServerDataTime(message.getMessage());
                break;
            case USER:
                processCodeUser(message.getMessage());
                break;
            case ONLINE_USER:
                processCodeOnlineUser(message.getMessage());
                break;
            case OFFLINE_USER:
                processCodeOfflineUser(message.getMessage());
                break;
            case MESSAGE:
                processCodeMessage(message.getMessage());
                break;
            case EXCEPTION:
                if (server == null) {
                    break;
                }
                chatWindowNotifier.notifyViewAboutSendingMessage(message.getMessage(), server);
                break;
        }
    }

    /**
     * Обработать сообщение с кодом BUSY_NICK_NAME
     */
    private void processCodeBusyNickname() {
        chatWindowNotifier.notifyViewAboutBusyNickname();
    }

    /**
     * Обработать сообщение с кодом SUCCESSFUL
     */
    private void processCodeSuccessful() {
        chatWindowNotifier.notifyViewAboutSuccessfulAuthorization();
    }

    /**
     * Обработать сообщение с кодом MESSAGE
     */
    private void processCodeMessage(String message) {
        int separatorIndex = message.indexOf(":");
        //Если не найдена подстрока
        if (separatorIndex == -1) {
            chatWindowNotifier.notifyViewAboutSendingMessage("Error message from server", server);
            log.error("Error message from server, message {}", message);
        } else {
            int beginIndex = 0;
            int serverHour = Integer.parseInt(message.substring(beginIndex, separatorIndex));
            int hoursPerDay = 24;
            int clientHour = (serverHour + hourlyDifference) % hoursPerDay;
            chatWindowNotifier.notifyViewAboutSendingMessage(clientHour + message.substring(separatorIndex), server);
        }
    }

    /**
     * Обработать сообщение с кодом SERVER_DATA_TIME
     *
     * @param dataTime время которое прислал сервер
     */
    private void processCodeServerDataTime(String dataTime) {
        ChatTime chatTime = ChatTime.valueOf(dataTime);
        hourlyDifference = ChatTime.getInstance().calculateDifferenceHours(chatTime);
    }

    /**
     * Обработать сообщение с кодом USER
     */
    private void processCodeUser(String nickName) {
        chatWindowNotifier.notifyViewAboutNewUserOnline(nickName);
    }

    /**
     * Обработать сообщение с кодом ONLINE_USER
     *
     * @param nickName имя пользователя
     */
    private synchronized void processCodeOnlineUser(String nickName) {
        chatWindowNotifier.notifyViewAboutNewUserOnline(nickName);
        chatWindowNotifier.notifyViewAboutSendingMessage("Client connected: " + nickName, server);
    }

    /**
     * Обработать сообщение с кодом OFFLINE_USER
     *
     * @param nickName имя пользователя
     */
    private synchronized void processCodeOfflineUser(String nickName) {
        chatWindowNotifier.notifyViewAboutNewUserOffline(nickName);
        chatWindowNotifier.notifyViewAboutSendingMessage("Client disconnected: " + nickName, server);
    }

    /**
     * Метод на дисконект сервера
     *
     * @param tcpConnection конект сервера
     */
    @Override
    public synchronized void onDisconnect(TcpConnection tcpConnection) {
        chatWindowNotifier.notifyViewAboutSendingMessage("Connection close", server);
        server = null;
        chatWindowNotifier.notifyViewAboutDisconnectAllUsers();
        while (!connectToServer(ipAddress, port)) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * Метод на ошибку сервера
     *
     * @param tcpConnection конект сервера
     * @param ex            ошибка
     */
    @Override
    public void onException(TcpConnection tcpConnection, Exception ex) {
        chatWindowNotifier.notifyViewAboutSendingMessage("Connection exception: " + ex, server);
    }

    /**
     * Метод производящий подключение к серверу
     *
     * @param ipAddress ip сервера
     * @param port      порт сервера
     * @return true если подлючение успешно, false если не удачно
     */
    private boolean connectToServer(String ipAddress, int port) {
        try {
            server = new TcpConnection(this, ipAddress, port);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Метод производящий подключение к серверу из превью
     *
     * @param ipAddress ip сервера
     * @param port      порт сервера
     * @param nickName  никнейм
     */
    public void connectToServerOfPreview(String ipAddress, int port, String nickName) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.nickName = nickName;
        if (!connectToServer(ipAddress, port)) {
            chatWindowNotifier.notifyViewAboutNewFailedToConnectToServer();
        }
    }
}
