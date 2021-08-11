package com.Assignment;

import com.Assignment.GamePage.Charactor.ActorInterface;
import com.Assignment.LComponent.LFrame;
import com.Assignment.PageAbstract.EnumInterface;

import javax.swing.*;
import java.util.*;
import java.util.List;


//made by jason
//modify by wang lin

//model is to act as a database from the mvc
public class Model {
    private LFrame mainFrame;
    private Map<EnumInterface, LFrame> popUpPageMap = new HashMap<>();
    private EnumInterface ending;
    private int day, worldSize, popSize, maxDay, probOfInfect, probOfQuarantine, probOfChronic, percentOfInitInfect, year;
    private List<ActorInterface> deadActorList = new LinkedList<>();
    private List<ActorInterface> actorList = new LinkedList<>();
    private boolean continueGame;
    private int totalNumberOfInfected,totalTimesOfQuarantine,killBySick,killByAge,totalOfCured;
    private int selectedActorId;

    // many will let data save in different place
    // not necessary to create multiple time
    // use singleton design pattern to avoid create multiple times.
    // A method of creating an instance (object) but only one object could only exist at a time
    //Singleton
    private static Model instance = null;
    public static Model getInstance(){
        if (instance == null) {      // double check
            synchronized (Model.class){  // thread safe singleton
                if (instance == null) {     // double check
                    instance = new Model();
                }
            }
        }
        return instance;
    }
    //Singleton End

    public void setMainFrame(LFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    // before set panel to frame will remove all component in frame
    // after remove will add new panel to it and refresh
    public void setMainPanel(JPanel mainPanel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(mainPanel);
        // refresh mainframe
        mainFrame.validate();
    }
    public int getWorldSize() {
        return worldSize;
    }
    public void setWorldSize(int worldSize) {
        this.worldSize = worldSize;
    }
    public int getPopSize() {
        return popSize;
    }
    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }
    public int getMaxDay() {
        return maxDay;
    }
    public void setMaxDay(int maxDay) {
        this.maxDay = maxDay;
    }
    public int getProbOfInfect() {
        return probOfInfect;
    }
    public void setProbOfInfect(int probOfInfect) {
        this.probOfInfect = probOfInfect;
    }
    public int getProbOfChronic() {
        return probOfChronic;
    }
    public void setProbOfChronic(int probOfChronic) {
        this.probOfChronic = probOfChronic;
    }
    public int getPercentOfInitInfect() {
        return percentOfInitInfect;
    }
    public void setPercentOfInitInfect(int percentOfInitInfect) {
        this.percentOfInitInfect = percentOfInitInfect;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public List<ActorInterface> getDeadActorList() {
        return deadActorList;
    }
    // set deadActorList to deadActorList (replace)
    public void setDeadActorList(List<ActorInterface> deadActorList) {
        this.deadActorList = deadActorList;
    }
    // set deadActor to deadActorList (add)
    public void setDeadActorList(ActorInterface deadActor) {
        this.deadActorList.add(deadActor);
    }
    // before open new pop up, it will check it is open or not
    // if yes it will close it and remove it from popUpPageMap first and create again
    // if no will directly create
    public void openPopUpPage(EnumInterface enumVar,LFrame popUpPage) {
        // putIfAbsent() = check exist or not, if not then put
        if(this.popUpPageMap.containsKey(enumVar)) {
            this.popUpPageMap.get(enumVar).dispose();
            this.popUpPageMap.remove(enumVar);
        }
        this.popUpPageMap.put(enumVar, popUpPage);
    }
    // close pop up page according key(enumVar) and remove it from popUpPageMap
    public void closePopUpPage(EnumInterface enumVar) {
        this.popUpPageMap.get(enumVar).dispose();
        this.popUpPageMap.remove(enumVar);
    }
    // close all pop up page and clear popUpPageMap
    public void closeAllPopUpPage() {
        for (Map.Entry<EnumInterface, LFrame> entry : popUpPageMap.entrySet()) {
            entry.getValue().dispose();
        }
        popUpPageMap.clear();
    }
    public boolean getContinueGame() {
        return continueGame;
    }
    public void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }
    public EnumInterface getEnding() {
        return ending;
    }
    public void setEnding(EnumInterface ending) {
        this.ending = ending;
    }
    public int getTotalNumberOfInfected() {
        return totalNumberOfInfected;
    }
    public void setTotalNumberOfInfected(int totalNumberOfInfected) {
        this.totalNumberOfInfected = totalNumberOfInfected;
    }
    public int getProbOfQuarantine() {
        return probOfQuarantine;
    }
    public void setProbOfQuarantine(int probOfQuarantine) {
        this.probOfQuarantine = probOfQuarantine;
    }
    public List<ActorInterface> getActorList() {
        return actorList;
    }
    public void setActorList(List<ActorInterface> actorList) {
        this.actorList = actorList;
    }
    public int getTotalTimesOfQuarantine() {
        return totalTimesOfQuarantine;
    }
    public void setTotalTimesOfQuarantine(int totalTimesOfQuarantine) {
        this.totalTimesOfQuarantine = totalTimesOfQuarantine;
    }
    public int getKillBySick() {
        return killBySick;
    }
    public void setKillBySick(int killBySick) {
        this.killBySick = killBySick;
    }
    public int getKillByAge() {
        return killByAge;
    }
    public void setKillByAge(int killByAge) {
        this.killByAge = killByAge;
    }
    public int getTotalOfCured() {
        return totalOfCured;
    }
    public void setTotalOfCured(int totalOfCured) {
        this.totalOfCured = totalOfCured;
    }
    public int getSelectedActorId() {
        return selectedActorId;
    }
    public void setSelectedActorId(int selectedActorId) {
        this.selectedActorId = selectedActorId;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
