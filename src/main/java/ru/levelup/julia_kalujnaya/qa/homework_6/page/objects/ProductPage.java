package ru.levelup.julia_kalujnaya.qa.homework_6.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.ConverterHelper;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

import java.util.List;

/**
 * Класс Page Object, представляющий страницу товара на EBay:
 * priceInRoubles - цена товара в рублях
 * selects - список выпадающих списков для выбора параметров товара (если есть)
 * addToCartButton - кнопка "Добавить в корзину"
 *
 * @author Юлия Калюжная
 */
public class ProductPage extends AbstractPage {
    @FindBy(id = "convbidPrice")
    private WebElement priceInRoubles;

    @FindBy(className = "msku-sel")
    private List<WebElement> selects;

    @FindBy(id = "isCartBtn_btn")
    private WebElement addToCartButton;

    public ProductPage(WebDriverHelper driverHelper) {
        super(driverHelper);
    }

    /**
     * @return Возвращает цену данного товара в рублях
     */
    public float getPriceInRoubles() {
        return ConverterHelper.convertPriceToNumber(priceInRoubles.getText());
    }

    /**
     * Проходит по всем выпадающим спискам с параметрами товара и выбирает в них пункт, отличный от "Выбрать"
     * (для возможности добавления товара в корзину)
     * @return Возвращает ту же страницу
     */
    public ProductPage selectAllSelectFields() {
        for (WebElement select : selects) {
            select.findElements(By.tagName("option")).get(1).click();
        }
        return this;
    }

    /**
     * Выполняет клик на кнопку "Добавить в корзину"
     * @return Возвращает страницу корзины
     */
    public CartPage clickAddToCartButton() {
        helper.click(addToCartButton);
        return new CartPage(helper);
    }
}