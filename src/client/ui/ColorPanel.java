package client.ui;

import ui.BasicColorButton;
import ui.ColorBlock;
import ui.ColorIndicator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Random;

import static java.lang.StrictMath.*;

public class ColorPanel extends FastSettingsPanel implements ChangeListener {
    private int VGAP = 5;
    private ColorBlock redBlock, greenBlock, blueBlock, alphaBlock;
    private ColorIndicator indicator;


    public ColorPanel(int r, int g, int b) {
        super();
        setBackground(Color.white);
        redBlock = new ColorBlock(r,  0, 255, 0);
        greenBlock = new ColorBlock(g,  0, 255, 1);
        blueBlock = new ColorBlock(b,  0, 255, 2);
        alphaBlock = new ColorBlock(255,0,255,3);
        redBlock.addChangeListener(this);
        greenBlock.addChangeListener(this);
        blueBlock.addChangeListener(this);
        alphaBlock.addChangeListener(this);

        indicator = new ColorIndicator();

        int cols = 7;
        JPanel basicColorPanel = new JPanel(new GridLayout(3,cols));
        int[] colorComponent = new int[3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j<cols; j++){
                colorComponent[i] = (int) abs(255*(sin(j*PI/(2*(cols-1)))));
                colorComponent[(i+1)%3] = (int) abs(255*(sin(j*PI/(2*(cols-1))+PI/2)));
                colorComponent[(i+2)%3] = (int) abs(255*(sin(j*PI/(2*(cols-1))-PI/2)));
                basicColorPanel.add(new BasicColorButton(new Color(colorComponent[0],colorComponent[1],colorComponent[2])));
            }
        }


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
        JPanel pairPanel = new JPanel(new GridLayout(1,2));
        constraints.fill = GridBagConstraints.BOTH;

        pairPanel.add(indicator);
        pairPanel.add(basicColorPanel);
        add(pairPanel, constraints);

    }

    public void setFp(FieldPanel fp) {
        this.fp = fp;
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
        fp.setPencilColor(getColor());
        repaint();
        getParent().getParent().repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        fp.setPencilColor(getColor());
        repaint();
        getParent().getParent().repaint();
        redBlock.setBackgroundColor(getColor());
        greenBlock.setBackgroundColor(getColor());
        blueBlock.setBackgroundColor(getColor());
        indicator.setColor(getColor());
    }


}
