package com.Assignment.SettingPopUp;

import com.Assignment.Model;
import com.Assignment.PageAbstract.FactoryInterface;

// Made by Cheok Jia Heng
// Modified by Wang Lin

// create setting pop up page 's MVC
public class SettingPopUpFactory implements FactoryInterface {
    public void exportMvc() {
        new SettingPopUpController(new SettingPopUpView(), Model.getInstance());
    }
}
