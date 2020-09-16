package utils.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    static Properties properties;
    static final String FILE_PATH;

	static {
		FILE_PATH = "src/test/resources/properties/food_delivery.properties";

		FileInputStream input;

		try {
			input = new FileInputStream(FILE_PATH);
			properties = new Properties();
			properties.load(input);
			input.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public static String getPropertiesValue(String key) {
		return properties.getProperty(key);

	}

}
