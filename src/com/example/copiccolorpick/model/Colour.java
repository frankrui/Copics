package com.example.copiccolorpick.model;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.copiccolorpick.R;

/**
 * Holds the Information for individual Colors
 * @author Frank
 *
 */
public class Colour {
	public final String name;
	public final String hexcode;
	public final String copic;
	public final String textHexCode;
	public final int id;
	private Boolean selected = false;
	
	/**
	 * 
	 * @param colorName
	 * @param colorHexcode
	 * @param colorCopic
	 */
	public Colour(String colorName, String colorHexcode, String colorCopic, String textHexCode, int id) {
		name = colorName;
		hexcode = colorHexcode;
		copic = colorCopic;
		this.textHexCode = textHexCode;
		this.id = id;
	}
	
	/**
	 * change selected state
	 */
	public void toggleColor() {
		selected = !selected;
	}

	/**
	 * 
	 * @return true if selected and false if not selected
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Draws the color on given View
	 * @param view
	 * @param viewGroup TODO
	 * @param inflater TODO
	 * @return 
	 */
	public View draw(View view, ViewGroup viewGroup, LayoutInflater inflater) {
		if (view == null) {
			view = inflater.inflate(R.layout.color_item, viewGroup);
			view.setBackgroundColor(Color.parseColor(hexcode));
			
			TextView copicCodeView = (TextView) view.findViewById(R.id.copic_code);
			copicCodeView.setText(copic);
			copicCodeView.setTextColor(Color.parseColor(textHexCode));
			
			TextView colourNameView = (TextView) view.findViewById(R.id.color_name);
			colourNameView.setText(name);
			colourNameView.setTextColor(Color.parseColor(textHexCode));
		}
		
		if (selected) {
			View checkMarkView = view.findViewById(R.id.check_mark);
			checkMarkView.setVisibility(View.VISIBLE);
		} else {
			View checkMarkView = view.findViewById(R.id.check_mark);
			checkMarkView.setVisibility(View.INVISIBLE);
		}
		
		return view;
	}
}