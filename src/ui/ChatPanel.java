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

    public ChatPanel() {
        setDoubleBuffered(true);
        setBackground(Color.black);
        messages = new ArrayList<>();
        addMouseWheelListener(e -> {
            currentScrollPosition -= e.getWheelRotation();
            if (currentScrollPosition < 0)
                currentScrollPosition = 0;
            if (currentScrollPosition > Math.max(messages.size() - getHeight() / 25, 0))
                currentScrollPosition = Math.max(messages.size() - getHeight() / 25, 0);
            repaint();
        });
    }

    public void addMessage(Message message) {
        messages.add(message);
        repaint();
    }

    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        g.setColor(Color.GREEN);
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).draw(g, 20 + 25 * i + getHeight() - messages.size() * 25 + 25 * currentScrollPosition, Color.GREEN);
        }
    }
}
