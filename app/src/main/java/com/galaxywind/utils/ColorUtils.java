package com.galaxywind.utils;

import java.util.Random;

import android.graphics.Color;

public class ColorUtils {
	private static final Random random = new Random();
	
	public static int getRandomColor() {
		return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}
}
