package me.hikari1426.main;

import org.lwjgl.opengl.Display;

import me.hikari1426.renderEngine.DisplayManager;
import me.hikari1426.renderEngine.Loader;
import me.hikari1426.renderEngine.RawModel;
import me.hikari1426.renderEngine.Renderer;
import me.hikari1426.shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
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
			shader.start();
			//OpenGL Render
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
