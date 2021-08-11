package com.Assignment;

import com.Assignment.GamePage.GamePageScreenFactory;
import com.Assignment.GameSettingPage.GameSettingScreenFactory;
import com.Assignment.MainPage.MainPageScreenFactory;
import com.Assignment.MapButtonPopUp.MapButtonPopUpFactory;
import com.Assignment.PageAbstract.EnumInterface;
import com.Assignment.PageAbstract.FactoryInterface;
import com.Assignment.ResultPopUp.ResultPopUpFactory;
import com.Assignment.SettingPopUp.SettingPopUpFactory;

import static com.Assignment.PageNameEnum.*;

import java.util.HashMap;
import java.util.Map;

//Made By : Joel
//Modify By : Wang Lin

//This class is to having a transaction from screen to screen.
public class Router {
    private Map<EnumInterface, FactoryInterface> mvcMap = new HashMap<EnumInterface, FactoryInterface>();

    //We are not necessary to create multiple time of this "Router" object.
    //So, we using singleton design pattern to avoid create multiple times.
    // Singleton
    private static Router instance = null;
    public static Router getInstance(){
        if (instance == null) {      // double check
            synchronized (Router.class){  // sync lock
                if (instance == null) {     // double check
                    instance = new Router();
                }
            }
        }
        return instance;
    }
    // Singleton end

    // Put value to map
    public Router() {
        mvcMap.put(MAIN_PAGE, new MainPageScreenFactory());
        mvcMap.put(GAME_SETTING_PAGE, new GameSettingScreenFactory());
        mvcMap.put(GAME_PAGE, new GamePageScreenFactory());
        mvcMap.put(SETTING_POP_UP, new SettingPopUpFactory());
        mvcMap.put(RESULT_POP_UP, new ResultPopUpFactory());
        mvcMap.put(MAP_BUTTON_POP_UP, new MapButtonPopUpFactory());
    }

    //Will update the screen by determine the EnumInterface(PageNameEnum).
    public void screenUpdate(EnumInterface screen) {
        // if screen = MAIN_PAGE, will call MainPageScreenFactory's exportMvc()
        mvcMap.get(screen).exportMvc();
    }
}
