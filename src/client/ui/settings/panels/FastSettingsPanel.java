package client.ui.settings.panels;

import client.ui.FieldPanel;
import client.ui.tools.Tool;

import javax.swing.*;
import java.awt.*;

abstract class FastSettingsPanel extends JPanel {
    static final int VGAP = 5;
    private static Dimension SIZE = new Dimension(200, 200);
    FastSettingsPanel() {
        super();
        setOpaque(true);
        setBackground(Color.WHITE);
        setFocusable(false);
        setPreferredSize(SIZE);
    }
    abstract public void updateValues();
}
