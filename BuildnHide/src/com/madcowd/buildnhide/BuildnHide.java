package com.madcowd.buildnhide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.madcowd.buildnhide.screens.GameplayScreen;
import com.punchline.javalib.Game;
import com.punchline.javalib.utils.Convert;
import com.punchline.javalib.utils.SoundManager;

public class BuildnHide extends Game {
	
	@Override
	public void create() {
		Convert.init(8f);
		
		title = "Build'n'Hide";
		
		landscapeMode = true;
		
		super.create();
		
		getScreenManager().addScreen(new GameplayScreen(this));
	}
	
	@Override
	protected void loadSounds() { 
		Preferences pref = Gdx.app.getPreferences("settings");
		
		float soundVol = pref.getBoolean("Sound", true) ? 1f : 0f;
		float musicVol = pref.getBoolean("Music", true) ? 1f : 0f;
		
		SoundManager.setSoundVolume(soundVol);
		SoundManager.setMusicVolume(musicVol);
	}
	
}
