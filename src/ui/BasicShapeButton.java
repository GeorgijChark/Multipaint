package ui;

/**
 * Created by Admin on 28.02.2017.
 */

import client.ui.PenPropertiesPanel;
import graphics.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class BasicShapeButton extends JButton{
    private Shape shape;
    private PenPropertiesPanel penPropertiesPanel;

    public BasicShapeButton(Shape shape, PenPropertiesPanel penPropertiesPanel) {
        this.shape = shape;
        this.penPropertiesPanel = penPropertiesPanel;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseClicked(e);
                penPropertiesPanel.setShape(shape);
            }
        });
    }
}
