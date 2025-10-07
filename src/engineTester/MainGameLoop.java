package engineTester;



import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;


//TODO: implement camera 
public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader=new Loader();
		StaticShader shader= new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		float[] vertices= {
				-0.5f,0.5f,0, //V1
				-0.5f,-0.5f,0,//v2
				0.5f,-0.5f,0,//v3
				0.5f,0.5f,0//v4
		};
		
		int[] indices=
			{
					0,1,3, //top left triangles
					3,1,2	//bottom right triangles
					
			};
		float[] textureCoords=
			{
				0,0, 	//v0
				0,1,	//v1
				1,1,	//v2
				1,0		//v3
			};
		RawModel model = loader.loadToVao(vertices,textureCoords,indices);
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("image")));
		
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-1),0,0,0,1);
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested())
		{
				//
				entity.increasePosition(0, 0, -0.001f);
				camera.move();
			renderer.prepare();
			//game logic
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
