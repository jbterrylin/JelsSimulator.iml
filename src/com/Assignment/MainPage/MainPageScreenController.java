package com.Assignment.MainPage;

import com.Assignment.PageAbstract.ControllerInterface;
import com.Assignment.Model;
import com.Assignment.PageAbstract.ViewAbstract;
import com.Assignment.Router;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.Assignment.MainPage.MainPageScreenEnum.*;
import static com.Assignment.PageNameEnum.GAME_SETTING_PAGE;

//Make by: Joel
//Modify by : Wang Lin
    //This class is a controller, where control between view and model(database)
public class MainPageScreenController implements ControllerInterface {

    public MainPageScreenController(ViewAbstract view, Model model) {
        view.uiBuildChain();// call view to create UI(in panel form)
        model.setMainPanel(view.getMainPanel());// set panel from  to main frame

        // set listener to ui component
        view.listenerDistributor(START_BTN, new startBtnListener());
        view.listenerDistributor(EXIT_BTN, new exitBtnListener());
    }

    //When Click start button will go to router and update the screen to game setting page
    class startBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Router.getInstance().screenUpdate(GAME_SETTING_PAGE);
        }
    }

    //This button will exit the programme
    static class exitBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
