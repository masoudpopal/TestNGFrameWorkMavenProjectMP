package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginPage extends CommonMethods {

    //This process of having the objects here is called (object repository)

    @FindBy(id="txtUsername")
    public WebElement usernameBox;

    @FindBy(id="txtPassword")
    public WebElement passwordBox;

    @FindBy(id="btnLogin")
    public WebElement loginBtn;

    @FindBy(id="spanMessage")
    public WebElement errorMessage;

    // to initialize the above elements we need to make a constructor as follows!
    public LoginPage(){  //this is a constructor / you can also define your webdriver inside the braces, so you don't need to extend.LoginPage(Webdriver driver).
        PageFactory.initElements(driver,this); // we have to extend the class otherwise this will give error


    }
    public void login(String username, String password){
        sendText(usernameBox, username);
        sendText(passwordBox, password);
        click(loginBtn);
    }
}
