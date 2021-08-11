package com.Assignment.GameSettingPage;

// Made by Cheok Jia Heng
// Modified by Wang Lin

// a template to save game setting page's value
public class GameSettingMemento {
    private int maxDay, probOfInfect, probOfQuarantine, probOfChronic;

    // get value from game setting page and save
    public GameSettingMemento(int maxDay, int probOfInfect, int probOfQuarantine, int probOfChronic) {
        this.maxDay = maxDay;
        this.probOfInfect = probOfInfect;
        this.probOfQuarantine = probOfQuarantine;
        this.probOfChronic = probOfChronic;
    }

    public int getMaxDay() {
        return maxDay;
    }
    public int getProbOfInfect() {
        return probOfInfect;
    }
    public int getProbOfQuarantine() {
        return probOfQuarantine;
    }
    public int getProbOfChronic() {
        return probOfChronic;
    }
}
