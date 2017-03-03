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
    private static final int CHAT_HEIGHT = 200;
    private WorkspacePanel workspacePanel;
    private ChatPanel chatPanel = new ChatPanel();
    private ConnectionManager connectionManager;
    private JTextField textField;

    public MainFrame() throws HeadlessException, IOException {
        initFrame();
        connectionManager = new ConnectionManager(chatPanel, workspacePanel.getFieldPanel());
        workspacePanel.setConnectionManager(connectionManager);
        Thread connectionThread = new Thread(connectionManager);
        connectionThread.start();
    }


    private void initFrame() {
        setTitle("Multipaint");
        setBackground(Color.CYAN);
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        workspacePanel = new WorkspacePanel();
        initTextField();
        initSpringLayout();
        pack();
        setVisible(true);
    }

    private void initTextField() {
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
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    hideChat();
                }
            }
        });
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!chatPanel.isVisible()) {
                    showChat();
                } else {
                    if (textField.getText().length() == 0) {
                        hideChat();
                    }
                }
            }
        });
    }

    private void initSpringLayout() {
        JLayeredPane layeredPane = new JLayeredPane();

        SpringLayout springLayout = new SpringLayout();
        layeredPane.setLayout(springLayout);
        layeredPane.add(workspacePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(chatPanel, JLayeredPane.POPUP_LAYER);
        layeredPane.add(textField, JLayeredPane.DEFAULT_LAYER);

        springLayout.putConstraint(SpringLayout.SOUTH, workspacePanel, 0, SpringLayout.NORTH, textField);
        springLayout.putConstraint(SpringLayout.NORTH, workspacePanel, 0, SpringLayout.NORTH, layeredPane);
        springLayout.putConstraint(SpringLayout.WEST, workspacePanel, 0, SpringLayout.WEST, layeredPane);
        springLayout.putConstraint(SpringLayout.EAST, workspacePanel, 0, SpringLayout.EAST, layeredPane);

        springLayout.putConstraint(SpringLayout.NORTH, chatPanel, -CHAT_HEIGHT, SpringLayout.NORTH, textField);
        springLayout.putConstraint(SpringLayout.SOUTH, chatPanel, 0, SpringLayout.NORTH, textField);
        springLayout.putConstraint(SpringLayout.WEST, chatPanel, 0, SpringLayout.WEST, layeredPane);
        springLayout.putConstraint(SpringLayout.EAST, chatPanel, 0, SpringLayout.EAST, layeredPane);


        springLayout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, layeredPane);
        springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, layeredPane);
        springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, layeredPane);

        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
        layeredPane.setBackground(Color.GREEN);
        layeredPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.err.println("click!");
                System.err.println("" + layeredPane.getComponentCountInLayer(JLayeredPane.POPUP_LAYER));
                System.err.println("" + layeredPane.getComponentCount());

            }
        });
    }

    private void hideChat() {
        chatPanel.setVisible(false);
    }

    private void showChat() {
        chatPanel.setVisible(true);
    }
}
