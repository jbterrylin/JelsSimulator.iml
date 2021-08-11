package com.Assignment.GamePage.Charactor;

import java.util.Random;

//made by jason
//modify by wang lin

// actor's decorator
// decorator for the actor, to let actor wears the 'sick' shirt
public class Sick extends Decorator {
    private int sickDay, probabilityOfDead;

    //need to receive actor to create this class
    //when create will random probability of dead
    public Sick(ActorInterface actor) {
        super(actor);
        probabilityOfDead = new Random().nextInt(100) + 1;
    }
    public int getSickDay() {
        return sickDay;
    }
    public int getProbabilityOfDead() {
        return probabilityOfDead;
    }
    // when call will increase sickDay
    // if sickDay reach day 10, this actor have chronic, and probability of dead is over 80 will return true
    // else false
    public boolean isKillBySick() {
        sickDay++;
        if(sickDay == 10 && isHaveChronic() && probabilityOfDead > 80) {
            setWayToDie(ActorEnum.KILL_BY_SICK);
            return true;
        }
        return false;
    }
}
