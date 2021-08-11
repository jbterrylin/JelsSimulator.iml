package com.Assignment;

import com.Assignment.LComponent.LFrame;

import javax.swing.*;

import java.awt.*;

import static com.Assignment.PageNameEnum.MAIN_PAGE;

// make by: Wang Lin

// starting point
// create main frame and sent to model
// call router to show main page
public class Main {
    public static void main(String[] args) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //Model and Router is Singleton
        Model.getInstance().setMainFrame(new LFrame("Covid-19 Simulator", d.width, d.height, JFrame.EXIT_ON_CLOSE));
        Router.getInstance().screenUpdate(MAIN_PAGE);
    }
}
