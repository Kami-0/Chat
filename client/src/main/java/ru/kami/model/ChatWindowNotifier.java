package ru.kami.model;

import lombok.NoArgsConstructor;
import ru.kami.network.TcpConnection;
import ru.kami.view.ui.ChatView;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
class ChatWindowNotifier {
    private final List<ChatView> chatViewArrayList = new ArrayList<>();

    void attachView(ChatView chatWindow) {
        chatViewArrayList.add(chatWindow);
    }

    void notifyViewConnectionReady() {
        chatViewArrayList.forEach(ChatView::onConnectionReady);
    }

    void notifyViewAboutSendingMessage(String message, TcpConnection server) {
        //Проверка есть ли подключение к серверу
        if (server == null) {
            return;
        }
        chatViewArrayList.forEach(chatView -> chatView.printMessage(message));
    }

    void notifyViewAboutBusyNickname() {
        chatViewArrayList.forEach(ChatView::onBusyNickname);
    }

    void notifyViewAboutSuccessfulAuthorization() {
        chatViewArrayList.forEach(ChatView::onSuccessfulAuthorization);
    }

    void notifyViewAboutNewUserOnline(String nickName) {
        chatViewArrayList.forEach(chatView -> chatView.addUserOnline(nickName));
    }

    void notifyViewAboutNewUserOffline(String nickName) {
        chatViewArrayList.forEach(chatView -> chatView.addUserOffline(nickName));
    }

    void notifyViewAboutDisconnectAllUsers() {
        chatViewArrayList.forEach(ChatView::onDisconnectAllUsers);
    }

    void notifyViewAboutNewFailedToConnectToServer() {
        chatViewArrayList.forEach(ChatView::failedToConnectToServer);
    }
}
