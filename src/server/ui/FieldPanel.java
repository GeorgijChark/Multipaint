package server.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;

public class FieldPanel extends JPanel {

    private boolean leftPressed, rightPressed;
    private BufferedImage imGraphics;
    private Graphics g1;

    FieldPanel(int fieldWidth, int fieldHeight) throws IOException {
        setSize(fieldWidth, fieldHeight);
        setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        leftPressed = false;
        rightPressed = false;
        setOpaque(true);
        setVisible(true);
        setBackground(Color.white);
        imGraphics = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        g1 = imGraphics.createGraphics();
        g1.setColor(Color.white);
        g1.fillRect(0, 0, getWidth(), getHeight());
    }


    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    BufferedImage getImGraphics() {
        return imGraphics;
    }

    public void paintComponent(Graphics g) { //отрисовка поля
        super.paintComponent(g); //отрисовка как JPanel
        g.drawImage(imGraphics, 0, 0, null);
        g.setColor(Color.black);
    }

    void clear() {  //возврат поля в исходное состояние
        repaint();
    }

    public String toString() {
        return "";
    }
}

