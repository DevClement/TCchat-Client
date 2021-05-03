package fr.tcchat.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFile {
	private final String NAME = "client.properties"; 
	private final File CONFIG_DIR = new File(System.getenv("APPDATA") + "/.tcchat");
	private final File CONFIG_FILE = new File(System.getenv("APPDATA") + "/.tcchat/" + this.NAME);
	
	private Properties prop;
	
	public ConfigFile() {
		
		if(!this.CONFIG_DIR.exists()) {
			this.CONFIG_DIR.mkdir();
		}
		
		if(!this.CONFIG_FILE.exists()) {
			this.createFile();
		} else {
			this.prop = readPropertiesFile(this.CONFIG_FILE);
		}
		
	}
	
	public void createFile() {
		this.prop = new Properties();
		this.save();
	}
	
	public void save() {
		try {
			this.prop.store(new FileOutputStream(this.CONFIG_FILE), "TCchat Config created by Tom & Clement");
		} catch (IOException e) {
			System.out.println("Impossible de sauvegarder la configuration");
			e.printStackTrace();
		}
	}
	
	public void set(String key, String data) {
		this.prop.setProperty(key, data);
		this.save();
	}
	
	public void remove(String key) {
		this.prop.remove(key);
		this.save();
	}
	
	public String get(String key) {
		return this.prop.getProperty(key);
	}
	
	public static Properties readPropertiesFile(File fileName) {
	    Properties prop = new Properties();
        try {
			prop.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return prop;
	}
}
