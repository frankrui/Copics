package com.example.copiccolorpick.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.copiccolorpick.utils.ColorLoader;

/**
 * Holds and manages all the copic colors
 * @author Frank
 *
 */
public class Colors implements Iterable<Colour> {
	
	private LinkedHashSet<Colour> colors;
	
	public Colors(Set<Integer> ids, Context context){
		colors = ColorLoader.getColours(context);
		for (Colour c : colors) {
			if (ids.contains(c.id)) {
				c.toggleColor();
			}
		}
	}
	
	/**
	 * Returns the view for the color of given id
	 * @param view
	 * @param viewGroup
	 * @param inflater
	 * @return
	 */
	public View draw(int id, View view, ViewGroup viewGroup, LayoutInflater inflater) {
		return retrieveColourById(id).draw(view, viewGroup, inflater);
	}
	
	/**
	 * changes the selectedState of the color of the given Id
	 * @param id
	 */
	public void toggleColors(int id){
		retrieveColourById(id).toggleColor();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfColors() {
		return colors.size();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Colour getColor(int id) {
		return retrieveColourById(id);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private Colour retrieveColourById(int id) {
		for (Colour c : colors) {
			if (c.id == id) {
				return c;
			}
		}
		return null;
	}
	
	public Set<Integer> getIdOfSelectedColours() {
		Set<Integer> ints = new HashSet<Integer>();
		
		for (Colour c : colors) {
			if (c.isSelected()) {
				ints.add(c.id);
			}
		}
		
		return ints;
	}

	@Override
	public Iterator<Colour> iterator() {
		return colors.iterator();
	}
}