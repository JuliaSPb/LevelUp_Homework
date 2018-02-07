package ru.levelup.julia_kalujnaya.qa.homework_6.service;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.AbstractPage;

import javax.annotation.Nullable;
import java.util.List;
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
        driver.manage().timeouts().pageLoadTimeout(DriverParams.PAGE_LOAD_TIMEOUT_S, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(DriverParams.TIMEOUT_S, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, DriverParams.WAIT_SEC);
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
     * Проверяет, является ли заданный элемент видимым
     * @param element - элемент для проверки
     */
    private void isElementVisible(final WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Проверяет, является ли заданный элемент кликабельным
     * @param element - элемент для проверки
     */
    private void isElementClickable(final WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
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
     * Вводит заданную строку в заданное текстовое поле
     * @param textField - текстовое поле
     * @param text - заданная строка
     */
    public void sendKeysToTextField(WebElement textField, String text) {
        textField.clear();
        textField.sendKeys(text);
        wait.until(new ExpectedCondition<Boolean>() {
            @Nullable
            public Boolean apply(@Nullable WebDriver webDriver) {
                return text.equalsIgnoreCase(textField.getAttribute("value"));
            }
        });
    }

    /**
     * Перемещает курсор на заданный элемент
     * @param element - элемент
     */
    public void moveToElement(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    /**
     * Выполняет клик на элементе меню с заданным названием
     * @param parent - родительский элемент (меню)
     * @param menuItemName - название элемента меню
     */
    public void clickOnMenuItem(WebElement parent, String menuItemName) {
        click(parent);
        clickOnItem(menuItemName);
    }

    /**
     * Выполняет клик на ссылке, текст которой содержит заданное название
     * @param itemName - название ссылки
     */
    public void clickOnItem(String itemName) {
        WebElement menuItem = driver.findElement(By.xpath("//a[contains(., '" + itemName + "')]"));
        wait.until(ExpectedConditions.visibilityOf(menuItem));
        click(menuItem);
    }

    /**
     * Выполняет клики на всех элементах меню, множество которых задано с помощью XPath
     * @param parent - родительский элемент (меню)
     * @param menuItemsXPath - XPath для поиска элементов в меню
     */
    public void clickOnAllMenuItems(WebElement parent, String menuItemsXPath) {
        int i = 0;
        List<WebElement> menuItems;
        do {
            click(parent);
            menuItems = driver.findElements(By.xpath(menuItemsXPath));
            wait.until(ExpectedConditions.visibilityOf(menuItems.get(i)));
            click(menuItems.get(i));
            i++;
        } while (i < menuItems.size());
    }
}