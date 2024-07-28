package com.hukss.UI;

import javax.swing.*;

public class JProcessBarTest {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("文件上传器");
        jFrame.setBounds(500,500,300,300);
        jFrame.setLayout(null);

        JProgressBar bar = new JProgressBar();
        bar.setMaximum(100);
        bar.setValue(50);
        bar.setBounds(20,20,200,10);
        jFrame.add(bar);

        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
