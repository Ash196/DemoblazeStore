import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class Login {
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
    @Step ("Test 007")
    @Test(priority = 1)
    public void AllDataIsCorrect() throws InterruptedException {
        driver.get("https://www.demoblaze.com/index.html");
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("MyUser");
        driver.findElement(By.id("loginpassword")).sendKeys("!Password123");
        driver.findElement(By.xpath("//button[@type='button' and @class='btn btn-primary' and @onclick='logIn()']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("error")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='nav-link' and @href='#' and @style='display: block;' and @id='logout2' and @onclick='logOut()']"))).click();
        Thread.sleep(2000);
    }
    @Step ("Test 008")
    @Test(priority = 8)
    public void AllDataIsIncorrect() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");

        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("qweasdz");
        driver.findElement(By.id("loginpassword")).sendKeys("weqwdas");
        driver.findElement(By.xpath("//button[@type='button' and @class='btn btn-primary' and @onclick='logIn()']")).click();
        Thread.sleep(5000);

        Alert alert1 = driver.switchTo().alert();
        System.out.println(alert1.getText());

        alert1.accept();
        if (driver.getPageSource().contains("User does not exist."));
        System.out.println("User does not exist.");
    }
    @Step ("Test 009")
    @Test(priority = 9)
    public void IncorrectPassword() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");

        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("MyUser");
        driver.findElement(By.id("loginpassword")).sendKeys("!123");
        driver.findElement(By.xpath("//button[@type='button' and @class='btn btn-primary' and @onclick='logIn()']")).click();
        Thread.sleep(5000);

        Alert alert1 = driver.switchTo().alert();
        System.out.println(alert1.getText());

        alert1.accept();
        if (driver.getPageSource().contains("Wrong password."));
        System.out.println("Wrong password.");
    }
    @Step ("Test 010")
    @Test(priority = 10)
    public void EmptyFields() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");

        driver.findElement(By.id("login2")).click();
        driver.findElement(By.xpath("//button[@type='button' and @class='btn btn-primary' and @onclick='logIn()']")).click();
        Thread.sleep(5000);

        Alert alert1 = driver.switchTo().alert();
        System.out.println(alert1.getText());

        alert1.accept();
        if (driver.getPageSource().contains("Please fill out Username and Password."));
        System.out.println("Please fill out Username and Password.");
    }
}
