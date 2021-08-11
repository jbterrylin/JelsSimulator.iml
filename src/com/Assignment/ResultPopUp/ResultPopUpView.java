package com.Assignment.ResultPopUp;

import com.Assignment.LComponent.LButton;
import com.Assignment.LComponent.LLabel;
import com.Assignment.PageAbstract.EnumInterface;
import com.Assignment.PageAbstract.ViewAbstract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static com.Assignment.LComponent.LComponentEnum.*;

//made by jason
//modify by wang lin


// view layout of result pop up after the game end
public class ResultPopUpView extends ViewAbstract {
    private JLabel titleLbl, dayLbl, worldPopLbl, probOfChronicLbl, chronicLbl, probOfInfectLbl, infectLbl, probOfQuarantineLbl, quarantineLbl;
    private JLabel killByAgeLbl, killBySickLbl, curedLbl;
    private List<JLabel> ageGroupLbl = new LinkedList<>();
    private JButton continueBtn,quitBtn;

    //Override from ViewAbstract class, use in controller to call
    //To create and add all the panel of the result pop up view
    public void uiBuildChain() {
        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets= new Insets(0, 0, 20, 0); //spacing between buttons

        mainPanel.add(createTitlePanel(), gbc);
        mainPanel.add(createDataPanel(), gbc);
        mainPanel.add(createAgeGroup(), gbc);
        mainPanel.add(createBtnPanel(), gbc);

        super.setMainPanel(mainPanel);
    }

    //To create title of this pop up
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titleLbl = new LLabel("", TITLE);
        titlePanel.add(titleLbl);
        return titlePanel;
    }

    //To create the display of all data from game after game end
    private  JPanel createDataPanel() {
        JPanel dataPanel = new JPanel(new GridLayout(6,2,0,30));
        dayLbl = new JLabel();
        worldPopLbl = new JLabel();
        probOfChronicLbl = new JLabel();
        chronicLbl = new JLabel();
        probOfInfectLbl = new JLabel();
        infectLbl = new JLabel();
        probOfQuarantineLbl = new JLabel();
        quarantineLbl = new JLabel();
        killByAgeLbl = new JLabel();
        killBySickLbl = new JLabel();
        curedLbl = new JLabel();

        dataPanel.add(dayLbl);
        dataPanel.add(worldPopLbl);
        dataPanel.add(probOfChronicLbl);
        dataPanel.add(chronicLbl);
        dataPanel.add(probOfInfectLbl);
        dataPanel.add(infectLbl);
        dataPanel.add(probOfQuarantineLbl);
        dataPanel.add(quarantineLbl);
        dataPanel.add(killByAgeLbl);
        dataPanel.add(killBySickLbl);
        dataPanel.add(curedLbl);

        return dataPanel;
    }

    //To create the display of age group data
    private JPanel createAgeGroup() {
        String [] statusText = new String [] {"Age Group: ", "Healthy: ", "Sick: ", "Kill by Age: ", "Kill by Sick: "};
        String [] ageGroupText = new String [] {"Below 20: ", "20 to 39: ", "40 to 59: ", "Above 60: "};
        JPanel ageGroupPanel = new JPanel(new GridLayout(ageGroupText.length + 1,statusText.length,0,30));
        for(int i=0; i<ageGroupText.length + 1; i++) {
            for (int j=0; j< statusText.length; j++) {
                if(i == 0) {
                    ageGroupPanel.add(new LLabel(statusText[j], HORIZONTAL_CENTER));
                } else {
                    if(j == 0) {
                        ageGroupPanel.add(new LLabel(ageGroupText[i-1], HORIZONTAL_CENTER));
                    } else {
                        JLabel temp = new LLabel("", HORIZONTAL_CENTER);
                        ageGroupLbl.add(temp);
                        ageGroupPanel.add(ageGroupLbl.get(ageGroupLbl.size() - 1));
                    }
                }
            }
        }

        return  ageGroupPanel;
    }

    //To create the button
    private JPanel createBtnPanel() {
        JPanel btnPanel = new JPanel(new BorderLayout());
        JPanel btnGridPanel = new JPanel(new GridLayout(1, 2));
        continueBtn = new LButton("/com/Assignment/image/continue.png",3, 3, DIV);
        quitBtn = new LButton("/com/Assignment/image/exitBtn.png",3, 3, DIV);
        btnGridPanel.add(continueBtn);
        btnGridPanel.add(quitBtn);
        btnPanel.add(btnGridPanel,BorderLayout.LINE_END);
        return btnPanel;
    }

    //To add listener to button
    public void listenerDistributor(EnumInterface enumVar, EventListener listener) {
        switch ((ResultPopUpEnum) enumVar) {
            case CONTINUE_BTN:
                continueBtn.addActionListener((ActionListener) listener);
                break;
            case QUIT_BTN:
                quitBtn.addActionListener((ActionListener) listener);
                break;
        }
    }

    //To set the value into the data display view
    public void setUiValue(EnumInterface enumVar, Object value) {
        switch ((ResultPopUpEnum) enumVar) {
            case TITLE_LBL:
                titleLbl.setText((String) value);
                break;
            case DAY_LBL:
                dayLbl.setText("Day use: " + value);
                break;
            case WORLD_POP_LBL:
                worldPopLbl.setText("Population density: " + value);
                break;
            case PROB_OF_CHRONIC_LBL:
                probOfChronicLbl.setText("Prob of getting Chronic: " + value);
                break;
            case CHRONIC_LBL:
                chronicLbl.setText("People getting Chronic: " + value);
                break;
            case PROB_OF_INFECT_LBL:
                probOfInfectLbl.setText("Prob of getting Infection: " + value);
                break;
            case INFECT_LBL:
                infectLbl.setText("People getting infect: " + value);
                break;
            case PROB_OF_QUARANTINE_LBL:
                probOfQuarantineLbl.setText("Prob of quarantine own: " + value);
                break;
            case QUARANTINE_LBL:
                quarantineLbl.setText("Total times of quarantine: " + value);
                break;
            case KILL_BY_AGE_LBL:
                killByAgeLbl.setText("Kill by Age: " + value);
                break;
            case KILL_BY_SICK_LBL:
                killBySickLbl.setText("Kill by Sick: " + value);
                break;
            case CURED_LBL:
                curedLbl.setText("Cured People: " + value);
                break;
            case AGE_GROUP_LBL:
                List<Long> temp = (List<Long>) value;
                for(int i=0; i<temp.size(); i++) {
                    ageGroupLbl.get(i).setText("" + temp.get(i));
                }
                break;
        }
    }
}
