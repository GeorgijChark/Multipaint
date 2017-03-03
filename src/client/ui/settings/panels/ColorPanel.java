package client.ui.settings.panels;

import client.ui.settings.basics.BasicColorButton;
import client.ui.settings.basics.ColorBlock;
import client.ui.settings.basics.ColorIndicator;
import client.ui.tools.ColoredTool;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ColorPanel extends FastSettingsPanel implements ChangeListener {
    private ColorBlock redBlock, greenBlock, blueBlock, alphaBlock;
    private ColorIndicator indicator;
    private JPanel basicColorsPanel;

    private ColoredTool tool;

    public ColorPanel(ColoredTool coloredTool) {
        super();
        tool = coloredTool;
        setBackground(Color.white);
        initComponents();
        initLayout();



    }

    private void initComponents() {
        redBlock = new ColorBlock(0, 0, 255, 0);
        greenBlock = new ColorBlock(0, 0, 255, 1);
        blueBlock = new ColorBlock(0, 0, 255, 2);
        alphaBlock = new ColorBlock(255, 0, 255, 3);
        redBlock.addChangeListener(this);
        greenBlock.addChangeListener(this);
        blueBlock.addChangeListener(this);
        alphaBlock.addChangeListener(this);

        indicator = new ColorIndicator();

        int cols = 7;
        basicColorsPanel = new JPanel(new GridLayout(4, cols));
        int[] colorComponent = new int[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < cols; j++) {
                colorComponent = new int[]{255*j/(cols-1),255*j/(cols-1),255*j/(cols-1), 0} ;
                colorComponent[i] = 255;
                basicColorsPanel.add(new BasicColorButton(new Color(colorComponent[0], colorComponent[1], colorComponent[2]), this));
            }
        }

    }

    private void initLayout() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0;
        constraints.weighty = 0.5;
        constraints.ipady = VGAP;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        add(redBlock, constraints);
        constraints.gridy = 2;
        add(greenBlock, constraints);
        constraints.gridy = 3;
        add(blueBlock, constraints);
        constraints.gridy = 4;
        add(alphaBlock, constraints);
        constraints.gridy = 5;
        constraints.weightx = 0.5;
        JPanel pairPanel = new JPanel(new GridLayout(1, 2));
        constraints.fill = GridBagConstraints.BOTH;

        pairPanel.add(indicator);
        pairPanel.add(basicColorsPanel);
        add(pairPanel, constraints);
    }


    public Color getColor() {
        return new Color(redBlock.getValue(), greenBlock.getValue(), blueBlock.getValue(), alphaBlock.getValue());
    }

    public void setColor(Color color) {
        redBlock.setValue(color.getRed());
        greenBlock.setValue(color.getGreen());
        blueBlock.setValue(color.getBlue());
        alphaBlock.setValue(color.getAlpha());
        indicator.setColor(color);
        tool.setColor(getColor());
        repaint();
        getParent().getParent().getParent().repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        tool.setColor(getColor());
        repaint();
        getParent().getParent().getParent().repaint();
        redBlock.setBackgroundColor(getColor());
        greenBlock.setBackgroundColor(getColor());
        blueBlock.setBackgroundColor(getColor());
        indicator.setColor(getColor());
    }


    @Override
    public void updateValues() {
        setColor(tool.getColor());
    }
}
