package me.hikari1426.main;

import org.lwjgl.opengl.Display;

import me.hikari1426.models.RawModel;
import me.hikari1426.models.TexturedModel;
import me.hikari1426.renderEngine.DisplayManager;
import me.hikari1426.renderEngine.Loader;
import me.hikari1426.renderEngine.Renderer;
import me.hikari1426.shaders.StaticShader;
import me.hikari1426.textures.ModelTexture;

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
		
		float[] texturesCoords = {
			0,0,		//V1
			0,1,		//V2
			1,1,		//V3
			1,0			//V4
		};
		
		RawModel model = loader.loadToVAO(vertices,texturesCoords,indices);			//Load a plane using vertices and indices
		ModelTexture texture = new ModelTexture(loader.loadTexture("syphon_s_512x256"));
		TexturedModel textureModel = new TexturedModel(model, texture);
		
		//Main Game Loop Here!
		while(!Display.isCloseRequested())
		{
			renderer.prepare();
			shader.start();
			//OpenGL Render
			renderer.render(textureModel);
			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
