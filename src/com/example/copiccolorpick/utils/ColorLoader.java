package com.example.copiccolorpick.utils;

import java.util.LinkedHashSet;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.example.copiccolorpick.R;
import com.example.copiccolorpick.model.Colour;

/**
 * Loads Copic colors from an xml file with singleton pattern
 * @author Frank
 *
 */
public class ColorLoader {
	
	private static LinkedHashSet<Colour> colors;

	public static LinkedHashSet<Colour> getColours(Context context) {
		if(colors == null) {
			colors = loadColors(context);
		}
		return colors;
	}
	
	public static LinkedHashSet<Colour> loadColors(Context context) {
		XmlResourceParser parser = context.getResources().getXml(R.xml.color_data);

		
		return null;
	}
	
	
	

}
