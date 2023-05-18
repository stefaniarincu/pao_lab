package ro.pao.SQL;

import ro.pao.config.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

public class AssociativeTable {
    public static void createAssociativeTable() {
        String sqlStatement = "CREATE TABLE ASSOCIATIVE_TABLE (" +
                "book_id INT REFERENCES BOOKS(id)," +
                "member_id INT REFERENCES MEMBERS(id)," +
                "borrow_date DATE" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             Statement statement = connection.createStatement()){

            statement.executeUpdate(sqlStatement);
            System.out.println("Associative table created successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating Associative table: " + e.getMessage());
        }
    }

    public static void insertIntoAssociativeTable(int bId, int mId, LocalDate date) {
        String sqlStatement = "INSERT INTO ASSOCIATIVE_TABLE (book_id, member_id, borrow_date) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setInt(1, bId);
            preparedStatement.setInt(2, mId);
            preparedStatement.setDate(3, java.sql.Date.valueOf(date));

            preparedStatement.executeUpdate();

            System.out.println("1 row inserted.");
        } catch (SQLException e) {
            System.err.println("Error inserting into associative table: " + e.getMessage());
        }
    }
}
