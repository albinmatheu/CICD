package framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	//public ConfirmationPage(WebDriver driver) {
		//super(driver);
		//this.driver=driver;
		//PageFactory.initElements(driver, this);
	//}
public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);

	}
@FindBy (css=".hero-primary")
WebElement message;



public  void  orderConfirmation(String actualMessage)
{
	String confirmationMessage= message.getText();
	verification(confirmationMessage,actualMessage);
	//return confirmationMessage;


}

}
