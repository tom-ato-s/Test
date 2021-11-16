package org.example;

//будет содержать локацию элементов страницы логина и методы для взаимодействия с этими элементами.

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    //Для того, чтобы аннотация @FindBy заработала, необходимо использовать класс PageFactory.
    // Для этого создадим конструктор и передадим ему в качестве параметра объект Webdriver:

    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


//  нашли элемент на странице и назвали его loginField
    @FindBy(xpath = "//*[contains(@id, 'passp-field-login')]")
    private WebElement loginField;


    @FindBy(xpath = "//*[contains(text(), 'Войти')]")  //Кнопка «Войти»:
    private WebElement loginBtn;

    @FindBy(xpath = "//*[contains(@id, 'passp-field-passwd')]")  //Поле ввода пароля:
    private WebElement passwdField;

    public void inputLogin(String login) { //Метод ввода логина:
        loginField.sendKeys(login);
    }

    public void inputPasswd(String passwd) { //Метод ввода пароля:
        passwdField.sendKeys(passwd);
    }


    public void clickLoginBtn() {//Метод нажатия кнопки входа:
    loginBtn.click();
    }

}
