package com.Assignment.ResultPopUp;

import com.Assignment.GamePage.Charactor.ActorEnum;
import com.Assignment.GamePage.Charactor.ActorInterface;
import com.Assignment.GamePage.GamePageScreenEnum;
import com.Assignment.LComponent.LFrame;
import com.Assignment.Model;
import com.Assignment.PageAbstract.ControllerInterface;
import com.Assignment.PageAbstract.ViewAbstract;
import com.Assignment.Router;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import static com.Assignment.PageNameEnum.*;
import static com.Assignment.ResultPopUp.ResultPopUpEnum.*;

//made by jason
//modify by wang lin

//Controller of the result pop up between model and view
public class ResultPopUpController implements ControllerInterface {
    private Model model;
    private ViewAbstract view;

    //Constructor that will call function to set view with data that get from model
    public ResultPopUpController(ViewAbstract view, Model model) {
        this.model = model;
        this.view = view;
        view.uiBuildChain();
        LFrame lFrame = new LFrame("Result", 600, 800, JFrame.DISPOSE_ON_CLOSE);
        lFrame.add(view.getMainPanel());
        this.model.openPopUpPage(RESULT_POP_UP, lFrame);

        setValue();

        this.view.listenerDistributor(CONTINUE_BTN, new ContinueBtnListener());
        this.view.listenerDistributor(QUIT_BTN, new QuitBtnListener());
    }

    //To get data from model and set to view display
    private void setValue() {
        switch ((GamePageScreenEnum)model.getEnding()) {
            case GOOD_ENDING:
                view.setUiValue(TITLE_LBL, "Congratulation");
                break;
            case NORMAL_ENDING:
                view.setUiValue(TITLE_LBL, "So-So");
                break;
            case BAD_ENDING:
                view.setUiValue(TITLE_LBL,"So sad");

        }
        DecimalFormat df2 = new DecimalFormat("#.##");
        int totalActor = model.getWorldSize()*model.getWorldSize()*model.getPopSize()/100;
        view.setUiValue(DAY_LBL, model.getDay() + " / " + model.getMaxDay() + " ( " + model.getDay()*100/model.getMaxDay()  + "% )");
        view.setUiValue(WORLD_POP_LBL, totalActor + " / " + model.getWorldSize()*model.getWorldSize() + " ( " + model.getPopSize() + "% )");
        view.setUiValue(PROB_OF_CHRONIC_LBL, model.getProbOfChronic() + " %");
        view.setUiValue(CHRONIC_LBL, model.getActorList().stream().filter(ActorInterface::isHaveChronic).count() + " / " + totalActor + " ( " + df2.format(model.getActorList().stream().filter(ActorInterface::isHaveChronic).count()*(float)100/totalActor) + "% )");
        view.setUiValue(PROB_OF_INFECT_LBL, model.getProbOfInfect() + " %");
        view.setUiValue(INFECT_LBL, model.getPercentOfInitInfect()*totalActor/100  + " -> " + model.getTotalNumberOfInfected() + " （ " + df2.format(((float)model.getTotalNumberOfInfected()/ (model.getPercentOfInitInfect()*(float)totalActor/100))*100) + "% Growth)");
        view.setUiValue(PROB_OF_QUARANTINE_LBL, model.getProbOfQuarantine() + " %");
        view.setUiValue(QUARANTINE_LBL, model.getTotalTimesOfQuarantine());
        view.setUiValue(KILL_BY_AGE_LBL, model.getKillByAge() + " ( " + df2.format((float)model.getKillByAge()*(float)100/totalActor)+ "% ) ");
        view.setUiValue(KILL_BY_SICK_LBL, model.getKillBySick() + " ( " + df2.format((float)model.getKillBySick()*(float)100/totalActor) + "% ) ");
        view.setUiValue(CURED_LBL, model.getTotalOfCured() + " ( " + df2.format((float)model.getTotalOfCured()*(float)100/model.getTotalNumberOfInfected()) + "% ) ");

        List<Long> ageGroupTemp = new LinkedList<>();
        List<int []> ageGroup = new LinkedList<>();
        ageGroup.add(new int[] {0,19});
        ageGroup.add(new int[] {20,39});
        ageGroup.add(new int[] {40,59});
        ageGroup.add(new int[] {60,100});

        //uses callback function syntax-like(for each loop) to run through user condition and set value into long (this is from wang lin)
        for (int[] age: ageGroup){
            ageGroupTemp.add(model.getActorList().stream().filter((k) -> k.getAge() >= age[0] && k.getAge() <= age[1] && k.getClass().toString().contains("Actor")).count());
            ageGroupTemp.add(model.getActorList().stream().filter((k) -> k.getAge() >= age[0] && k.getAge() <= age[1] && k.getClass().toString().contains("Sick")).count());
            ageGroupTemp.add(model.getDeadActorList().stream().filter((k) -> k.getAge() >= age[0] && k.getAge() <= age[1] && k.getWayToDie() == ActorEnum.KILL_BY_AGE).count());
            ageGroupTemp.add(model.getDeadActorList().stream().filter((k) -> k.getAge() >= age[0] && k.getAge() <= age[1] && k.getWayToDie() == ActorEnum.KILL_BY_SICK).count());
        }
        view.setUiValue(AGE_GROUP_LBL, ageGroupTemp);
    }

    //Click to close the result pop up
    //To let user to continue the game but it will continue without any result pop up again
    class ContinueBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.setContinueGame(true);
            model.closePopUpPage(RESULT_POP_UP);
        }
    }

    //Click to close the result pop up
    //To let user to return back to main page, not quit the game
    class QuitBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.closeAllPopUpPage();
            Router.getInstance().screenUpdate(MAIN_PAGE);
        }
    }
}
