package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class MarsPanel extends JFrame {
    private BufferedImage image;

    public MarsPanel(String imageUrl) {
        System.out.println("Attempting to load image from URL: " + imageUrl);
        loadImageFromURL(imageUrl);
    }

    private void loadImageFromURL(String urlImagen) {
        try {
            URL url = new URL(urlImagen);
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to load image: " + e.getMessage());
        }
    }

}
