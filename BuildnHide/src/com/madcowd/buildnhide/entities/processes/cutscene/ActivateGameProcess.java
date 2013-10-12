/**
 * 
 */
package com.madcowd.buildnhide.entities.processes.cutscene;

import com.madcowd.buildnhide.entities.systems.PlayerControlSystem;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.processes.Process;
import com.punchline.javalib.entities.processes.ProcessState;
import com.punchline.javalib.entities.systems.generic.TrackingCameraSystem;

/**
 * Activates game systems after cutscene is over.
 * 
 * @author MadcowD
 */
public class ActivateGameProcess extends Process {

	/**
	 * The main constructor for this process
	 */
	public ActivateGameProcess() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.punchline.javalib.entities.processes.Process#update(com.punchline
	 * .javalib.entities.EntityWorld, float)
	 */
	@Override
	public void update(EntityWorld world, float deltaTime) {
		end(ProcessState.SUCCEEDED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.punchline.javalib.entities.processes.Process#onEnd(com.punchline.
	 * javalib.entities.EntityWorld,
	 * com.punchline.javalib.entities.processes.ProcessState)
	 */
	@Override
	public void onEnd(EntityWorld world, ProcessState endState) {
		// ACTIVATION OCCURS HERE.
		world.getSystemManager().addSystem(
				new PlayerControlSystem(world.getInput()));
		world.getSystemManager().addSystem(
				new TrackingCameraSystem("Player", world.getCamera()));
	}
}
