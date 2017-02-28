package graphics.shape;

import java.awt.*;

public class SSqare extends Shape {
    @Override
    public void draw(Graphics g, int size, int x, int y) {
        g.fillRect(x, y, size, size);
    }

    @Override
    public void drawContour(Graphics g, int size, int x, int y) {
        g.drawRect(x, y, size, size);
    }
}
