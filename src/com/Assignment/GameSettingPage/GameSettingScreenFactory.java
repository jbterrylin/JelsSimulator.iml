package com.Assignment.GameSettingPage;

import com.Assignment.Model;
import com.Assignment.PageAbstract.FactoryInterface;

// Made by Cheok Jia Heng
// Modified by Wang Lin

// create game setting page's MVC
public class GameSettingScreenFactory implements FactoryInterface {
    public void exportMvc() {
        new GameSettingScreenController(new GameSettingScreenView(), Model.getInstance());
    }
}
