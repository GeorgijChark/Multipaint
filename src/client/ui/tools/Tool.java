package client.ui.tools;

import client.ui.Layer;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Tool implements MouseListener, MouseMotionListener {
    protected Layer activeLayer;
    abstract void drawContour(Graphics g);
}
