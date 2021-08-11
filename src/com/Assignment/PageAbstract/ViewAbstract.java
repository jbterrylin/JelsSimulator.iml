package com.Assignment.PageAbstract;

import javax.swing.*;
import java.util.EventListener;

//Make By: Joel
//Modify by: Wang Lin

// Abstract factory
// why we use:
// we create this abstract class to all View's Class to let it more easily to control
public abstract class ViewAbstract {
    private JPanel mainPanel;

    // To combine all panel
    public abstract void uiBuildChain();

    // Help to distribute listener
    public void listenerDistributor(EnumInterface enumVar, EventListener listener) {
    }

    // Help to assign value or set value to UI
    // For example : set value to the label.
    public void setUiValue(EnumInterface enumVar, Object value) {
    }

    // Help to get value from user input
    public Object getUiValue(EnumInterface enumVar) { return "-1"; }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
