package com.Assignment.GamePage;

import com.Assignment.GamePage.Charactor.Actor;
import com.Assignment.GamePage.Charactor.ActorEnum;
import com.Assignment.GamePage.Charactor.ActorInterface;
import com.Assignment.GamePage.Charactor.Sick;
import com.Assignment.Model;
import com.Assignment.PageAbstract.ControllerInterface;
import com.Assignment.PageAbstract.ViewAbstract;
import com.Assignment.Router;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static com.Assignment.GamePage.GamePageScreenEnum.*;
import static com.Assignment.PageNameEnum.*;

// make by: wang lin

// controller for game page
public class GamePageScreenController implements ControllerInterface {
    private ViewAbstract view;
    private Model model;
    // ArrayList(row) <ArrayList (column)<Screen3Enum (var type)>>
    private ArrayList<ArrayList<GamePageScreenEnum>> map = new ArrayList<>();
    Timer timer;

    // set default value and initial map
    // spawn actor and put it to map
    // call view to create UI(in panel form)
    // set panel from  to main frame
    // set listener to ui component
    public GamePageScreenController(ViewAbstract view, Model model) {
        this.view = view;
        this.model = model;

        this.model.setDay(0);
        this.model.setTotalNumberOfInfected(0);
        this.model.setTotalTimesOfQuarantine(0);
        this.model.setKillByAge(0);
        this.model.setKillBySick(0);
        this.model.setTotalOfCured(0);
        this.model.setActorList(new LinkedList<>());
        this.model.setDeadActorList(new LinkedList<>());
        this.model.setContinueGame(false);

        this.view.setUiValue(WORLD_SIZE, model.getWorldSize());
        this.view.uiBuildChain();
        initialMap();
        spawnActorInitialLocation();


        setTimer();
        rightPanelDataUpdate();

        this.view.setUiValue(MAP_COORDINATE, map);
        this.view.setUiValue(SPEED_TF, timer.getDelay());
        this.view.setUiValue(DAY_TF, model.getDay() +" / " + model.getMaxDay());

        this.view.listenerDistributor(SLOWER_BTN, new SlowerBtnListener());
        this.view.listenerDistributor(PLAY_PAUSE_BTN, new PlayPauseBtnListener());
        this.view.listenerDistributor(FASTER_BTN, new FasterBtnListener());
        this.view.listenerDistributor(SETTING_BTN, new SettingBtnListener());
        this.view.listenerDistributor(EXIT_BTN, new ExitBtnListener());
        this.view.listenerDistributor(MAP, new MapListener());

        this.model.setMainPanel(view.getMainPanel());
    }

    // get data from model and set the healthy,infected, quarantine and dead to UI(right data panel)
    private void rightPanelDataUpdate() {
        // uses callback function syntax-like(for each loop) to run through user condition and set value(long format)
        this.view.setUiValue(HEALTHY_TF, model.getActorList().stream().filter((ActorInterface k) -> !k.getClass().toString().contains("Sick")).count());
        this.view.setUiValue(INFECTED_TF, model.getActorList().stream().filter((ActorInterface k) -> k.getClass().toString().contains("Sick")).count());
        this.view.setUiValue(QUARANTINE_TF, model.getActorList().stream().filter((ActorInterface k) -> k.getIsQuarantine() > 0).count());
        this.view.setUiValue(DEAD_TF, model.getDeadActorList().size());
    }

