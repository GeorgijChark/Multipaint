package client.ui;

import client.net.ConnectionManager;
import graphics.Line;
import graphics.shape.SCircle;
import graphics.shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FieldPanel extends JPanel {

    private final int BACKGROUND_CELL_SIZE = 5;
    private boolean leftPressed, rightPressed;

    private boolean soft;
    private BufferedImage mainImage, tempImage, backgroundImage;
    private Graphics mainGraphics, tempGraphics, backgroundGraphics;
    private ConnectionManager connectionManager;
    private Color pencilColor;
    private Shape shape;
    private int[] lastPosition;
    private int[] nowPosition;
    private int pencilSize;
    private int dx = 0, dy = 0;
    private boolean basicMode;

    FieldPanel(int fieldWidth, int fieldHeight) throws IOException {
        nowPosition = new int[]{-1, -1};
        lastPosition = new int[]{-1, -1};
        pencilSize = 20;
        pencilColor = Color.black;
        shape = new SCircle();
        soft = false;
        basicMode = false;
        leftPressed = false;
        rightPressed = false;

        setSize(fieldWidth, fieldHeight);
        setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        setOpaque(true);
        setVisible(true);

        mainImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        tempImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        backgroundImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        mainGraphics = mainImage.createGraphics();
        backgroundGraphics = backgroundImage.createGraphics();
        backgroundGraphics.setColor(Color.white);
        backgroundGraphics.fillRect(0, 0, getWidth(), getHeight());
        backgroundGraphics.setColor(Color.lightGray);
        for (int i = 0; BACKGROUND_CELL_SIZE * i < getWidth(); i++) {
            for (int j = 0; BACKGROUND_CELL_SIZE * j < getHeight(); j++) {
                if (i % 2 != j % 2) {
                    backgroundGraphics.fillRect(BACKGROUND_CELL_SIZE * i, BACKGROUND_CELL_SIZE * j, BACKGROUND_CELL_SIZE, BACKGROUND_CELL_SIZE);
                }
            }
        }



        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                switch (e.getButton()) {
                    case 1:
                        leftPressed = true;
                        lastPosition = new int[]{e.getX(), e.getY()};
                        break;
                    case 3:
                        rightPressed = true;
                        lastPosition = new int[]{e.getX(), e.getY()};
                        break;
                }
                tempImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                tempGraphics = tempImage.getGraphics();
                ((Graphics2D) tempGraphics).setComposite(soft ? AlphaComposite.SrcOver : AlphaComposite.Src);
                drawLine(e.getX(), e.getY());
                if (soft) {
                    (new Line(lastPosition, new int[]{e.getX(), e.getY()}, pencilSize, pencilColor, shape)).softDraw(tempGraphics);
                }
                lastPosition = new int[]{e.getX(), e.getY()};
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                switch (e.getButton()) {
                    case 1:
                        leftPressed = false;
                        mainGraphics.drawImage(tempImage, 0, 0, null);
                        tempImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                        break;
                    case 3:
                        rightPressed = false;
                        ((Graphics2D) mainGraphics).setComposite(AlphaComposite.DstOut);
                        mainGraphics.drawImage(tempImage, 0, 0, null);
                        ((Graphics2D) mainGraphics).setComposite(AlphaComposite.SrcOver);
                        tempImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                        break;
                }
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                nowPosition = new int[]{e.getX(), e.getY()};
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                nowPosition = new int[]{-1, -1};
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (leftPressed) {
                    drawLine(e.getX(), e.getY());
                    lastPosition = new int[]{e.getX(), e.getY()};
                }
                if (rightPressed) {
                    drawLine(e.getX(), e.getY());
                    lastPosition = new int[]{e.getX(), e.getY()};
                }
                nowPosition = new int[]{e.getX(), e.getY()};
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                nowPosition = new int[]{e.getX(), e.getY()};
                repaint();
            }
        });
    }



    private void drawLine(int x, int y) {
        if (soft) {
            ((Graphics2D) tempGraphics).setComposite(AlphaComposite.SrcOver);
            dx += (Math.abs(lastPosition[0] - x));
            dy += (Math.abs(lastPosition[1] - y));
            if (dx + dy > Math.min(pencilSize / 2, 15)) {
                (new Line(lastPosition, new int[]{x, y}, pencilSize, pencilColor, shape)).softDraw(tempGraphics);
                dx = 0;
                dy = 0;
            }
        } else {
            (new Line(lastPosition, new int[]{x, y}, pencilSize, pencilColor, shape)).draw(tempGraphics);
        }
        if (connectionManager.isConnected()) {
            connectionManager.sendCommand("line" + " " + lastPosition[0] + " " + lastPosition[1] + " " + x + " " + y +
                    " " + pencilSize + " "
                    + pencilColor.getRed() + " " + pencilColor.getGreen() + " " + pencilColor.getBlue() + " " + pencilColor.getAlpha());
        }
    }

    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);
        if (!rightPressed) {
            g.drawImage(backgroundImage, 0, 0, null);
            g.drawImage(mainImage, 0, 0, null);
            g.drawImage(tempImage, 0, 0, null);
        } else {
            BufferedImage superTempImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D superTempGraphics = (Graphics2D) superTempImage.getGraphics();
            g.drawImage(backgroundImage, 0, 0, null);
            superTempGraphics.drawImage(mainImage, 0, 0, null);
            superTempGraphics.setComposite(AlphaComposite.DstOut);
            superTempGraphics.drawImage(tempImage, 0, 0, null);
            g.drawImage(superTempImage, 0, 0, null);
        }
        if (nowPosition[0] + nowPosition[1] > 0) {
            g.setColor(Color.black);
            shape.drawContour(g, 2 * pencilSize, nowPosition[0] - pencilSize, nowPosition[1] - pencilSize);
            g.setColor(Color.white);
            shape.drawContour(g, 2 * pencilSize + 2, nowPosition[0] - pencilSize - 1, nowPosition[1] - pencilSize - 1);
        }

    }

    public Graphics getMainGraphics() {
        return mainGraphics;
    }
    Color getPencilColor() {
        return pencilColor;
    }
    int getPencilSize() {
        return pencilSize;
    }
    void setPencilColor(Color pencilColor) {
        this.pencilColor = pencilColor;
    }
    void setPencilSize(int pencilSize) {
        this.pencilSize = pencilSize;
    }
    void setShape(Shape shape) {
        this.shape = shape;
    }
    void setSoft(boolean soft) {
        this.soft = soft;
    }
    void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}

