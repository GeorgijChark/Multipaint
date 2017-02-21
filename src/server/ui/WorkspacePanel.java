package server.ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WorkspacePanel extends JPanel {
    private FieldPanel fieldPanel;

    WorkspacePanel() {
        setDoubleBuffered(true);
        setBackground(Color.LIGHT_GRAY);
        try {
            fieldPanel = new FieldPanel(800, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        add(fieldPanel);


    }

    FieldPanel getFieldPanel() {
        return fieldPanel;
    }

    public void setFieldPanel(FieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;
    }
}
