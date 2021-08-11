package com.Assignment.GamePage.Charactor;

import com.Assignment.GamePage.GamePageScreenEnum;

import java.util.*;

import static com.Assignment.GamePage.GamePageScreenEnum.NO_PEOPLE;

//made by jason
//modify by wang lin


//To create actor and do set/get actor data
//To do actor movement in the game
public class Actor implements ActorInterface {
    private static int actorCount = 0;
    private int Id;
    private int age, expectedAge;
    private boolean haveChronic = false, isImmune = false;
    private int x,y,exX = -1,exY = -1;
    private boolean isMove;
    // randomQuarantine is chance to quarantine
    // isQuarantine show how many day he need to quarantine, if 0 means no quarantine
    private int randomQuarantine, isQuarantine, totalQuarantineDay;
    // which coordinate he can go
    private List<int []> possiblePlace = new LinkedList<>();
    private ActorEnum wayToDie;

    //Constructor for get the current location and set probability chronic of actor
    // set initial x, y, haveChronic, expectedAge, age, Id, reset actor count if needed
    public Actor(int x, int y, int probOfChronic, boolean resetCount) {
        this.x = x;
        this.y = y;
        if(new Random().nextInt(100) + 1 < probOfChronic) {
            haveChronic = true;
        }
        expectedAge = new Random().nextInt(100) + 1;
        age = new Random().nextInt(expectedAge);
        if(resetCount) {
            actorCount = 0;
        }
        actorCount++;
        Id = actorCount;
    }

    // To do surround place coordinate checking and set new coordinate for the actor to move
    // get map from controller and select the place can move
    // set final select move's coordinate to own's x, y
    public void move(ArrayList<ArrayList<GamePageScreenEnum>> map) {
        List<int []> possiblePlaceToGo = new ArrayList<>();

        // loop to check place he can go
        // if the coordinate around it is full will check bigger circle around him
        List<Integer> temp = new ArrayList<>();
        temp.add(0); temp.add(-1); temp.add(1);
        while (possiblePlaceToGo.size() == 0) {
            for(int i=0; i<temp.size(); i++) {
                for(int j=0; j<temp.size(); j++) {
                    // filter coordinate over map bound
                    if (getX() + temp.get(i) < map.size() && getX() + temp.get(i) >= 0) //row
                        if(getY() + temp.get(j) < map.size() && getY() + temp.get(j) >= 0) //column
                            // check coordinate have other actor or not
                            if(map.get(getX() + temp.get(i)).get(getY() + temp.get(j)) == NO_PEOPLE)
                                possiblePlaceToGo.add(new int[]{getX() + temp.get(i), getY() + temp.get(j)});
                }
            }
            // if this circle is full, create bigger circle checking
            if(possiblePlaceToGo.size() == 0) {
                for (int i = 0; i < 2; i++) {
                    temp.add(temp.get(temp.size() - 2) - 1);
                    temp.add(temp.get(temp.size() - 2) + 1);
                }
            }
        }

        // firstly pick up, down, left, right
        // if own place have people will choose bevel
        // if not will pick bigger angel bevel
        possiblePlace = new LinkedList<>();
        int i = 0, j = 0;
        while (possiblePlace.size() == 0) {
            for (int[] placeToGo : possiblePlaceToGo) {
                if (placeToGo[0] == x + i || placeToGo[0] == x + j || placeToGo[1] == y + i || placeToGo[1] == y + j) {
                        possiblePlace.add(new int[]{placeToGo[0], placeToGo[1]});
                }
            }
            // if actor can move up,down,left,right one step, then he cannot stand on original coordinate
            if(temp.size() == 3 && i == 0 && possiblePlace.size() > 1) {
                possiblePlace.removeIf(value -> value[0] == x && value[1] == y);
            }
            i++;
            j--;
        }
        // random from the coordinate he can go
        int SelectedPlace = new Random().nextInt(possiblePlace.size());
        exX = x;
        exY = y;
        x = possiblePlace.get(SelectedPlace)[0];
        y = possiblePlace.get(SelectedPlace)[1];
    }

    // random a number of quarantine
    public void setRandomQuarantine() {
        randomQuarantine = new Random().nextInt(100) + 1;
    }

    public int getRandomQuarantine() {
        return randomQuarantine;
    }

    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean isHaveChronic() {
        return haveChronic;
    }
    public void setHaveChronic(boolean haveChronic) {
        this.haveChronic = haveChronic;
    }
    public boolean getIsImmune() {
        return isImmune;
    }
    public void setIsImmune(boolean isImmune) {
        this.isImmune = isImmune;
    }
    public int getExpectedAge() {
        return expectedAge;
    }
    public void setExpectedAge(int expectedAge) {
        this.expectedAge = expectedAge;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean getIsMove() {
        return isMove;
    }
    public void setIsMove(boolean isMove) {
        this.isMove = isMove;
    }
    public int getIsQuarantine() {
        return isQuarantine;
    }
    public void setIsQuarantine(int isQuarantine) {
        this.isQuarantine = isQuarantine;
    }
    public int getTotalQuarantineDay() {
        return totalQuarantineDay;
    }
    public void setTotalQuarantineDay(int totalQuarantineDay) {
        this.totalQuarantineDay = totalQuarantineDay;
    }
    public int getExX() {
        return exX;
    }
    public void setExX(int exX) {
        this.exX = exX;
    }
    public int getExY() {
        return exY;
    }
    public void setExY(int exY) {
        this.exY = exY;
    }
    public List<int []> getPossiblePlace() {
        return possiblePlace;
    }
    public ActorEnum getWayToDie() {
        return wayToDie;
    }
    public void setWayToDie(ActorEnum wayToDie) {
        this.wayToDie = wayToDie;
    }
}
