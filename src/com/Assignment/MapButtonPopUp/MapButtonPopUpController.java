package com.Assignment.MapButtonPopUp;

import com.Assignment.GamePage.Charactor.ActorInterface;
import com.Assignment.GamePage.Charactor.Sick;
import com.Assignment.LComponent.LFrame;
import com.Assignment.Model;
import com.Assignment.PageAbstract.ControllerInterface;
import com.Assignment.PageAbstract.ViewAbstract;

import javax.swing.*;

import static com.Assignment.PageNameEnum.SETTING_POP_UP;
import static com.Assignment.MapButtonPopUp.MapButtonPopUpEnum.*;

// make by: wang lin

// controller for map button pop up page
public class MapButtonPopUpController implements ControllerInterface {

    // create frame and save panel create by map button pop up view
    // get value by actor id set value to UI
    public MapButtonPopUpController(ViewAbstract view, Model model) {
        view.uiBuildChain();
        LFrame lFrame = new LFrame("Map Button", 800, 500, JFrame.DISPOSE_ON_CLOSE);
        lFrame.add(view.getMainPanel());
        model.openPopUpPage(SETTING_POP_UP, lFrame);


        for (ActorInterface actor : model.getActorList()) {
            if (actor.getId() == model.getSelectedActorId()) {
                view.setUiValue(TITLE_LBL, actor.getClass().toString().contains("Sick") ? "Sick people" : "Normal people");
                view.setUiValue(ID_LBL, actor.getId());
                view.setUiValue(AGE_LBL, actor.getAge() + " / " + actor.getExpectedAge());
                view.setUiValue(STATUS_LBL, actor.isHaveChronic() + " / " + (actor.getIsQuarantine() > 0) + " / " + actor.getIsImmune());
                view.setUiValue(LOCATION_LBL,"x: " + actor.getX() + " y:" + actor.getY());

                if(actor.getExX() != -1 && actor.getExY() != -1) {
                    view.setUiValue(EX_LOCATION_LBL,"last round x: " + actor.getExX() + " last round y: " + actor.getExY());
                } else {
                    view.setUiValue(EX_LOCATION_LBL, "No history move");
                }
                view.setUiValue(TOTAL_QUARANTINE_DAY_LBL, actor.getTotalQuarantineDay());

                StringBuilder temp = new StringBuilder();
                for (int[] k : actor.getPossiblePlace()) {
                    temp.append("[").append(k[0]).append(",").append(k[1]).append("],");
                }
                view.setUiValue(POSSIBLE_PLACE_LBL, temp.toString().length() == 0 ? "Haven't start" : temp.toString().substring(0,temp.length() - 1));

                if(actor.getClass().toString().contains("Sick")) {
                    Sick sick = (Sick) actor;
                    view.setUiValue(SICK_DAY_LEFT_LBL, sick.getSickDay());
                    view.setUiValue(PROBABILITY_OF_DEAD_LBL, sick.getProbabilityOfDead());
                }

                view.setUiValue(RANDOM_QUARANTINE_LBL, model.getDay() <= 1 ? "First day cannot quarantine" : actor.getRandomQuarantine());
            }
        }
    }
}
