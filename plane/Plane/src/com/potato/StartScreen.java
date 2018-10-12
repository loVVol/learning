package com.potato;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class StartScreen implements Screen {

	Texture texture;
	Texture textureBg;
	TextureRegion region;
	Stage stage;
	Image image;
	Music music;
	SpriteBatch batch;
	float width;
	float height;
	MyGame game;
	
	public StartScreen(MyGame game){
		
		this.game = game;
	}
	
	@Override
	public void show() {

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		stage = new Stage();
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("data/logo.png"));
		textureBg = new Texture(Gdx.files.internal("data/background.png"));
		region = new TextureRegion(textureBg, 0, 0, 480, 852);
		// “Ù¿÷…Ë÷√
		music = Gdx.audio.newMusic(Gdx.files.internal("data/plane.ogg"));
		music.play();
		music.setLooping(true);
		music.setVolume(15);
		// ø™ ºÕº∆¨
		setImage();
		stage.addActor(image);
		Gdx.input.setInputProcessor(stage);

	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(region, 0, 0, width, height);
		batch.end();
		stage.act();
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	
	void setImage() {
		image = new Image(texture);
		image.setScale(0.7f);
		image.setPosition(0, height / 2 - image.getHeight() / 2);

		image.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(game.gameScreen);
				
				stage.clear();
				return false;
			}

		});
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
