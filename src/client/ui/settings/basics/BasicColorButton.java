package client.ui.settings.basics;

import client.ui.settings.panels.ColorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BasicColorButton extends JButton {
    private Color color;
    private ColorPanel colorPanel;

    public BasicColorButton(Color color, ColorPanel colorPanel) {
        this.color = color;
        this.colorPanel = colorPanel;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseClicked(e);
                colorPanel.setColor(color);
            }
        });
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }


}
