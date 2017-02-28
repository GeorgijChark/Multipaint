package net;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import static util.FontFactory.*;

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

        FontRenderContext frc = g.getFontRenderContext();

        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
        TextLayout time = new TextLayout(this.time + " ", ITALIC_MESSAGE, frc);
        time.draw(g, 5, y);
        g.setColor(color);


        TextLayout sender = new TextLayout(this.sender + ": ", BOLD_ITALIC_MESSAGE, frc);
        sender.draw(g, (float) (15 + time.getBounds().getWidth()), y);
        TextLayout text = new TextLayout(this.text, MESSAGE, frc);
        text.draw(g, (float) (20 + time.getBounds().getWidth() + sender.getBounds().getWidth()), y);

    }
}
