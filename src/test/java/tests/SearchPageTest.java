import base.DriverSetup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchPageTest {

    WebDriver driver;
    SearchPage searchPage;
    WebDriverWait wait;
    Actions actions;

    @BeforeClass
    public void setup() {
        driver = DriverSetup.getDriver();
        driver.get("https://www.amazon.in/");
        searchPage = new SearchPage(driver);
    }

    @Test(priority = 1)
    public void testSearchProduct() {
        searchPage.searchProduct("laptop");
        Assert.assertTrue(searchPage.verifyResultsDisplayed(), "Search results not displayed!");
    }

    @Test(priority = 3)
    public void testSortLowToHigh() {
        searchPage.sortLowToHigh();
        // Just verify results still visible after sort
        Assert.assertTrue(searchPage.verifyResultsDisplayed(), "Results not visible after Low-High sort!");
    }
    
    
    
    
    @Test(priority = 2)
    public void testApplyFilters() throws InterruptedException {
        // Search for laptops first
        searchPage.searchProduct("laptop");

        // Apply brand filter
        searchPage.selectBrand("HP");

        // Apply price range
        //searchPage.setPriceRange(50, -50);


        // Apply customer rating (4 stars & above)
        //searchPage.selectCustomerRating("4");

        // Optional wait to let filters apply
        Thread.sleep(3000);

        // Verify results displayed after applying filters
        Assert.assertTrue(searchPage.verifyResultsDisplayed(), "Filtered results not displayed!");
    }
    
    
  
    @Test(priority = 4)
    public void testBlankSearch() {
        searchPage.searchBlank();
        // Amazon usually reloads with suggestions or validation
        Assert.assertTrue(driver.getTitle().contains("Amazon"),
                "Blank search did not stay on Amazon search page!");
    }

   @Test(priority = 5)
   public boolean verifyResultsDisplayed() {
       return wait.until(ExpectedConditions
               .presenceOfAllElementsLocatedBy(By.xpath("//div[@data-component-type='s-search-result']")))
               .size() > 0;
   }

    @AfterClass
    public void tearDown() {
        DriverSetup.quitDriver();
    }
}