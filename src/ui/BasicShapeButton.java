package ui;

/**
 * Created by Admin on 28.02.2017.
 */

import client.ui.PenPropertiesPanel;
import graphics.shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BasicShapeButton extends JButton {
    private Shape shape;

    public BasicShapeButton(Shape shape, PenPropertiesPanel penPropertiesPanel) {
        this.shape = shape;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseClicked(e);
                penPropertiesPanel.updateShape(shape);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        shape.draw(g, getWidth() - 2, 1, 1);
    }
}
