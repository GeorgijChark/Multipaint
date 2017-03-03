package graphics.shape;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class Shape {
    BufferedImage ti;
    public void initSoftShape(int size, Color color){
        ti = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gt = ti.createGraphics();
        gt.setComposite(AlphaComposite.Src);
        gt.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        int red = color.getRed(), green = color.getGreen(), blue = color.getBlue(), alpha = color.getAlpha();
        for (int i = size; i > 0; i--) {
            gt.setColor(new Color(red, green, blue, alpha * (size - i) / size));
            draw(gt, i, (size - i) / 2, (size - i) / 2);
        }
    }

    public abstract void draw(Graphics g, int size, int x, int y);

    public abstract void drawContour(Graphics g, int size, int x, int y);

    public void softDraw(Graphics g, int x, int y) {
        g.drawImage(ti, x, y, null);
    }
}
