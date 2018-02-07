package ru.levelup.julia_kalujnaya.qa.homework_6.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.elements.CategoriesBlock;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.ConverterHelper;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

import java.util.List;

/**
 * Класс Page Object, представляющий страницу с результатами поиска:
 * foundProductsLinks - список ссылок на найденные товары
 * foundProductsPrices - список цен на найденные товары
 * priceTypes - список характеристик найденных товаров по типу цены (ставка или "Купить сейчас")
 * sortDropDown - выпадающее меню с видами сортировки
 * shippingDetails - список с информацией о доставке найденных товаров
 * category - категория товаров
 * foundProductsAmount - число результатов поиска в верхнем левом углу
 * isNewProduct - список характеристик найденных товаров по новизне ("Совершенно новый" или др.)
 * categoriesBlock - блок с меню категорий товаров
 *
 * @author Юлия Калюжная
 */
public class SearchResultsPage extends AbstractPage {
    private static final String BUY_NOW = "Купить сейчас";
    private static final String NEW = "Совершенно новый";
    private static final String FREE_SHIPPING = "Бесплатная международная доставка";
    private static final String BID = "ставок";
    private static final String PRICE_RANGE = "до";

    @FindBy(xpath = "//*[@class=\"lvtitle\"]/a")
    private List<WebElement> foundProductsLinks;

    @FindBy(className = "lvprice")
    private List<WebElement> foundProductsPrices;

    @FindBy(className = "lvformat")
    private List<WebElement> priceTypes;

    @FindBy(xpath = "//*[@id=\"DashSortByContainer\"]/ul[1]/li/div/a")
    private WebElement sortDropDown;

    @FindBy(xpath = "//*[@class=\"ship\"]/span")
    private List<WebElement> shippingDetails;

    @FindBy(className = "cat-link")
    private WebElement category;

    @FindBy(className = "rcnt")
    private WebElement foundProductsAmount;

    @FindBy(className = "lvsubtitle")
    private List<WebElement> isNewProduct;

    private CategoriesBlock categoriesBlock;

    public SearchResultsPage(WebDriverHelper helper) {
        super(helper);
        categoriesBlock = new CategoriesBlock(helper);
    }

    /**
     * Выполняет переход на страницу товара по его номеру в списке найденных товаров (нумерация начиная с 1)
     * @return Возвращает страницу товара
     */
    public ProductPage viewFoundProduct(int productNumber) {
        helper.click(foundProductsLinks.get(productNumber - 1));
        return new ProductPage(helper);
    }

    /**
     * Возвращает цену товара по его номеру в списке найденных товаров (при этом цены, относящиеся к ставкам, исключаются;
     * если для товара был указан диапазон цен, то берётся нижняя граница цены)
     * @param productNumber - номер товара в списке найденных
     * @return Возвращает цену заданного товара
     */
    public float getProductPrice(int productNumber) {
        int productCount = 0;
        int i = 0;
        for (i = 0; i < priceTypes.size(); i++) {
            if (! priceTypes.get(i).getText().contains(BID)) {
                productCount++;
            }
            if (productCount == productNumber)
                break;
        }
        String productPrice = foundProductsPrices.get(i).getText();
        return ConverterHelper.convertPriceToNumber(productPrice.substring(0, productPrice.indexOf('.') + 1));
    }

    /**
     * Проверяет, является ли стоимость заданного товара диапазоном цен (нумерация начиная с 1)
     * @param productNumber - номер товара в списке найденных
     * @return Возвращает true, если указан диапазон цен
     */
    public boolean isPriceRange(int productNumber) {
        return foundProductsPrices.get(productNumber - 1).getText().contains(PRICE_RANGE);
    }

