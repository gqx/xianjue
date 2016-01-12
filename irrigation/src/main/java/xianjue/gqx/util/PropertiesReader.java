package xianjue.gqx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public Properties getProperties(String filename){
		Properties prop = new Properties();
		InputStream in =getClass().getResourceAsStream(filename);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
