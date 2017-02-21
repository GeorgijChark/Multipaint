package graphics;

import java.awt.*;

public class SoftLine extends Line{
    public SoftLine(int[] first, int[] last, int size, Color color) {
        super(first, last, size, color);
    }

    public SoftLine(int[] first, int[] last, int size) {
        super(first, last, size);
    }

    public void draw(Graphics g) {
        int dy = (last[1] - first[1]), dx = (last[0] - first[0]);
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        Color tempColor = g.getColor();
        g.setColor(color);

        if (steps == 0)
            shape.softDraw(g, 2 * size, first[0] - size, first[1] - size);
        for (int i = 0; i < steps; i+=1+size/2) {
            shape.softDraw(g, 2 * size, (first[0] + i * dx / steps) - size, (first[1] + i * dy / steps) - size);
        }
        g.setColor(tempColor);
    }

}
