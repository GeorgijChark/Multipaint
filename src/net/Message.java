package net;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class Message {
    private String sender, text, time;

    public Message(String sender, String text, String time) {
        this.sender = sender;
        this.text = text;
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return time + " " + sender + ':' + text;
    }

    public void draw(Graphics2D g, int y, Color color) {


        g.setColor(new Color(10, 150, 10));
        if (sender.equals("Server"))
            g.setColor(new Color(150, 10, 10));
        if (sender.equals("System"))
            g.setColor(new Color(10, 100, 200));
        if (sender.equals("Me"))
            g.setColor(new Color(150, 170, 170));
        if (sender.equals("Paint"))
            g.setColor(new Color(223, 200, 0));

        Font font = new Font("Times New Roman", 2, 20);
        FontRenderContext frc = g.getFontRenderContext();
        TextLayout time = new TextLayout(this.time + " ", font, frc);
        time.draw(g, 6, y);

        g.setColor(new Color(10, 250, 10));
        if (sender.equals("Server"))
            g.setColor(new Color(250, 10, 10));
        if (sender.equals("System"))
            g.setColor(new Color(0, 0, 255));
        if (sender.equals("Me"))
            g.setColor(new Color(200, 250, 250));
        if (sender.equals("Paint"))
            g.setColor(new Color(255, 232, 0));

        font = new Font("Times New Roman", 1, 20);
        TextLayout sender = new TextLayout(this.sender + ": ", font, frc);
        sender.draw(g, (float) (10 + time.getBounds().getWidth()), y);

        font = new Font("Times New Roman", 0, 20);
        TextLayout text = new TextLayout(this.text, font, frc);
        text.draw(g, (float) (20 + time.getBounds().getWidth() + sender.getBounds().getWidth()), y);

    }
}