    // spawn actor with giving initial location
    private void spawnActorInitialLocation() {
        List<ActorInterface> actorList = model.getActorList();

        // shuffle substitute random
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < model.getWorldSize() * model.getWorldSize(); i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        // before change point is sick actor, then
        // before stop point is healthy actor
        // coordinate base on random number above
        int stopPoint = model.getWorldSize() * model.getWorldSize() * model.getPopSize() / 100; // healthy actor
        int changePoint = stopPoint * model.getPercentOfInitInfect() / 100; // sick actor
        Iterator<Integer> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            int temp = iterator.next();
            if(i < changePoint) {
                actorList.add( new Sick( new Actor(temp % model.getWorldSize(), temp / model.getWorldSize(), model.getProbOfChronic(), i == 0 ) ) );
                model.setTotalNumberOfInfected(model.getTotalNumberOfInfected() + 1);
            } else {
                actorList.add( new Actor(temp % model.getWorldSize(), temp / model.getWorldSize(), model.getProbOfChronic(), i == 0 ) );
            }
            putActorToMap(actorList.get(actorList.size() - 1));
            i++;
            if(i == stopPoint) {
                break;
            }
        }
        model.setActorList(actorList);
    }

    // refresh / initial map
    private void initialMap() {
        map = new ArrayList<>();
        for(int i=0; i<model.getWorldSize(); i++){
            map.add(new ArrayList<>());
            for(int j=0; j<model.getWorldSize(); j++){
                map.get(i).add(NO_PEOPLE);
            }
        }
    }

    // check actor class(sick/ healthy) and put to map
    // if actor is quarantine also need to note down
    private void putActorToMap(ActorInterface actor) {
        if(actor.getClass().toString().contains("Actor")) {
            map.get(actor.getX()).set(actor.getY(), NORMAL_PEOPLE);
        } else {
            map.get(actor.getX()).set(actor.getY(), SICK_PEOPLE);
        }
        if(actor.getIsQuarantine() != 0) {
            for(int i=-1; i<2; i++) {
                for(int j=-1; j<2; j++) {
                    if(actor.getX() + i < map.size() && actor.getX() + i >= 0)
                        if(actor.getY() + j < map.size() && actor.getY() + j >= 0)
                            if(!(i == 0 && j == 0)) {
                                map.get(actor.getX() + i).set(actor.getY() + j, QUARANTINE_ZONE);
                            }
                }
            }
        }
    }

    // check have any actor beside this actor or not
    // direction can be 4(up, down, left, right) / 8
    private List<int []> checkNearByPeople(ActorInterface actor,int direction) {
        List<int []> nearByPeople = new LinkedList<>();
        for(int i=-1; i<2; i++) {
            for(int j=-1; j<2; j++) {
                if(actor.getX() + i < map.size() && actor.getX() + i >= 0)
                    if(actor.getY() + j < map.size() && actor.getY() + j >= 0)
                        if(direction == 4) {
                            if(!(i == 0 && j == 0) && i == 0 || j == 0) {
                                if(map.get(actor.getX() + i).get(actor.getY() + j) == NORMAL_PEOPLE ||
                                        map.get(actor.getX() + i).get(actor.getY() + j) == SICK_PEOPLE) {
                                    nearByPeople.add(new int[] {actor.getX() + i, actor.getY() + j});
                                }
                            }
                        } else {
                            if(!(i == 0 && j == 0)) {
                                if(map.get(actor.getX() + i).get(actor.getY() + j) == NORMAL_PEOPLE ||
                                        map.get(actor.getX() + i).get(actor.getY() + j) == SICK_PEOPLE) {
                                    nearByPeople.add(new int[] {actor.getX() + i, actor.getY() + j});
                                }
                            }
                        }

            }
        }
        return nearByPeople;
    }

    // check game is end or not
    // way of end: iteration up, all people die, no people sick anymore
    private void checkEnd() {
        if (!model.getContinueGame()) {
            List<ActorInterface> actorList = model.getActorList();
            if(model.getMaxDay() == model.getDay()) {
                model.setEnding(NORMAL_ENDING);
                Router.getInstance().screenUpdate(RESULT_POP_UP);
                timer.stop();
                view.setUiValue(PLAY_PAUSE_BTN, "/com/Assignment/image/play.png");
            } else if(actorList.size() == 0) {
                model.setEnding(BAD_ENDING);
                Router.getInstance().screenUpdate(RESULT_POP_UP);
                timer.stop();
                view.setUiValue(PLAY_PAUSE_BTN, "/com/Assignment/image/play.png");
            } else {
                int sickPeople = (int) actorList.stream().filter(k -> k.getClass().toString().contains("Sick")).count();
                if(sickPeople == 0) {
                    model.setEnding(GOOD_ENDING);
                    Router.getInstance().screenUpdate(RESULT_POP_UP);
                    timer.stop();
                    view.setUiValue(PLAY_PAUSE_BTN, "/com/Assignment/image/play.png");
                }
            }
        }
    }

    // Priority of loop:
        // 1 = die actor die first
        // 2 = actor is already quarantine then continue quarantine
        // 3 = check actor wish to quarantine or move, if move then move
        // 4 = quarantine actor that wish to quarantine
        // 5 = if actor cannot quarantine, then move
    private void setTimer() {
        List<ActorInterface> actorList = model.getActorList();
        timer=new Timer(1000, e -> {
            model.setDay(model.getDay() + 1);
            checkEnd();
            initialMap();
            // move dead actor to deadActorList
            Iterator<ActorInterface> iterator = actorList.iterator();
            while(iterator.hasNext()) {
                ActorInterface actor = iterator.next();
                if(model.getDay() / 365 > model.getYear()) {
                    model.setYear(model.getYear() + 1);
                    actor.setAge(actor.getAge() + 1);
                }

                // kill by sick
                if(actor.getClass().toString().contains("Sick")) {
                    Sick sick = (Sick) actor;
                    if(sick.isKillBySick()) {
                        model.setDeadActorList(actorList.get(actorList.indexOf(actor)));
                        //actorList.remove(i);
                        iterator.remove();
                        model.setKillBySick(model.getKillBySick() + 1);
                        continue;
                    }
                }

                // kill by age
                if(actor.getAge() == actor.getExpectedAge() && actorList.indexOf(actor) != -1) {
                    model.setDeadActorList(actorList.get(actorList.indexOf(actor)));
                    model.setKillByAge(model.getKillByAge() + 1);
                    actor.setWayToDie(ActorEnum.KILL_BY_AGE);
                    //actorList.remove(i);
                    iterator.remove();
                    continue;
                }

                // check already quarantine or not, if quarantine-ing then continue
                // if not give a pass(isMove) to next move
                if (actor.getIsQuarantine() > 0) {
                    actor.setIsQuarantine(actor.getIsQuarantine() - 1);
                    actor.setTotalQuarantineDay(actor.getTotalQuarantineDay() + 1);
                    actor.setIsMove(true);
                    if(actor.getIsQuarantine() == 1 && actor.getClass().toString().contains("Sick")) {
                        Sick sick = (Sick) actor;
                        actorList.set(actorList.indexOf(actor), sick.getActor());
                        model.setTotalOfCured(model.getTotalOfCured() + 1);
                        actor.setIsImmune(true);
                    }
                    putActorToMap(actor);
                } else {
                    //if not quarantine, give a pass to quarantine or move
                    actor.setIsMove(false);
                }
            }

            // if quarantine actor want to move then move
            // if want quarantine also will put on map first
            iterator = actorList.iterator();
            while(iterator.hasNext()) {
                ActorInterface actor = iterator.next();
                if (!actor.getIsMove()) {
                    actor.setRandomQuarantine();
                    //if move
                    if (actor.getRandomQuarantine() > model.getProbOfQuarantine()) {
                        actor.move(map);
                        actor.setIsMove(true);
                    }
                    putActorToMap(actor);
                }
            }

            // quarantine actor that want to quarantine, if cannot quarantine then move
            iterator = actorList.iterator();
            while(iterator.hasNext()) {
                ActorInterface actor = iterator.next();
                if(!actor.getIsMove()) {
                    if(actor.getRandomQuarantine() <= model.getProbOfQuarantine()) {
                        if(checkNearByPeople(actor,8).size() == 0 && model.getDay() != 1) {
                            // quarantine
                            actor.setIsQuarantine(14);
                            if(actor.getIsQuarantine() == 14) {
                                model.setTotalTimesOfQuarantine(model.getTotalTimesOfQuarantine() + 1);
                            }
                        } else {
                            // move
                            if(actorList.stream().filter((k) -> k.getX() == actor.getX() && k.getY() == actor.getY()).count() == 1) {
                                map.get(actor.getX()).set(actor.getY(), NO_PEOPLE);
                            }
                            actor.move(map);
                        }
                        actor.setIsMove(true);
                        putActorToMap(actor);
                    }
                }
            }

            //spread virus
            iterator = actorList.iterator();
            while(iterator.hasNext()) {
                ActorInterface actor = iterator.next();
                if(actor.getClass().toString().contains("Sick")) {
                    Sick temp = (Sick) actor;
                    if(temp.getSickDay() != 0){
                        List<int[]> nearByPeople = checkNearByPeople(actor,4);
                        nearByPeople.forEach((k) ->
                            actorList.forEach((v) -> {
                                    if (k[0] == v.getX() && k[1] == v.getY() && !v.getClass().toString().contains("Sick")) {
                                        if(!actorList.get(actorList.indexOf(v)).getIsImmune()) {
                                            actorList.set(actorList.indexOf(v), new Sick(v));
                                            model.setTotalNumberOfInfected(model.getTotalNumberOfInfected() + 1);
                                            putActorToMap(v);
                                        }
                                    }
                                }
                        ));
                    }
                }
            }

            rightPanelDataUpdate();
            // send map to UI and let it refresh
            this.view.setUiValue(MAP_COORDINATE, map);
            this.model.setMainPanel(view.getMainPanel());
            this.view.setUiValue(DAY_TF, model.getDay() +" / " + model.getMaxDay());
        });
    }

    // listener of slower button
    // if click timer slower than original 2 times
    class SlowerBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (timer.getDelay() < 4000)
                timer.setDelay(timer.getDelay() * 2);
                view.setUiValue(SPEED_TF, timer.getDelay());
        }
    }

    // listener of play and pause button
    // if click can stop / start timer
    class PlayPauseBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(!timer.isRunning()) {
                timer.start();
                view.setUiValue(PLAY_PAUSE_BTN, "/com/Assignment/image/pause.png");
            } else {
                timer.stop();
                view.setUiValue(PLAY_PAUSE_BTN, "/com/Assignment/image/play.png");
            }
        }
    }

    // listener of faster button
    // if click timer faster than original 2 times
    class FasterBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (timer.getDelay() > 125)
                timer.setDelay(timer.getDelay() / 2);
                view.setUiValue(SPEED_TF, timer.getDelay());
        }
    }

    // listener of setting button
    // if click will call router and setting pop up page will pop up
    class SettingBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Router.getInstance().screenUpdate(SETTING_POP_UP);
        }
    }

    // listener of exit button
    // if click will close all pop up page and route to main page
    class ExitBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            model.closeAllPopUpPage();
            Router.getInstance().screenUpdate(MAIN_PAGE);
        }
    }

    // listener of map button
    // if click will find actor according map coordinate
    // if found will set actor id to model and open map button pop up page
    class MapListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MapButton mapButton = (MapButton) e.getSource();
            for (ActorInterface actor : model.getActorList()) {
                if (actor.getX() == mapButton.getMapX() && (actor.getY() == mapButton.getMapY())) {
                    //Pop up
                    Model.getInstance().setSelectedActorId(actor.getId());
                    Router.getInstance().screenUpdate(MAP_BUTTON_POP_UP);
                }
            }
        }
    }
}
