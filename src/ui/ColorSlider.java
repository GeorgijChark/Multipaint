package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by charkovskijgk.17 on 15.02.2017.
 */
public class ColorSlider extends JSlider {
    private Color backgroundColor = new Color(0, 0, 0);
    private int type = 0;

    ColorSlider(int value, int min, int max, int type) {
        super(Scrollbar.HORIZONTAL, min, max,value);
        this.type = type;
        setOpaque(false);
        setPaintTrack(false);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void paintComponent(Graphics graphics) {

        for (int i = 0; i < getWidth(); i++) {
            int r = backgroundColor.getRed(), g = backgroundColor.getGreen(), b = backgroundColor.getBlue();
            switch (type) {
                case 0:
                    r = 256 * i / getWidth();
                    break;
                case 1:
                    g = 256 * i / getWidth();
                    break;
                case 2:
                    b = 256 * i / getWidth();
                    break;
                case 3:
                    r = 256 * (getWidth()-i-1) / getWidth();
                    g = 256 * (getWidth()-i-1) / getWidth();
                    b = 256 * (getWidth()-i-1) / getWidth();
            }

            graphics.setColor(new Color(r, g, b));
            graphics.drawRect(i, 0, 1, getHeight());
        }
        super.paintComponent(graphics);

    }
}
