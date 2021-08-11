package com.Assignment.GamePage;

import com.Assignment.LComponent.LButton;
import com.Assignment.LComponent.LImage;
import com.Assignment.LComponent.LLabel;
import com.Assignment.PageAbstract.EnumInterface;
import com.Assignment.PageAbstract.ViewAbstract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import static com.Assignment.LComponent.LComponentEnum.*;

// make by: wang lin

// iterator
    // why we use:
        // easy to help us loop
        // don't care about collection and variable type
        // clean code style
    // for my understanding, foreach is iterator also, but it care about variable type


// view page for game page
public class GamePageScreenView extends ViewAbstract {
    private ArrayList<ArrayList<MapButton>> map = new ArrayList<>();
    private JLabel dayTf, speedTf, healthyTf, infectedTf, quarantineTf, deadTf;
    private JButton slowerBtn, playPauseBtn, fasterBtn, settingBtn, exitBtn;
    private int worldSize;

    // create main panel
    // call other functions to create their responsible's panel and return to it
    // save all panel to main panel and send to super class to save
    public void uiBuildChain() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createMapPanel(), BorderLayout.CENTER);
        mainPanel.add(createRightPanel(),BorderLayout.EAST);
        super.setMainPanel(mainPanel);
    }

    // create map panel
    public JPanel createMapPanel() {
        JPanel mapPanel = new JPanel(new GridLayout(worldSize,worldSize));
        for(int i=0; i<worldSize; i++){
            map.add(new ArrayList<>());
            for(int j=0; j<worldSize; j++){
                MapButton temp = new MapButton(i, j);
                temp.setEnabled(true);
                map.get(i).add(temp);
                mapPanel.add(map.get(i).get(j));
            }
        }
        return mapPanel;
    }

    // create right panel
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; //fill both button into same size
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 5, 0, 5);

        JLabel titleLbl = new LLabel("/com/Assignment/image/title.png",2, 2, DIV);

        JPanel daySpeedPanel = new JPanel(new GridLayout(1, 2));
        dayTf = new LLabel("", HORIZONTAL_CENTER);
        speedTf = new LLabel("", HORIZONTAL_CENTER);
        daySpeedPanel.add(dayTf);
        daySpeedPanel.add(speedTf);

        JPanel SPFPanel = new JPanel(new GridLayout(1, 3));
        slowerBtn = new LButton("/com/Assignment/image/slower.png",1.25, 1.25, DIV);
        playPauseBtn = new LButton("/com/Assignment/image/play.png",1.25, 1.25, DIV);
        fasterBtn = new LButton("/com/Assignment/image/faster.png",1.25, 1.25, DIV);
        SPFPanel.add(slowerBtn);
        SPFPanel.add(playPauseBtn);
        SPFPanel.add(fasterBtn);

        settingBtn = new LButton("/com/Assignment/image/setting.png",1.15, 1.15, DIV);
        exitBtn = new LButton("/com/Assignment/image/exitBtn.png",1.15, 1.15, DIV);

        healthyTf = new LLabel("", HORIZONTAL_CENTER);
        infectedTf = new LLabel("", HORIZONTAL_CENTER);
        quarantineTf = new LLabel("", HORIZONTAL_CENTER);
        deadTf = new LLabel("", HORIZONTAL_CENTER);

        JPanel dataPanel = new JPanel(new GridLayout(4, 2));
        dataPanel.add(new LLabel("/com/Assignment/image/healthy.png", 2, 2, DIV));
        dataPanel.add(healthyTf, gbc);
        dataPanel.add(new LLabel("/com/Assignment/image/sick.png", 2, 2, DIV));
        dataPanel.add(infectedTf, gbc);
        dataPanel.add(new LLabel("/com/Assignment/image/quarantine.png", 2, 2, DIV));
        dataPanel.add(quarantineTf, gbc);
        dataPanel.add(new LLabel("/com/Assignment/image/dead.png", 2, 2, DIV));
        dataPanel.add(deadTf, gbc);

        rightPanel.add(titleLbl,gbc);
        rightPanel.add(daySpeedPanel, gbc);
        rightPanel.add(SPFPanel, gbc);
        rightPanel.add(settingBtn, gbc);
        rightPanel.add(exitBtn, gbc);
        rightPanel.add(dataPanel,gbc);



        return rightPanel;
    }

    // refresh actor location according value(map from controller)
    private void refreshActorLocation(ArrayList<ArrayList<GamePageScreenEnum>> value) {
        map.forEach((k) -> k.forEach((v) -> v.setBackground(Color.WHITE) ) );

        Iterator<ArrayList<GamePageScreenEnum>> iterator = value.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            Iterator<GamePageScreenEnum> buttonStatus = iterator.next().iterator();
            int j = 0;
            while(buttonStatus.hasNext()) {
                switch (buttonStatus.next()) {
                    case NORMAL_PEOPLE:
                        map.get(i).get(j).setBackground(Color.BLACK);
                        break;
                    case SICK_PEOPLE:
                        map.get(i).get(j).setBackground(Color.RED);
                        break;
                    case QUARANTINE_ZONE:
                        map.get(i).get(j).setBackground(new Color(0,128,255));
                        break;
                }
                j++;
            }
            i++;
        }
    }

    // receive key(enumVar) and value(listener) and distribute value according key
    public void listenerDistributor(EnumInterface enumVar, EventListener listener) {
        switch ((GamePageScreenEnum) enumVar) {
            case SLOWER_BTN:
                slowerBtn.addActionListener((ActionListener) listener);
                break;
            case PLAY_PAUSE_BTN:
                playPauseBtn.addActionListener((ActionListener) listener);
                break;
            case FASTER_BTN:
                fasterBtn.addActionListener((ActionListener) listener);
                break;
            case SETTING_BTN:
                settingBtn.addActionListener((ActionListener) listener);
                break;
            case EXIT_BTN:
                exitBtn.addActionListener((ActionListener) listener);
                break;
            case MAP:
                for (ArrayList<MapButton> jButtonList: map) {
                    for(JButton button: jButtonList) {
                        button.addActionListener((ActionListener) listener);
                    }
                }
                break;
        }
    }

    public void setUiValue(EnumInterface enumVar, Object value) {
        switch ((GamePageScreenEnum) enumVar) {
            case WORLD_SIZE:
                worldSize = (int) value;
                break;
            case MAP_COORDINATE:
                refreshActorLocation((ArrayList<ArrayList<GamePageScreenEnum>>) value);
                break;
            case PLAY_PAUSE_BTN:
                playPauseBtn.setIcon(new LImage().loadImage((String) value,1.25, 1.25, DIV));
                break;
            case DAY_TF:
                dayTf.setText("Day / Target Day: " + value);
                break;
            case SPEED_TF:
                speedTf.setText("Speed: " + value);
                break;
            case HEALTHY_TF:
                healthyTf.setText("" + value);
                break;
            case INFECTED_TF:
                infectedTf.setText("" + value);
                break;
            case QUARANTINE_TF:
                quarantineTf.setText("" + value);
                break;
            case DEAD_TF:
                deadTf.setText("" + value);
                break;

        }
    }

    public Object getUiValue(EnumInterface componentName) {
        /*
        switch ((Screen3Enum) componentName) {
            case MAP:
                return map;
        }
        */
        return map;
        //return "-1";
    }
}
