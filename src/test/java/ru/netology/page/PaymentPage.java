package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {
    // Элементы страницы
    private SelenideElement heading = $(withText("Путешествие дня"));
    private SelenideElement buyButton = $(withText("Купить"));
    private SelenideElement creditButton = $(withText("Купить в кредит"));

    // Проверка видимости заголовка
    public void verifyHeadingIsVisible() {
        heading.shouldBe(visible); // Проверяем, что заголовок виден
    }
    // Переход на страницу оплаты дебетовой картой
    public DebitPage proceedToDebitCardPayment() {
        buyButton.click(); // Нажимаем на кнопку "Купить"
        return new DebitPage(); // Возвращаем объект страницы для дебетовой карты
    }
    // Переход на страницу оплаты кредитной картой
    public CreditPage proceedToCreditCardPayment() {
        creditButton.click(); // Нажимаем на кнопку "Купить в кредит"
        return new CreditPage(); // Возвращаем объект страницы для кредитной карты
    }
}
