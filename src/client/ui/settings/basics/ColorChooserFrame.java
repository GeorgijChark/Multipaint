package client.ui.settings.basics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooserFrame extends JFrame{
    private JColorChooser colorChooser;
    private ColorIndicator colorIndicator;

    public ColorChooserFrame(ColorIndicator colorIndicator) throws HeadlessException {
        this.colorIndicator = colorIndicator;
        colorChooser = new JColorChooser(colorIndicator.getColor());
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
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);
        buttonsPanel.add(applyButton);
        colorChooser.setVisible(true);
        setTitle("Choose color");
        setLocationRelativeTo(colorChooser);
        setLayout(new BorderLayout());
        add(colorChooser, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setResizable(false);
        setAlwaysOnTop(true);
    }
    private class ButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().contains("save")) {
                colorIndicator.save();
            }
            if (e.getActionCommand().contains("exit")) {
                colorIndicator.close();
            }
        }
    }

    public Color getColor(){
        return colorChooser.getColor();
    }

    public void setColor(Color color){
        colorChooser.setColor(color);
    }

}
