package client.ui.tools;

import client.ui.Layer;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Tool implements MouseListener, MouseMotionListener {
    Layer activeLayer;
    boolean isColored = false, isBrushed = false;

    public boolean isColored() {
        return isColored;
    }

    public boolean isBrushed() {
        return isBrushed;
    }

    public void setActiveLayer(Layer activeLayer) {
        this.activeLayer = activeLayer;
    }

    public abstract void drawContour(Graphics g, int x, int y);
}
