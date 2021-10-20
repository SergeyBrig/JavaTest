import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListTitleTest {

    private WebDriver driver;
    private static final String URL = "https://www.webstaurantstore.com";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

    @Test
    public void searchTest() {

        driver.get(URL);

        final String searchText = "fork";
        driver.findElement(By.id("searchval")).sendKeys(searchText + "\n");
        List<WebElement> itemList = driver.findElements(By.xpath("//div/a[@data-testid='itemDescription']"));
        for (int i = 0; i < itemList.size(); i++) {
            Assert.assertTrue(itemList.get(i).getText().toLowerCase().contains(searchText));
        }
    }

    @Test
    public void brandMenuTest() {
        driver.get(URL);

        driver.findElement(By.xpath("//a[@title='Amana Commercial Microwaves']")).click();

        List<WebElement> brandList = driver.findElements(By.xpath("//p[@class = 'description category_name']"));
        for (int i = 0; i < brandList.size(); i++) {
            Assert.assertTrue(brandList.get(i).getText().toLowerCase().contains("amana"));
        }
    }
}
