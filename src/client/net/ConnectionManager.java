package client.net;

import client.ui.MainFrame;
import graphics.Command;
import net.Message;
import ui.ChatPanel;
import util.Input;
import util.StringFormats;
import util.StringStream;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import static java.lang.Thread.sleep;

public class ConnectionManager implements Runnable {

    private static String HOST = "127.0.0.1";
    private static int PORT = 2391;
    private Socket socketToServer;
    private String username = "Somebody1";
    private PrintWriter netPrintWriter;
    private Input in;
    private ChatPanel chatPanel;
    private boolean connected = false;
    private boolean enterPressed = false;
    private ArrayList<String> commandsQueue;

    public ConnectionManager(String username, ChatPanel chatPanel) {
        this.username = username;
        this.chatPanel = chatPanel;
    }

    public ConnectionManager(ChatPanel chatPanel) {
        username = "Unnamed " + (new Random().nextInt(1000));
        this.chatPanel = chatPanel;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }

    public boolean isConnected() {
        return connected;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        try {
            while (socketToServer == null) {
                try {
                    socketToServer = new Socket(HOST, PORT);
                } catch (ConnectException e) {
                    chatPanel.addMessage(new Message("System", "Unable connect to the server, press ENTER to retry", "" + StringFormats.getTime(System.currentTimeMillis())));
                    enterPressed = false;
                    while (!enterPressed) {
                        try {
                            sleep(100);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                    enterPressed = false;
                }
            }
            in = new Input(socketToServer.getInputStream());
            chatPanel.addMessage(new Message("System", "Connected on port:" + socketToServer.getPort(),
                    "" + StringFormats.getTime(System.currentTimeMillis())));
            connected = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        netPrintWriter = null;
        try {
            netPrintWriter = new PrintWriter(socketToServer.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        netPrintWriter.println(username);
        sendMessage("Hi, I'm " + username);
        while (takeMessage()) {
        }

    }

    public void sendMessage(String text) {
        netPrintWriter.println(text);
        netPrintWriter.println(StringFormats.getTime(System.currentTimeMillis()));
    }

    public void sendCommand(String text) {
        netPrintWriter.println("$" + text);
        netPrintWriter.println(StringFormats.getTime(System.currentTimeMillis()));
    }

    private void takeCommand(String comText) {

        StringStream stringStream = new StringStream(comText);
        String type = stringStream.next();
        Command com = new Command("line");
        com.addArgument(((MainFrame) chatPanel.getParent().getParent().getParent().getParent().getParent()).getFieldPanelGraphics());
        for (int i = 1; i < com.getNumberOfArguments(); i++) {
            com.addArgument(stringStream.nextInt());
        }
        com.perform();
        chatPanel.getParent().getParent().getParent().getParent().repaint();

    }

    public boolean takeMessage() {
        String sender, text, time;
        if (in == null) {
            chatPanel.addMessage(new Message("Server", "You have been disconnected", "" + StringFormats.getTime(System.currentTimeMillis())));
            return false;
        } else {
            sender = in.nextLine();
            text = in.nextLine();
            time = in.nextLine();
            if (time == null) {
                chatPanel.addMessage(new Message("Server", "You have been disconnected", "" + StringFormats.getTime(System.currentTimeMillis())));
                return false;
            }

            char[] firstSymbol = new char[1];
            text.getChars(0, 1, firstSymbol, 0);
            if (firstSymbol[0] == '$') {
                takeCommand(text);
            } else {
                if (time == null) {
                    chatPanel.addMessage(new Message("Server", "You have been disconnected", "" + StringFormats.getTime(System.currentTimeMillis())));
                    return false;
                }
                if (Objects.equals(sender, username))
                    sender = "Me";
                chatPanel.addMessage(new Message(sender, text, time));
            }

            return true;
        }
    }
}
