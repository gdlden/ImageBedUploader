package com.hukss.UI;

import com.hukss.constance.SystemConstance;

import javax.swing.event.MenuKeyEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AwtComponense extends Frame {
    public AwtComponense() {
        setTitle(SystemConstance.APP_INFO.APP_TITLE);
        setLayout(new BorderLayout());
        setAlwaysOnTop(true);
        setSize(SystemConstance.APP_INFO.DEFAULT_WIDTH,SystemConstance.APP_INFO.DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("我是菜单");
        MenuItem menuItem1 = new MenuItem("一号选项");
        MenuItem menuItem2 = new MenuItem("二号选项");
        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);
        setMenuBar(menuBar);

        PopupMenu popupMenu = new PopupMenu();
        popupMenu.add(new MenuItem("一号弹出菜单"));
        popupMenu.add(new MenuItem("二号弹出菜单"));

        add(popupMenu);
        AwtComponense awtComponense = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(awtComponense,e.getX(),e.getY());
                }
            }
        });

        Dialog dialog = new Dialog(this,"提示",true);
        dialog.setSize(320,280);
        dialog.add(new Label("你确定要不退出吗？"),BorderLayout.NORTH);
        dialog.add(new Button("取消"),BorderLayout.WEST);
        dialog.add(new Button("确定"),BorderLayout.EAST);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.setVisible(true);
            }
        });



        setVisible(true);
    }
}
