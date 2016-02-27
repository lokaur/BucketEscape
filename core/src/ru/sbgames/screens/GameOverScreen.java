package ru.sbgames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.sbgames.BucketEscape;

public class GameOverScreen implements Screen {
	private final BucketEscape game;

	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	private Sound looseSound;
	private BitmapFont looseFont;
	private BitmapFont scoreFont;
	private BitmapFont againFont;
	private OrthographicCamera camera;

	public GameOverScreen(BucketEscape game) {
		this.game = game;
		backgroundTexture = new Texture("start.png");
		backgroundSprite = new Sprite(backgroundTexture);
		looseSound = Gdx.audio.newSound(Gdx.files.internal("loose.wav"));
		looseFont = game.generateFont(42);
		scoreFont = game.generateFont(42);
		againFont = game.generateFont(18);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 800);
	}

	@Override
	public void show() {
		looseSound.play();
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
		looseFont.draw(batch, "You loose", 140, 650);
		scoreFont.draw(batch, "Score: " + game.getScore(), 140, 500);
		againFont.draw(batch, "Tap anywhere to try again", 90, 300);
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
		backgroundTexture.dispose();
		againFont.dispose();
		scoreFont.dispose();
		looseFont.dispose();
		looseSound.dispose();
	}
}
