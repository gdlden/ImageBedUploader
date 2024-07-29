package com.hukss.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class ImageView extends JComponent {
    private Image drawImage;
    public ImageView (String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            drawImage = ImageIO.read(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        System.out.println(width +":"+height);
        g.drawImage(drawImage,0,0,drawImage.getWidth(null),drawImage.getHeight(null),null);
    }

    public void repaint(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            drawImage = ImageIO.read(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.repaint();
    }
    public static void main(String[] args) throws IOException {
        Frame frame = new Frame();
        frame.setBounds(500,500,500,300);

        ImageView imageView = new ImageView("src/main/resources/222.png");
        frame.setBounds(20,50,100,50);
        frame.add(imageView);


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }
}
