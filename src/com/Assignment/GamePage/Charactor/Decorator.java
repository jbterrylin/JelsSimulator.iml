package com.Assignment.GamePage.Charactor;

import com.Assignment.GamePage.GamePageScreenEnum;

import java.util.ArrayList;
import java.util.List;

//made by jason
//modify by wang lin

// Decorator
//to reduce redundant function
//can let actor change'shirt' frequently between normal people and sick people

    // Explanation:
    // if we create object call sick people and normal people, it is meaningless because they have redundant function
    // if create a parent class to them is not good also, because i want actor to change between normal people and sick people frequently
    // so decorator is most suitable because it can let normal people wear "Sick" if needed and drop "Sick" and without moving a lot of data

// For our scenario:
// this class is not really useful in this program
// but if have over 2 decorator in future, this class can help us a lot
// because it help us reduce redundant function that need call from actor
public class Decorator implements ActorInterface {
    private ActorInterface actor;

    public Decorator(ActorInterface actor){
        this.actor = actor;
    }
    public void move(ArrayList<ArrayList<GamePageScreenEnum>> map) {
        actor.move(map);
    }
    public void setRandomQuarantine() {
        actor.setRandomQuarantine();
    }
    public int getRandomQuarantine() {
        return actor.getRandomQuarantine();
    }
    public ActorInterface getActor() {
        return actor;
    }
    public int getId() {
        return actor.getId();
    }
    public void setId(int Id) {
        actor.setId(Id);
    }
    public int getAge() {
        return actor.getAge();
    }
    public void setAge(int age) {
        actor.setAge(age);
    }
    public boolean isHaveChronic() {
        return actor.isHaveChronic();
    }
    public void setHaveChronic(boolean haveChronic) {
        actor.setHaveChronic(haveChronic);
    }
    public boolean getIsImmune() {
        return actor.getIsImmune();
    }
    public void setIsImmune(boolean immune) {
        actor.setIsImmune(immune);
    }
    public int getExpectedAge() {
        return actor.getExpectedAge();
    }
    public void setExpectedAge(int expectedAge) {
        actor.setExpectedAge(expectedAge);
    }
    public int getX() {
        return actor.getX();
    }
    public void setX(int x) {
        actor.setX(x);
    }
    public int getY() {
        return actor.getY();
    }
    public void setY(int y) {
        actor.setY(y);
    }
    public boolean getIsMove() {
        return actor.getIsMove();
    }
    public void setIsMove(boolean isMove) {
        actor.setIsMove(isMove);
    }
    public int getIsQuarantine() {
        return actor.getIsQuarantine();
    }
    public void setIsQuarantine(int isQuarantine) {
        actor.setIsQuarantine(isQuarantine);
    }
    public int getTotalQuarantineDay() {
        return actor.getTotalQuarantineDay();
    }
    public void setTotalQuarantineDay(int totalQuarantineDay) {
        actor.setTotalQuarantineDay(totalQuarantineDay);
    }
    public int getExX() {
        return actor.getExX();
    }
    public void setExX(int exX) {
        actor.setExX(exX);
    }
    public int getExY() {
        return actor.getExY();
    }
    public void setExY(int exY) {
        actor.setExY(exY);
    }
    public List<int []> getPossiblePlace() {
        return actor.getPossiblePlace();
    }
    public ActorEnum getWayToDie() {
        return actor.getWayToDie();
    }
    public void setWayToDie(ActorEnum wayToDie) {
        actor.setWayToDie(wayToDie);
    }
}
