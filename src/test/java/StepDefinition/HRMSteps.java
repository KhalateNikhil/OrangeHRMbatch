package StepDefinition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import BaseLayer.BaseClass;
import PageLayer.LoginPage;
import PageLayer.PimPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HRMSteps extends BaseClass {
	private static LoginPage loginpage;
	private static String Empid;

	private static PimPage pipage;

	@Given("user is on Login Page")
	public void user_is_on_login_page() {
		BaseClass.initialization();
		String url1 = prop.getProperty("url");
		driver.get(url1);
	}

	@When("user enter valid credentails")
	public void user_enter_valid_credentails(DataTable dataTable) {

		List<List<String>> ls = dataTable.asLists();
		String uname = ls.get(0).get(0);
		String pass = ls.get(0).get(1);

		loginpage = new LoginPage();
		loginpage.loginFunctionality(uname, pass);

	}

	@When("user click on login button")
	public void user_click_on_login_button() {
		loginpage.clickfunctionality();
	}

	@When("user is on home page validate home page title")
	public void user_is_on_home_page_validate_home_page_title(DataTable dataTable) {
		String title = dataTable.asLists().get(0).get(0);
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, title);

	}

	@When("user validate home page url")
	public void user_validate_home_page_url() {

		String acturl = driver.getCurrentUrl();
		boolean a = acturl.contains("php");
		Assert.assertTrue(a);
	}

	@When("user validate home page logo")
	public void user_validate_home_page_logo(DataTable dataTable) {
		String abc = dataTable.asLists().get(0).get(0);
		// convert String into boolean
		boolean xyz = Boolean.parseBoolean(abc);
		boolean logo = driver.findElement(By.xpath("//div[@class='oxd-brand-banner']")).isDisplayed();
		Assert.assertEquals(xyz, logo);
	}

	@When("user click on pim page link")
	public void user_click_on_pim_page_link() {
		pipage = new PimPage();
		pipage.clickonPimPage();

	}

	@When("user validate user is on pim page by using url")
	public void user_validate_user_is_on_pim_page_by_using_url(DataTable dataTable) {

		String acturl1 = dataTable.asLists().get(0).get(0);
		String pimurl = driver.getCurrentUrl();
		boolean b = pimurl.contains("pim");
		Assert.assertTrue(b);

	}

	@When("user click add employee and enter firstname, lastname and click on save button")
	public void user_click_add_employee_and_enter_firstname_lastname_and_click_on_save_button(DataTable dataTable)
			throws InterruptedException {
		List<List<String>> ls = dataTable.asLists();
		for (int i = 0; i < ls.size(); i++) {
			pipage.clickonaddEmp();
			String fName = ls.get(i).get(0);
			String lName = ls.get(i).get(1);
			Thread.sleep(5000);
			pipage.addEmpFunctionality(fName, lName);
			Thread.sleep(5000);
		}

	}

	@When("user capture employee id number and user click on employeelist")
	public void user_capture_employee_id_number_and_user_click_on_employeelist() {
		Empid = driver.findElement(By.xpath("//label[text()='Employee Id']/following::input[1]")).getAttribute("value");
		driver.findElement(By.xpath("//a[text()='Employee List']")).click();
	}

	@When("user enter employee id is employee id text  box and click on search button")
	public void user_enter_employee_id_is_employee_id_text_box_and_click_on_search_button()
			throws InterruptedException {
		driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys(Empid);

		driver.findElement(By.xpath("//button[text()=' Search ']")).click();
		Thread.sleep(5000);
	}

	@When("user selected searched employee and click on delete")
	public void user_selected_searched_employee_and_click_on_delete() throws InterruptedException {
		driver.findElement(By.xpath("(//i[@class='oxd-icon bi-check oxd-checkbox-input-icon'])[2]")).click();
		driver.findElement(By.xpath("//button[text()=' Delete Selected ']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()=' Yes, Delete ']")).click();

	}

	@Then("validate employee is deleted or not")
	public void validate_employee_is_deleted_or_not() {

		driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys(Empid);

		driver.findElement(By.xpath("//button[text()=' Search ']")).click();

	}

	@When("user click on profile and click on logout button")
	public void user_click_on_profile_and_click_on_logout_button() throws InterruptedException {
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();

	}

	@AfterStep
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			String date = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
			scenario.attach(f, "image/png", date + scenario.getName());
		} else {
			byte[] f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			String date = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
			scenario.attach(f, "image/png", date + scenario.getName());
		}

	}

}
