package ru.sbgames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Rectangle {
	private Texture enemyTexture;

	public Enemy() {
		Texture[] textures = new Texture[3];
		textures[0] = new Texture("frag1.png");
		textures[1] = new Texture("frag2.png");
		textures[2] = new Texture("block.png");

		enemyTexture = textures[MathUtils.random(textures.length - 1)];
	}

	public Texture getEnemyTexture() {
		return enemyTexture;
	}
}
