package com.example.copiccolorpick.activity;

import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;

import com.example.copiccolorpick.R;
import com.example.copiccolorpick.model.Colors;
import com.example.copiccolorpick.model.Colour;
import com.example.copiccolorpick.utils.ColorPickActivityState;
import com.example.copiccolorpick.utils.FileManager;

public class ColorPickActivity extends Activity {
	private Colors colors;
	private boolean lockState;
	private String fileName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color_pick);
		
		// Get the message from the intent
        Intent intent = getIntent();
        fileName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
        if( fileName == null ){
        	colors = new Colors(new HashSet<Integer>(), getApplicationContext());
        	lockState = false;
        } else {
        	FileManager fm = new FileManager(fileName);
        	ColorPickActivityState cpas = fm.read();
        	
        	colors = new Colors(cpas.selectedColors, getApplicationContext());
        	lockState = cpas.lockState;
        	
        }

		//TODO: set up grid view
		GridView gridview = (GridView) findViewById(R.id.color_grid);
	    gridview.setAdapter(new ColorAdapter(this, colors));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	toggleColors(position);
	        	v.invalidate();
	        }
	    });

		// Show the Up button in the action bar.
		setupActionBar();
	}

	/** Called when user taps to select a color */
	protected void toggleColors(int id) {
		if(!lockState) {
			colors.toggleColors(id);
		}
	}
	
	/** Called when the user clicks the Toggle button */
	public void toggleLock( View view ) {
		lockState = !lockState;
	}
	
	/** Called when the user clicks the Save button */
	public void saveProject( View view ) {
		if(fileName == null) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Saving Project...");
			alert.setMessage("Please Enter Project Name");

			// Set an EditText view to get user input 
			final EditText inputField = new EditText(this);
			alert.setView(inputField);

			alert.setPositiveButton("Save",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							fileName = inputField.getText().toString();

						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// Cancelled
						}
					});

			alert.show();
		}
		
		if (fileName == null) {
			return;
		}
		
		FileManager fm = new FileManager(fileName);
		ColorPickActivityState cpas = new ColorPickActivityState(colors.getIdOfSelectedColours(), lockState);
		
		fm.save(cpas);
	}
	
	@Override
    protected void onPause(){
		super.onPause();

		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Closing App");
		alert.setMessage("Would you like to save?");

		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				saveProject(null);
			}
		});

		alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});

		alert.show();
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.color_pick, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class ColorAdapter extends BaseAdapter {
		private Context context;
		private Colors colors;
		private Map<Integer, Integer> positionIdPairs;
		
		public ColorAdapter(Context c, Colors colors) {
			context = c;
			positionIdPairs = new TreeMap<Integer, Integer>();
			
			this.colors = colors;
			
			int i = 0;
			for (Colour colour : colors) {
				positionIdPairs.put(i++, colour.id);
			}
		}

		@Override
		public int getCount() {
			return colors.getNumberOfColors();
		}

		@Override
		public Object getItem(int position) {
			return colors.getColor(positionIdPairs.get(position));
		}

		@Override
		public long getItemId(int position) {
			return positionIdPairs.get(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return colors.draw((int) getItemId(position), convertView, parent, (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		}
		
	}

}
