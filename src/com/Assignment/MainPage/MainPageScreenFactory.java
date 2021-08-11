package com.Assignment.MainPage;

import com.Assignment.PageAbstract.FactoryInterface;
import com.Assignment.Model;

//Make by: Joel
//Modify by : Wang Lin
    //This is implements from FactoryInterface
    //The purpose is to create the MVC
public class MainPageScreenFactory implements FactoryInterface {
    public void exportMvc() {
        new MainPageScreenController(new MainPageScreenView(), Model.getInstance());
    }
}
