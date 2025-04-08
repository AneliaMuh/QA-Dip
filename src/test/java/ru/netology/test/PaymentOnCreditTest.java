package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPaymentPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentOnCreditTest {

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
    @DisplayName("Should be approved card payment on credit") // Должна быть одобрена оплата картой в кредит
    void shouldApprovePaymentInCreditWithValidCard() {
        var cardInfo = DataHelper.getValidCardInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Should be declined payment in credit") // Должен отклонить платеж в кредит
    void  shouldDeclinedPaymentInCreditWithDeclinedCard() {
        var cardInfo = DataHelper.getValidCardInfoCredit();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Should be an error when paying on credit with an unknown card") // Должна быть ошибка при оплате в кредит неизвестной картой
    void cardNumberNotRegisteredPaymentOnCredit() {
        var cardInfo =  DataHelper.getUnknownCardInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeErrorNotification();
    }

    @Test
    @DisplayName("Should be an error when paying card number is too short when paying on credit") // Должна быть ошибка, когда номер платежной карты слишком короткий при оплате в кредит
    void cardNumberTooShortPaymentOnCredit() {
        var cardInfo = DataHelper.getCardWithShortNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeCardNumberError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error when card number is empty when paying on credit") // Должна отображаться ошибка, если номер карты пуст при оплате в кредит
    void cardNumberIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getEmptyCardNumberInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeCardNumberError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the month field is empty when paying on credit") // При оплате в кредит должно отображаться сообщение об ошибке, если поле месяца пустое.
    void monthIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getEmptyMonthInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeMonthError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the month field is filled with a single digit on paying on credit") // Если при оплате в кредит поле месяца заполнено одной цифрой, должно отображаться сообщение об ошибке.
    void monthFilledOneNumberPaymentOnCredit() {
        var cardInfo = DataHelper.getMonthOneNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeMonthError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error when month is 00 when paying on credit") // Должна отображаться ошибка, когда месяц равен 00 при оплате в кредит
    void monthFilledIs00PaymentOnCredit() {
        var cardInfo = DataHelper.getMonthWithZeros();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should show error when entering invalid month when paying on credit") // Должна отображаться ошибка при вводе неверного месяца при оплате в кредит
    void filledInvalidMonthPaymentOnCredit() {
        var cardInfo = DataHelper.getMonthUnreal();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should show error when entering the past year when payment on credit") // Должна отображаться ошибка при вводе прошлого года при оплате в кредит
    void enteringThePastYearPaymentOnCredit() {
        var cardInfo = DataHelper.getInvalidPastYear();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeYearError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Should show error if the year field is empty  when paying on credit") // должна отображаться ошибка, если поле года пустое при оплате в кредит
    void fieldYearIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getEmptyYearInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeYearError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the year field is filled with a single digit on paying on credit") // При оплате в кредит должно отображаться сообщение об ошибке, если поле года заполнено одной цифрой.
    void yearFilledOneNumberPaymentOnCredit() {
        var cardInfo = DataHelper.getYearOneNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeYearError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled numbers on paying on credit") // Должна отображаться ошибка, если поле держателя заполнено цифрами при оплате в кредит
    void holderFilledNumbersPaymentOnCredit() {
        var cardInfo = DataHelper.getHolderNumbers();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled in Cyrillic on paying on credit") // При оплате в кредит должно отображаться сообщение об ошибке, если поле держателя заполнено кириллицей.
    void holderFilledCyrillicPaymentOnCredit() {
        var cardInfo = DataHelper.getCyrillicHolder();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled in special chars on paying on credit") // При оплате в кредит должно отображаться сообщение об ошибке, если поле держателя заполнено специальными символами.
    void holderFilledSpecialCharsPaymentOnCredit() {
        var cardInfo = DataHelper.getHolderSpecialChars();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is empty  when paying on credit") // При оплате в кредит должно отображаться сообщение об ошибке, если поле держателя пустое.
    void fieldHolderIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getEmptyHolderInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeHolderError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the CVV field is empty on paying on credit") // Должна отображаться ошибка, если поле CVV пустое при оплате в кредит.
    void fieldCVVIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getEmptyCVVInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeCVVError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the CVV is short on paying on credit") // Должна отображаться ошибка, если CVV короткое при оплате в кредит
    void fieldCVVIsShortPaymentOnCredit() {
        var cardInfo = DataHelper.getInvalidCVVShort();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeCVVError("Неверный формат");
    }

}
