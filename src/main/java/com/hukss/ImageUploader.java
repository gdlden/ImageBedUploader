package com.hukss;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hukss.UI.ImageView;
import com.hukss.UI.ScaleIcon;
import com.hukss.constance.SystemConstance;
import com.hukss.util.OkHttpUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.plaf.MenuItemUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.xml.xpath.XPath;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ImageUploader extends JFrame {
    public ImageUploader() throws IOException {
        setTitle(SystemConstance.APP_INFO.APP_TITLE);
        setLayout(new BorderLayout());
        setSize(SystemConstance.APP_INFO.DEFAULT_WIDTH,SystemConstance.APP_INFO.DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setCursor(Cursor.DEFAULT_CURSOR);


        String fileName = "222.png";
        ImageView imageView = new ImageView(fileName);

        JPanel menuJPanel = new JPanel();


        menuJPanel.setLayout(new BorderLayout());
        var jbar = new JMenuBar();
        JMenu jMenu = new JMenu("文件");
        JMenuItem jMenuItem = new JMenuItem("上传");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setPreferredSize(new Dimension(480,320));
        this.add(jFileChooser);
        ImageUploader uploader = this;
        final String[] path = {""};

        jMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jFileChooser.showSaveDialog(uploader);
                if (i == JFileChooser.APPROVE_OPTION) {
                    path[0] = jFileChooser.getSelectedFile().getPath();
                    imageView.repaint(path[0]);
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
        jlabelJP.setPreferredSize(new Dimension(480,320));
        jlabelJP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jlabelJP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(jlabelJP,BorderLayout.CENTER);

//        var jeP = new JPanel();
//        jeP.setPreferredSize(new Dimension(100,100));
//        this.add(jeP,BorderLayout.EAST);

        var jbuttonJP = new JPanel();
        JButton jButton = new JButton("上传");
        JLabel jLabelUrl = new JLabel("返回的网址");
        Properties properties = new Properties();
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        properties.load(resource);
        String cookie = properties.getProperty("cookie");
        System.out.println(cookie);
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    String url = "https://z4a.net/json";
                    File file = new File(path[0]);
                    HttpResponse execute = HttpRequest.post(url)
                            .header("user-agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36")
                            .header("origin","https://z4a.net")
                            .header("content-type","multipart/form-data; boundary=----WebKitFormBoundaryyLvtYfl4qFw8cm5A")
                            .header("cookie",cookie)
                            .header("content-length",String.valueOf(file.length()))
                            .form("type", "file")
                            .form("action", "upload")
                            .form("timestamp", new Date().getTime())
                            .form("auth_token", "83fa6ebdc8bd37c4877646c8c786a1bdeb6e55ea")
                            .form("nsfw", 0)
                            .form("source",file )
                            .execute();
                    JSONObject jsonObject = JSON.parseObject(execute.body());
                    String statusCode = jsonObject.getString("status_code");
                    if (StringUtils.isNotBlank(statusCode) && "200".equals(statusCode)) {
                        JSONObject success = jsonObject.getJSONObject("success");
                        if ("200".equals(success.getString("code"))) {
                            String resUrl = jsonObject.getJSONObject("image").getString("url");
                            jLabelUrl.setText(resUrl);
                            jLabelUrl.repaint();
                        }
                    }

                }
            }
        });
        JButton jButton1 = new JButton("复制URL");
//        jbuttonJP.setPreferredSize(new Dimension(100,100));
        jbuttonJP.add(jLabelUrl);
        jbuttonJP.add(jButton);
        jbuttonJP.add(jButton1);
        jButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                systemClipboard.setContents(new StringSelection(jLabelUrl.getText()),null);
            }
        });
        this.add(jbuttonJP,BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
