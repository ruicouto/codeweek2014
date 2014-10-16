package com.rmsc.codeweek;

import com.rmsc.codeweek.data.Evento;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewEntryActivity extends ActionBarActivity {
	/**
	 * Reference to the title edittext
	 */
	private EditText title;
	/**
	 * Reference to the description edittext
	 */
	private EditText description;
	/**
	 * Reference to the event (being created or edited)
	 */
	private Evento evento;
	
	/**
	 * Initialization of the data and references.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_entry);
		
		Button b = (Button) findViewById(R.id.activity_new_entry_button_accept);
		b.setOnClickListener(clickOk);
		
		title = (EditText) findViewById(R.id.activity_new_entry_text_title);
		description = (EditText) findViewById(R.id.activity_new_entry_text_description);
		
		
		
		if(getIntent().getExtras()!=null && getIntent().getExtras().get("e")!=null) {
			evento = (Evento) getIntent().getExtras().get("e");
			title.setText(evento.getName());
			description.setText(evento.getDescription());
		}
		
	}
	
	/**
	 * Handler for click on the ok button.
	 * Returns a new event if creating new, or update the existing one if editing.
	 */
	private OnClickListener clickOk = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Evento e = null;
			if(evento == null) { //creating new
				e = new Evento(title.getText().toString(),description.getText().toString());
			} else { //editing
				evento.setName(title.getText().toString());
				evento.setDescription(description.getText().toString());
				e = evento;
			}
			Intent i = new Intent();
			i.putExtra("e", e);
			setResult(RESULT_OK,i);
			finish();
			
		}
		
	};
}
