package com.Assignment.LComponent;

import javax.swing.*;
import java.awt.*;

//Made By : Joel
//Modify By : Wang Lin
public class LImage {
    // resize image
    public ImageIcon loadImage(String path, double width, double height, LComponentEnum operator){
        Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
        switch (operator) {
            case DIV:
                image = image.getScaledInstance((int) (image.getWidth(null) / width), (int) (image.getHeight(null) /height), Image.SCALE_SMOOTH);
                break;
            case NONE:
                image = image.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
                break;
        }
        return new ImageIcon(image);
    }
}
