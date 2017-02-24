package ui;

import net.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class ChatPanel extends JPanel {

    private ArrayList<Message> messages;
    private int currentScrollPosition = 0;
    private int STRING_HEIGHT = 25;

    public ChatPanel() {
        setDoubleBuffered(true);
        setOpaque(false);
        setBackground(new Color(0,0,0,220));
        messages = new ArrayList<>();
        addMouseWheelListener(e -> {
            currentScrollPosition -= e.getWheelRotation();
            if (currentScrollPosition < 0)
                currentScrollPosition = 0;
            if (currentScrollPosition > Math.max(messages.size() - getHeight() / STRING_HEIGHT, 0))
                currentScrollPosition = Math.max(messages.size() - getHeight() / STRING_HEIGHT, 0);
            repaint();
        });
    }

    public void addMessage(Message message) {
        messages.add(message);
        repaint();
    }

    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setColor(new Color(0,0,0,218));
        g.fillRect(0,0,getWidth(),getHeight());

        super.paintComponent(g1);
        g.setColor(Color.GREEN);
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).draw(g, 20 + getHeight() + STRING_HEIGHT * i  - messages.size() * STRING_HEIGHT + STRING_HEIGHT * currentScrollPosition, Color.GREEN);
        }
    }
}
