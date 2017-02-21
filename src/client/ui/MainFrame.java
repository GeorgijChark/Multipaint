package client.ui;

import client.net.ConnectionManager;
import ui.ChatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainFrame extends JFrame {
    private static int WIDTH = 1000; //размеры
    private static int HEIGHT = 800; // окна
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


    void initFrame() {
        setTitle("Client");
        setBackground(Color.CYAN);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 0.7;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 1;
        c.gridy = 1;
        workspacePanel = new WorkspacePanel();
        gbl.setConstraints(workspacePanel, c);
        add(workspacePanel);
        c.gridy = 2;
        c.weighty = 0.3;
        gbl.setConstraints(chatPanel, c);
        add(chatPanel);

        textField = new JTextField();
        textField.setBackground(Color.black);
        textField.setForeground(Color.white);
        textField.setFont(new Font("Calibri", 0, 20));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (textField.getText().length() != 0 && connectionManager.isConnected()) {
                        connectionManager.sendMessage(textField.getText());
                        textField.setText("");
                    } else {
                        connectionManager.setEnterPressed(true);
                    }
                }
            }
        });
        c.gridy = 3;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(textField, c);
        add(textField);


        pack();
        setVisible(true);
    }

}
