package com.Assignment.LComponent;

import javax.swing.*;
import java.util.Dictionary;

//Made By : Joel
//Modify By : Wang Lin
public class LSlider extends JSlider {
    // assign value to slider
    public LSlider(int orient, int min, int max, int value, Dictionary<Integer,JLabel> labelTable, boolean paintLabels, boolean snapToTicks, int minorTickSpacing, int majorTickSpacing, boolean paintTicks) {
        super(orient, min, max, value);
        this.setLabelTable(labelTable);
        this.setPaintLabels(paintLabels);
        this.setSnapToTicks(snapToTicks);
        this.setMinorTickSpacing(minorTickSpacing);
        this.setMajorTickSpacing(majorTickSpacing);
        this.setPaintTicks(paintTicks);
    }
}
