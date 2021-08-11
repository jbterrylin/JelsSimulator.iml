package com.Assignment.SettingPopUp;

import com.Assignment.LComponent.LFrame;
import com.Assignment.MementoTaker;
import com.Assignment.Model;
import com.Assignment.PageAbstract.ControllerInterface;
import com.Assignment.PageAbstract.ViewAbstract;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.Assignment.SettingPopUp.SettingPopUpEnum.*;
import static com.Assignment.PageNameEnum.SETTING_POP_UP;

// Made by Cheok Jia Heng
// Modified by Wang Lin

// Model = database, View = html, Controller = PHP
public class SettingPopUpController implements ControllerInterface {
    private Model model;
    private ViewAbstract view;

    // create frame and save panel create by setting pop up view
    // Set value into setting pop up view that defined by the user in game setting page
    // set listener to UI
    public SettingPopUpController(ViewAbstract view, Model model) {
        this.model = model;
        this.view = view;
        view.uiBuildChain();
        LFrame lFrame = new LFrame("Setting", 800, 800, JFrame.DISPOSE_ON_CLOSE);
        lFrame.add(view.getMainPanel());
        this.model.openPopUpPage(SETTING_POP_UP, lFrame);

        this.view.setUiValue(DAY_SLIDER, model.getMaxDay() / 10);
        this.view.setUiValue(PROB_OF_INFECT_SLIDER, model.getProbOfInfect());
        this.view.setUiValue(PROB_OF_QUARANTINE_SLIDER, model.getProbOfQuarantine());
        this.view.setUiValue(PROB_OF_CHRONIC_SLIDER, model.getProbOfChronic());

        this.view.listenerDistributor(READY_BTN, new ReadyBtnListener());
        this.view.listenerDistributor(RESTORE_BTN, new RestoreBtnListener());
    }

    // Listens button for ready button
    // if press will save sliders value to model and close this page
    class ReadyBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setMaxDay((int) view.getUiValue(DAY_SLIDER) * 10);
            model.setProbOfInfect((int) view.getUiValue(PROB_OF_INFECT_SLIDER));
            model.setProbOfQuarantine((int) view.getUiValue(PROB_OF_QUARANTINE_SLIDER));
            model.setProbOfChronic((int) view.getUiValue(PROB_OF_CHRONIC_SLIDER));
            model.closePopUpPage(SETTING_POP_UP);
        }
    }

    // listener for restore button
    // Reset slider value to become same with Game setting button's sliders
    class RestoreBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.setUiValue(DAY_SLIDER, MementoTaker.getInstance().getGameSettingMemento().getMaxDay() / 10);
            view.setUiValue(PROB_OF_INFECT_SLIDER, MementoTaker.getInstance().getGameSettingMemento().getProbOfInfect());
            view.setUiValue(PROB_OF_QUARANTINE_SLIDER, MementoTaker.getInstance().getGameSettingMemento().getProbOfQuarantine());
            view.setUiValue(PROB_OF_CHRONIC_SLIDER, MementoTaker.getInstance().getGameSettingMemento().getProbOfChronic());
        }
    }
}
