package client.ui.settings.basics;

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
        ((Graphics2D) g).setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g.setColor(Color.black);
        shape.initSoftShape(min(getHeight(), getWidth()) - 2, g.getColor());
        if (soft) {
            shape.softDraw(g,  1, 1);
        } else {
            shape.draw(g, min(getHeight(), getWidth()) - 2, 1, 1);
        }
    }

    public void setSoft(boolean soft) {
        this.soft = soft;
    }
}
