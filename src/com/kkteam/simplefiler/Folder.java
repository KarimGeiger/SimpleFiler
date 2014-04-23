package com.kkteam.simplefiler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Folder class shows folder content and path.
 * 
 * @author Karim Geiger <me@karim-geiger.de>
 * 
 */
public class Folder {
	private File[] files;
	private File sdCardRoot;
	private String absolutePath;
	private int fileCount = -1;

	/**
	 * Constructor: Set path and files.
	 * 
	 * @param sdCardRoot
	 *            root for sd card location
	 * @param absolutePath
	 *            absolute path to folder
	 * @throws Exception
	 *             not a folder
	 */
	public Folder(File sdCardRoot, String absolutePath) throws Exception {
		this.sdCardRoot = sdCardRoot;
		this.absolutePath = absolutePath;
		this.files = getFolderContent();
	}

	/**
	 * Get content for folder.
	 * 
	 * @return array containing files
	 * @throws Exception
	 *             not a folder
	 */
	private File[] getFolderContent() throws Exception {
		File dir = new File(sdCardRoot, absolutePath);
		if (dir.isDirectory()) {
			return dir.listFiles();
		}

		throw new Exception("Not a folder");
	}

	/**
	 * Get file names as String array.
	 * 
	 * @return array with names
	 */
	public String[] getFileNames() {
		List<String> fileList = new ArrayList<String>();
		for (File f : files) {
			fileList.add(f.getName());
		}

		fileCount = fileList.size();

		if (fileList.size() == 0) {
			fileList.add("[Empty dir]");
		}

		return fileList.toArray(new String[fileList.size()]);
	}

	/**
	 * Get file count in folder.
	 * 
	 * @return file count
	 */
	public int getFileCount() {
		if (fileCount == -1) {
			getFileNames();
		}
		return fileCount;
	}

	/**
	 * Get absolute path for folder.
	 * 
	 * @return absolute path with ending slash
	 */
	public String getAbsolutePath() {
		if (!absolutePath.substring(absolutePath.length() - 1).equals("/")) {
			return absolutePath + "/";
		}
		return absolutePath;
	}

}
