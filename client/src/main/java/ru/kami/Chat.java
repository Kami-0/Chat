package ru.kami;

import ru.kami.controller.Controller;
import ru.kami.model.ClientTcpConnectionListener;
import ru.kami.view.ui.ChatWindow;

public class Chat {
    public static void main(String[] args) {
        ClientTcpConnectionListener clientTcpConnectionListener = new ClientTcpConnectionListener();
        Controller controller = new Controller(clientTcpConnectionListener);
        ChatWindow chatWindow = new ChatWindow(controller);
        clientTcpConnectionListener.attachView(chatWindow);
    }
}
