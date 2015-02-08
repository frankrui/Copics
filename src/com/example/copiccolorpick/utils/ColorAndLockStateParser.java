package com.example.copiccolorpick.utils;

import java.util.HashSet;
import java.util.Set;

public class ColorAndLockStateParser {
	/**
	 * converts a ColorPickActivityState into a string for storage
	 * @param cpas
	 * @return
	 */
	public static String parseToString(ColorPickActivityState cpas) {
		StringBuilder builder = new StringBuilder(cpas.selectedColors.size() * 4 + 5);
		
		for (Integer i: cpas.selectedColors) {
			builder.append(i + " ");
		}
		builder.append(cpas.lockState);
		return builder.toString();
	}
	
	/**
	 * converts a String from save file back into a ColorPickActivityState
	 * @param string
	 * @return
	 */
	public static ColorPickActivityState parseFromString(String string) {
		Set<Integer> ids = new HashSet<Integer>();
		
		String[] parts = string.split(" ");
		for (int i = 0; i < parts.length - 1; i++) {
			ids.add(Integer.parseInt(parts[i]));
		}
		
		boolean lockState = Boolean.parseBoolean(parts[parts.length-1]);
		
		return new ColorPickActivityState(ids, lockState);
	}

}
