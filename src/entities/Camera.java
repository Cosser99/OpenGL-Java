package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;
	private float yaw;
	private float roll;
	private float SPEED;
	private float MOUSE_SENS=0.1f;
	public Camera()
	{
		this.SPEED=0.05f;
		Mouse.setGrabbed(true);

	}
	
	public void move()
	{

		float deltaX = Mouse.getDX(); // movimento orizzontale dall’ultimo frame
		float deltaY = Mouse.getDY(); // movimento verticale dall’ultimo frame
	
		yaw+=deltaX*MOUSE_SENS;
		pitch-=deltaY*MOUSE_SENS;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			position.z-=SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			position.x+=SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			position.x-=SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			position.z+=SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			yaw-=SPEED*5;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
		{
			yaw+=SPEED*5;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
		{
			position.y-=SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			position.y+=SPEED;
		}
		//TODO: Does not move on local
		
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
}
