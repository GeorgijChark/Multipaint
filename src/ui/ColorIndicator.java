package ui;

import client.ui.ColorPanel;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorIndicator extends JPanel{
    private Color color;

    public ColorIndicator() {
        color = Color.black;
        setColor(color);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JColorChooser colorChooser = new JColorChooser(color);
                colorChooser.setVisible(true);
                JInternalFrame frame = new JInternalFrame("Choose color",
                        false, true, false,true);
                frame.add(colorChooser);
                frame.pack();
                frame.setVisible(true);
                getParent().getParent().getParent().getParent().getParent().getParent().add(frame);
                frame.addInternalFrameListener(new InternalFrameAdapter() {
                    @Override
                    public void internalFrameClosing(InternalFrameEvent e) {
                        super.internalFrameClosing(e);
                        color = colorChooser.getColor();
                        ((ColorPanel) getParent().getParent()).setColor(color);
                    }
                });

            }
        });
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }


}
