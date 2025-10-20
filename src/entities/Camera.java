package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import toolbox.Maths;

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
		pitch-=deltaY*MOUSE_SENS;	//OCCHIO è negativo 
		Vector3f forward=Maths.getForward(this);
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			Vector3f.add(position, (Vector3f) new Vector3f(forward).scale(SPEED), position);
			//position.z-=SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			Vector3f.add(position, (Vector3f) new Vector3f(forward).scale(-SPEED), position);
			//position.z+=SPEED;
		}
		//STRAFING
		Vector3f right = new Vector3f();
		Vector3f.cross(forward, new Vector3f(0,1,0), right);
		right.normalise();

		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
		    Vector3f.add(position, (Vector3f) new Vector3f(right).scale(SPEED), position);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
		    Vector3f.add(position, (Vector3f) new Vector3f(right).scale(-SPEED), position);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			position.y-=SPEED;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
		{
			position.y+=SPEED;
		}
		
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
