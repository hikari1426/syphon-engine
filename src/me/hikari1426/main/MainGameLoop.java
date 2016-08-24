package me.hikari1426.main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import me.hikari1426.models.RawModel;
import me.hikari1426.models.TexturedModel;
import me.hikari1426.renderEngine.DisplayManager;
import me.hikari1426.renderEngine.Loader;
import me.hikari1426.renderEngine.OBJLoader;
import me.hikari1426.renderEngine.Renderer;
import me.hikari1426.shaders.StaticShader;
import me.hikari1426.textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		Camera camera = new Camera();
		
		
		
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("/models/stallTexture")));
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-50),0,0,0,1);
		
		//Main Game Loop Here!
		while(!Display.isCloseRequested())
		{
			entity.increaseRotation(0, 0.001f, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity,shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
