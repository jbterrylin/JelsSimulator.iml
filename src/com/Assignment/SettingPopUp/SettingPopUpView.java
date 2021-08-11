package com.Assignment.SettingPopUp;

import com.Assignment.LComponent.LButton;
import com.Assignment.LComponent.LLabel;
import com.Assignment.LComponent.LSlider;
import com.Assignment.PageAbstract.EnumInterface;
import com.Assignment.PageAbstract.ViewAbstract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static com.Assignment.LComponent.LComponentEnum.*;
import static com.Assignment.LComponent.LComponentEnum.NO_BORDER;

// Made by Cheok Jia Heng
// Modified by Wang Lin

public class SettingPopUpView extends ViewAbstract {
    private JSlider daySlider, probOfInfectSlider,probOfQuarantineSlider, probOfChronicSlider;
    private JButton readyBtn, restoreBtn;

    // Gathers all the UI components and put to mainPanel
    public void uiBuildChain() {
        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets= new Insets(0, 0, 20, 0); //spacing between buttons

        mainPanel.add(createTitlePanel(), gbc);
        mainPanel.add(createSliderPanel(), gbc);
        mainPanel.add(createBtnPanel(), gbc);

        super.setMainPanel(mainPanel);
    }

    // Creates title panel
    private JPanel createTitlePanel()
    {
        JPanel titlePanel = new JPanel();
        JLabel titleDisplay = new LLabel("SETTING", TITLE);
        titlePanel.add(titleDisplay);
        return titlePanel;
    }

    // Creates slider panel
    private  JPanel createSliderPanel() {
        JPanel sliderPanel = new JPanel(new GridLayout(6,2,0,30));

        Dictionary<Integer, JLabel> tens = new Hashtable<>();
        for(int i=10; i<=1000; i=i+10) {
            tens.put(i, new JLabel(String.valueOf(i)));
        }

        sliderPanel.add(new JLabel("Number of Simulation Day (number * 10): "));
        daySlider = new LSlider(0,10,100,10,tens,true,true,0,10,true);
        sliderPanel.add(daySlider);

        sliderPanel.add(new JLabel("Infection Rate (%): "));
        probOfInfectSlider = new LSlider(0,10,100,10,tens,true,true,0,10,true);
        sliderPanel.add(probOfInfectSlider);

        sliderPanel.add(new JLabel("Probability of Quarantine (%): "));
        probOfQuarantineSlider = new LSlider(0,10,100,10,tens,true,true,0,10,true);
        sliderPanel.add(probOfQuarantineSlider);

        sliderPanel.add(new JLabel("Probability of Chronic (%): "));
        probOfChronicSlider = new LSlider(0,10,100,10,tens,true,true,0,10,true);
        sliderPanel.add(probOfChronicSlider);

        return sliderPanel;
    }

    // Create Button Panel
    private JPanel createBtnPanel() {
        JPanel btnPanel = new JPanel(new BorderLayout());
        readyBtn = new LButton("/com/Assignment/image/set.png",3, 3, DIV);
        btnPanel.add(readyBtn,BorderLayout.LINE_END);
        restoreBtn = new LButton("Restore Most Original Setting",new ArrayList<>(List.of(SET_FOCUSABLE_FALSE, NO_BORDER, UNDER_LINE)));
        btnPanel.add(restoreBtn,BorderLayout.LINE_START);
        return btnPanel;
    }

    public void listenerDistributor(EnumInterface enumVar, EventListener listener) {
        switch ((SettingPopUpEnum) enumVar) {
            case READY_BTN:
                readyBtn.addActionListener((ActionListener) listener);
                break;
            case RESTORE_BTN:
                restoreBtn.addActionListener((ActionListener) listener);
                break;
        }
    }

    // Set value of sliders defined by user
    public void setUiValue(EnumInterface enumVar, Object value) {
        switch ((SettingPopUpEnum) enumVar) {
            case DAY_SLIDER:
                daySlider.setValue((int)value);
                break;
            case PROB_OF_INFECT_SLIDER:
                probOfInfectSlider.setValue((int)value);
                break;
            case PROB_OF_QUARANTINE_SLIDER:
                probOfQuarantineSlider.setValue((int)value);
                break;
            case PROB_OF_CHRONIC_SLIDER:
                probOfChronicSlider.setValue((int)value);
                break;
        }
    }

    // Extract values that are needed for simulation
    public Object getUiValue(EnumInterface componentName) {
        switch ((SettingPopUpEnum) componentName) {
            case DAY_SLIDER:
                return daySlider.getValue();
            case PROB_OF_INFECT_SLIDER:
                return probOfInfectSlider.getValue();
            case PROB_OF_QUARANTINE_SLIDER:
                return probOfQuarantineSlider.getValue();
            case PROB_OF_CHRONIC_SLIDER:
                return probOfChronicSlider.getValue();
        }
        return "-1";
    }
}
