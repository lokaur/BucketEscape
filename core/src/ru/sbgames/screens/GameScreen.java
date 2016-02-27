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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import ru.sbgames.BucketEscape;
import ru.sbgames.Enemy;

public class GameScreen implements Screen {
	private final BucketEscape game;

	private Sprite backgroundSprite;
	private OrthographicCamera camera;
	private long lastDropTime;
	private int score;
	private Vector3 touchPos;
	private Array<Enemy> enemies;
	private BitmapFont scoreFont;
	private Sound hitSound;
	private boolean isJustClicked;
	private int difficult;

	public GameScreen(final BucketEscape game) {
		this.game = game;
		init();
	}

	private void init() {
		difficult = 200;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 800);
		touchPos = new Vector3();
		backgroundSprite = new Sprite(new Texture("play.png"));
		scoreFont = game.generateFont(18);
		enemies = new Array<Enemy>();
		hitSound = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
		spawnEnemy();
	}

	private void spawnEnemy() {
		Enemy enemy = new Enemy();
		enemy.x = MathUtils.random(0, 480 - 64);
		enemy.y = -64;
		enemy.width = 64;
		enemy.height = 64;
		enemies.add(enemy);
		lastDropTime = TimeUtils.nanoTime();
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
		scoreFont.draw(batch, "Score: " + score, 5, 800 - 5);
		for (Enemy enemy : enemies) {
			batch.draw(enemy.getEnemyTexture(), enemy.x, enemy.y);
		}
		batch.end();

		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			isJustClicked = true;
		}

		if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
			spawnEnemy();
			if (difficult > 0)
				difficult += 5;
		}

		Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			Enemy enemy = iterator.next();
			enemy.y += difficult * Gdx.graphics.getDeltaTime();
			if (enemy.y > 800) {
				iterator.remove();
				game.setScore(score);
				game.setScreen(new GameOverScreen(game));
				dispose();
			}

			if (isJustClicked && enemy.overlaps(new Rectangle(touchPos.x, touchPos.y, 64, 64))) {
				score++;
				hitSound.play();
				iterator.remove();
			}
		}
		isJustClicked = false;
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
		hitSound.dispose();
	}
}
