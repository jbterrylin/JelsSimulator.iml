package com.Assignment.GameSettingPage;

import com.Assignment.MementoTaker;
import com.Assignment.Model;
import com.Assignment.PageAbstract.ControllerInterface;
import com.Assignment.PageAbstract.ViewAbstract;
import com.Assignment.Router;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.Assignment.GameSettingPage.GameSettingScreenEnum.*;
import static com.Assignment.PageNameEnum.GAME_PAGE;

// Made by Cheok Jia Heng
// Modified by Wang Lin

// controller for game setting page
public class GameSettingScreenController implements ControllerInterface {
    private ViewAbstract view;
    private Model model;

    // call view to create UI(in panel form)
    // set panel to main frame
    // set listener to ui component
    public GameSettingScreenController(ViewAbstract view, Model model) {
        this.view = view;
        this.model = model;
        this.view.uiBuildChain();
        this.model.setMainPanel(view.getMainPanel());

        this.view.listenerDistributor(ASSIGN_REQUIRE_BTN, new AssignRequireBtnListener());
        this.view.listenerDistributor(READY_BTN, new ReadyBtnListener());
    }

    // listener for ready button
    // if click will get slider value from ui and save it to model,
    // save value to memento
    // route to game page
    class ReadyBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setWorldSize((int) view.getUiValue(WORLD_SIZE_SLIDER));
            model.setPopSize((int) view.getUiValue(POP_SIZE_SLIDER));
            model.setMaxDay((int) view.getUiValue(DAY_SLIDER) * 10);
            model.setProbOfInfect((int) view.getUiValue(PROB_OF_INFECT_SLIDER));
            model.setProbOfQuarantine((int) view.getUiValue(PROB_OF_QUARANTINE_SLIDER));
            model.setProbOfChronic((int) view.getUiValue(PROB_OF_CHRONIC_SLIDER));
            model.setPercentOfInitInfect((int) view.getUiValue(PERCENT_OF_INIT_INFECT_SLIDER));
            MementoTaker.getInstance().setGameSettingMemento(new GameSettingMemento(model.getMaxDay(),model.getProbOfInfect(),model.getProbOfQuarantine(),model.getProbOfChronic()));
            Router.getInstance().screenUpdate(GAME_PAGE);
        }
    }

    // listener of assign require button
    // if click will set default value to slider on UI and model
    class AssignRequireBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setWorldSize(25);
            view.setUiValue(WORLD_SIZE_SLIDER, model.getWorldSize());
            model.setPopSize(20);
            view.setUiValue(POP_SIZE_SLIDER, model.getPopSize());
            model.setMaxDay(300 * 10);
            view.setUiValue(DAY_SLIDER, model.getMaxDay());
            model.setProbOfInfect(100);
            view.setUiValue(PROB_OF_INFECT_SLIDER, model.getProbOfInfect());
            model.setProbOfQuarantine(50);
            view.setUiValue(PROB_OF_QUARANTINE_SLIDER, model.getProbOfQuarantine());
            model.setProbOfChronic(50);
            view.setUiValue(PROB_OF_CHRONIC_SLIDER, model.getProbOfChronic());
            model.setPercentOfInitInfect(20);
            view.setUiValue(PERCENT_OF_INIT_INFECT_SLIDER, model.getPercentOfInitInfect());
        }
    }
}
