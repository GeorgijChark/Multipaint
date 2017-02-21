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
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
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

        Thread connectionThread = new Thread(new ConnectionManager(chatPanel));
        connectionThread.start();


        pack();
        setVisible(true);
    }

}
