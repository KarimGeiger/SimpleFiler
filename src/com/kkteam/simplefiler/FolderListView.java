package com.kkteam.simplefiler;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Folder List View class generates list view for each folder.
 * 
 * @author Karim Geiger <me@karim-geiger.de>
 * 
 */
public class FolderListView {
	private ListView lv;
	private MainActivity main;
	private int position;
	private Folder folder;

	/**
	 * Constructor: Generate view object and set onClickListener if folder is
	 * not empty.
	 * 
	 * @param main
	 *            main activity for initializing Adapter
	 * @param folder
	 *            Folder object for files
	 * @param position
	 *            position in base layout
	 */
	public FolderListView(MainActivity main, Folder folder, int position) {
		this.main = main;
		this.position = position;
		this.folder = folder;
		lv = new ListView(main);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(main, android.R.layout.simple_list_item_1, folder.getFileNames());
		lv.setAdapter(adapter);

		if (folder.getFileCount() != 0) {
			// Only listen to click if folder is not empty
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> view, View arg1, int pos, long arg3) {
					FolderListView.this.main.onFilePress(FolderListView.this.folder.getAbsolutePath() + lv.getItemAtPosition(pos),
							FolderListView.this.position);
				}

			});
		}
	}

	/**
	 * Get view object.
	 * 
	 * @return view object
	 */
	public ListView getView() {
		return lv;
	}
}
