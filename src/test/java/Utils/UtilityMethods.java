package Utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityMethods {
	
	public static Properties prop;
	
	public static String getPropKeyValue(String keyName) {
		
		prop = new Properties();
		try {
			prop.load(new FileInputStream("./src/test/resources/awscredentials/AwsCredentails.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop.getProperty(keyName);
		
	}

}
