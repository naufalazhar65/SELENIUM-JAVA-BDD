package StepDefinitions;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutStepDefinition {

	public WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/naufalazhar/.cache/selenium/chromedriver/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("disable-gpu");

		driver = new ChromeDriver(options);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}
	
	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	@Given("User has logged in with valid credentials")
	public void user_has_logged_in_with_valid_credentials() {
		driver.get("https://www.saucedemo.com/");

		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("login-button"));

		usernameField.sendKeys("standard_user");
		passwordField.sendKeys("secret_sauce");

		loginButton.click();
	}

	@When("User adds items to cart and goes to checkout")
	public void user_adds_items_to_cart_and_goes_to_checkout() {
		WebElement addToCartButton = driver.findElement(By.cssSelector(".inventory_item button"));
		addToCartButton.click();

		WebElement cartButton = driver.findElement(By.cssSelector(".shopping_cart_container"));
		cartButton.click();

		WebElement checkoutButton = driver.findElement(By.cssSelector(".checkout_button"));
		checkoutButton.click();
	}

	@And("User enters checkout information and completes checkout")
	public void user_enters_checkout_information_and_completes_checkout() {
		enterCheckoutInformation("Naufal", "Azhar", "12345");

		WebElement finishButton = driver.findElement(By.id("finish"));
		finishButton.click();
	}

	@When("User enters incomplete checkout information and tries to complete checkout")
	public void user_enters_incomplete_checkout_information_and_tries_to_complete_checkout() {
		enterCheckoutInformation("John", "Doe", "");

		WebElement finishButton = driver.findElement(By.cssSelector(".checkout_buttons .btn_action.cart_button"));
		finishButton.click();
	}

	@Then("User should see checkout complete message")
	public void user_should_see_checkout_complete_message() {
		WebElement completeMessage = driver.findElement(By.cssSelector(".complete-header"));
		assertEquals("Thank you for your order!", completeMessage.getText());
	}

	@Then("User should see error message indicating missing information")
	public void user_should_see_error_message_indicating_missing_information() {
		WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container"));
		assertEquals("Error: Postal Code is required", errorMessage.getText());
	}

	private void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
		WebElement firstNameField = driver.findElement(By.id("first-name"));
		WebElement lastNameField = driver.findElement(By.id("last-name"));
		WebElement postalCodeField = driver.findElement(By.id("postal-code"));
		WebElement continueButton = driver.findElement(By.cssSelector(".checkout_buttons .btn_primary.cart_button"));

		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		postalCodeField.sendKeys(postalCode);

		continueButton.click();
	}
}
