package com.Assignment;

import com.Assignment.GameSettingPage.GameSettingMemento;

// Made by Cheok Jia Heng
// Modified by Wang Lin

// Memento
// why we use:
    // create a place to let us save value
    // Memento is place to save value
    // Memento takes a snapshot of current value and restore it in the future
    // Memento does not disrupt the object internal structure
    // MementoTaker is place to keep memento
public class MementoTaker {
    private GameSettingMemento gameSettingMemento;

    //Singleton
    private static MementoTaker instance = null;
    public static MementoTaker getInstance(){
        if (instance == null) {      //双重检测机制
            synchronized (MementoTaker.class){  //同步锁
                if (instance == null) {     //双重检测机制
                    instance = new MementoTaker();
                }
            }
        }
        return instance;
    }
    //Singleton End

    public GameSettingMemento getGameSettingMemento() {
        return gameSettingMemento;
    }
    public void setGameSettingMemento(GameSettingMemento gameSettingMemento) {
        this.gameSettingMemento = gameSettingMemento;
    }
}
