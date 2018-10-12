package com.potato;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class MyGame extends Game {
	
	public static GameScreen gameScreen;
	public static StartScreen startscreen;

	@Override
	public void create() {

		
		startscreen = new StartScreen(this);
		gameScreen = new GameScreen(this);
		
		this.setScreen(startscreen);

	}

}
