package server.net;

import graphics.Command;
import net.Message;
import server.ui.MainFrame;
import ui.ChatPanel;
import util.Input;
import util.StringFormats;
import util.StringStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ConnectionChanel implements Runnable {

    private String username;
    private int port;
    private Socket socketToClient;
    private PrintWriter netPrintWriter;
    private Input in;
    private ChatPanel chatPanel;
    private ConnectionManager connectionManager;


    ConnectionChanel(Socket socketToClient, ChatPanel chatPanel, ConnectionManager connectionManager) {
        this.port = port;
        this.chatPanel = chatPanel;
        this.connectionManager = connectionManager;
        this.socketToClient = socketToClient;
    }

    int getPort() {
        return port;
    }

    @Override
    public void run() {
        try {
            in = new Input(socketToClient.getInputStream());
            netPrintWriter = new PrintWriter(socketToClient.getOutputStream(), true);
            username = in.nextLine();
            chatPanel.addMessage(new Message(username,
                    "Connected from: " + socketToClient.getInetAddress() + ":" + socketToClient.getPort(),
                    "" + StringFormats.getTime(System.currentTimeMillis())));
            while (takeMessage()) ;
            socketToClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void sendMessage(String text, String username) {
        netPrintWriter.println(username);
        netPrintWriter.println(text);
        netPrintWriter.println(StringFormats.getTime(System.currentTimeMillis()));

    }

    private boolean takeMessage() {
        String sender, text, time;

        if (in == null) {
            chatPanel.addMessage(new Message(username, "Disconnected", "" + StringFormats.getTime(System.currentTimeMillis())));
            return false;
        } else {
            sender = username;
            text = in.nextLine();
            time = in.nextLine();
            if (time == null) {
                chatPanel.addMessage(new Message(username, "Disconnected", "" + StringFormats.getTime(System.currentTimeMillis())));
                return false;
            }
            char[] firstSymbol = new char[1];
            text.getChars(0, 1, firstSymbol, 0);
            if (firstSymbol[0] == '$') {
                takeCommand(text);
            } else {
                chatPanel.addMessage(new Message(sender, text, time));
            }
            connectionManager.resend(new Message(sender, text, time));
            return true;
        }

    }

    private void takeCommand(String comText) {

        StringStream stringStream = new StringStream(comText);

        String type = stringStream.next();
        ArrayList<Object> arguments = new ArrayList<>();

        Command com = new Command("line");
        com.addArgument(((MainFrame) chatPanel.getParent().getParent().getParent().getParent()).getFieldPanelGraphics());
        for (int i = 1; i < com.getNumberOfArguments(); i++) {
            com.addArgument(stringStream.nextInt());
        }
        com.perform();
        System.out.println("performed");
        (chatPanel.getParent().getParent().getParent().getParent()).repaint();

    }

    void close() {
        System.out.println("Connection on port " + port + " have been closed");
        try {
            socketToClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(Message message) {
        System.out.println("sending " + message);
        netPrintWriter.println(message.getSender());
        netPrintWriter.println(message.getText());
        netPrintWriter.println(message.getTime());
        System.out.println("sent");

    }
}
