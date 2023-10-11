package com.qa.Resources.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties
{
	public static Properties property;
	private static String configpath = "src\\test\\java\\com\\qa\\Resources\\Configuration\\Configuration.properties";
	public static void initializePropertyFile()
	{
		property = new Properties();
		try {
			InputStream input = new FileInputStream(configpath);
			property.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
