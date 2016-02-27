package ru.sbgames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.sbgames.BucketEscape;

public class MainMenuScreen implements Screen {
	private final BucketEscape game;
	private OrthographicCamera camera;
	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	private BitmapFont fontTitle;
	private BitmapFont fontTapAnywhere;

	public MainMenuScreen(final BucketEscape game) {
		this.game = game;
		init();
	}

	private void init() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 800);
		backgroundTexture = new Texture("start.png");
		backgroundSprite = new Sprite(backgroundTexture);
		fontTitle = game.generateFont(42);
		fontTapAnywhere = game.generateFont(18);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		Batch batch = game.getBatch();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		backgroundSprite.draw(batch);
		fontTitle.draw(batch, "Bucket Escape", 70, 650);
		fontTapAnywhere.draw(batch, "Tap anywhere to start game", 90, 300);
		batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		fontTitle.dispose();
		fontTapAnywhere.dispose();
		backgroundTexture.dispose();
	}
}
