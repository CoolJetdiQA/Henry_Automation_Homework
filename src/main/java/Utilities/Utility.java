package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class Utility {
	private static Properties configFile;
	private static String filePath = "src/test/resources/datasource/config.properties";


	static {
		try(FileInputStream input = new FileInputStream(filePath)) {		
			configFile = new Properties();
			configFile.load(input);		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperties(String keyName) {		
		return configFile.getProperty(keyName);
	}
}
