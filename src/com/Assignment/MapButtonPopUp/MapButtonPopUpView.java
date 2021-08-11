package com.Assignment.MapButtonPopUp;

import com.Assignment.LComponent.LLabel;
import com.Assignment.PageAbstract.EnumInterface;
import com.Assignment.PageAbstract.ViewAbstract;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.Assignment.LComponent.LComponentEnum.*;

// make by: wang lin

// view page for map button pop up
public class MapButtonPopUpView extends ViewAbstract {
    private JLabel titleLbl, idLbl, ageLbl, statusLbl, locationLbl, exLocationLbl, totalQuarantineDayLbl, possiblePlaceLbl;
    private JLabel sickDayLeftLbl, probabilityOfDeadLbl, randomQuarantineLbl;

    // Gathers all the UI components and put to mainPanel
    public void uiBuildChain() {
        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets= new Insets(0, 0, 20, 0); //spacing between buttons

        mainPanel.add(createTitlePanel(), gbc);
        mainPanel.add(createDataPanel(), gbc);

        super.setMainPanel(mainPanel);
    }

    // create title panel
    private JPanel createTitlePanel()
    {
        JPanel titlePanel = new JPanel();
        titleLbl = new LLabel("",new ArrayList<>(List.of(UNDER_LINE)));
        titlePanel.add(titleLbl);
        return titlePanel;
    }

    // create data panel
    private  JPanel createDataPanel() {
        JPanel dataPanel = new JPanel(new GridLayout(6,2,0,30));
        idLbl = new JLabel();
        ageLbl = new JLabel();
        possiblePlaceLbl = new JLabel();
        locationLbl = new JLabel();
        exLocationLbl = new JLabel();
        statusLbl = new JLabel(); // haveChronicLbl,isQuarantineLbl, isImmuneLbl
        totalQuarantineDayLbl = new JLabel();
        randomQuarantineLbl = new JLabel();

        dataPanel.add(idLbl);
        dataPanel.add(ageLbl);
        dataPanel.add(possiblePlaceLbl);
        dataPanel.add(locationLbl);
        dataPanel.add(exLocationLbl);
        dataPanel.add(statusLbl);
        dataPanel.add(totalQuarantineDayLbl);
        dataPanel.add(randomQuarantineLbl);

        sickDayLeftLbl = new JLabel();
        probabilityOfDeadLbl = new JLabel();
        dataPanel.add(sickDayLeftLbl);
        dataPanel.add(probabilityOfDeadLbl);
        return dataPanel;
    }

    public void setUiValue(EnumInterface enumVar, Object value) {
        switch ((MapButtonPopUpEnum) enumVar) {
            case TITLE_LBL:
                titleLbl.setText("" + value);
                break;
            case ID_LBL:
                idLbl.setText("ID: " + value);
                break;
            case AGE_LBL:
                ageLbl.setText("Age / Expected Age: " + value);
                break;
            case STATUS_LBL:
                statusLbl.setText("Chronic/Quarantine/Immune: " + value);
                break;
            case LOCATION_LBL:
                locationLbl.setText("" + value);
                break;
            case EX_LOCATION_LBL:
                exLocationLbl.setText("" + value);
                break;
            case TOTAL_QUARANTINE_DAY_LBL:
                totalQuarantineDayLbl.setText("Total Quarantine Day: " + value);
                break;
            case POSSIBLE_PLACE_LBL:
                possiblePlaceLbl.setText("Last Round Place he can go: " + value);
                break;
            case SICK_DAY_LEFT_LBL:
                sickDayLeftLbl.setText("Sick Day Left: " + value);
                break;
            case PROBABILITY_OF_DEAD_LBL:
                probabilityOfDeadLbl.setText("Probability of dead: " + value);
                break;
            case RANDOM_QUARANTINE_LBL:
                randomQuarantineLbl.setText("Random number for Quarantine: " + value);
                break;
        }
    }
}
