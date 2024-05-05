package StepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

public class LoginStepDefinition {
	private WebDriver driver;

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

	@Given("I am on the SauceDemo login page")
	public void iAmOnTheLoginPage() {
		driver.get("https://www.saucedemo.com/");
	}

	@When("I enter username {string} and password {string}")
	public void iEnterUsernameAndPassword(String username, String password) {
		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
	}

	@And("I click the login button")
	public void iClickTheLoginButton() {
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();
	}

	@Then("I should be on the inventory page")
	public void iShouldBeOnTheInventoryPage() {
		assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
	}

	@Then("I should see an error message")
	public void iShouldSeeAnErrorMessage() {
		WebElement errorElement = driver.findElement(By.cssSelector("[data-test='error']"));
		assertTrue(errorElement.isDisplayed());
	}

	@And("User clicks the logout button")
	public void user_clicks_the_logout_button() {
		WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
		menuButton.click();
		WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
		logoutButton.click();
	}

	@Then("The user should be logged out successfully")
	public void the_user_should_be_logged_out_successfully() {
		WebElement loginButton = driver.findElement(By.id("login-button"));
		assertTrue(loginButton.isDisplayed());
	}

	@Then("The login page should be displayed")
	public void the_login_page_should_be_displayed() {
		WebElement usernameField = driver.findElement(By.id("user-name"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("login-button"));

		assertTrue(usernameField.isDisplayed());
		assertTrue(passwordField.isDisplayed());
		assertTrue(loginButton.isDisplayed());
	}
}