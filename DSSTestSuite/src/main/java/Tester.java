import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Tester {

	static InternetExplorerDriver  driver;

    public static void main(String[] args) throws IOException  {
        
        System.setProperty("webdriver.ie.driver", "C:\\selenium_drivers\\IEDriver32bit\\IEDriverServer32bit.exe");
        
        driver = new InternetExplorerDriver();
        driver.get("https://www.google.co.in/");
        driver.manage().window().maximize();
        driver.findElement(By.name("q")).sendKeys("Working with IE");
        driver.findElement(By.name("btnG")).click();

          
    }


}
