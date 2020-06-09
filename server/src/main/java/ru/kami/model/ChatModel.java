package ru.kami.model;

import lombok.extern.slf4j.Slf4j;
import ru.kami.api.ChatTime;
import ru.kami.entity.ClientTcpConnection;
import ru.kami.model.exception.InvalidMessageException;
import ru.kami.parser.MessageParser;
import ru.kami.server.ChatServer;

import static ru.kami.code.CodeEnum.*;

@Slf4j
public class ChatModel {
    private ChatServer chatServer;

    public ChatModel(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    public synchronized void processMessage(ClientTcpConnection client, String value) {
        MessageParser messageString = new MessageParser(value);
        switch (messageString.getCode()) {
            case MESSAGE:
                processCodeMessage(
                        addTimeToMessage(
                                addUserNicknameToMessage(client, messageString.getMessage())));
                break;
            case NICK_NAME:
                processCodeNickName(client, messageString.getMessage());
                break;
            case EXCEPTION:
                processCodeException(client);
                break;
        }
    }

    /**
     * Добавляет к сообщению никнейм клиента
     *
     * @param client  конект клиента
     * @param message сообщение
     * @return строка с никнеймом клиента
     */
    private String addUserNicknameToMessage(ClientTcpConnection client, String message) {
        return client.getNickName() + ": " + message;
    }

    /**
     * Добавляет время к сообщению
     *
     * @param message сообщение
     * @return строку со временем
     */
    private String addTimeToMessage(String message) {
        return ChatTime.getInstance().getHourAndMinute() + " " + message;
    }

    /**
     * Обработать сообщение с кодом NICK_NAME
     *
     * @param client   конект клиента
     * @param nickName никнейм клиента
     */
    private synchronized void processCodeNickName(ClientTcpConnection client, String nickName) {
        boolean nickNameExists = chatServer.isExistNickName(nickName);
        if (nickNameExists) {
            chatServer.sendToConnection(client, BUSY_NICK_NAME.wrapString(nickName));
            chatServer.removeConnection(client);
        } else {
            chatServer.sendToConnection(client, SUCCESSFUL.wrapString(nickName));
            chatServer.sendToConnection(client, SERVER_DATA_TIME.wrapString(ChatTime.getInstance().convertToString()));
            chatServer.authorizeUser(client, nickName);
            chatServer.sendToAllConnections(ONLINE_USER.wrapString(nickName));
        }
    }

    /**
     * Обработать сообщение с кодом MESSAGE
     *
     * @param message конект клиента
     */
    private synchronized void processCodeMessage(String message) {
        chatServer.sendToAllConnections(MESSAGE.wrapString(message));
    }

    /**
     * Обработать сообщение с кодом EXCEPTION
     *
     * @param client конект клиента
     */
    private synchronized void processCodeException(ClientTcpConnection client) {
        chatServer.onException(client, new InvalidMessageException("No valid code found in message"));
        client.sendString(EXCEPTION.wrapString("Failed to send message."));
    }

}
