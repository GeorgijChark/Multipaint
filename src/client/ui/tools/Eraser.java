package client.ui.tools;

import client.ui.Layer;
import graphics.shape.*;
import graphics.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Eraser extends Brush {


    public Eraser(int size, boolean soft, Shape shape) {
        super(size, soft, shape);
        isColored = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        activeLayer.setEraseMode(true);
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ((Graphics2D) activeLayer.getMainGraphics()).setComposite(AlphaComposite.DstOut);
        activeLayer.combine();
        ((Graphics2D)  activeLayer.getMainGraphics()).setComposite(AlphaComposite.SrcOver);
    }


}
