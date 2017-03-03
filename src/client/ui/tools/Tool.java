package client.ui.tools;

import client.ui.Layer;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Tool implements MouseListener, MouseMotionListener {
    public void setActiveLayer(Layer activeLayer) {
        this.activeLayer = activeLayer;
    }
    protected Layer activeLayer;
    public abstract void drawContour(Graphics g, int x, int y);
}
