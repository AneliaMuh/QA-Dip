package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPaymentPage;
import ru.netology.page.CardPaymentPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentByCardTest {

    @BeforeAll
     static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
     static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
     void setUp() {
        open("http://localhost:8080");
    }

    @AfterEach
    void shouldCleanBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Should be approved card payment") // Должен быть одобрен платеж по карте
     void shouldApprovePaymentWithValidCard() {
        var cardInfo = DataHelper.getValidCardInfo();
        var paymentPage = new PaymentPage();
        paymentPage.verifyHeadingIsVisible();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCardPaymentStatus());
    }

    @Test
    @DisplayName("Should be declined payment") // Платеж должен быть отклонен
    void shouldDeclinedPaymentWithDeclinedCard() {
        var cardInfo = DataHelper.getValidCardInfoCredit();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCardPaymentStatus());
    }

    @Test
    @DisplayName("Should be an error when paying with an unknown card") // Должна быть ошибка при оплате неизвестной картой
    void cardNumberNotRegistered() {
        var cardInfo =  DataHelper.getUnknownCardInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeErrorNotification();
    }

    @Test
    @DisplayName("Should be an error when paying card number is too short") // Должна быть ошибка, если номер платежной карты слишком короткий
    void cardNumberIsTooShort() {
        var cardInfo = DataHelper.getCardWithShortNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeCardNumberError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error when card number is empty") // Должна отображаться ошибка, если номер карты пуст
    void fieldCardNumberIsEmpty() {
        var cardInfo = DataHelper.getEmptyCardNumberInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeCardNumberError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the month field is empty") // Если поле месяца пустое, должно отображаться сообщение об ошибке.
    void fieldMonthIsEmpty() {
        var cardInfo = DataHelper.getEmptyMonthInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeMonthError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the month field is filled with a single digit") // Должна отображаться ошибка, если поле месяца заполнено одной цифрой.
    void monthFilledOneNumber() {
        var cardInfo = DataHelper.getMonthOneNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeMonthError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error when month is 00") // Должна выдавать ошибку, если месяц равен 00
    void monthFilledIs00() {
        var cardInfo = DataHelper.getMonthWithZeros();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should show error when entering invalid month") // Должна выдавать ошибку при вводе неверного месяца
    void filledInvalidMonth() {
        var cardInfo = DataHelper.getMonthUnreal();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should show error when entering the past year when card payment") // При вводе прошлого года при оплате картой должна отображаться ошибка
    void enteringThePastYear() {
        var cardInfo = DataHelper.getInvalidPastYear();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeYearError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Should show error if the year field is empty") // Если поле года пустое, должна отображаться ошибка.
    void fieldYearIsEmpty() {
        var cardInfo = DataHelper.getEmptyYearInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeYearError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the year field is filled with a single digit") // Должна отображаться ошибка, если поле года заполнено одной цифрой.
    void yearFilledOneNumber() {
        var cardInfo = DataHelper.getYearOneNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeYearError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled numbers") // Должна отображаться ошибка, если поле держателя заполнено числами
    void holderFilledNumbers() {
        var cardInfo = DataHelper.getHolderNumbers();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled in Cyrillic") // Должна отображаться ошибка, если поле владельца заполнено кириллицей.
    void holderFilledCyrillic() {
        var cardInfo = DataHelper.getCyrillicHolder();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeHolderError("Неверный формат");

    }

    @Test
    @DisplayName("Should show error if the holder field is filled in special chars") // Должна отображаться ошибка, если поле держателя заполнено специальными символами.
    void holderFilledSpecialChars() {
        var cardInfo = DataHelper.getHolderSpecialChars();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is empty") // Должна отображаться ошибка, если поле держателя пустое.
    void fieldHolderIsEmpty() {
        var cardInfo = DataHelper.getEmptyHolderInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeHolderError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the CVV field is empty") // Если поле CVV пустое, должна отображаться ошибка.
    void fieldCVVIsEmpty() {
        var cardInfo = DataHelper.getEmptyCVVInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeCVVError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the CVV is short") // Если CVV короткий, должна отображаться ошибка.
    void fieldCVVIsShort() {
        var cardInfo = DataHelper.getInvalidCVVShort();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeCVVError("Неверный формат");
    }
}


























