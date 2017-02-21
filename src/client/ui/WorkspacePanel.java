package client.ui;

import client.net.ConnectionManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


class WorkspacePanel extends JPanel {
    private FieldPanel fieldPanel;
    private int fieldWidth = 600, fieldHeight = 600;

    WorkspacePanel() {
        setDoubleBuffered(true);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        try {
            initField();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SizePanel sizePanel = new SizePanel(20);
        sizePanel.setPreferredSize(new Dimension(200, 200));
        sizePanel.setFp(fieldPanel);


        ColorPanel colorPanel = new ColorPanel(0, 0, 0);
        colorPanel.setPreferredSize(new Dimension(200, 200));
        colorPanel.setFp(fieldPanel);

        fieldPanel.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.weightx = 0.7;
        c.fill = GridBagConstraints.NONE;
//        c.gridwidth = fieldWidth;
//        c.gridheight = fieldHeight;
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        gbl.setConstraints(fieldPanel, c);
        add(fieldPanel);
        c.weightx = 0.3;
        c.weighty = 0.3;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2;
        c.anchor = GridBagConstraints.NORTHEAST;
//        c.gridwidth = FastSettingsPanel.SIZE.width;
//        c.gridheight = FastSettingsPanel.SIZE.height;

        JPanel settingsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        settingsPanel.add(sizePanel);
        settingsPanel.add(colorPanel);

        gbl.setConstraints(settingsPanel, c);
        add(settingsPanel);
//        c.gridy = 2;
//        gbl.setConstraints(colorPanel, c);
//
//        add(colorPanel);

    }

    public FieldPanel getFieldPanel() {
        return fieldPanel;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        fieldPanel.setConnectionManager(connectionManager);
    }

    private void initField() throws IOException {
        fieldPanel = new FieldPanel(fieldWidth, fieldHeight); //создание нового поля с имеющмися параметрами
        fieldPanel.setDoubleBuffered(true); // защита от мерцания
    }
}
