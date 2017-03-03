package client.ui;

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
    }

    public Graphics getMainGraphics() {
        return mainGraphics;
    }

    public void combine() {
        mainGraphics.drawImage(tempImage, 0, 0, null);
        tempImage = new BufferedImage(mainImage.getWidth(), mainImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        tempGraphics = tempImage.getGraphics();

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
}
