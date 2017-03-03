package client.ui.tools;

import client.ui.Layer;
import graphics.Line;
import graphics.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Brush extends Tool implements ColoredTool {
    protected int size;
    protected boolean soft;
    protected Shape shape;
    protected Color color;
    protected int[] lastPosition;
    protected int dx, dy;
    public Brush(int size, boolean soft, Shape shape) {
        this.size = size;
        this.soft = soft;
        this.shape = shape;
        isBrushed = true;
        isColored = true;
        color = Color.black;
    }



    public void setActiveLayer(Layer activeLayer) {
        this.activeLayer = activeLayer;
    }

    @Override
    public void drawContour(Graphics g, int x, int y) {
        g.setColor(Color.white);
        shape.drawContour(g, size + 2, x - size / 2 - 1, y - size / 2 - 1);
        g.setColor(Color.black);
        shape.drawContour(g, size, x - size / 2, y - size / 2);
        g.setColor(Color.white);
        shape.drawContour(g, size - 2, x - size / 2 + 1, y - size / 2 + 1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        activeLayer.getTempGraphics().setColor(color);
        ((Graphics2D) activeLayer.getTempGraphics()).setComposite(soft ? AlphaComposite.SrcOver : AlphaComposite.Src);
        if (!soft) {
            shape.draw(activeLayer.getTempGraphics(), size, e.getX() - size / 2, e.getY() - size / 2);
        } else {
            shape.initSoftShape(size, activeLayer.getTempGraphics().getColor());
            shape.softDraw(activeLayer.getTempGraphics(),  e.getX() - size / 2, e.getY() - size / 2);
        }
        lastPosition = new int[]{e.getX(), e.getY()};
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        activeLayer.combine();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawLine(e.getX(), e.getY());
        lastPosition = new int[]{e.getX(), e.getY()};
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    protected void drawLine(int x, int y) {
        if (soft) {
            ((Graphics2D) activeLayer.getTempGraphics()).setComposite(AlphaComposite.SrcOver);
            dx += (Math.abs(lastPosition[0] - x));
            dy += (Math.abs(lastPosition[1] - y));
            if (dx + dy > Math.min(size / 4, 15)) {
                (new Line(lastPosition, new int[]{x, y}, size, color, shape)).softDraw(activeLayer.getTempGraphics());
                dx = 0;
                dy = 0;
            }
        } else {
            (new Line(lastPosition, new int[]{x, y}, size, color, shape)).draw(activeLayer.getTempGraphics());
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isSoft() {
        return soft;
    }

    public void setSoft(boolean soft) {
        this.soft = soft;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
