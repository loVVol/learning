package com.potato;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen implements Screen {

	MyGame game;
	Texture textureBg1;
	Texture textureBg2;
	Texture planeTexture;
	TextureRegion region1;
	TextureRegion region2;
	SpriteBatch batch;

	float width;
	float height;
	int bg1x, bg1y, bg2x, bg2y;
	float touch_X;
	float touch_Y;
	float touchBaseX;
	float touchBaseY;
	boolean isTouching;
	float dis_X;
	float dis_Y;
	float planeX = 0;
	float planeY = 0;

	InputProcessor inputProcessor;

	public GameScreen(MyGame game) {
		this.game = game;

	}

	@Override
	public void show() {

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		textureBg1 = new Texture(Gdx.files.internal("data/background1.png"));
		textureBg2 = new Texture(Gdx.files.internal("data/background2.png"));
		planeTexture = new Texture(Gdx.files.internal("data/plane.png"));

		region1 = new TextureRegion(textureBg1, 0, 0, 480, 852);
		region2 = new TextureRegion(textureBg2, 0, 0, 480, 852);

		bg1x = bg2x = 0;
		bg1y = 0;
		bg2y = bg1y + region1.getRegionHeight();

	}

	void logic() {

		bg1y -= 3;
		bg2y -= 3;

		float RH1;
		float RH2;

		RH1 = region1.getRegionHeight();
		RH2 = region2.getRegionHeight();
		if (bg1y < -RH1) {
			bg1y = bg2y + region1.getRegionHeight();
		}

		if (bg2y < -RH2) {

			bg2y = bg1y + region2.getRegionHeight();
		}
	}

	void touch() {
		// justTouched 是开始按下手指的第一个点。
		if (Gdx.input.justTouched()) {

			touchBaseX = Gdx.input.getX(0);
			touchBaseY = Gdx.input.getY(0);

		} // isTouched 是结束时，手指按下的点。
		else if (Gdx.input.isTouched(0)) {
			touch_X = Gdx.input.getX(0);
			touch_Y = Gdx.input.getY(0);
		}

		dis_X = touch_X - touchBaseX;
		dis_Y = -(touch_Y - touchBaseY);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		touch();
		this.logic();
		batch.begin();
		batch.draw(region1, bg1x, bg1y, 320, 852);
		batch.draw(region2, bg2x, bg2y, 320, 852);
		batch.draw(planeTexture, 120 + dis_X, dis_Y, 80, 80);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
