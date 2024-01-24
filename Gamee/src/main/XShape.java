package main;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class XShape extends Shape{

    @Override
    public void draw(JPanel panel) throws IOException {
        BufferedImage buff = ImageIO.read(new File("Gamee\\Ximage.png"));
        JLabel label = scaleImage(buff);
        panel.add(label);
    }

    @Override
    public JLabel scaleImage(BufferedImage buff) {
        JLabel label;
        Image img;
        ImageIcon x;
        img = buff.getScaledInstance(95, 90, Image.SCALE_SMOOTH); // made pic smaller
        x = new ImageIcon(img);// turned from image to imageicon
        label = new JLabel(x);// added imageicon to button
        label.setBackground(null);
        label.setPreferredSize(new Dimension(95, 90));
        return label;
    }

}
