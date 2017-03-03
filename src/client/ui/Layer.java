package client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Layer {
    private BufferedImage mainImage, tempImage;
    private Graphics mainGraphics;
    private Graphics tempGraphics;

    private boolean eraseMode = false;
    public Layer(int width, int height) {
        mainImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        tempImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        mainGraphics = mainImage.createGraphics();
        tempGraphics = tempImage.getGraphics();
        ((Graphics2D) mainGraphics).setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        ((Graphics2D) tempGraphics).setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

    }

    public Graphics getMainGraphics() {
        return mainGraphics;
    }

    public void combine() {
        mainGraphics.drawImage(tempImage, 0, 0, null);
        tempImage = new BufferedImage(mainImage.getWidth(), mainImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        tempGraphics = tempImage.getGraphics();
        ((Graphics2D) tempGraphics).setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
    }

    public Graphics getTempGraphics() {
        return tempGraphics;
    }

    public void draw(Graphics g) {

        g.drawImage(mainImage, 0, 0, null);
        g.drawImage(tempImage, 0, 0, null);
    }

    public void setEraseMode(boolean eraseMode) {
        this.eraseMode = eraseMode;
    }

    public JPanel getPreview(){
        return new JPanel();
    }
}
