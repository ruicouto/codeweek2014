package com.rmsc.codeweek;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.rmsc.codeweek.data.Evento;
import com.rmsc.codeweek.data.Eventos;

/**
 * The initial activity.
 * @author ruicouto
 *
 */
public class MainActivity extends ActionBarActivity {
	
	/**
	 * Action to detect new event activity result
	 */
	private static final int ACTION_NEW=1;
	/**
	 * Action to detect edit activity result
	 */
	private static final int ACTION_EDIT=2;
	
	/**
	 * Memory representation of the events
	 */
	private Eventos eventos;
	/**
	 * Reference to the listview
	 */
	private ListView listView;
	
	/**
	 * Method called when the application starts
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        eventos = new Eventos();
        listView = (ListView) findViewById(R.id.activity_main_list);
        listView.setOnItemClickListener(editNote);
        
        fillList();
        
        Button b = (Button) findViewById(R.id.activity_main_button_new);
        b.setOnClickListener(newEvento);
    }
    
    /**
     * Click listener for the "new event" button
     */
    private OnClickListener newEvento = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, NewEntryActivity.class);
			startActivityForResult(i,ACTION_NEW);
		}
	};

	/**
	 * Method to handle the result of the other activities.
	 * Called when another activity (started by this) finishes.
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			switch(requestCode) {
				case ACTION_NEW: {
					Evento e = (Evento) data.getSerializableExtra("e");
					eventos.getEventos().add(e);
					fillList();
					Log.v("","here");
				} break;
				case ACTION_EDIT: {
					Evento e = (Evento) data.getSerializableExtra("e");
					eventos.getEventos().remove(e);
					eventos.getEventos().add(e);
					fillList();
				} break;
			}
		}
	};
	
	/**
	 * Update the main activity list
	 */
	private void fillList() {
		ArrayAdapter<Evento> a = new ArrayAdapter<Evento>(this, 
				android.R.layout.simple_list_item_1,eventos.getEventos());
		listView.setAdapter(a);
	}
	
	/**
	 * Click listener for when clicking an item in the list.
	 */
	private OnItemClickListener editNote = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Evento e = (Evento) arg0.getItemAtPosition(arg2);
			Intent i = new Intent(MainActivity.this, NewEntryActivity.class);
			i.putExtra("e", e);
			startActivityForResult(i, ACTION_EDIT);
		}
	};
	
	/**
	 * Injection of the menu in the action bar.
	 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Method to handle click in the menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
