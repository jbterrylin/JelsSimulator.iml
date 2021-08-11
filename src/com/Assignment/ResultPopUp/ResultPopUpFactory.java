package com.Assignment.ResultPopUp;

import com.Assignment.Model;
import com.Assignment.PageAbstract.FactoryInterface;

//made by jason
//modify by wang lin

//use to do create result pop up mvc by using the abstract factory design pattern
public class ResultPopUpFactory implements FactoryInterface {
    public void exportMvc() {
        new ResultPopUpController(new ResultPopUpView(), Model.getInstance());
    }
}
