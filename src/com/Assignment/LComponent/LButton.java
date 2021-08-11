package com.Assignment.LComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.*;

//Made By : Joel
//Modify By : Wang Lin
public class LButton extends JButton {
    // will set text and "require" according requires list
    public LButton(String text, ArrayList<LComponentEnum>  requires) {
        this.setText(text);
        for(LComponentEnum require: requires) {
            switch (require) {
                case SET_FOCUSABLE_FALSE:
                    this.setFocusable(false);
                    break;
                case NO_BORDER:
                    this.setBorder(BorderFactory.createEmptyBorder());
                    this.setContentAreaFilled(false);
                    break;
                case UNDER_LINE:
                    Font font = this.getFont();
                    Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    this.setFont(font.deriveFont(attributes));
                    break;
            }
        }
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // set button to image button
    public LButton(String path, double width, double height, LComponentEnum operator) {
        this.setIcon(new LImage().loadImage(path, width, height, operator));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setFocusable(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
