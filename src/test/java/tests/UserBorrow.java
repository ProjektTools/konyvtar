package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UserBorrow {
   /*
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUser() throws Exception {
    driver.get(baseUrl + "/Project_Tools/");
    driver.findElement(By.linkText("Bejelentkezés")).click();
    driver.findElement(By.id("username_input")).clear();
    driver.findElement(By.id("username_input")).sendKeys("ildi");
    driver.findElement(By.id("password_input")).clear();
    driver.findElement(By.id("password_input")).sendKeys("ildi");
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    driver.findElement(By.linkText("Könyvek listázása")).click();
    driver.findElement(By.xpath("//div[@id='kategorialista']/div[6]/div/div[2]/div/a/h5")).click();
    driver.findElement(By.id("kolcsonzom")).click();
    driver.findElement(By.linkText("Kölcsönzések kezelése")).click();
    driver.findElement(By.xpath("//button[@onclick='returnfunction(2)']")).click();
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }*/
}
