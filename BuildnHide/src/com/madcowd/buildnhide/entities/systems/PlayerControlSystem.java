package com.madcowd.buildnhide.entities.systems;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.generic.View;
import com.punchline.javalib.entities.components.physical.Body;
import com.punchline.javalib.entities.components.render.AnimatedSprite;
import com.punchline.javalib.entities.systems.InputSystem;

public class PlayerControlSystem extends InputSystem {

	public PlayerControlSystem(InputMultiplexer input) {
		super(input);
	}
	
	@Override
	public boolean canProcess(Entity e) {
		return e.getTag().equals("Player");
	}
	
	Vector2 velocity = new Vector2(0,0);
	boolean jump = false;
	
	@Override
	public void process(Entity e){
		Body b = e.getComponent(Body.class);
		b.setPosition(b.getPosition().cpy().add(velocity.cpy().scl(this.deltaSeconds()* 12)));
		
		//Jumping
		if(jump && b.getLinearVelocity().y == 0){
			b.getBody().applyLinearImpulse(0, 30, 0, 0, true);
		}
		
		
		
		
		
		
		//ANimatyion
		AnimatedSprite as = e.getComponent(AnimatedSprite.class);
		Vector2 linv = b.getLinearVelocity();
		if(linv.y == 0 &&  velocity.x != 0){
			if(velocity.x > 0)
				as.setState("Right", true);
			else if (velocity.x < 0)
				as.setState("Left", true);
			
		}
		else
			as.setState("Straight", true);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.A) {
			velocity.scl(0,1).add(-1, 0);
			return true;
		}
		
		if (keycode == Keys.D) {
			velocity.scl(0,1).add(1, 0);
			return true;
		}
		
		if(keycode == Keys.SPACE){
			jump = true;
			return true;
		}
		
		
		if(keycode == Keys.J){
				world.setTimeCoefficient(0.5f);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.A || keycode == Keys.D) {
			velocity.scl(0,1);
			return true;
		}
		if(keycode == Keys.SPACE){
			jump = false;
			return true;
		}
		
		if(keycode == Keys.J){
			world.setTimeCoefficient(1f);
			return true;
		}
		
		
		return false;
	}




}
