package me.hikari1426.engineTest;

import org.lwjgl.opengl.Display;

import me.hikari1426.renderEngine.DisplayManager;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		//Main Game Loop Here!
		while(!Display.isCloseRequested())
		{
			//OpenGL Render
			DisplayManager.updateDisplay();
		}

		DisplayManager.closeDisplay();
		
	}

}
