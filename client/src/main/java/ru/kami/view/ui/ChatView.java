package ru.kami.view.ui;

public interface ChatView {
    void onConnectionReady();

    void onDisconnectAllUsers();

    void printMessage(String message);

    void onBusyNickname();

    void onSuccessfulAuthorization();

    void addUserOnline(String nickName);

    void addUserOffline(String nickName);

    void failedToConnectToServer();
}
