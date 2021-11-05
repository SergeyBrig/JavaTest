import Base.BaseTest;
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

public class ListCheckTest extends BaseTest {


    private static final String URL = "https://www.webstaurantstore.com";
    private static String SEARCHWORD = "cup";
    private static final By SEARCHFIELD = By.xpath("//input[@id = 'searchval']");
    private static final By BUYBUTTON = By.xpath("//input[@id = 'buyButton']");


    @Test
    public void SergeyBrigBrandSearch() {
        getDriver().get(URL);

        getDriver().findElement(SEARCHFIELD).sendKeys(SEARCHWORD + "\n");

        List<WebElement> brandList = getDriver().findElements(By.xpath("//a[@ data-testid='itemDescription']"));
        for (int i = 0; i < brandList.size(); i++) {
            Assert.assertTrue(brandList.get(i).getText().toLowerCase().contains(SEARCHWORD));
        }
    }

    @Test
    public void SergeyBrigMenu2Test() {
        getDriver().get(URL);

        List<WebElement> menuList = getDriver().findElements(By.xpath("//div[@class = 'm-0 lt:flex']/a"));
        for (int i = 0; i < menuList.size(); i++) {
            if(menuList.get(i).getText().toLowerCase().contains("furniture")) {
                menuList.get(i).click();
                break;
            }
        }
        List<WebElement> categoryList = getDriver().findElements(By.xpath("//div/a/h2[@data-testid='displayTitle']"));
        for(int i = 0; i < categoryList.size(); i++) {
            if(categoryList.get(i).getText().contains("Hotel Furniture")) {
                categoryList.get(i).click();
                break;
            }
        }
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.webstaurantstore.com/64111/hotel-furniture.html");
    }

    @Test
    private void searchItemNumber() {
        getDriver().get(URL);

        getDriver().findElement(SEARCHFIELD).sendKeys("600PTSD2472" + "\n");

        List<WebElement> sizeTitle = getDriver().findElements(By.xpath("//ul[@class = 'nav nav-pills nav-stacked']/li"));
        for (int i = 0; i < sizeTitle.size(); i++) {
            if(sizeTitle.get(i).getText().contains("24\" x 108\"")) {
                sizeTitle.get(i).click();
                break;
            }
        }
        WebElement titleText = getDriver().findElement(By.id("page-header-description"));


        Assert.assertTrue(titleText.getText().contains("24\" x 108\""));
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.webstaurantstore.com/regency-16-gauge-type-304-stainless-steel-pass-through-shelf-with-overshelf-108-x-24/600PTSD24108.html");
    }
    @Test
    private void eddToCart() {
        getDriver().get(URL);

        getDriver().findElement(SEARCHFIELD).sendKeys("220SPRDWNEN3"+ "\n");

        List<WebElement> sizeVariations = getDriver().findElements(By.xpath("//ul[@class = 'nav nav-pills nav-stacked']/li"));
        for (int i = 0; i < sizeVariations.size(); i++) {
            if(sizeVariations.get(i).getText().contains("5 1/2\"")) {
                sizeVariations.get(i).click();
                break;
            }
        }
        getDriver().findElement(By.xpath("//input[@id ='qty']")).sendKeys("2");
        getDriver().findElement(By.xpath("//input[@id ='buyButton']")).click();
        getDriver().findElement(By.xpath("//a[@data-testid='cart-nav-link']")).click();
        WebElement orderItem = getDriver().findElement(By.xpath("//span[@id= 'cartItemCountSpan']"));

        Assert.assertEquals(orderItem.getText(), "21");

    }

    @Test
    public void SergeyBrigCheckCard() {
        getDriver().get("https://www.webstaurantstore.com");


        getDriver().findElement(SEARCHFIELD).sendKeys("3493048BBESP" + "\n");
        List<WebElement> sizeList = getDriver().findElements(By.xpath("//ul[@class = 'nav nav-pills nav-stacked']/li"));
        for (WebElement item : sizeList) {
            if (item.getText().contains("36\" x 36\"")) {
                item.click();
                break;
            }
        }
        getDriver().findElement(BUYBUTTON).click();

        getDriver().findElement(SEARCHFIELD).sendKeys("176BIN21GL" + "\n");
        List<WebElement> cupsList = getDriver().findElements(By.xpath("//ul[@class = 'nav nav-pills nav-stacked']/li"));
        for (WebElement item : cupsList) {
            if (item.getText().contains("27 Gallons (430 Cups)")) {
                item.click();
                break;
            }
        }
        getDriver().findElement(BUYBUTTON).click();

        getDriver().findElement(SEARCHFIELD).sendKeys("4070669" + "\n");
        List<WebElement> nobleList = getDriver().findElements(By.xpath("//ul[@class = 'nav nav-pills nav-stacked']/li"));
        for (WebElement item : nobleList) {
            if (item.getText().contains("4\" Deep ")) {
                item.click();
                break;
            }
        }
        getDriver().findElement(BUYBUTTON).click();

        getDriver().findElement(By.xpath("//a[@data-testid='cart-nav-link']")).click();

        List<WebElement> cardItemList = getDriver().findElements(By.xpath("//div[@class = 'ag ag-cart box']/div/div[@class='cartItem ag-item gtm-product-auto']"));
        int countItem = cardItemList.size();;

        Assert.assertEquals(countItem,3);
    }
    @Test
    public void sBrigGetBrandProductTest() {
        getDriver().get("https://www.webstaurantstore.com/");

        getDriver().findElement(By.xpath("//a[@title = 'Cambro']")).click();

        List<WebElement> titleList = getDriver().findElements(By.xpath("//div/a/h2[@data-testid='displayTitle']"));
        for (WebElement item : titleList) {
            if (item.getText().toLowerCase().contains("catering bags")) {
                item.click();
                break;
            }
        }
        List<WebElement> productListBrand = getDriver().findElements(By.xpath("//div[@id='details']/a[2]"));
        for (WebElement item : productListBrand) {
            Assert.assertTrue(item.getText().toLowerCase().contains("cambro"));
        }
    }


}
