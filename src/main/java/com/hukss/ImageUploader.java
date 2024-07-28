package com.hukss;

import com.hukss.UI.ImageView;
import com.hukss.UI.ScaleIcon;
import com.hukss.constance.SystemConstance;

import javax.swing.*;
import javax.swing.plaf.MenuItemUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class ImageUploader extends JFrame {
    public ImageUploader() {
        setTitle(SystemConstance.APP_INFO.APP_TITLE);
        setLayout(new BorderLayout());
        setSize(SystemConstance.APP_INFO.DEFAULT_WIDTH,SystemConstance.APP_INFO.DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setCursor(Cursor.DEFAULT_CURSOR);


        String fileName = "src/main/resources/222.png";
        ImageView imageView = new ImageView(fileName);

        JPanel menuJPanel = new JPanel();


        menuJPanel.setLayout(new BorderLayout());
        var jbar = new JMenuBar();
        JMenu jMenu = new JMenu("文件");
        JMenuItem jMenuItem = new JMenuItem("上传");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setPreferredSize(new Dimension(320,480));
        this.add(jFileChooser);
        ImageUploader uploader = this;

        jMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jFileChooser.showSaveDialog(uploader);
                if (i == JFileChooser.APPROVE_OPTION) {
                    String path = jFileChooser.getSelectedFile().getPath();
                    System.out.println(path);
                    imageView.repaint(path);
                }
            }
        });
        jMenu.add(jMenuItem);
        jbar.add(jMenu);
        menuJPanel.add(jbar);
        this.add(menuJPanel,BorderLayout.NORTH);

//        var jwP = new JPanel();
//        this.add(jwP,BorderLayout.WEST);

        var jlabelJP = new JScrollPane(imageView);
        this.add(jlabelJP,BorderLayout.CENTER);

//        var jeP = new JPanel();
//        jeP.setPreferredSize(new Dimension(100,100));
//        this.add(jeP,BorderLayout.EAST);

        var jbuttonJP = new JPanel();
        JButton jButton = new JButton("上传");
        JButton jButton1 = new JButton("复制URL");
//        jbuttonJP.setPreferredSize(new Dimension(100,100));
        jbuttonJP.add(jButton);
        jbuttonJP.add(jButton1);
        this.add(jbuttonJP,BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
