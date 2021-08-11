package com.Assignment.MainPage;

import com.Assignment.LComponent.LButton;
import com.Assignment.LComponent.LLabel;
import com.Assignment.PageAbstract.EnumInterface;
import com.Assignment.PageAbstract.ViewAbstract;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

import static com.Assignment.LComponent.LComponentEnum.*;

//Make by: Joel
//Modify by : Wang Lin
    //This is the class View for Main Page
public class MainPageScreenView extends ViewAbstract {
    private JButton startBtn, exitBtn;

    public void uiBuildChain() {
        JPanel mainPanel = new JPanel(new GridLayout(2,1));

        mainPanel.add(createTitlePanel());
        mainPanel.add(createButtonPanel());
        super.setMainPanel(mainPanel);
    }

    //To create the Label we are using Proxy design pattern where the class name is LLabel
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        JLabel titleLbl = new LLabel("/com/Assignment/image/title.png",-1, -1, NONE);
        titlePanel.add(titleLbl);
        titlePanel.setBorder(new EmptyBorder(40, 0, 0, 0));
        return titlePanel;
    }

    //To create the button we are using Proxy design pattern where the class name is LButton.
    private JPanel createButtonPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // specify that the component be the last one
        gbc.fill = GridBagConstraints.HORIZONTAL; // fill both button into same size
        gbc.insets = new Insets(0, 0, 5, 0); // spacing between buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        startBtn = new LButton("/com/Assignment/image/start.png",-1, -1, NONE);
        exitBtn = new LButton("/com/Assignment/image/exitBtn.png",-1, -1, NONE);
        buttonPanel.add(startBtn, gbc);
        buttonPanel.add(exitBtn, gbc);
        return buttonPanel;
    }

    //This function is to add the Listener to all the button in main page.
    public void listenerDistributor(EnumInterface enumVar, EventListener listener) {
        switch ((MainPageScreenEnum) enumVar) {
            case START_BTN:
                startBtn.addActionListener((ActionListener) listener);
                break;
            case EXIT_BTN:
                exitBtn.addActionListener((ActionListener) listener);
                break;
        }
    }
}
