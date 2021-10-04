package main;

import controller.Control;
import model.LightMatrix;
import view.Board;

public class Main
{
	public static void main(String[] args)
	{
		Board v = new Board();
		LightMatrix m = new LightMatrix();
		Control c = new Control(v, m);
		c.initMainMenu();
	}
}
