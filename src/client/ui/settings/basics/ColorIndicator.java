package client.ui.settings.basics;

import client.ui.settings.panels.ColorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorIndicator extends JPanel {
    private Color color;
    private ColorChooserFrame colorChooserFrame;

    public ColorIndicator() {
        color = Color.black;
        setColor(color);
        setBackground(Color.white);
        colorChooserFrame = new ColorChooserFrame(this);
        colorChooserFrame.setVisible(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(colorChooserFrame!=null)
                     colorChooserFrame.setVisible(!colorChooserFrame.isVisible());
            }
        });
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        if(colorChooserFrame!=null)
            colorChooserFrame.setColor(color);
    }

    void save() {
        color = colorChooserFrame.getColor();
        ((ColorPanel) getParent().getParent()).setColor(color);
    }

    void close() {
        colorChooserFrame.dispatchEvent(new WindowEvent(colorChooserFrame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.lightGray);
        for(int i = -5*(getWidth()/10); i<getWidth(); i+=10){
            g.drawLine(i,getHeight(),i+10,0);
        }
        int a = (int) (Math.min(getWidth(), getHeight()) * 0.8);
        g.setColor(color);
        g.fillRoundRect((getWidth() - a) / 2, (getHeight() - a) / 2, a, a, 15, 15);
        g.setColor(Color.black);
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.drawRoundRect((getWidth() - a) / 2, (getHeight() - a) / 2, a, a, 15, 15);
    }



}
