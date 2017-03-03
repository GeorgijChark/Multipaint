package client.ui.settings.panels;

import client.ui.tools.Brush;
import client.ui.tools.ColoredTool;
import client.ui.tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolPropertiesPanel extends JPanel {
    ArrayList<FastSettingsPanel> fastSettingsPanels = new ArrayList<>();
    private Tool tool;
    public ToolPropertiesPanel(Tool tool) {
        this.tool = tool;
        if(tool.isBrushed()){
            fastSettingsPanels.add(new PenPropertiesPanel((Brush) tool));
        }
        if(tool.isColored()){
            fastSettingsPanels.add(new ColorPanel((ColoredTool) tool));
        }
        initLayout();
    }

    public void update(){
        for (FastSettingsPanel fastSettingsPanel: fastSettingsPanels) {
            fastSettingsPanel.updateValues();
        }
    }


    private void initLayout(){
        setLayout(new GridLayout(fastSettingsPanels.size(), 1, 10,10));
        for ( FastSettingsPanel fastSettingsPanel: fastSettingsPanels) {
            add(fastSettingsPanel);
        }
    }

}
