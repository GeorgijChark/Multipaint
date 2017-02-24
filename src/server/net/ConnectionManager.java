package server.net;

import net.Message;
import ui.ChatPanel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ConnectionManager implements Runnable {
    private static int PORT = 2391;
    private ChatPanel chatPanel;
    private ArrayList<Integer> occupiedPort = new ArrayList<>();
    private ArrayList<ConnectionChanel> connections = new ArrayList<>();

    public ConnectionManager(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socketToClient = serverSocket.accept();
                connections.add(new ConnectionChanel(socketToClient, chatPanel, this));
                Thread connectionThread = new Thread(connections.get(connections.size() - 1));
                connectionThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resend(Message message) {
        for (ConnectionChanel connection : connections) {
            connection.sendMessage(message);
        }
    }

}
