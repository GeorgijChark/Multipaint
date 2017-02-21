package client.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class SizePanel extends FastSettingsPanel implements AdjustmentListener {
    public int HGAP = 40;
    Scrollbar sizeSlider;
    FieldPanel fp;
    JLabel label;

    public SizePanel() {
        super();
        sizeSlider = new Scrollbar(Scrollbar.HORIZONTAL, 1, 1, 0, 255);
        sizeSlider.setBounds(0, 0, 250, 20);
        add(sizeSlider);
        setSize(200, 100);
        setBounds(0, 0, 200, 100);
    }

    public SizePanel(int size) {
        super();
        setBackground(Color.white);
        sizeSlider = new Scrollbar(Scrollbar.HORIZONTAL, size, 1, 0, 1000);
        sizeSlider.setBounds(0, 0, 250, 20);
        sizeSlider.addAdjustmentListener(this);

        sizeSlider.setFocusable(false);
        label = new JLabel(sizeSlider.getValue() + "");


        setLayout(new GridLayout(3, 1, HGAP, 0));
        add(sizeSlider);
        add(label);

        label.setAlignmentX(CENTER_ALIGNMENT);

        setSize(200, 100);
        setBounds(0, 0, 200, 100);
    }

    public void setFp(FieldPanel fp) {
        this.fp = fp;
    }

    public int getValue() {
        return sizeSlider.getValue();
    }

    public void setValue(int value) {
        sizeSlider.setValue(value);

    }

    @Override
    public void paintComponent(Graphics g) {
        sizeSlider.setValue(fp.getPencilSize());
        label.setText(sizeSlider.getValue() + "");

        super.paintComponent(g);
        g.setColor(fp.getPencilColor());
        g.fillOval(getWidth() / 2 - sizeSlider.getValue(), (int) (getHeight() * 1.33) / 2 - sizeSlider.getValue(), 2 * sizeSlider.getValue(), 2 * sizeSlider.getValue());
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {

        fp.setPencilSize(sizeSlider.getValue());
        label.setText(sizeSlider.getValue() + "");
        repaint();
    }
}
