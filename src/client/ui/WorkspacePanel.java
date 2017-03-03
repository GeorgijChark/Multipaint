package client.ui;

import client.net.ConnectionManager;
import client.ui.settings.panels.ColorPanel;
import client.ui.settings.panels.PenPropertiesPanel;
import client.ui.settings.panels.ToolPropertiesPanel;
import client.ui.tools.Brush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;


class WorkspacePanel extends JPanel {
    private static final int fieldWidth = 800, fieldHeight = 800;
    private final ToolPropertiesPanel toolPropertiesPanel;
    private FieldPanel fieldPanel;
    private Brush tool;

    WorkspacePanel() {
        setDoubleBuffered(true);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        try {
            initField();
        } catch (IOException e) {
            e.printStackTrace();
        }


        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.weightx = 0.7;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        gbl.setConstraints(fieldPanel, c);
        add(fieldPanel);
        c.weightx = 0;
        c.weighty = 0.3;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2;
        c.anchor = GridBagConstraints.NORTHEAST;
        tool = (Brush) fieldPanel.getTool();
        toolPropertiesPanel = new ToolPropertiesPanel(tool);
        gbl.setConstraints(toolPropertiesPanel, c);
        add(toolPropertiesPanel);
    }

    FieldPanel getFieldPanel() {
        return fieldPanel;
    }

    void setConnectionManager(ConnectionManager connectionManager) {
        fieldPanel.setConnectionManager(connectionManager);
    }

    private void initField() throws IOException {
        fieldPanel = new FieldPanel(fieldWidth, fieldHeight);
        fieldPanel.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        fieldPanel.setDoubleBuffered(true);
        fieldPanel.addMouseWheelListener(new mMouseWheelListener());
    }

    public class mMouseWheelListener extends MouseAdapter {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

            super.mouseWheelMoved(e);
            if(tool.isBrushed()) {
                int dSize = -e.getWheelRotation() * e.getWheelRotation() * e.getWheelRotation() / Math.abs(e.getWheelRotation());
                tool.setSize(((Brush) tool).getSize() + dSize);
                if (((Brush) tool).getSize() > 1000)
                    ((Brush) tool).setSize(1000);
                if (((Brush) tool).getSize() < 1)
                    ((Brush) tool).setSize(1);
                toolPropertiesPanel.update();
                repaint();
            }

        }
    }
}
