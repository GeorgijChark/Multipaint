package client.ui.settings.panels;

import client.ui.settings.basics.BasicShapeButton;
import client.ui.settings.basics.ShapePreview;
import client.ui.tools.Brush;
import client.ui.tools.Tool;
import graphics.shape.SCircle;
import graphics.shape.SSqare;
import graphics.shape.Shape;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Random;

/**
 * Created by Admin on 28.02.2017.
 */
public class PenPropertiesPanel extends FastSettingsPanel {

    private Shape shape;
    private int size;
    private boolean soft;
    private Brush brush;


    private JSlider sizeSlider;
    private JLabel sizeLabel;
    private JCheckBox softCheckBox;
    private JPanel shapeGridPanel;
    private ShapePreview preview;

    public PenPropertiesPanel(Brush brush) {
        this.brush = brush;
        shape = new SCircle();
        size = 20;
        soft = false;
        initComponents();
        initLayout();
    }

    private void initComponents() {
        sizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, size);
        sizeSlider.addChangeListener(new mChangeListener());
        softCheckBox = new JCheckBox("soft", soft);
        softCheckBox.addChangeListener(new mChangeListener());
        sizeLabel = new JLabel(""+size);
        int rows = 3, cols = 3;
        shapeGridPanel = new JPanel(new GridLayout(rows, cols));
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                shapeGridPanel.add(new BasicShapeButton((new Random().nextBoolean() ? new SCircle() : new SSqare()), this));

            }
        }
        preview = new ShapePreview(shape, soft);
        preview.setBackground(Color.white);
    }

    private void initLayout() {

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0;
        constraints.weighty = 0.3;
        constraints.ipady = VGAP;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        add(sizeSlider, constraints);
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 2;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.weightx = 0.7;
        constraints.weighty = 0.7;
        add(shapeGridPanel, constraints);
        constraints.gridheight = 1;
        constraints.gridx = 2;
        constraints.weightx = 0.3;
        constraints.weighty = 0;
        add(sizeLabel, constraints);
        constraints.gridy = 3;
        add(softCheckBox, constraints);
        constraints.gridy = 4;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        add(preview, constraints);


    }

    public void updateSize(int size) {
        this.size = size;
        sizeLabel.setText("" + size);
        sizeSlider.setValue(size);
        brush.setSize(size);
        repaint();
    }

    public void updateShape(Shape shape) {
        this.shape = shape;
        preview.setShape(shape);
        brush.setShape(shape);
        repaint();
    }

    private void updateSoft(boolean soft) {
        this.soft = soft;
        preview.setSoft(soft);
        brush.setSoft(soft);
        repaint();
    }

    @Override
    public void updateValues() {
        updateSize(brush.getSize());
        updateShape(brush.getShape());
        updateSoft(brush.isSoft());
    }

    class mChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            updateSize(sizeSlider.getValue());
            updateSoft(softCheckBox.isSelected());
        }
    }


}
