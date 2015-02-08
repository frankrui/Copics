package com.example.copiccolorpick.activity;

import com.example.copiccolorpick.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.copiccolorpick.MESSAGE";
	public String[] colorProjs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

		colorProjs = fileList();
		
		GridView gridview = (GridView) findViewById(R.id.file_grid);
	    gridview.setAdapter(new FileAdapter(this, colorProjs));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	beginColorPickFile(position);
	        }
	    });

	}

	protected void beginColorPickFile(int position) {
		Intent intent = new Intent(this, ColorPickActivity.class);
        intent.putExtra(EXTRA_MESSAGE, colorProjs[position]);
        startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()){
            case R.id.action_new_project:
                newColorPick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
       }
   }
	
	private void newColorPick() {
		// TODO: generate new color pick activity
		
	}

	public class FileAdapter extends BaseAdapter {
	    @SuppressWarnings("unused")
		private Context mContext; //suppress unused warning: needed for getLayoutInflater()
	    private String[] Files;

	    public FileAdapter(Context c, String[] colorProjs) {
	    	mContext = c;
			Files = colorProjs;
	    }

	    public int getCount() {
	        return Files.length;
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

	    // create a new View for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View view;
	        
			if (convertView == null) {  // if it's not recycled, initialize some attributes
		    	LayoutInflater mInflater = getLayoutInflater();
		    	view = mInflater.inflate(R.layout.file_item, null);
		    	TextView tv = (TextView) view.findViewById(R.id.title);
		    	tv.setText(Files[position]);
	            ImageView iv = (ImageView) view.findViewById(R.id.icon);
	            
	            //TODO: change ic_launcher to correct image for each file
	            iv.setImageResource(R.drawable.ic_launcher);
			} else {
	        	view = convertView;
	        }
	 
	        return view;
	    }

	}
}