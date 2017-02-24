package client.ui;

import client.net.ConnectionManager;
import ui.ChatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static util.FontFactory.CHAT_FONT;

public class MainFrame extends JFrame {
    private static final int FRAME_WIDTH = 1100;
    private static final int FRAME_HEIGHT = 900;
    private WorkspacePanel workspacePanel;
    private ChatPanel chatPanel = new ChatPanel();
    private ConnectionManager connectionManager;
    private JTextField textField;

    public MainFrame() throws HeadlessException, IOException {
        initFrame();
        connectionManager = new ConnectionManager(chatPanel);
        workspacePanel.setConnectionManager(connectionManager);
        Thread connectionThread = new Thread(connectionManager);
        connectionThread.start();
    }

    public Graphics getFieldPanelGraphics() {
        return workspacePanel.getFieldPanel().getMainImage().getGraphics();
    }

    private void hideChat(){
        chatPanel.setVisible(false);
    }
    private void showChat(){
        chatPanel.setVisible(true);
    }

    private void initFrame() {
        setTitle("Client");
        setBackground(Color.CYAN);
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        workspacePanel = new WorkspacePanel();
        initTextField();
        initLayout();
        pack();
        setVisible(true);
    }
    private void initTextField(){
        textField = new JTextField();
        textField.setBackground(Color.black);
        textField.setForeground(Color.white);
        textField.setFont(CHAT_FONT);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    showChat();
                    if (textField.getText().length() != 0 && connectionManager.isConnected()) {
                        connectionManager.sendMessage(textField.getText());
                        textField.setText("");
                    } else {
                        connectionManager.setEnterPressed(true);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    hideChat();
                }
            }
        });
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showChat();
            }
        });
    }
    private void initLayout(){
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 0.7;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 1;
        c.gridy = 1;
        gbl.setConstraints(workspacePanel, c);
        add(workspacePanel);
        c.gridy = 2;
        c.weighty = 0.3;
        gbl.setConstraints(chatPanel, c);
        add(chatPanel);
        c.gridy = 3;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(textField, c);
        add(textField);
    }
}
