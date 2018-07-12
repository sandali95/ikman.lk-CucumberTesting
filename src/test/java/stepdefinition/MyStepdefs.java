package stepdefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class MyStepdefs {

    public WebDriver driver ;
    String text;
    String[] text_array;
    int noOfads;
    int adCounter =1;

    @Given("^I am on the \"([^\"]*)\" page$")
    public void iAmOnThePage(String arg0) throws Throwable {
        System.setProperty("webdriver.gecko.driver", "E:\\java\\gecko\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://ikman.lk/");
    }

    @When("^I click on Property link$")
    public void iClickOnPropertyLink() throws Throwable {
        driver.findElement(By.linkText("Property")).click();
    }

    @And("^I click on Houses link$")
    public void iClickOnHousesLink() throws Throwable {
        driver.findElement(By.partialLinkText("Houses")).click();
    }

    @And("^I click on Colombo link$")
    public void iClickOnColomboLink() throws Throwable {
        driver.findElement(By.partialLinkText("Colombo")).click();
    }

    @And("^I enter min_price as \"([^\"]*)\"and a max_Price as \"([^\"]*)\"$")
    public void iEnterMin_priceAsAndAMax_PriceAs(String min, String max) throws Throwable {
        //apply minimum and maximum price
        driver.findElement(By.partialLinkText("Price (Rs)")).click();
        driver.findElement(By.name("filters[0][minimum]")).sendKeys(min);
        driver.findElement(By.name("filters[0][maximum]")).sendKeys(max);

        //click filter
        driver.findElement(By.className("btn-apply")).click();
    }

    @And("^I set bed as \"([^\"]*)\"$")
    public void iSetBedAs(String arg0) throws Throwable {
        //apply no of beds
        driver.findElement(By.partialLinkText("Beds")).click();
        driver.findElement(By.id("filters2values-3")).click();
    }


    @Then("^I am naviageted to the result page$")
    public void iAmNaviagetedToTheResultPage() throws Throwable {
        //get the no of ads shown
        text = driver.findElement(By.className("summary-count")).getText();
        text_array = text.split(" ");
        noOfads = Integer.parseInt(text_array[3]);

        //print all the ads
        //Iterate through all pages
        for (int i = 0; i <= (noOfads / 25); i++) {

            //Get ads on one page to a list
            int count = 0;
            List<WebElement> ads = ((FirefoxDriver) driver).findElementsByClassName("ui-item");

            //Iterate through ads
            for (WebElement ad : ads) {
                //Remove first two ads (Sponsored)
                if (count < 2) {
                    count++;
                } else {
                    //Getting Price of the ad
                    String price = ad.findElement(By.className("item-info")).getText();

                    //Printing the ad as per the need
                    System.out.println("Ad Number " + (adCounter) + " Price is : " + price);

                    //Sanitizing the Price to integer
                    String[] priceArr = price.split("Rs| |,");
                    String priceMerge = priceArr[2] + priceArr[3] + priceArr[4];
                    Integer priceVal = Integer.parseInt(priceMerge);

                    //Getting no. of beds//a[@class='col-6 lg-3 pag-next']
                    String beds = ad.findElement(By.xpath("//p[@class='item-meta']//span[1]")).getText();
                    String[] bedsArr = beds.split(" ");
                    Integer bedsCount = Integer.parseInt(bedsArr[1]);

                    //Validating the price and no.of Beds
                    if (5000000 <= priceVal && priceVal <= 7500000 && bedsCount == 3) {
                        count++;
                        adCounter++;
                    } else {
                        System.out.println("Ad Number " + (adCounter) + "'s Validation failed");
                        count++;
                        adCounter++;
                    }

                }
            }
            try {
                String nextBtnLink = "//a[@class='col-6 lg-3 pag-next']";
                WebElement nextBtn = ((FirefoxDriver) driver).findElementByXPath(nextBtnLink);
                nextBtn.click();
            } catch (Exception e) {
                System.out.println("End of ads");
            }

        }
    }


}
