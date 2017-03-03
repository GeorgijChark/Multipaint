package client.ui.settings.basics;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class ColorBlock extends JPanel {


    private int value;
    private ColorSlider colorSlider;
    private JLabel valueLabel;

    public ColorBlock(int value, int min, int max, int type) {
        setBackground(Color.white);
        colorSlider = new ColorSlider(value, min, max, type);
        valueLabel = new JLabel(type != 3 ? " 000" : " 255");
        this.value = value;
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);
        constraints.gridx = 1;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.BOTH;
        JLabel label = new JLabel(type == 0 ? " R " : (type == 1 ? " G " : (type == 2 ? " B " : " A ")));
        label.setOpaque(true);
        label.setBackground(Color.white);
        add(label, constraints);
        constraints.gridx = 2;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        add(colorSlider, constraints);
        constraints.weightx = 0.1;
        constraints.gridx = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.WEST;
        valueLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        valueLabel.setOpaque(true);
        valueLabel.setBackground(Color.white);
        add(valueLabel, constraints);

        colorSlider.addChangeListener(new ColorActionListener());
    }

    public void setBackgroundColor(Color backgroundColor) {
        colorSlider.setBackgroundColor(backgroundColor);
    }

    public void addChangeListener(ChangeListener l) {
        colorSlider.addChangeListener(l);
    }

    public int getValue() {

        return value;
    }

    public void setValue(int value) {
        this.value = min(255, max(0, value));
        colorSlider.setValue(value);
    }

    class ColorActionListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            valueLabel.setText(colorSlider.getValue() + "");
            value = colorSlider.getValue();

            while (valueLabel.getText().length() < 3) {
                valueLabel.setText(0 + valueLabel.getText());
            }
            valueLabel.setText(" " + valueLabel.getText());
        }
    }
}


