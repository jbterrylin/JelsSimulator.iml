package com.Assignment.GamePage.Charactor;

import com.Assignment.GamePage.GamePageScreenEnum;

import java.util.ArrayList;
import java.util.List;

//made by jason
//modify by wang lin

// interface for actor, create to achieve decorator
// for doing further implement (actor&decorator)
public interface ActorInterface {
    int getId();
    void move(ArrayList<ArrayList<GamePageScreenEnum>> map);
    void setRandomQuarantine();
    int getRandomQuarantine();
    void setId(int Id);
    int getAge();
    void setAge(int age);
    boolean isHaveChronic();
    void setHaveChronic(boolean haveChronic);
    boolean getIsImmune();
    void setIsImmune(boolean isImmune);
    int getExpectedAge();
    void setExpectedAge(int expectedAge);
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
    boolean getIsMove();
    void setIsMove(boolean isMove);
    int getIsQuarantine();
    void setIsQuarantine(int isQuarantine);
    int getTotalQuarantineDay();
    void setTotalQuarantineDay(int totalQuarantineDay);
    int getExX();
    void setExX(int exX);
    int getExY();
    void setExY(int exY);
    List<int []> getPossiblePlace();
    ActorEnum getWayToDie();
    void setWayToDie(ActorEnum wayToDie);
}
