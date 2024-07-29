package com.hukss;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.hukss.UI.AwtComponense;

import java.io.IOException;


public class GuiTest {
    public static void main(String[] args) throws IOException {
        FlatDarculaLaf.setup();
        new ImageUploader();
//        new AwtComponense();
    }
}
