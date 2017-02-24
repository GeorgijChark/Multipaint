package server.ui;

import net.Message;
import server.net.ConnectionManager;
import ui.ChatPanel;
import util.StringFormats.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;

import static util.FontFactory.CHAT_FONT;
import static util.StringFormats.getTime;

public class MainFrame extends JFrame {
    private static final int FRAME_WIDTH = 1000; //размеры
    private static final int FRAME_HEIGHT = 1000; // окна
    private WorkspacePanel workspacePanel = new WorkspacePanel();
    private ChatPanel chatPanel = new ChatPanel();
    private JTextField textField;
    private ConnectionManager connectionManager;

    public MainFrame() throws HeadlessException, IOException {
        initFrame();
        connectionManager = new ConnectionManager(chatPanel);
        Thread connectionThread = new Thread(connectionManager);
        connectionThread.start();
    }

    public Graphics getFieldPanelGraphics() {
        return workspacePanel.getFieldPanel().getImGraphics().getGraphics();
    }

    private void initFrame() {
        setTitle("Server");
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                    if (textField.getText().length() != 0) {
                        connectionManager.resend(new Message("Server", textField.getText(), ""+getTime(System.currentTimeMillis())));
                        textField.setText("");
                    }
                }
            }
        });
    }

    private void initLayout(){
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 1;
        c.gridy = 1;
        gbl.setConstraints(chatPanel, c);
        add(chatPanel);
        c.gridy = 2;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(textField, c);
        add(textField);
    }
}
