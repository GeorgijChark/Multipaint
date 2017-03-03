package graphics;

import java.awt.*;


public class Line {
    protected Color color;
    int[] first, last;
    int size;
    graphics.shape.Shape shape;

    public Line(int[] first, int[] last, int size, Color color, graphics.shape.Shape shape) {
        this.first = first;
        this.last = last;
        this.size = size;
        this.color = color;
        this.shape = shape;
    }

    public void draw(Graphics g) {
        int dy = (last[1] - first[1]), dx = (last[0] - first[0]);
        int steps = Math.max(Math.max(Math.abs(dx), Math.abs(dy))/size, 10) ;
        Color tempColor = g.getColor();
        g.setColor(color);
        ((Graphics2D)g).setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        if (steps == 0)
            shape.draw(g, size, first[0] - size/2, first[1] - size/2);
        for (int i = 0; i < steps; i++) {
            shape.draw(g, size, (first[0] + i * dx / steps) - size/2, (first[1] + i * dy / steps) - size/2);
        }
        g.setColor(tempColor);
    }

    public void softDraw(Graphics g) {
        int dy = (last[1] - first[1]), dx = (last[0] - first[0]);
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        Color tempColor = g.getColor();
        g.setColor(color);
        ((Graphics2D)g).setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        if (steps == 0)
            shape.softDraw(g,  first[0] - size/2, first[1] - size/2);
        for (int i = 0; i < steps; i += 1 + size / 4) {
            shape.softDraw(g,  (first[0] + i * dx / steps) - size/2, (first[1] + i * dy / steps) - size/2);
        }
        g.setColor(tempColor);
    }

}
