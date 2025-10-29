package engineTester;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import shaders.StaticShader;
import textures.ModelTexture;

 
public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader=new Loader();
		
		
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("stallTexture")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		Entity entity = new Entity(staticModel, new Vector3f(0,0,0),0,0,0,1);
		Light light = new Light(new Vector3f(0,0,30),new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		List<Entity> allCubes = new ArrayList<Entity>();
		Random random = new Random();
		for(int i=0;i<500;i++)
		{
			float x = random.nextFloat()*100-50;
			float y = random.nextFloat()*100-50;
			float z = random.nextFloat()*-300;
			allCubes.add(new Entity(staticModel,new Vector3f(x,y,z),random.nextFloat()*180f,
					random.nextFloat()*180f,0f,1f));
			
		}
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested())
		{
			//Change shader
			
			if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
				renderer.setWireframe(true);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
				renderer.setWireframe(false);
			}
				//
				//entity.increaseRotation(0, 0.3f, 0);
			for(Entity C : allCubes)
			{
				C.increaseRotation(0.1f, 0.1f, 0.1f);
			}
				camera.move();
			//game logic

			for(Entity e : allCubes)
			{
				renderer.processEntity(e);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
