package BaseLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	public static Properties prop;
	public static WebDriver driver;
	public BaseClass()
	{
		File f= new File(System.getProperty("user.dir")+"//src//main//java//ConfigurationLayer//hrm.properties");
		try {
			FileInputStream fis= new FileInputStream(f);
			 prop= new Properties();
			 prop.load(fis);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
	public static void initialization()
	{
		 driver= new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		 driver.manage().deleteAllCookies();
		 
		
	}
	
	
	
	
}
