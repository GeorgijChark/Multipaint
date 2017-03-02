package client.ui.tools;

import client.ui.Layer;
import graphics.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Brush extends Tool {
    private int size;
    private boolean soft;
    private Shape shape;

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
    void drawContour(Graphics g) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
