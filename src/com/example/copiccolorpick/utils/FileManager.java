package com.example.copiccolorpick.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.content.Context;

public class FileManager {
	
	private String name;
	private Context context;
	
	public FileManager(String name) {
		this.name = name;
	}
	
	public void save(ColorPickActivityState cpas) {
		String string = ColorAndLockStateParser.parseToString(cpas);
		
		try {
			FileOutputStream outputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
			outputStream.write(string.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ColorPickActivityState read() {
		File file = new File(context.getFilesDir(), name);
		ColorPickActivityState cpas = null;
		
		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line = br.readLine();
		    cpas = ColorAndLockStateParser.parseFromString(line);
		}
		catch (IOException e) {
		    //You'll need to add proper error handling here
		}
		
		return cpas;
	}

}
