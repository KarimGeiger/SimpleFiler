package com.kkteam.simplefiler;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity {

	LinearLayout baseLayout;
	List<ListView> listViewArray = new ArrayList<ListView>();
	List<Integer> listViewIdArray = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		baseLayout = (LinearLayout) findViewById(R.id.llBase);

		// Debugging purposes
		String[] values = new String[20];
		for (int i = 0; i < 20; i++) {
			values[i] = "" + i;
		}

		addView(values);
		addView(values);
	}

	private void addView(String[] data) {
		ListView lv = new ListView(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.d("DEBUG",
						""
								+ listViewArray.indexOf(listViewIdArray
										.indexOf(arg0.getId())));
				// TODO: Does not work. I'll check later why

			}

		});
		baseLayout.addView(lv);
		listViewArray.add(lv);
		listViewIdArray.add(lv.getId());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
