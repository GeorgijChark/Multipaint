package client.ui.settings;

import client.ui.FieldPanel;

import javax.swing.*;
import java.awt.*;

public class FastSettingsPanel extends JPanel {
    protected static final int VGAP = 5;
    public static Dimension SIZE = new Dimension(200, 200);
    protected FieldPanel fp;

    public FastSettingsPanel() {
        super();
        setOpaque(true);
        setBackground(Color.WHITE);
        setFocusable(false);
        setPreferredSize(SIZE);
    }


}
