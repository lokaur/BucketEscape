package ru.sbgames.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.sbgames.BucketEscape;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bucket Escape";
		config.height = 800;
		config.width = 480;
		new LwjglApplication(new BucketEscape(), config);
	}
}
