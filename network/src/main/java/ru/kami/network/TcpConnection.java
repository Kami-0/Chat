package ru.kami.network;

import ru.kami.constant.TcpConnectionConstants;

import java.io.*;
import java.net.Socket;

public class TcpConnection {
    private static final String LINE_SEPARATOR = "\n";
    private final Socket socket;
    private final Thread rxThread;
    private final BufferedWriter out;
    private final BufferedReader in;
    private final TcpConnectionListener eventListener;

    public TcpConnection(TcpConnectionListener eventListener, String ipAddress, int port) throws IOException {
        this(eventListener, new Socket(ipAddress, port));
    }

    public TcpConnection(TcpConnectionListener eventListener, Socket socket) throws IOException {
        this.socket = socket;
        this.eventListener = eventListener;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), TcpConnectionConstants.ENCODING));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), TcpConnectionConstants.ENCODING));
        rxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(TcpConnection.this);
                    while (!rxThread.isInterrupted()) {
                        eventListener.onReceiveString(TcpConnection.this, in.readLine());
                    }
                } catch (IOException ex) {
                    eventListener.onDisconnect(TcpConnection.this);
                }
            }
        });
        rxThread.start();
    }

    public synchronized void sendString(String value) {
        try {
            out.write(value + LINE_SEPARATOR);
            out.flush();
        } catch (IOException ex) {
            eventListener.onException(TcpConnection.this, ex);
            disconnect();
        }
    }

    private synchronized void disconnect() {
        rxThread.interrupt();
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            eventListener.onException(TcpConnection.this, ex);
        }
    }

    @Override
    public String toString() {
        return "TCPConnection: " + socket.getInetAddress() + ": " + socket.getPort();
    }
}
