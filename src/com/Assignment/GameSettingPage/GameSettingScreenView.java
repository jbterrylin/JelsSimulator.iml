package com.Assignment.GameSettingPage;

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

// Made by Cheok Jia Heng
// Modified by Wang Lin

// view page for game setting page
public class GameSettingScreenView extends ViewAbstract {
    private JSlider worldSizeSlider, popSizeSlider, daySlider, infectRateSlider, probOfQuarantineSlider , probOfChronicSlider, percentOfInitInfectSlider;
    private JButton readyBtn, assignRequireBtn;

    // create main panel
    // call other functions to create their responsible's panel and return to it
    // save all panel to main panel and send to super class to save
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

    // create title panel
    private JPanel createTitlePanel()
    {
        JPanel titlePanel = new JPanel();
        JLabel titleDisplay = new LLabel("SETTING", TITLE);
        titlePanel.add(titleDisplay);
        return titlePanel;
    }

    // create slider panel
    private  JPanel createSliderPanel() {
        JPanel sliderPanel = new JPanel(new GridLayout(7,2,20,20));

        Dictionary<Integer, JLabel> tens = new Hashtable<>();
        for(int i=10; i<=1000; i=i+10) {
            tens.put(i, new JLabel(String.valueOf(i)));
        }

        sliderPanel.add(new JLabel("World Size (number X number): "));
        worldSizeSlider = new LSlider(0,10,40,10,tens,true,true,5,10,true);
        sliderPanel.add(worldSizeSlider);

        sliderPanel.add(new JLabel("Population Size in (%): "));
        popSizeSlider= new LSlider(0,10,100,10,tens,true,true,0,10,true);
        sliderPanel.add(popSizeSlider);

        sliderPanel.add(new JLabel("Number of Simulation Day (number * 10): "));
        daySlider = new LSlider(0,10,100,10,tens,true,true,0,10,true);
        sliderPanel.add(daySlider);

        sliderPanel.add(new JLabel("Infection Rate (%): "));
        infectRateSlider = new LSlider(0,10,100,10,tens,true,true,0,10,true);
        sliderPanel.add(infectRateSlider);

        sliderPanel.add(new JLabel("Probability of Quarantine (%): "));
        probOfQuarantineSlider = new LSlider(0,10,100,10,tens,true,true,0,10,true);
        sliderPanel.add(probOfQuarantineSlider);

        sliderPanel.add(new JLabel("Probability of Chronic (%): "));
        probOfChronicSlider = new LSlider(0,10,100,10,tens,true,true,0,10,true);
        sliderPanel.add(probOfChronicSlider);

        sliderPanel.add(new JLabel("Initial percentage of infected person: "));
        percentOfInitInfectSlider = new LSlider(0,10,100,10,tens,true,true,5,10,true);
        sliderPanel.add(percentOfInitInfectSlider);

        return sliderPanel;
    }

    // create button panel
    private JPanel createBtnPanel() {
        JPanel btnPanel = new JPanel(new BorderLayout());
        assignRequireBtn = new LButton("Assignment Requirement",new ArrayList<>(List.of(SET_FOCUSABLE_FALSE, NO_BORDER, UNDER_LINE)));
        readyBtn = new LButton("/com/Assignment/image/next.png",3, 3,DIV);
        btnPanel.add(assignRequireBtn,BorderLayout.LINE_START);
        btnPanel.add(readyBtn,BorderLayout.LINE_END);
        return btnPanel;
    }

    public void listenerDistributor(EnumInterface enumVar, EventListener listener) {
        switch ((GameSettingScreenEnum) enumVar) {
            case READY_BTN:
                readyBtn.addActionListener((ActionListener) listener);
                break;
            case ASSIGN_REQUIRE_BTN:
                assignRequireBtn.addActionListener((ActionListener) listener);
        }
    }

    public void setUiValue(EnumInterface enumVar, Object value) {
        switch ((GameSettingScreenEnum) enumVar) {
            case WORLD_SIZE_SLIDER:
                worldSizeSlider.setValue((int) value);
                break;
            case POP_SIZE_SLIDER:
                popSizeSlider.setValue((int) value);
                break;
            case DAY_SLIDER:
                daySlider.setValue((int) value);
                break;
            case PROB_OF_INFECT_SLIDER:
                infectRateSlider.setValue((int) value);
                break;
            case PROB_OF_QUARANTINE_SLIDER:
                probOfQuarantineSlider.setValue((int) value);
            case PROB_OF_CHRONIC_SLIDER:
                probOfChronicSlider.setValue((int) value);
                break;
            case PERCENT_OF_INIT_INFECT_SLIDER:
               percentOfInitInfectSlider.setValue((int) value);
                break;
        }
    }

    public Object getUiValue(EnumInterface componentName) {
        switch ((GameSettingScreenEnum) componentName) {
            case WORLD_SIZE_SLIDER:
                return worldSizeSlider.getValue();
            case POP_SIZE_SLIDER:
                return popSizeSlider.getValue();
            case DAY_SLIDER:
                return daySlider.getValue();
            case PROB_OF_INFECT_SLIDER:
                return infectRateSlider.getValue();
            case PROB_OF_QUARANTINE_SLIDER:
                return probOfQuarantineSlider.getValue();
            case PROB_OF_CHRONIC_SLIDER:
                return probOfChronicSlider.getValue();
            case PERCENT_OF_INIT_INFECT_SLIDER:
                return percentOfInitInfectSlider.getValue();
        }
        return "-1";
    }
}
