package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;
  
    Actions actions;
    JavascriptExecutor js;

    // Locators
    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-text")
    private WebElement searchBtn;

    @FindBy(xpath = "/html/body/div[1]/div[1]/span/div/h1/div/div[4]/div/div/form/span/span/span/span")
    private WebElement sortDropdown;
    
 //Low to high option
    @FindBy(xpath = "/html/body/div[4]/div/div/ul/li[2]/a")
    private WebElement lowToHighOption;
    


    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/a/h2/span")
    private WebElement firstProduct;
    
    // Price filter fields
    private By priceMinField = By.xpath("//*[@id=\"p_36/range-slider_slider-item_lower-bound-slider\"]");
    private By priceMaxField = By.xpath("//*[@id=\"p_36/range-slider_slider-item_upper-bound-slider\"]");
    private By priceGoBtn = By.xpath("//*[@id=\"a-autoid-24\"]/span/input");

    
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public void searchBlank() {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }
   
    public void sortLowToHigh() {
    
    	    wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
    	    wait.until(ExpectedConditions.elementToBeClickable(lowToHighOption)).click();
    	
    }
    
    public void selectBrand(String brand) {
        By brandLocator = By.xpath("//span[text()='" + brand + "']/preceding-sibling::div");
        wait.until(ExpectedConditions.elementToBeClickable(brandLocator)).click();
    }

    // Set Price Range
//    public void setPriceRange(int minOffset, int maxOffset) {
//        WebElement minSlider = wait.until(ExpectedConditions.elementToBeClickable(priceMinField));
//        WebElement maxSlider = wait.until(ExpectedConditions.elementToBeClickable(priceMaxField));
//
//        // Scroll sliders into view
//        js.executeScript("arguments[0].scrollIntoView(true);", minSlider);
//
//        // Drag sliders
//        actions.dragAndDropBy(minSlider, minOffset, 0).perform(); // move min slider right
//        actions.dragAndDropBy(maxSlider, maxOffset, 0).perform(); // move max slider left
//
//        // Click Go button
//        WebElement goBtn = wait.until(ExpectedConditions.elementToBeClickable(priceGoBtn));
//        goBtn.click();
//    }
    // Select Customer Rating (e.g., "4" = 4 stars & above)
//    public void selectCustomerRating(String stars) {
//        By ratingLocator = By.xpath("//i[@class='a-icon a-icon-star-medium a-star-medium-" + stars + "']/ancestor::a");
//        wait.until(ExpectedConditions.elementToBeClickable(ratingLocator)).click();
//    }




    public void clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
    }

    public boolean verifyResultsDisplayed() {
        return wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//div[@data-component-type='s-search-result']")))
                .size() > 0;
    }
}