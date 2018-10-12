package com.potato;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Plane";
		cfg.useGL20 = false;
		cfg.width = 320;
		cfg.height = 600;
		
		new LwjglApplication(new MyGame(), cfg);
	}
}
