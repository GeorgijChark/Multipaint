package client.ui;

import graphics.Shape;
import ui.BasicShapeButton;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Admin on 28.02.2017.
 */
public class PenPropertiesPanel extends FastSettingsPanel {

    private Shape shape;
    private int size;
    private boolean soft;
    private FieldPanel fieldPanel;

    private JSlider sizeSlider;
    private JLabel sizeLabel;
    private JCheckBox softCheckBox;

    public PenPropertiesPanel(FieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
        shape = new Shape();
        size = 20;
        soft = false;
        initPanel();
    }

    private void initPanel() {
        sizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, size);
        softCheckBox = new JCheckBox("soft", soft);
        sizeLabel = new JLabel(""+size);
        int rows = 3, cols = 3;
        JPanel shapeGridPanel = new JPanel(new GridLayout(rows,cols));
        for(int i = 0; i<cols; i++){
            for(int j = 0; j<rows;j++){
                shapeGridPanel.add(new BasicShapeButton(new Shape(),this));
            }
        }


    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

}
