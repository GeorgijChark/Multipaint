package graphics;

import java.awt.*;


public class Line {
    int[] first, last;
    int size;
    protected Color color;
    Shape shape;

    public Line(int[] first, int[] last, int size, Color color) {
        this.first = first;
        this.last = last;
        this.size = size;
        this.color = color;
        shape = new Shape();
    }

    public Line(int[] first, int[] last, int size) {
        this.first = first;
        this.last = last;
        this.size = size;
        color = Color.black;
        shape = new Shape();
    }

    public void draw(Graphics g) {
        int dy = (last[1] - first[1]), dx = (last[0] - first[0]);
        int steps = Math.max(Math.abs(dx), Math.abs(dy));        Color tempColor = g.getColor();
        g.setColor(color);
        if (steps == 0)
            shape.draw(g, 2 * size, first[0] - size, first[1] - size);
        for (int i = 0; i < steps; i++) {
            shape.draw(g, 2 * size, (first[0] + i * dx / steps) - size, (first[1] + i * dy / steps) - size);
        }
        g.setColor(tempColor);
    }



    public void clear(Graphics g) {
        int dy = (last[1] - first[1]), dx = (last[0] - first[0]);
        int steps = Math.max(Math.abs(dx), Math.abs(dy));        Color tempColor = g.getColor();
        g.setColor(color);

        ((Graphics2D)g).setComposite(AlphaComposite.Clear);
        if (steps == 0)
            shape.draw(g, 2 * size, first[0] - size, first[1] - size);
        for (int i = 0; i < steps; i++) {
            shape.draw(g, 2 * size, (first[0] + i * dx / steps) - size, (first[1] + i * dy / steps) - size);
        }
        ((Graphics2D)g).setComposite(AlphaComposite.SrcOver);

        g.setColor(tempColor);


    }
}