    /**
     * Возвращает стоимость доставки товара по номеру товара в списке найденных (нумерация начиная с 1)
     * @param productNumber - номер товара в списке найденных
     * @return Возвращает цену заданного товара
     */
    public float getProductShippingPrice(int productNumber) {
        String shipInfo = shippingDetails.get(productNumber - 1).getText();
        if (shipInfo.equals(FREE_SHIPPING))
            return 0;
        else
            return ConverterHelper.convertPriceToNumber(shipInfo.substring(2, shipInfo.indexOf('.') + 1));
    }

    /**
     * Возвращает стоимость товара с доставкой по номеру товара в списке найденных
     * @param productNumber - номер товара в списке найденных
     * @return Возвращает цену заданного товара
     */
    public float getProductTotalPrice(int productNumber) {
        return getProductPrice(productNumber) + getProductShippingPrice(productNumber);
    }

    /**
     * Выполняет клик на заданный элемент меню в блоке подкатегорий товаров
     * @param menuItemName - название элемента в меню
     * @return Возвращает ту же страницу
     */
    public SearchResultsPage clickOnSubcategoriesMenuItem(String menuItemName) {
        categoriesBlock.clickOnMenuItem(menuItemName);
        return this;
    }

    /**
     * Выбирает заданный вид сортировки товаров
     * @param sortName - вид сортировки
     * @return Возвращает ту же страницу
     */
    public SearchResultsPage selectSorting(final String sortName) {
        helper.moveToElement(sortDropDown);
        helper.clickOnItem(sortName);
        return this;
    }

    /**
     * Проверяет, что названия всех найденных товаров содержат заданный текст
     * @param text - заданный текст
     * @return Возвращает true, если названия всех товаров содержат текст
     */
    public boolean checkProductNames(String text) {
        boolean result = true;
        int amount = Math.min(Integer.parseInt(foundProductsAmount.getText()), foundProductsLinks.size());
        for (int i = 1; i <= amount; i++) {
            result = result && foundProductsLinks.get(i - 1).getText().contains(text);
        }
        return result;
    }

    /**
     * Проверяет, что название категории товаров совпадает с заданным
     * @param text - заданное значение
     * @return Возвращает true в случае совпадения
     */
    public boolean checkCategory(String text) {
        return category.getText().equals(text);
    }

    /**
     * Проверяет, что цены всех найденных товаров находятся в заданном диапазоне
     * @param minPrice - минимальная цена
     * @param maxPrice - максимальная цена
     * @return Возвращает true, если цены всех товаров находятся в заданном диапазоне
     */
    public boolean checkProductPrices(float minPrice, float maxPrice) {
        boolean result = true;
        int amount = Math.min(Integer.parseInt(foundProductsAmount.getText()), foundProductsLinks.size());
        for (int i = 1; i <= amount; i++) {
            if (!isPriceRange(i)) {
                result = result && (getProductPrice(i) >= minPrice) && (getProductPrice(i) <= maxPrice);
            }
        }
        return result;
    }

    /**
     * @return Возвращает true, если все найденные товары имеют характеристику "Купить сейчас"
     */
    public boolean checkBuyNow() {
        return checkElement(priceTypes, BUY_NOW);
    }

    /**
     * @return Возвращает true, если все найденные товары имеют характеристику "Совершенно новый"
     */
    public boolean checkNewProduct() {
        return checkElement(isNewProduct, NEW);
    }

    /**
     * @return Возвращает true, если все найденные товары имеют характеристику "Бесплатная международная доставка"
     */
    public boolean checkFreeShipping() {
        return checkElement(shippingDetails, FREE_SHIPPING);
    }

    /**
     * Проверяет, что все найденные товары имеют заданную характеристику
     * @param elements - список элементов со значениями данной характеристики
     * @param text - заданное значение характеристики
     * @return Возвращает true, если все найденные товары имеют заданную характеристику
     */
    private boolean checkElement(List<WebElement> elements, String text) {
        boolean result = true;
        int amount = Math.min(Integer.parseInt(foundProductsAmount.getText()), foundProductsLinks.size());
        for (int i = 1; i <= amount; i++) {
            result = result && elements.get(i - 1).getText().contains(text);
        }
        return result;
    }
}