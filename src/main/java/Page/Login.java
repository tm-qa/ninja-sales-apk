package Page;

import Base.TestBase;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import junit.framework.Assert;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.TestUtil;

public class Login extends TestBase {

    public Login() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")")
   WebElement androidAllow ;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.gms:id/cancel\")")
    WebElement cancel;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"in.ninja.app:id/et_phone_no\")")
    WebElement entrePhoneNumber;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"in.ninja.app:id/btn_submit\")")
    WebElement cont;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"in.ninja.app:id/et_otp\")")
    WebElement enterOtp;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"in.ninja.app:id/btn_allow\")")
    WebElement allowPermission;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"in.ninja.app:id/tv_deny\")")
    WebElement denyPermission;

    @AndroidFindBy (uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_deny_button\")")
    WebElement androidDeny ;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Permission to access call logs is mandatory for app usage. Denying it will make the app unusable.\")")
    WebElement permissionError;


    public void login() throws InterruptedException {
        Thread.sleep(2000);
        try {
            TestUtil.click(androidAllow,"allow notifcation");
        }
        catch (Exception e){
            TestUtil.click(cancel, "cancel");

        }

        TestUtil.sendKeys(entrePhoneNumber, "6999123456", "mobile number entered");
        TestUtil.click(cont, "continue");
        TestUtil.sendKeys(enterOtp, "1234", "1234 sent");
        TestUtil.click(cont, "Submit");

    }

    public void permissionAllow() throws InterruptedException {
        Thread.sleep(2000);
        TestUtil.click(allowPermission,"allow call log permission");
        Thread.sleep(2000);
        TestUtil.click(androidAllow,"click on android allow for call log ");
        TestUtil.click(androidAllow,"click on android allow for contact ");

    }

    public void permissionDeny() throws InterruptedException {
        Thread.sleep(2000);
        TestUtil.click(denyPermission,"deny call log permission");
        Thread.sleep(2000);
        TestUtil.assertText(permissionError,"Permission to access call logs is mandatory for app usage. Denying it will make the app unusable.");
        Thread.sleep(2000);
        TestUtil.getScreenShot();
    }


}
