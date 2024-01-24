package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Shape {

    protected String type;

    public abstract void  draw(JPanel panel) throws IOException;
    public abstract JLabel scaleImage(BufferedImage buff);
}
