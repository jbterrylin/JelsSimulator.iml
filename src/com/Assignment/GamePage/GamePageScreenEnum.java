package com.Assignment.GamePage;

import com.Assignment.PageAbstract.EnumInterface;

// make by: wang lin

// list of Enum will be use in main page
public enum GamePageScreenEnum implements EnumInterface {
    // map coordinate status
    NO_PEOPLE,NORMAL_PEOPLE, SICK_PEOPLE, QUARANTINE_ZONE,
    // set ui value
    WORLD_SIZE, MAP_COORDINATE, DAY_TF, SPEED_TF, HEALTHY_TF, INFECTED_TF, QUARANTINE_TF, DEAD_TF,
    // get ui value
    MAP,
    // button
    SLOWER_BTN, PLAY_PAUSE_BTN, FASTER_BTN, SETTING_BTN, EXIT_BTN,
    // ending
    GOOD_ENDING, BAD_ENDING, NORMAL_ENDING
}
