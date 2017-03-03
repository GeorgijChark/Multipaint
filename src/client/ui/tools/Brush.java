package client.ui.tools;

import client.ui.Layer;
import graphics.Line;
import graphics.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Brush extends Tool implements ColoredTool {
    private int size;
    private boolean soft;
    private Shape shape;
    private Color color;
    private int[] lastPosition;
    private int dx, dy;
    public Brush(int size, boolean soft, Shape shape) {
        this.size = size;
        this.soft = soft;
        this.shape = shape;
    }


    public void setActiveLayer(Layer activeLayer) {
        this.activeLayer = activeLayer;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSoft(boolean soft) {
        this.soft = soft;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }


    @Override
    public void drawContour(Graphics g, int x, int y) {
        g.setColor(Color.white);
        shape.drawContour(g, size+2, x - size / 2 - 1, y - size / 2 - 1);
        g.setColor(Color.black);
        shape.drawContour(g, size, x - size / 2, y - size / 2);
        g.setColor(Color.white);
        shape.drawContour(g, size-2, x - size / 2 + 1, y - size / 2 + 1);
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
            shape.softDraw(activeLayer.getTempGraphics(), size, e.getX() - size / 2, e.getY() - size / 2);
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

    private void drawLine(int x, int y) {
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

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
