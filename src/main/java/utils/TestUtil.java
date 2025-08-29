package utils;

import Base.TestBase;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Allure;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Random;
import java.util.Set;


public class TestUtil extends TestBase {

    public static long Page_load_time = 60;
    public static long implicit_wait = 12;

    public String firstname;
    public String lastname;

    public String full_name;
    public String RegNo;
    public static String plno;


    public static String getTimeStamp() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
        return timeStamp;
    }

    public void GenerateRegNo() {
        int alpha1 = 'A' + (int) (Math.random() * ('Z' - 'A'));
        int alpha2 = 'A' + (int) (Math.random() * ('Z' - 'A'));
        // int alpha3 = 'A' + (int)(Math.random() * ('Z' - 'A'));
        int digit1 = (int) (Math.random() * 10);
        int digit2 = (int) (Math.random() * 10);
        int digit3 = (int) (Math.random() * 10);
        int digit4 = (int) (Math.random() * 10);
        RegNo = ("MH39" + (char) (alpha1) + ((char) (alpha2)) +
                +digit1 + digit2 + digit3 + digit4);
        System.out.println(RegNo + "IN test UTIL");
    }

    public static void click(WebElement element, String msg) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        LogUtils.info(msg);
    }

    public static void assertText(WebElement expected, String actual) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(expected));
        Assert.assertEquals(expected.getText(),actual);

        LogUtils.info(actual);
    }

    public static void reClick(WebElement element, String msg) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        LogUtils.info(msg);

    }


    public static void sendKeys(WebElement element, String keys, String msg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(keys);
        LogUtils.info(msg);
    }

    public static void scrolltoElement(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("+xpath+")"))));
    }

    public static void IsDisplayed(WebElement element, String msg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.isDisplayed();
        LogUtils.info(msg);
    }
    public void swipe(String direction) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY, endY;

        if (direction.equalsIgnoreCase("up")) {
            startY = (int) (size.height * 0.8);
            endY = (int) (size.height * 0.2);
        } else if (direction.equalsIgnoreCase("down")) {
            startY = (int) (size.height * 0.2);
            endY = (int) (size.height * 0.8);
        } else {
            throw new IllegalArgumentException("Invalid swipe direction: " + direction);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }


    public void scrollToElementAndClick(By locator, String direction, int maxSwipes) throws InterruptedException {
        int swipeCount = 0;

        while (driver.findElements(locator).isEmpty() && swipeCount < maxSwipes) {
            swipe(direction);
            Thread.sleep(2000);
            swipeCount++;
        }

        if (!driver.findElements(locator).isEmpty()) {
            driver.findElement(locator).click();
        } else {
            throw new NoSuchElementException("Element not found after " + maxSwipes + " swipes: " + locator);
        }
    }

    public static void IsSelected(WebElement element, String msg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.isSelected();
        LogUtils.info(msg);
    }

    public static void waitElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilTextToPresent(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitUntilElementToBeVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitUntilInvisibilityOfElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(element)));
    }

    public static void waitUntilVisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static void scroll(WebElement element,String pixels) {
        while (true) {
            if (element.isDisplayed()) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,"+pixels+")");
                LogUtils.info("Successfully Scrolled Down by "+pixels+" pixels");
                break;
            }
        }
    }


    public static String PastDate(int days) {
        LocalDateTime currentDateTime = LocalDateTime.now().minusDays(days);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTimeFormatter.format(currentDateTime);
    }

    public static String FutureDate(int days) {
        LocalDateTime currentDateTime = LocalDateTime.now().plusDays(days);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTimeFormatter.format(currentDateTime);

    }

    public static String PresentDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTimeFormatter.format(currentDateTime);

    }

    public static String ninjaFutureDate(int days) {
        LocalDateTime currentDateTime = LocalDateTime.now().plusDays(days);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return dateTimeFormatter.format(currentDateTime);
    }

    public static String ninjaPastDate(int days) {
        LocalDateTime currentDateTime = LocalDateTime.now().minusDays(days);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return dateTimeFormatter.format(currentDateTime);
    }

    public static String generateRandomPolicyNo(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        plno = sb.toString();
        return plno;
    }

    public static String getRandomTransactionNo() {
        // It will generate 7 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 7 character.
        return String.format("%07d", number);
    }

    public static String getRandomOtp() {
        // It will generate 4 digit random Number.
        // from 0 to 9999
        Random rnd = new Random();
        int number = rnd.nextInt(9999);

        // this will convert any number sequence into 7 character.
        return String.format("%04d", number);
    }

    public static void LoginLess() {
          driver.get(System.getProperty("url"));
        //driver.get(prop.getProperty("localurl"));

        String Current = driver.getWindowHandle();
        Set<String> AllHandles = driver.getWindowHandles();
        for (String x : AllHandles) {
            if (x.equals(Current)) {
                continue;
            }
            driver.switchTo().window(x).close();
        }
        driver.switchTo().window(Current);
    }
    public static String ninjaPresentDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return dateTimeFormatter.format(currentDateTime);
    }

    public static void getScreenShot() {
        try {
            // Call getScreenshotAs method to create image file
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File f = scrShot.getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("Screenshot " + getTimeStamp(), FileUtils.openInputStream(f));

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}

