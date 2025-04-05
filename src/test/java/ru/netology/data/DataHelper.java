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
    // НОМЕР КАРТЫ
    // Валидная карта (одобренная)
    public static String getCardApproved(){

        return ("4444 4444 4444 4441");
    }
    // невалидная карта (отклонить)
    public static String getCardDeclined(){

        return ("4444 4444 4444 4441");
    }
    // произвольная невалидная карта
    public static String getInvalidCardNumber() {
        return "1234 5678 9012 3456";
    }
    // короткий номер карты
    public static String getShortCardNumber() {
        return "4444 4444 4444";
    }
    // Поле номер карты пустое.
    public static String getEmptyCardNumber() {
        return "";
    }

    // МЕСЯЦ
    // Генерация валидного месяца
    private static String getValidMonth() {
        int month = LocalDate.now().getMonthValue();
        return String.format("%02d", month);
    }
    // генерация невалидного месяца (больше 12)
    public static String getInvalidMonth() {
        return "13";
    }
    //генерация месяца с 00
    public static String getZeroMonth() {
        return "00";
    }
    // поле месяц не заполнено
    public static String getEmptyMonth() {
        return "";
    }
    // Ввод одной цифры в поле месяц
    public static String getInvalidShortMonth() {
        return "1"; // одна цифра
    }

    //ГОД
    // генерация валидного года (текущий +1)
    private static String getValidYear() {
        int year = LocalDate.now().getYear() + 1;
        return String.format("%02d", year % 100);
    }
    // генерация года меньше текущего
    public static String getPastYear() {
        int year = LocalDate.now().getYear() - 1;
        return String.format("%02d", year % 100);
    }
    // не заполнено поле год
    public static String getEmptyYear() {
        return "";
    }
    // ввод одной цифры в поле год
    public static String getInvalidShortYear() {
        return "2";
    }

    // ИМЯ ФАМИЛИЯ
    // генерация валидного имени и фамилии
    public static String generateName(String locale){
        Faker faker = locale.equals("en") ? fakerEn : fakerRus;
        return faker.name().lastName() + " " + faker.name().firstName();
    }
    // пустое поле владелец
    public static String getEmptyOwner() {
        return "";
    }
    // имя кириллицей
    public static String generateNameWithRus(String locale){
        Faker faker = locale.equals("ru") ? fakerRus : fakerEn ;
        return faker.name().lastName() + " " + faker.name().firstName();
    }
    // Владелец с цифрами в имени
    public static String getOwnerWithNumbers() {
        return "An4l1a";
    }
    // Владелец со спецсимволами
    public static String getOwnerWithSpecialChars() {
        return "An@l!a#";
    }
    //CVV
    // генерация валидного CVV
    public static String getValidCvc() {
        return fakerEn.number().digits(3); // например, "123"
    }
    // генерация CVV меньше 3 цифр
    public static String getShortCvc() {
        return fakerEn.number().digits(2); // например, "12"
    }
    // пустое поле CVV
    public static String getEmptyCVC() {
        return "";
    }

    //генерация валидных данных
    @Value
    public static class cardInfo {
        public String cardNumber;
        public String Status;
        public String month;
        public String year;
        public String holder;
        public String CVC;


    }

}
