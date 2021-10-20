import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OrderTest {

    WebDriver driver;
    private final String url = "http://automationpractice.com/";
    private final String expectedResultOrder = "1 Product";


    @BeforeMethod
    public void setUp() {
       WebDriverManager.chromedriver().setup();

       driver = new ChromeDriver();

       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.close();
        driver.quit();
    }
    @Test
    public void orderTest() {
        driver.get(url);
        driver.findElement(By.xpath("//input[@id = 'search_query_top']")).sendKeys("Dress\n");
        driver.findElement(By.xpath("//a[@href=\"http://automationpractice.com/index.php?controller=cart&add=1&id_product=5&token=e817bb0705dd58da8db074c69f729fd8\"]"));
        driver.findElement(By.xpath("//a[@title = 'View my shopping cart']")).click();
        WebElement actualResult = driver.findElement(By.xpath("//h1[@id = 'cart_title']/span/span"));

        Assert.assertEquals(actualResult.getText(), expectedResultOrder);
    }





}
