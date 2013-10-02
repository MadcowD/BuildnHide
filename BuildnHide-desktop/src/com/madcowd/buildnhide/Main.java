package com.madcowd.buildnhide;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.punchline.javalib.utils.LogManager;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.useGL20 = false;
		cfg.resizable = false;
		
		LwjglApplication a = new LwjglApplication(new BuildnHide(), cfg); 
		LogManager.init(a);
	}
}
