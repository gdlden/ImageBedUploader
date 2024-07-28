package com.hukss.UI;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class testCustomMenuBar {
    public static void main(String[] args) {
        Frame frame = new Frame("Hello World!"){
            @Override
            public void paint(Graphics g) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0,0,getWidth(),28);
                g.setColor(Color.black);
                g.drawString(getTitle(),getWidth()/2,20);
                super.paint(g);
            }
        };
        frame.setUndecorated(true);
        frame.setBounds(0,0,1024,768);
        frame.setShape(new RoundRectangle2D.Double(0,0,frame.getWidth(),frame.getHeight(),30,30));
        frame.setVisible(true);
    }
}
