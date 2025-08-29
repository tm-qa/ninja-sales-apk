package Login;

import Base.TestBase;
import Page.Login;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestUtil;

import java.net.MalformedURLException;

public class login extends TestBase {

    public login() {
        super();
    }

    Login lg;



    @BeforeMethod
    public void start() throws MalformedURLException, InterruptedException {
        TurtlemintProApp();
        lg = new Login();
        lg.login();

    }

    @Test(description = "TC_01  Verifying login function ")
    public void loginFunction() throws InterruptedException {
        System.out.printf("successfully login to ninja salesPro apk");
        TestUtil.getScreenShot();

    }

    @Test(description = "TC_02 Verifying allow permission ")
    public void permissionFunctionality() throws InterruptedException{
        lg.permissionAllow();
        System.out.printf("successfully call and contact permission given");
        TestUtil.getScreenShot();
    }
    @Test(description = "TC_03 Verifying deny  permission ")
    public void permissionDenyFunctionality() throws InterruptedException{
        lg.permissionDeny();
        System.out.printf("successfully call and contact permission Denied");
        lg.permissionAllow();
        TestUtil.getScreenShot();
    }


    @AfterMethod
    public void close() {
        driver.quit();
    }

}
