package com.kkteam.simplefiler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Main activity.
 * 
 * TODO:
 *  - Widget
 *  - One folder back at back key press
 *  - Settings
 *  - Highlight selected item
 *  - Fix width for generated List Views
 *  - Open last path on orientation change
 *  - Copy/Paste/Rename/Create
 *  
 * @author Karim Geiger <me@karim-geiger.de>
 *
 */
public class MainActivity extends Activity {

	public LinearLayout baseLayout;
	private List<FolderListView> listViewArray = new ArrayList<FolderListView>();
	private File sdCardRoot = Environment.getExternalStorageDirectory();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		baseLayout = (LinearLayout) findViewById(R.id.llBase);

		// Initiate root view
		onFilePress("/", 0);
	}

	/**
	 * Add new view to base layout.
	 * 
	 * @param folder
	 *            folder containing files
	 * @param location
	 *            view location in layout
	 */
	private void addView(Folder folder, int location) {
		FolderListView lv = new FolderListView(this, folder, listViewArray.size() + 1);

		baseLayout.addView(lv.getView());
		listViewArray.add(lv);
	}

	/**
	 * Open file or folder on file press.
	 * 
	 * @param path
	 *            new file path
	 * @param currentViewLocation
	 *            view location in layout
	 */
	public void onFilePress(String path, int currentViewLocation) {
		try {
			// It's a directory
			Folder folder = new Folder(sdCardRoot, path);
			removeViews(currentViewLocation - 1);
			addView(folder, currentViewLocation - 1);
		} catch (Exception e) {
			// It's a file
			openFile(path);
		}
	}

	/**
	 * Open file through intent.
	 * 
	 * @param path
	 *            path to file
	 */
	private void openFile(String path) {
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
		File file = new File(sdCardRoot, path);
		try {
			String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
			String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
			intent.setDataAndType(Uri.fromFile(file), mimetype);
			startActivity(intent);
		} catch (Exception e) {
			intent.setDataAndType(Uri.fromFile(file), "text/*");
			Toast.makeText(this, "Unknown filetype. Opening as text.", Toast.LENGTH_SHORT).show();
		}
		startActivity(intent);
	}

	/**
	 * Remove view with all views depending on base view.
	 * 
	 * @param location
	 *            location for base view
	 */
	private void removeViews(int location) {
		while (listViewArray.size() - 1 > location) {
			baseLayout.removeViewAt(listViewArray.size() - 1);
			listViewArray.remove(listViewArray.size() - 1);
		}
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
