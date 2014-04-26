package com.kkteam.simplefiler;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Database {
	SharedPreferences prefs;
	private String prefix = "com.kkteam.simplefiler";

	/**
	 * Constructor creates shared preference.
	 * 
	 * @param context
	 *            application content.
	 */
	public Database(Context context) {
		prefs = context.getSharedPreferences(this.prefix, Context.MODE_PRIVATE);
	}

	/**
	 * Save [content] as [name].
	 * 
	 * @param name
	 *            the unique identifier.
	 * @param content
	 *            the content to save.
	 */
	public void setData(String name, String content) {
		prefs.edit().putString(this.prefix + "." + name, content).commit();
	}

	/**
	 * Save [content] as [name].
	 * 
	 * @param name
	 *            the unique identifier.
	 * @param content
	 *            the content to save.
	 */
	public void setData(String name, int content) {
		prefs.edit().putInt(this.prefix + "." + name, content).commit();
	}

	/**
	 * Save [content] as [name].
	 * 
	 * @param name
	 *            the unique identifier.
	 * @param content
	 *            the content to save.
	 */
	public void setData(String name, float content) {
		prefs.edit().putFloat(this.prefix + "." + name, (float) content).commit();
	}

	/**
	 * Save [content] as [name].
	 * 
	 * @param name
	 *            the unique identifier.
	 * @param content
	 *            the content to save.
	 */
	public void setData(String name, long content) {
		prefs.edit().putLong(this.prefix + "." + name, content).commit();
	}

	/**
	 * Get [name] from saved database.
	 * 
	 * @param name
	 *            the unique identifier.
	 * @return saved data.
	 */
	public String getString(String name) {
		return prefs.getString(this.prefix + "." + name, null);
	}

	/**
	 * Get [name] from saved database.
	 * 
	 * @param name
	 *            the unique identifier.
	 * @return saved data.
	 */
	public int getInteger(String name) {
		return prefs.getInt(this.prefix + "." + name, 0);
	}

	/**
	 * Get [name] from saved database.
	 * 
	 * @param name
	 *            the unique identifier.
	 * @return saved data.
	 */
	public long getLong(String name) {
		return prefs.getLong(this.prefix + "." + name, 0);
	}

	/**
	 * Get [name] from saved database.
	 * 
	 * @param name
	 *            the unique identifier.
	 * @return saved data.
	 */
	public double getDouble(String name) {
		return prefs.getFloat(this.prefix + "." + name, 0);
	}

	public void scrubData() {
		Editor editor = prefs.edit();
		editor.clear();
		editor.commit();
	}
}
