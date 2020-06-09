package ru.kami.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.kami.network.TcpConnection;
import ru.kami.network.TcpConnectionListener;

import java.io.IOException;
import java.net.Socket;

@EqualsAndHashCode(callSuper = true)
public class ClientTcpConnection extends TcpConnection {
    private static final String NICK_NAME_DEFAULT = "None";
    @Setter
    @Getter
    private boolean hasAuthorization = false;
    @Setter
    @Getter
    private String nickName = NICK_NAME_DEFAULT;

    public ClientTcpConnection(TcpConnectionListener eventListener, Socket socket) throws IOException {
        super(eventListener, socket);
    }
}
