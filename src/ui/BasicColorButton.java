package ui;

import client.ui.ColorPanel;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BasicColorButton extends JButton {
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color color;

    public BasicColorButton(Color color) {
        this.color = color;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseClicked(e);
                ((ColorPanel) getParent().getParent().getParent()).setColor(color);
            }
        });
    }
    @Override
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(0,0,getWidth(),getHeight());

    }


}
