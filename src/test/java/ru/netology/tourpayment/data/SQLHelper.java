package ru.netology.tourpayment.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");


    public static void clearData() throws SQLException {
        val deleteOrderEntity = "DELETE FROM order_entity";
        val deletePaymentEntity = "DELETE FROM payment_entity";
        val deleteCreditRequestEntity = "DELETE FROM credit_request_entity";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(url, user, password
                );
        ) {
            runner.update(conn, deleteOrderEntity);
            runner.update(conn, deletePaymentEntity);
            runner.update(conn, deleteCreditRequestEntity);
        }
    }

    public static String getStatus(String query) throws SQLException {
        String result = "";
        var runner = new QueryRunner();
        try
                (var conn = DriverManager.getConnection(url, user, password)) {

            result = runner.query(conn, query, new ScalarHandler<String>());
            System.out.println(result);
            return result;
        }
    }

    public static String getDebitStatus() throws SQLException {
        val statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    public static String getCreditStatus() throws SQLException {
        val statusSQL = "SELECT status FROM credit_request_entity";
        return getStatus(statusSQL);
    }

}
