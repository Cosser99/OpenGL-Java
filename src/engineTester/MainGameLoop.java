package engineTester;



import org.lwjgl.opengl.Display;

import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader=new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader= new StaticShader();
		
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
		RawModel model=loader.loadToVao(vertices,textureCoords,indices);
		ModelTexture texture=new ModelTexture(loader.loadTexture("image"));
		TexturedModel texturedModel = new TexturedModel(model,texture);
		
		while(!Display.isCloseRequested())
		{
			
			renderer.prepare();
			//game logic
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
