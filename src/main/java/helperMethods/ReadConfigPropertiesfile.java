package helperMethods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
;

public class ReadConfigPropertiesfile {
	public static Properties property = null;

	public ReadConfigPropertiesfile() {
		String configFilePath=System.getProperty("user.dir") +"\\resources\\config.properties";
		try {
			FileInputStream input = new FileInputStream(configFilePath);
			property = new Properties();
			property.load(input);	
		}
		catch (FileNotFoundException e) {
			System.out.println("Config file isn't found: "  +e.getLocalizedMessage());
		}

		catch (IOException e) {
			System.out.println("There is no data in config file: " +e.getLocalizedMessage());
		}

	}

	public String getURL() {
		String URL ="";
		URL = property.getProperty("URL");
		if(URL == null)
		{
			return "";
		}
		else
		{
			return URL;
		}
	}

	public String getBrowser() {
		String Browser ="";
		Browser = property.getProperty("Browser").toLowerCase();
		if(Browser == null)
		{
			return "";
		}
		else
		{
			return Browser;
		}
	}

	public int getImplicitWait() {
		int DesiredImplicitWait = Integer.parseInt(property.getProperty("ImplicitWait"));
		return DesiredImplicitWait;
	}
}
