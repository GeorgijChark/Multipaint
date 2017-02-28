package graphics.shape;

import java.awt.*;

public class SCircle extends graphics.shape.Shape {
    @Override
    public void draw(Graphics g, int size, int x, int y) {
        g.fillOval(x, y, size, size);
    }

    @Override
    public void drawContour(Graphics g, int size, int x, int y) {
        g.drawOval(x, y, size, size);
    }
}
