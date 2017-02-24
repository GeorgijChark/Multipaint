package client.ui;

import javax.swing.*;
import java.awt.*;

public class FastSettingsPanel extends JPanel {
    public static Dimension SIZE = new Dimension(200, 200);
    protected FieldPanel fp;

    public FastSettingsPanel() {
        super();
        setOpaque(true);
        setBackground(Color.lightGray);
        setFocusable(false);
        setPreferredSize(SIZE);
    }


}
