package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {

    }

    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");


    @SneakyThrows
    private static Connection getConnection() {
        return DriverManager.getConnection(url, user, password);
    }

    // Очищает все таблицы
    @SneakyThrows
    public static void cleanDatabase() {
        try (Connection connection = getConnection()) {
            runner.execute(connection, "DELETE FROM credit_request_entity");
            runner.execute(connection, "DELETE FROM order_entity");
            runner.execute(connection, "DELETE FROM payment_entity");
        }
    }

    // Получает статус оплаты по карте
    @SneakyThrows
    public static String getCardPaymentStatus() {
        Thread.sleep(500); // даём БД время на запись
        String query = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        try (Connection connection = getConnection()) {
            return runner.query(connection, query, new ScalarHandler<>());
        }
    }

        // Получает статус оплаты в кредит
        @SneakyThrows
        public static String getCreditPaymentStatus () {
            Thread.sleep(500);
            String query = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
            try (Connection connection = getConnection()) {
                return runner.query(connection, query, new ScalarHandler<>());
            }
        }
    }
