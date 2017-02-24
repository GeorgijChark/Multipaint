package server.ui;

import server.net.ConnectionManager;
import ui.ChatPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private static int WIDTH = 300; //размеры
    private static int HEIGHT = 300; // окна
    private WorkspacePanel workspacePanel = new WorkspacePanel();
    private ChatPanel chatPanel = new ChatPanel();

    public MainFrame() throws HeadlessException, IOException {
        initFrame();
    }

    public Graphics getFieldPanelGraphics() {
        return workspacePanel.getFieldPanel().getImGraphics().getGraphics();
    }

    private void initFrame() {
        setTitle("Server");
        setBackground(Color.CYAN);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(chatPanel);
        Thread connectionThread = new Thread(new ConnectionManager(chatPanel));
        connectionThread.start();


        pack();
        setVisible(true);
    }

}
