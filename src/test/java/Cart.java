import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.concurrent.TimeUnit;

public class Cart {
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

    @Test(priority = 1)
    @Step ("Test 001")
    public void PurchaseOfGoods() throws InterruptedException { driver.get("https://www.demoblaze.com/");

    //Precondition
    driver.findElement(By.xpath("//div[8]//div[1]//a[1]//img[1]")).click();
    driver.findElement(By.xpath("//a[@href='#' and contains(text(),'Add to cart')]")).click();
    driver.findElement(By.xpath("//a[@href='index.html']")).click();

    driver.findElement(By.xpath("//div[8]//div[1]//a[1]//img[1]")).click();
    driver.findElement(By.xpath("//a[@href='#' and contains(text(),'Add to cart')]")).click();
    driver.findElement(By.xpath("//a[@href='index.html']")).click();

    driver.findElement(By.xpath("//div[9]//div[1]//a[1]//img[1]")).click();
    driver.findElement(By.xpath("//a[@href='#' and contains(text(),'Add to cart')]")).click();
    driver.findElement(By.xpath("//a[@href='index.html']")).click();

    //Actions
    driver.findElement(By.xpath("//*[@id='cartur']")).click();
    driver.findElement(By.xpath("//tbody/tr[1]/td[4]/a[1]")).click();

        Thread.sleep(6000);

        driver.findElement(By.xpath("//*[@id='page-wrapper']/div/div[2]/button")).click();

        driver.findElement(By.cssSelector("input#name")).sendKeys("Buyer");
        driver.findElement(By.cssSelector("input#country")).sendKeys("Russia");
        driver.findElement(By.cssSelector("input#city")).sendKeys("Moscow");
        driver.findElement(By.cssSelector("input#card")).sendKeys("2222 2222 2222 2222");
        driver.findElement(By.cssSelector("input#month")).sendKeys("July");
        driver.findElement(By.cssSelector("input#year")).sendKeys("2024");

        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='orderModal']/div/div/div[3]/button[2]")).click();
        Thread.sleep(3000);



        // Получить значение Date из popup поля
        String date = driver.findElement(By.xpath("/html/body/div[10]/p")).getAttribute("innerText");

        // Сравнение даты из формы и актуальной
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
        String expectedDate = currentDate.minusMonths(1).format(formatter);

        driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click();


        if (!date.equals(expectedDate)) {
            Assert.fail("Bug detected: The month in the Date field is incorrect.");
        }
    }



    }
