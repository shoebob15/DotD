package com.shoebob.dotd;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Depths of the Dungeon");
		config.setDecorated(false);
		config.setWindowIcon("logo.png");
		new Lwjgl3Application(new DotDGame(), config);
	}
}
