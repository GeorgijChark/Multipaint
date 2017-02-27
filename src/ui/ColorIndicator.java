package ui;

import client.ui.ColorPanel;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.*;

public class ColorIndicator extends JPanel {
    private Color color;
    private JColorChooser colorChooser;
    private JFrame colorChooserFrame;

    public ColorIndicator() {
        color = Color.black;
        setColor(color);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                colorChooser = new JColorChooser(color);
                JButton cancelButton, saveButton, applyButton;
                cancelButton = new JButton("Cancel");
                saveButton = new JButton("OK");
                applyButton = new JButton("Apply");
                ActionListener listener = new ButtonsListener();
                cancelButton.setActionCommand("exit");
                saveButton.setActionCommand("exit save");
                applyButton.setActionCommand("save");
                cancelButton.addActionListener(listener);
                saveButton.addActionListener(listener);
                applyButton.addActionListener(listener);
                JPanel buttonsPanel = new JPanel();
                buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10,10));
                buttonsPanel.add(saveButton);
                buttonsPanel.add(cancelButton);
                buttonsPanel.add(applyButton);
                colorChooser.setVisible(true);
                colorChooserFrame = new JFrame("Choose color");
                colorChooserFrame.setLayout(new BorderLayout());
                colorChooserFrame.add(colorChooser,BorderLayout.NORTH);
                colorChooserFrame.add(buttonsPanel,BorderLayout.SOUTH);
                colorChooserFrame.pack();
                colorChooserFrame.setVisible(true);
                colorChooserFrame.setResizable(false);
            }
        });
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    private void save(){
        color = colorChooser.getColor();
        ((ColorPanel) getParent().getParent()).setColor(color);
    }

    private void close(){
        colorChooserFrame.dispatchEvent(new WindowEvent(colorChooserFrame,WindowEvent.WINDOW_CLOSING));
    }

    private class ButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().contains("save")){save();}
            if(e.getActionCommand().contains("exit")){close();}
        }
    }


}
