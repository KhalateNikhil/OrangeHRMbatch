package PageLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseLayer.BaseClass;
import UtilityLayer.Wait;

public class PimPage extends BaseClass{
	
	@FindBy(xpath="//span[text()='PIM']")
	private WebElement clbtn;
	
	@FindBy(xpath="//a[text()='Add Employee']")
	private WebElement ademp;
	
	@FindBy(name="firstName")
	private WebElement fname;
	
	@FindBy(name="lastName")
	private WebElement lname;

	@FindBy(xpath="//button[text()=' Save ']")
	private WebElement svbutton;
	
	
	public PimPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickonPimPage()
	{
		Wait.click(clbtn);
	}
	
	
	public void clickonaddEmp()
	{
		Wait.click(ademp);
	}
	
	public void addEmpFunctionality(String fisrtname,String lastname)
	{
		Wait.sendKeys(fname, fisrtname);
		Wait.sendKeys(lname, lastname);
		Wait.click(svbutton);
	}
	
	
	
}
