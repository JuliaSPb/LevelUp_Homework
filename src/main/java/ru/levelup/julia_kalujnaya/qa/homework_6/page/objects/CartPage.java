package ru.levelup.julia_kalujnaya.qa.homework_6.page.objects;

import ru.levelup.julia_kalujnaya.qa.homework_6.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.elements.Header;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

/**
 * Класс Page Object, представляющий страницу корзины на EBay
 *
 * @author Юлия Калюжная
 */
public class CartPage extends AbstractPage {
    private Header header;

    public CartPage(WebDriverHelper driverHelper) {
        super(driverHelper);
        header = new Header(helper);
    }

    /**
     * @return Возвращает количество товаров в корзине (если были добавлены)
     */
    public int checkCart() {
        return header.checkCart();
    }
}