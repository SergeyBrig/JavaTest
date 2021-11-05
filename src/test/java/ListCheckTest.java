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

public class ListCheckTest {


    private static final String URL = "https://www.webstaurantstore.com";
    private static String SEARCHWORD = "cup";
    private static final By SEARCHFIELD = By.xpath("//input[@id = 'searchval']");


    private WebDriver driver;

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
    public void SergeyBrigBrandSearch() {
        driver.get(URL);

        driver.findElement(SEARCHFIELD).sendKeys(SEARCHWORD + "\n");

        List<WebElement> brandList = driver.findElements(By.xpath("//a[@ data-testid='itemDescription']"));
        for (int i = 0; i < brandList.size(); i++) {
            Assert.assertTrue(brandList.get(i).getText().toLowerCase().contains(SEARCHWORD));
        }
    }

    @Test
    public void SergeyBrigMenu2Test() {
        driver.get(URL);

        List<WebElement> menuList = driver.findElements(By.xpath("//div[@class = 'm-0 lt:flex']/a"));
        for (int i = 0; i < menuList.size(); i++) {
            if(menuList.get(i).getText().toLowerCase().contains("furniture")) {
                menuList.get(i).click();
                break;
            }
        }
        List<WebElement> categoryList = driver.findElements(By.xpath("//div/a/h2[@data-testid='displayTitle']"));
        for(int i = 0; i < categoryList.size(); i++) {
            if(categoryList.get(i).getText().contains("Hotel Furniture")) {
                categoryList.get(i).click();
                break;
            }
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.webstaurantstore.com/64111/hotel-furniture.html");
    }

    @Test
    private void searchItemNumber() {
        driver.get(URL);

        driver.findElement(SEARCHFIELD).sendKeys("600PTSD2472" + "\n");

        List<WebElement> sizeTitle = driver.findElements(By.xpath("//ul[@class = 'nav nav-pills nav-stacked']/li"));
        for (int i = 0; i < sizeTitle.size(); i++) {
            if(sizeTitle.get(i).getText().contains("24\" x 108\"")) {
                sizeTitle.get(i).click();
                break;
            }
        }
        WebElement titleText = driver.findElement(By.id("page-header-description"));


        Assert.assertTrue(titleText.getText().contains("24\" x 108\""));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.webstaurantstore.com/regency-16-gauge-type-304-stainless-steel-pass-through-shelf-with-overshelf-108-x-24/600PTSD24108.html");
    }
    @Test
    private void eddToCart() {
        driver.get(URL);

        driver.findElement(SEARCHFIELD).sendKeys("220SPRDWNEN3"+ "\n");

        List<WebElement> sizeVariations = driver.findElements(By.xpath("//ul[@class = 'nav nav-pills nav-stacked']/li"));
        for (int i = 0; i < sizeVariations.size(); i++) {
            if(sizeVariations.get(i).getText().contains("5 1/2\"")) {
                sizeVariations.get(i).click();
                break;
            }
        }
        driver.findElement(By.xpath("//input[@id ='qty']")).sendKeys("2");
        driver.findElement(By.xpath("//input[@id ='buyButton']")).click();
        driver.findElement(By.xpath("//a[@data-testid='cart-nav-link']")).click();
        WebElement orderItem = driver.findElement(By.xpath("//span[@id= 'cartItemCountSpan']"));

        Assert.assertEquals(orderItem.getText(), "21");

    }


}
