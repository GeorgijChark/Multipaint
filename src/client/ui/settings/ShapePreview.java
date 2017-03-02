package client.ui.settings;

import graphics.shape.Shape;

import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.min;

public class ShapePreview extends JPanel {
    private Shape shape;
    private boolean soft;

    public ShapePreview(Shape shape, boolean soft) {
        this.shape = shape;
        this.soft = soft;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.black);
        if (soft) {
            shape.softDraw(g, min(getHeight(), getWidth()) - 2, 1, 1);
        } else {
            shape.draw(g, min(getHeight(), getWidth()) - 2, 1, 1);
        }
    }

    public void setSoft(boolean soft) {
        this.soft = soft;
    }
}
