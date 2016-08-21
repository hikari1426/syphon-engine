package me.hikari1426.engineTest;

import org.lwjgl.opengl.Display;

import me.hikari1426.renderEngine.DisplayManager;
import me.hikari1426.renderEngine.Loader;
import me.hikari1426.renderEngine.RawModel;
import me.hikari1426.renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
				-0.5f,0.5f,0,-0.5f,-0.5f,0,0.5f,-0.5f,0,0.5f,0.5f,0
		};
		
		int[] indices = {
			0,1,3,3,1,2
		};
		
		RawModel model = loader.loadToVAO(vertices,indices);		
		//Main Game Loop Here!
		while(!Display.isCloseRequested())
		{
			renderer.prepare();
			//OpenGL Render
			renderer.render(model);
			DisplayManager.updateDisplay();
		}

		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
