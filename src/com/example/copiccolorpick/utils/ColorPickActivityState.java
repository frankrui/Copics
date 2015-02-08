package com.example.copiccolorpick.utils;

import java.util.Set;

/**
 * Represents the ids of the selected colors and lockState of a ColorPickActivity
 * @author Frank
 *
 */
public class ColorPickActivityState {
	
	public final Set<Integer> selectedColors;
	public final boolean lockState;
	
	public ColorPickActivityState(Set<Integer> colors, boolean lockSt) {
		selectedColors = colors;
		lockState = lockSt;
	}
	

}
