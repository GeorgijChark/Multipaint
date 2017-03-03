package ui;

import net.Message;
import util.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatPanel extends JPanel {

    private ArrayList<Message> messages;
    private int currentScrollPosition = 0;
    private int STRING_HEIGHT = FontFactory.textSize + 5;

    public ChatPanel() {
        setDoubleBuffered(true);
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 220));
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
        g.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        drawBackground(g);
        super.paintComponent(g1);
        g.setColor(Color.GREEN);
        for (int i = 0; i < messages.size(); i++) {
            String sender = messages.get(i).getSender();
            Color color = new Color(10, 250, 10);
            if (sender.equals("Server"))
                color = new Color(250, 10, 10);
            if (sender.equals("System"))
                color = new Color(0, 0, 255);
            if (sender.equals("Me"))
                color = new Color(200, 250, 250);
            if (sender.equals("Paint"))
                color = new Color(255, 232, 0);
            messages.get(i).draw(g, 20 + getHeight() + STRING_HEIGHT * i - messages.size() * STRING_HEIGHT + STRING_HEIGHT * currentScrollPosition, color);
        }
    }

    private void drawBackground(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 210));
        g.fillRect(0, STRING_HEIGHT, getWidth(), getHeight());
        for (int i = 0; i < STRING_HEIGHT; i++) {
            g.setColor(new Color(0, 0, 0, i * 210 / STRING_HEIGHT));
            g.drawLine(0, i, getWidth(), i);
        }
    }
}
