package graphics.shape;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Shape {
    public abstract void draw(Graphics g, int size, int x, int y);

    public abstract void drawContour(Graphics g, int size, int x, int y);

    public void softDraw(Graphics g, int size, int x, int y) {
        Color color = g.getColor();
        int red = color.getRed(), green = color.getGreen(), blue = color.getBlue(), alpha = color.getAlpha();
        BufferedImage ti = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gt = ti.createGraphics();
        gt.setComposite(AlphaComposite.Src);

        for (int i = size; i > 0; i--) {
            gt.setColor(new Color(red, green, blue, alpha * (size - i) / size));
            draw(gt, i, (size - i) / 2, (size - i) / 2);
        }
        g.drawImage(ti, x, y, null);

    }
}
