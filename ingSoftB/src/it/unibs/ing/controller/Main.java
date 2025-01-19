package it.unibs.ing.controller;

import it.unibs.ing.view.ViewInit;

//File: Main.java
public class Main {
	
	public static void main(String[] args) {

        ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore();
        ControllerFruitore controllerFruitore = new ControllerFruitore();

        ViewInit viewInit = new ViewInit(controllerConfiguratore, controllerFruitore);
        viewInit.start();
 
 }

}
