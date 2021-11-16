package org.example;

https://habr.com/ru/post/502292/
//Пишем автотест с использованием Selenium Webdriver, Java 8 и паттерна Page Object


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    public static LoginPage loginPage;
    public static ProfilePage profilePage;
    public static WebDriver driver;

    @BeforeClass  //метод будет выполняться 1 раз до выполнения всех тестов
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.firefox.driver", ConfProperties.getProperty("firefoxdriver"));
        //создание экземпляра драйвераdriver = new FirefoxDriver();
        //окно разворачивается на полный экран
        driver.manage().window().maximize(); // отображение текста во весь экран
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // неявное ожидание может быть использовано при каждом вызове поиска елемента  (10 секунд) и шагом в 500 мс
        driver.get("https://passport.yandex.ru/auth");
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("loginpage"));

        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }
     @Test
    public  void loginTesr() {
         //значение login/password берутся из файла настроек по аналогии с chromedriver
//и loginpage
//вводим логин
         loginPage.inputLogin(ConfProperties.getProperty("login"));
         //нажимаем кнопку входа
         loginPage.clickLoginBtn();
         //вводим пароль
         loginPage.inputPasswd(ConfProperties.getProperty("password"));
         //нажимаем кнопку входа
         loginPage.clickLoginBtn();
         //получаем отображаемый логин
         String user = profilePage.getUserName();
         //и сравниваем его с логином из файла настроек
         Assert.assertEquals(ConfProperties.getProperty("login"), user);
    }

    @AfterClass
    public static void tearDown() {
        profilePage.entryMenu();
        profilePage.userLogout();
        driver.quit(); }

}
