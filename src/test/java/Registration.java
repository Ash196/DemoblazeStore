import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
public class Registration {
    private WebDriver driver;
    @BeforeClass
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "C:\\IdeaProjects\\DemoblazeStore\\src\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Step ("Test 002")
    @Test (priority = 2)
    public void RegistrationAndLogin() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");

        // Генерация уникального логина и пароля
        String uniqueUsername = "testUser" + UUID.randomUUID().toString().substring(0, 8);
        String password = "password" + UUID.randomUUID().toString().substring(0, 8);

        driver.findElement(By.id("signin2")).click();
        driver.findElement(By.id("sign-username")).sendKeys(uniqueUsername);
        driver.findElement(By.id("sign-password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='button' and @onclick='register()' and @class='btn btn-primary'] ")).click();
        Thread.sleep(5000);

        Alert alert1 = driver.switchTo().alert();
        alert1.accept();
        Thread.sleep(7000);

       driver.findElement(By.id("login2")).click();
       driver.findElement(By.id("loginusername")).sendKeys(uniqueUsername);
       driver.findElement(By.id("loginpassword")).sendKeys(password);
       driver.findElement(By.xpath("//button[@type='button' and @onclick='logIn()' and @class='btn btn-primary']")).click();
       Thread.sleep(7000);
       driver.findElement(By.id("logout2")).click();
            }
    @Step ("Test 003")
    @Test (priority = 3)
    public void UserAlreadyExist() throws InterruptedException {
    driver.get("https://www.demoblaze.com/");

    driver.findElement(By.id("signin2")).click();
    driver.findElement(By.id("sign-username")).sendKeys("MyUser");
    driver.findElement(By.id("sign-password")).sendKeys("Password123");
    driver.findElement(By.xpath("//button[@type='button' and @onclick='register()' and @class='btn btn-primary'] ")).click();
        Thread.sleep(7000);
        Alert alert1 = driver.switchTo().alert();
        System.out.println(alert1.getText());
        alert1.accept();
        if (driver.getPageSource().contains("This user already exist."));
        System.out.println("This user already exist.");
    }
    @Step ("Test 004")
    @Test (priority = 4)
    public void EmptyFields() throws InterruptedException {
    driver.get("https://www.demoblaze.com/");

    driver.findElement(By.id("signin2")).click();
    driver.findElement(By.xpath("//button[@type='button' and @onclick='register()' and @class='btn btn-primary'] ")).click();
    Thread.sleep(7000);
        Alert alert1 = driver.switchTo().alert();
        System.out.println(alert1.getText());
        alert1.accept();
        if (driver.getPageSource().contains("Please fill out Username and Password."));
        System.out.println("Please fill out Username and Password.");
    }
}
