package client.ui;

import client.net.ConnectionManager;
import client.ui.tools.Brush;
import client.ui.tools.Tool;
import graphics.shape.SCircle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class FieldPanel extends JPanel {

    private static final int BACKGROUND_CELL_SIZE = 5;

    private int[] mousePosition;
    private int pencilSize;
    private Tool tool;
    private ArrayList<Layer> layers = new ArrayList<>();
    private Layer activeLayer;
    private BufferedImage backgroundImage;
    FieldPanel(int fieldWidth, int fieldHeight) throws IOException {
        initFrame(fieldWidth, fieldHeight);
        initBackground();
        mousePosition = new int[]{-1, -1};
        activeLayer = new Layer(getWidth(), getHeight());
        layers.add(activeLayer);
        tool = new Brush(20, false, new SCircle());
        tool.setActiveLayer(activeLayer);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mousePosition = new int[]{e.getX(), e.getY()};
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mousePosition = new int[]{-1, -1};
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mousePosition = new int[]{e.getX(), e.getY()};
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition = new int[]{e.getX(), e.getY()};
                repaint();
            }
        });
        addMouseListener(tool);
        addMouseMotionListener(tool);
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        removeMouseListener(this.tool);
        removeMouseMotionListener(this.tool);
        this.tool = tool;
        this.tool.setActiveLayer(activeLayer);
        addMouseListener(this.tool);
        addMouseMotionListener(this.tool);
    }

    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, null);
        for (Layer layer : layers) {
            layer.draw(g);
        }
        if (mousePosition[0] + mousePosition[1] > 0) {
            tool.drawContour(g, mousePosition[0], mousePosition[1]);
        }
    }

    private void initBackground() {
        backgroundImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics backgroundGraphics = backgroundImage.createGraphics();
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
    }

    private void initFrame(int fieldWidth, int fieldHeight) {
        setSize(fieldWidth, fieldHeight);
        setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        setOpaque(true);
        setVisible(true);
    }

    public int getPencilSize() {
        return pencilSize;
    }

    public void setSize(int pencilSize) {
        this.pencilSize = pencilSize;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
//        this.connectionManager = connectionManager;
    }
}

