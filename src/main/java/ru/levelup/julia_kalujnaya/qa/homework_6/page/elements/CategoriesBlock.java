package ru.levelup.julia_kalujnaya.qa.homework_6.page.elements;

import ru.levelup.julia_kalujnaya.qa.homework_6.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

/**
 * Класс Page Object, представляющий блок с категориями товаров
 *
 * @author Юлия Калюжная
 */
public class CategoriesBlock extends AbstractPage {
    public CategoriesBlock(WebDriverHelper driverHelper) {
        super(driverHelper);
    }

    /**
     * Выполняет клик на заданный элемент меню категорий товаров
     * @param menuItemName - название элемента в меню
     */
    public void clickOnMenuItem(String menuItemName) {
        helper.clickOnItem(menuItemName);
    }
}