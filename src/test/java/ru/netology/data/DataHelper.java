package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.util.Locale;

public class DataHelper {
    private static final Faker fakerEn = new Faker(new Locale("en"));
    private static final Faker fakerRus = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    // Генерация валидных данных
    // Валидная карта (одобренная)
    public static String getCardApproved() {
        return ("4444 4444 4444 4441");
    }
    // Отклоненная карта
    public static String getCardDeclined() {
        return ("4444 4444 4444 4442");
    }
    // Генерация валидного месяца
    public static String getValidMonth() {
        return String.format("%02d", fakerEn.number().numberBetween(1, 12));
    }
    // Генерация валидного года
    public static String getValidYear() {
        int year = LocalDate.now().plusYears(1).getYear() % 100;
        return String.format("%02d", year);
    }
    // Генерация валидного владельца
    public static String getValidHolder() {
        String fullName = fakerEn.name().fullName();
        // Оставляем только буквы, пробелы и тире
        return fullName.replaceAll("[^A-Za-zА-Яа-яёЁ\\-\\s]", "");
    }
    // Генерация валидного CVC
    public static String getValidCvc() {
        return String.format("%03d", fakerEn.number().numberBetween(100, 999));
    }
    // Метод для получения валидных данных карты
    public static CardInfo getValidCardInfo() {
        return new CardInfo(getCardApproved(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvc());
    }

    // Метод для получения валидных данных отклоненной карты
    public static CardInfo getValidCardInfoCredit() {
        return new CardInfo(getCardDeclined(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvc());
    }


    // НЕГАТИВНЫЕ СЦЕНАРИИ
    //Генерация случайного номера карты длиной 16 символов
    public static String getInvalidCardNumberInfo() {
        return fakerEn.number().digits(16);
    }
    // Метод для получения данных с незарегистрированной картой
    public static CardInfo getUnknownCardInfo() {
        return new CardInfo(getInvalidCardNumberInfo(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvc());
    }


    // Генерация короткого номера карты (меньше 16 цифр)
    public static String getShortCardNumber() {
        return fakerEn.number().digits(15);
    }
    // Метод для получения данных с коротким номером карты
    public static CardInfo getCardWithShortNumber() {
        return new CardInfo(getShortCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvc());
    }


    // Генерация невалидного месяца, который превышает 12)
    public static String getInvalidMonth() {
        return String.format("%02d", fakerEn.number().numberBetween(13, 20));
    }
    // Метод для получения данных с несуществующим месяцем
    public static CardInfo getMonthUnreal() {
        return new CardInfo(getCardApproved(), getInvalidMonth(), getValidYear(), getValidHolder(), getValidCvc());
    }


    // Генерация месяца с 00
    public static String getZeroMonth() {
        return "00";
    }
    // Метод для получения данных с вводом 00 в поле Месяц
    public static CardInfo getMonthWithZeros() {
        return new CardInfo(getCardApproved(), getZeroMonth(), getValidYear(), getValidHolder(), getValidCvc());
    }


    // Ввод одной цифры в поле месяц
    public static String getInvalidShortMonth() {
        return "1"; // одна цифра
    }
    // Метод для получения данных с одной цифрой в поле Месяц
    public static CardInfo getMonthOneNumber() {
        return new CardInfo(getCardApproved(), getInvalidShortMonth(), getValidYear(), getValidHolder(), getValidCvc());
    }


    // Генерация невалидного года (например, год, который был в прошлом)
    public static String getPastYear() {
        int year = fakerEn.number().numberBetween(1, 25);  // Генерация прошлого года
        return String.format("%02d", year % 100); // Генерация года в формате двух цифр
    }
    // Метод для получения данных с истекшего года в поле Год
    public static CardInfo getInvalidPastYear() {
        return new CardInfo(getCardApproved(), getValidMonth(), getPastYear(), getValidHolder(), getValidCvc());
    }


    // Ввод одной цифры в поле год
    public static String getInvalidShortYear() {
        return "2";
    }
    // Метод для получения данных с одной цифрой поле Год
    public static CardInfo getYearOneNumber() {
        return new CardInfo(getCardApproved(), getValidMonth(), getInvalidShortYear(), getValidHolder(), getValidCvc());
    }


    // Генерация невалидного имени кириллицей
    public static String generateNameWithCyrillic(String locale) {
        return fakerRus.name().firstName() + " " + fakerRus.name().lastName();
    }
    // Метод для получения данных с кириллицей в поле Владелец
    public static CardInfo getCyrillicHolder() {
        return new CardInfo(getCardApproved(), getValidMonth(),getValidYear(), generateNameWithCyrillic("ru"), getValidCvc());
    }


    // Генерация невалидного имени владельца с цифрами
    public static String getInvalidHolderWithNumbers() {
        return fakerEn.name().firstName() + fakerEn.number().digits(4);
    }
    // Метод для получения данных с цифровыми значениями в поле Владелец
    public static CardInfo getHolderNumbers() {
        return new CardInfo(getCardApproved(), getValidMonth(), getValidYear(), getInvalidHolderWithNumbers(),getValidCvc());
    }


    // Генерация невалидного имени владельца со спецсимволами
    public static String getInvalidHolderWithSpecialChars() {
        return fakerEn.name().firstName() + fakerEn.letterify("!@#$%^&*()+=~`{}[]|:;'<>,.?/_");
    }
    // Метод для получения данных со спецсимволами в поле Владелец
    public static CardInfo getHolderSpecialChars() {
        return new CardInfo(getCardApproved(), getValidMonth(), getValidYear(), getInvalidHolderWithSpecialChars(), getValidCvc());
    }



    // генерация CVV меньше 3 цифр
    public static String getShortCvc() {
        return fakerEn.number().digits(2); // например, "12"
    }
    // Метод для получения данных с вводом менее 3-х цифр в поле CVV
    public static CardInfo getInvalidCVVShort() {
        return new CardInfo(getCardApproved(), getValidMonth(), getValidYear(), getValidHolder(),getShortCvc());
    }


    // Генерация пустого поля
    public static String getEmptyField() {
        return ""; // Пустое поле
    }
    // Метод для получения данных с пустым полем Номер карты.
    public static CardInfo getEmptyCardNumberInfo() {
        return new CardInfo(getEmptyField(), getValidMonth(), getValidYear(), getValidHolder(), getValidCvc());
    }
    // Метод для получения данных с пустым полем Месяц
    public static CardInfo getEmptyMonthInfo() {
        return new CardInfo(getCardApproved(), getEmptyField(), getValidYear(), getValidHolder(), getValidCvc());
    }
    // Метод для получения данных с пустым полем Год
    public static CardInfo getEmptyYearInfo() {
        return new CardInfo(getCardApproved(), getValidMonth(), getEmptyField(),getValidHolder(), getValidCvc());
    }
    // Метод для получения данных с пустым полем Владелец
    public static CardInfo getEmptyHolderInfo() {
        return new CardInfo(getCardApproved(), getValidMonth(), getValidYear(),getEmptyField(), getValidCvc());
    }
    // Метод для получения данных с пустым полем CVV
    public static CardInfo getEmptyCVVInfo() {
        return new CardInfo(getCardApproved(), getValidMonth(), getValidYear(),getValidHolder(), getEmptyField());
    }


    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String holder;
        String Cvc;
    }
}



