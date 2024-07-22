package ru.netology.tourpayment.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class SQLHelper {
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

    @SneakyThrows
    public static void clearData() {
        var deleteOrderEntity = "DELETE FROM order_entity";
        var deletePaymentEntity = "DELETE FROM payment_entity";
        var deleteCreditRequestEntity = "DELETE FROM credit_request_entity";
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url, user, password
                );
        ) {
            runner.update(conn, deleteOrderEntity);
            runner.update(conn, deletePaymentEntity);
            runner.update(conn, deleteCreditRequestEntity);
        }
    }

    @SneakyThrows
    public static String getStatus(String query) {
        String result = "";
        var runner = new QueryRunner();
        try
                (var conn = DriverManager.getConnection(url, user, password)) {

            result = runner.query(conn, query, new ScalarHandler<String>());
            System.out.println(result);
            return result;
        }
    }

    @SneakyThrows
    public static String getDebitStatus() {
        var statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    @SneakyThrows
    public static String getCreditStatus() {
        var statusSQL = "SELECT status FROM credit_request_entity";
        return getStatus(statusSQL);
    }

}
