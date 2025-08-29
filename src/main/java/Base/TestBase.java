package Base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

   public static AndroidDriver driver;

   public static void TurtlemintProApp() throws MalformedURLException, InterruptedException {

      DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

      desiredCapabilities.setCapability("deviceName", "Pixel 4");
      desiredCapabilities.setCapability("udid", "emulator-5554");
      desiredCapabilities.setCapability("platformName", "Android");
      desiredCapabilities.setCapability("platformVersion", "12");

//        desiredCapabilities.setCapability("noReset", "true");
//        desiredCapabilities.setCapability("fullReset", "false");
      desiredCapabilities.setCapability("automationName", "UiAutomator2");
     //               desiredCapabilities.setCapability("app", "/Users/tejasbahadure/Downloads/app-release.apk");
      desiredCapabilities.setCapability("appPackage", "in.ninja.app");
      desiredCapabilities.setCapability("appActivity", "in.ninja.app.MainActivity");


      URL url = new URL("http://127.0.0.1:4723/");

      driver = new AndroidDriver(url, desiredCapabilities);
      System.out.println("Application Start");

   }
}
