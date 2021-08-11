package com.Assignment.MapButtonPopUp;

import com.Assignment.Model;
import com.Assignment.PageAbstract.FactoryInterface;

// make by: wang lin

// create map button pop up page's MVC
public class MapButtonPopUpFactory implements FactoryInterface {
    public void exportMvc() {
        new MapButtonPopUpController(new MapButtonPopUpView(), Model.getInstance());
    }
}
