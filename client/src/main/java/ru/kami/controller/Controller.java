package ru.kami.controller;

import lombok.AllArgsConstructor;
import ru.kami.model.ClientTcpConnectionListener;

@AllArgsConstructor
public class Controller {
    private final ClientTcpConnectionListener clientTcpConnectionListener;

    public void handleUserClickedOnConnectionToServer(String ipAddress, int port, String nickName) {
        clientTcpConnectionListener.connectToServerOfPreview(ipAddress, port, nickName);
    }

    public void handleUserClickedOnSendToMessage(String value) {
        clientTcpConnectionListener.sendMessageToServer(value);
    }
}