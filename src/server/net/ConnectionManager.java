package server.net;

import net.Message;
import ui.ChatPanel;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ConnectionManager implements Runnable {
    private static int port = 2391;
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
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socketToClient = serverSocket.accept();
                System.out.println("new client " + socketToClient.getInetAddress());
                PrintWriter netPrintWriter = new PrintWriter(socketToClient.getOutputStream(), true);
                int port = generatePort();
                System.out.println(ConnectionManager.port + port);
                occupiedPort.add(port);
                netPrintWriter.println(port);
                //sleep(100);
                socketToClient.close();
                connections.add(new ConnectionChanel(ConnectionManager.port + port, chatPanel, this));
                Thread connectionThread = new Thread(connections.get(connections.size() - 1));
                connectionThread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void resend(Message message) {
        System.out.println("resending " + message);
        for (ConnectionChanel connection : connections) {
            connection.sendMessage(message);
        }
    }

    void releasePort(int p) {
        chatPanel.addMessage(new Message("System", "Try release " + p, "" + new Date(System.currentTimeMillis())));

        for (int i = 0; i < occupiedPort.size(); i++) {
            if (occupiedPort.get(i) == p - port) {

                for (int j = 0; j < connections.size(); j++) {
                    if (connections.get(j).getPort() == port + occupiedPort.get(i)) {
                        connections.get(j).close();
                        connections.remove(j);
                        break;
                    }
                }
                occupiedPort.remove(i);
                chatPanel.addMessage(new Message("System", "port " + p + " released", "" + new Date(System.currentTimeMillis())));
                break;
            }
        }
    }

    private int generatePort() {
        boolean[] isFree = new boolean[1000];
        for (int i = 1; i < 1000; i++) {
            isFree[i] = true;
        }
        for (Integer anOccupiedPort : occupiedPort) {
            isFree[anOccupiedPort] = false;
        }
        for (int i = 1; i < 1000; i++) {
            if (isFree[i]) {
                return i;
            }
        }
        return 44444;

    }
}
