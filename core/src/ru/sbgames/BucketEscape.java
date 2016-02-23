package ru.sbgames;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class BucketEscape extends Game {

	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();

		setScreen(new ru.sbgames.screens.MainMenuScreen(this));
	}

	public BitmapFont generateFont(int size) {
		FreeTypeFontGenerator generator =
				new FreeTypeFontGenerator(Gdx.files.internal("fonts/Minecraft.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter =
				new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = size;
		parameter.borderColor = Color.BLACK;
		parameter.borderWidth = 2;
		BitmapFont font = generator.generateFont(parameter);
		generator.dispose();
		return font;
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
