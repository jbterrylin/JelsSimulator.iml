package com.Assignment.GamePage;

import com.Assignment.LComponent.LButton;
import com.Assignment.LComponent.LComponentEnum;
import com.Assignment.LComponent.LImage;

// make by: wang lin

import javax.swing.*;
import java.awt.*;

// map button that use in map
public class MapButton extends JButton {
    private int mapX,mapY;

    // get x and y coordinate in map and save
    public MapButton (int mapX, int mapY) {
        this.mapX = mapX;
        this.mapY = mapY;
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public int getMapX() {
        return mapX;
    }
    public int getMapY() {
        return mapY;
    }
}
