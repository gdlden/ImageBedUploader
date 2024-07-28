package com.hukss;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.hukss.UI.AwtComponense;


public class GuiTest {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        new ImageUploader();
//        new AwtComponense();
    }
}
