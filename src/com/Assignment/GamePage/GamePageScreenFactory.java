package com.Assignment.GamePage;

import com.Assignment.Model;
import com.Assignment.PageAbstract.FactoryInterface;

// make by: wang lin

// MVC:
    //why we use:
        // let class can be single responsibility
        // more easy let many people work together(1 people ui, 1 people controller)

// create game page's MVC
public class GamePageScreenFactory implements FactoryInterface {
    public void exportMvc() {
        new GamePageScreenController(new GamePageScreenView(), Model.getInstance());
    }
}
