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

public class BrigTest {

    private WebDriver driver;
    private String url = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private String expectedResult = "Authentication failed.";

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
    public void loginTest() {

        driver.get(url);

        WebElement email = driver.findElement(By.xpath("//input[@id = 'email']"));
        WebElement password = driver.findElement(By.xpath("//input[@id = 'passwd']"));
        WebElement signButton = driver.findElement(By.xpath("//button[@id = 'SubmitLogin']"));

        email.sendKeys("dhajjjd@gmail.com");
        password.sendKeys("qw3wwe398");
        signButton.click();

        WebElement actualResult = driver.findElement(By.xpath("//div/ol/li"));

        Assert.assertEquals(actualResult.getText(), expectedResult);

    }

    @Test
    public void forgotLinkTest() {
        driver.get(url);

        WebElement forgotLink = driver.findElement(By.xpath("//a[@title = 'Recover your forgotten password']"));
        forgotLink.click();
        String actualResult = driver.getCurrentUrl();
        String expectedResult = "http://automationpractice.com/index.php?controller=password";

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void SergeyBrigMenuTest() {

        String expectedResult = "http://automationpractice.com/index.php?id_product=1&controller=product";

        driver.get(url);

        WebElement shirtLink = driver.findElement(By.xpath("//div[@id = 'block_top_menu']/ul/li[3]/a"));
        shirtLink.click();
        WebElement productLink = driver.findElement(By.xpath("//a[@class = 'product_img_link']"));
        productLink.click();
        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

    }
}
