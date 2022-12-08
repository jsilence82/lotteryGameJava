package Lottery;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.RenderingHints.*;

public class AsciiArt {

    public AsciiArt() {
        BufferedImage image = new BufferedImage(144, 32, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Dialog", Font.PLAIN, 24));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(KEY_TEXT_ANTIALIASING,
                VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("LOTTERY", 6, 24);

        for (int y = 0; y < 32; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < 144; x++)
                sb.append(image.getRGB(x, y) == -16777216 ? " " : image.getRGB(x, y) == -1 ? "#" : "*");
            if (sb.toString().trim().isEmpty()) continue;
            System.out.println(sb);

        }

    }
}
