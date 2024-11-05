import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.List;

public class StepDefination {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Given("User visits iths.se")
    public void user_visits_iths_se() {
        driver.get("https://www.iths.se");
        acceptCookies();
        driver.manage().window().maximize();  // Maximize window at startup
    }
    private void acceptCookies() {
        try {
            WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")));
            cookieBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie consent banner not found or already dismissed: " + e.getMessage());
        }
    }

    @When("User check the title")
    public void userCheckTheTitle() {

    }

    @Then("the title should be {string}")
    public void theTitleShouldBe(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle, "IT-Högskolan – Här startar din IT-karriär!");
    }

    @When("User scroll to the bottom of the page")
    public void iScrollToTheBottomOfThePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    @Then("the phone number should be {string}")
    public void thePhoneNumberShouldBe(String expectedPhoneNumber) {
        WebElement phoneLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='callto:031-790 42 55']")));
        String actualPhoneNumber = phoneLink.getText().trim();
        Assert.assertEquals(expectedPhoneNumber, actualPhoneNumber, "031-790 42 55");
    }

    @When("User minimize the screen size")
    public void minimize_screen_size() {
        driver.manage().window().setSize(new Dimension(375, 812));
    }

    @When("User click on the menu button")
    public void click_menu_button() {
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='fa fa-bars']")));
        menuButton.click();
    }
    @Then("the first menu item should display {string}")
    public void verify_first_menu_item(String expectedText) {
        WebElement firstMenuItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='nav-utbildningar']")));
        Assert.assertEquals(expectedText, firstMenuItem.getText().trim());
    }

    @When("User click on {string}")
    public void click_on_link(String linkText) {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
        link.click();
    }

    @Then("the page header should be {string}")
    public void verify_page_header(String expectedHeader) {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        Assert.assertEquals(expectedHeader, header.getText().trim());
    }
    @When("User navigates and clicks on {string}")
    public void userNavigatesAndClicksOn(String linkText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement utbildningarLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='nav-utbildningar']")));
        utbildningarLink.click();
    }

    @When("User clicks on {string} link")
    public void userClicksOnLink(String linkText) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement stockholmLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='course-filter-bar']/div/div[1]/a[2]")));
        Thread.sleep(10000); // Ideally, replace this with a more efficient wait if possible
        stockholmLink.click();
    }

    @Then("Page header should be {string}")
    public void pageHeaderShouldBe(String expectedHeaderText) {
        WebElement pageHeader = driver.findElement(By.xpath("//h1[normalize-space()='IT-utbildningar Stockholm']"));
        String actualText = pageHeader.getText().trim();
        Assert.assertEquals(expectedHeaderText, actualText);
        System.out.println("Assertion passed: Page header is as expected.");
    }





    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        driver.get("https://www.ithsdistans.se/login/index.php");
    }

    @When("User enter username {string}")
    public void user_enter_username(String username) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.sendKeys(username);
    }

    @And("User enter password {string}")
    public void user_enter_password(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
    }

    @And("User click the login button")
    public void user_click_the_login_button() {
        WebElement loginButton = driver.findElement(By.className("loginbtn"));
        loginButton.click();
    }

    @Then("User should see an error message {string}")
    public void user_should_see_an_error_message(String expectedMessage) {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginerrormessage")));
        String actualMessage = errorMessage.getText().trim();
        Assert.assertEquals(expectedMessage, actualMessage, "Invalid login, please try again");
    }

    @When("User locates and clicks on the {string} link")
    public void user_locates_and_clicks_on_the_link(String linkText) {
        WebElement kontaktLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-kontakt")));
        kontaktLink.click();
    }

    @Then("User should see the contact email {string}")
    public void user_should_see_the_contact_email(String expectedEmail) {
        WebElement contactEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.card-employees__email")));
        Assert.assertTrue(contactEmail.isDisplayed());
        Assert.assertEquals(expectedEmail, contactEmail.getText().trim(), "marcus@iths.se");
    }

    @Then("User should see the contact phone number {string}")
    public void user_should_see_the_contact_phone_number(String expectedPhone) {
        WebElement phoneLink = driver.findElement(By.cssSelector("a.card-employees__phone"));
        Assert.assertEquals(expectedPhone, phoneLink.getText().trim(), "070-5169513");
    }

    @When("User click on the {string} link")
    public void user_clicks_on_the_link(String linkText) {
        WebElement nyheterLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#nav-nyheter a")));
        nyheterLink.click();
    }

    @Then("User should be redirected to the news page")
    public void user_should_be_redirected_to_the_news_page() {
        wait.until(ExpectedConditions.urlContains("/nyheter/"));
    }

    @And("User scroll to the {string} news title")
    public void user_scroll_to_the_news_title(String newsTitle) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement scrumMasterTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[4]/a/div[2]/h6")));
        js.executeScript("arguments[0].scrollIntoView(true);", scrumMasterTitle);
    }

    @Then("User should see the news title {string}")
    public void user_should_see_the_news_title(String expectedTitle) {
        WebElement scrumMasterTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[4]/a/div[2]/h6")));
        String actualText = scrumMasterTitle.getText().trim();
        Assert.assertEquals(expectedTitle, actualText, "Scrum Master Lön");
    }

    @When("User navigates and clicks on the {string} link")
    public void user_navigates_and_clicks_on_the_link(String linkText) {
        WebElement ItShopLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#nav-ithshop")));
        ItShopLink.click();
    }

    @When("User closes any popup")
    public void user_closes_any_popup() {
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.cky-banner-btn-close")));
        closeButton.click();
    }

    @When("User scrolls to the {string} category")
    public void user_scrolls_to_the_category(String category) {
        WebElement hoodiesElement = driver.findElement(By.xpath("//h3/span[text()='Hoodies']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", hoodiesElement);
    }

    @When("User clicks on {string} button")
    public void user_clicks_on_button(String buttonText) {
        WebElement handlaHarButton = driver.findElement(By.cssSelector("a.fl-button"));
        handlaHarButton.click();
    }

    @Then("the page should display the product {string}")
    public void the_page_should_display_the_product(String productName) {
        WebElement productLink = driver.findElement(By.xpath("//a[@href='https://www.ithshop.se/produkt/iths-tygkasse-lila/']//h2[contains(text(),'ITHS tygkasse lila')]"));
        Assert.assertTrue("The product link for '" + productName + "' should be present on the page", productLink.isDisplayed());
        System.out.println("All assertions passed successfully!");

    }

    @When("User hovers over the {string} menu")
    public void user_hovers_over_the_menu(String menuText) {
        WebElement härDuAnsökarLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#nav-hurduansker")));
        Actions actions = new Actions(driver);
        actions.moveToElement(härDuAnsökarLink).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#nav-hurduansker ul.sub-menu")));
    }

    @When("User clicks on the {string} link")
    public void user_clicks_on_the_link_H(String linkText) {
        WebElement viktigaDatumLink = driver.findElement(By.id("nav-viktigadatum"));
        viktigaDatumLink.click();
    }
    @Then("the page header should display {string}")
    public void the_page_header_should_display(String expectedHeaderText) {
        WebElement heroHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.page-item__hero-content h1")));
        String actualHeaderText = heroHeader.getText().trim();
        Assert.assertEquals(expectedHeaderText, actualHeaderText, "Viktiga datum");
        System.out.println("Assertion passed: Header text matches!");
    }




}
