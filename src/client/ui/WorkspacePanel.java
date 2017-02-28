package client.ui;

import client.net.ConnectionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;


class WorkspacePanel extends JPanel {
    private static final int fieldWidth = 800, fieldHeight = 800;
    private ColorPanel colorPanel;
    private PenPropertiesPanel penPropertiesPanel;
    private FieldPanel fieldPanel;

    WorkspacePanel() {
        setDoubleBuffered(true);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        try {
            initField();
        } catch (IOException e) {
            e.printStackTrace();
        }

        penPropertiesPanel = new PenPropertiesPanel(fieldPanel);
        colorPanel = new ColorPanel(fieldPanel);
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
        JPanel settingsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        settingsPanel.add(penPropertiesPanel);
        settingsPanel.add(colorPanel);
        gbl.setConstraints(settingsPanel, c);
        add(settingsPanel);
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
        fieldPanel.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                int dSize = - e.getWheelRotation() * e.getWheelRotation() * e.getWheelRotation() / Math.abs(e.getWheelRotation());
                fieldPanel.setPencilSize(fieldPanel.getPencilSize()+dSize);
                if (fieldPanel.getPencilSize() > 1000)
                    fieldPanel.setPencilSize(1000);
                if (fieldPanel.getPencilSize() < 1)
                    fieldPanel.setPencilSize(1);
                penPropertiesPanel.updateSize(fieldPanel.getPencilSize());
                repaint();

            }
        });
    }
}
