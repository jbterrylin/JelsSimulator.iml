package com.Assignment.LComponent;

import javax.swing.*;
import java.awt.*;

//Made By : Joel
//Modify By : Wang Lin
// Proxy Design Pattern use it on --> (LFrame,LButton,LLabel,LSlider,LImage)
    // why we use:
        // To create a class that include or return an existed object to let it more easily implement
    // why easily to use:
        // it can add new function
        // it can add advanced setting
        // it can help us do something that need to initialize first

public class LFrame extends JFrame {
    public LFrame(String title, int width, int height, int defaultCloseOperation) {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(defaultCloseOperation);
        setVisible(true);
        setResizable(true);
    }
}
