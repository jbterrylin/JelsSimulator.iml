package com.Assignment.LComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Made By : Joel
//Modify By : Wang Lin
public class LLabel extends JLabel {
    // assign value to label according requires
    public LLabel(String text, ArrayList<LComponentEnum> requires) {
        this.setText(text);
        for(LComponentEnum require: requires) {
            switch (require) {
                case HORIZONTAL_CENTER:
                    this.setHorizontalAlignment(JLabel.CENTER);
                    break;
                case TITLE:
                    this.setFont(new Font("Serif", Font.BOLD, 30));
                    break;
            }
        }
    }

    // fast hand
    public LLabel(String text, LComponentEnum require) {
        this(text, new ArrayList<>(List.of(require)));
    }

    // create image label
    public LLabel(String path, double width, double height, LComponentEnum operator) {
        this.setIcon(new LImage().loadImage(path, width, height, operator));
        this.setHorizontalAlignment(JLabel.CENTER);
    }
}
