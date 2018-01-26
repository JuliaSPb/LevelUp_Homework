package ru.levelup.julia_kalujnaya.qa.homework_5.service;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.AbstractPage;

import java.util.concurrent.TimeUnit;

/**
 * Вспомогательный класс, реализующий работу с WebDriver (браузер Chrome)
 *
 * @author Юлия Калюжная
 */
public class WebDriverHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    public WebDriverHelper() {
        ChromeDriverManager.getInstance().setup();
    }

    public WebDriverWait getWait() {
        return wait;
    }

    /**
     * Создаёт WebDriver и объект явного ожидания, задаёт таймауты
     */
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Завершает работу WebDriver
     */
    public void quitDriver() {
        driver.quit();
    }

    /**
     * Инициализирует элементы страницы с помощью PageFactory
     * @param page - страница (Page Object)
     */
    public void initPageFactoryElements(AbstractPage page) {
        PageFactory.initElements(driver, page);
    }

    /**
     * Переходит по заданному URL
     * @param URL - адрес для перехода
     */
    public void getToURL(String URL) {
        driver.get(URL);
    }

    /**
     * Ищет элемент страницы
     * @param by - условие поиска
     * @return Возвращает найденный элемент
     */
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    /**
     * @return Возвращает заголовок текущей страницы
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * Проверяет, является ли заданный элемент видимым
     * @param element - элемент для проверки
     */
    private void isElementVisible(final WebElement element) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Проверяет, является ли заданный элемент кликабельным
     * @param element - элемент для проверки
     */
    private void isElementClickable(final WebElement element) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Выполняет клик на заданный элемент с проверкой исключений
     * @param element - элемент
     */
    public void click(final WebElement element) {
        try {
            isElementVisible(element);
            isElementClickable(element);
            element.click();
        } catch (NoAlertPresentException e) {
            Alert alert = driver.switchTo().alert();
            if (alert != null) {
                alert.dismiss();
            }
        } catch (StaleElementReferenceException e) {
            click(element);
        }
    }

    /**
     * Переключает фокус на 2-ю вкладку из двух имеющихся
     */
    public void switchToSecondTab() {
        String firstTab = driver.getWindowHandle();
        String secondTab = "";
        for (String window : driver.getWindowHandles()){
            if (!window.equals(firstTab)){
                secondTab = window;
            }
        }
        driver.switchTo().window(secondTab);
    }

    /**
     * Перемещает курсор на заданный элемент
     * @param element - элемент
     */
    public void moveToElement(WebElement element) {
        new Actions(driver).moveToElement(element).perform();

    }
}