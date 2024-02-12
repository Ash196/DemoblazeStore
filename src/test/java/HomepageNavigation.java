import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomepageNavigation {
    private WebDriver driver;
    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\IdeaProjects\\DemoblazeStore\\src\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Step ("Test 005")
    @Test(priority = 5)
    public void SlideOnTheLeftAndRight() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");

        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[@class='carousel-control-prev-icon']")).click();
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement thirdSlide = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Third slide']")));

        if (thirdSlide != null) {
            System.out.println("Элемент 'Third slide' найден на странице.");
        } else {
            System.out.println("Элемент 'Third slide' не был найден на странице.");
            Assert.fail("Элемент 'Third slide' не найден на странице.");
        }

        driver.findElement(By.xpath("//span[@class='carousel-control-next-icon']")).click();

        Thread.sleep(3000);

        WebElement firstSlide = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='First slide']")));

        if (firstSlide != null) {
            System.out.println("Элемент 'First Slide' найден на странице.");
        } else {
            System.out.println("Элемент 'First Slide' не был найден на странице.");
            Assert.fail("Элемент 'First Slide' не найден на странице.");
        }

    }
    @Step ("Test 006")
    @Test(priority = 6)
    public void CategoriesButtons() throws InterruptedException {
        driver.get("https://www.demoblaze.com/");

        driver.findElement(By.xpath("//*[@id='nava']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[4]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[1]/div/a/img")));
        WebElement secondElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[2]/div/a/img")));

        if (firstElement != null && secondElement != null) {
            System.out.println("Оба элемента найдены на странице.");
        } else {
            System.out.println("Один или оба элемента не были найдены на странице.");
            Assert.fail("Один или оба элемента не найдены на странице.");
        }

                driver.findElement(By.xpath("//div[@id='contcont']//a[2]")).click();

                WebElement thirdElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tbodyid']//div[1]//div[1]//a[1]//img[1]")));
                WebElement fourthElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='tbodyid']//div[6]//div[1]//a[1]//img[1]")));

                if (thirdElement != null && fourthElement != null) {
                    System.out.println("Оба элемента найдены на странице.");
                } else {
                    System.out.println("Один или оба элемента не были найдены на странице.");
                    Assert.fail("Один или оба элемента не найдены на странице.");
                }

        driver.findElement(By.xpath("//div[@class='col-lg-3']//a[1]")).click();
        WebElement fifthElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]//div[1]//a[1]//img[1]")));
        WebElement sixthElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[3]//div[1]//a[1]//img[1]")));

        if (fifthElement != null && sixthElement != null) {
            System.out.println("Оба элемента найдены на странице.");
        } else {
            System.out.println("Один или оба элемента не были найдены на странице.");
            Assert.fail("Один или оба элемента не найдены на странице.");
        }
        driver.findElement(By.xpath("//a[@id='cat']")).click();
    }
}