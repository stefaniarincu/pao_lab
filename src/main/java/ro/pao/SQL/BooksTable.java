package ro.pao.SQL;

import ro.pao.config.DatabaseConfiguration;

import java.sql.*;
import java.util.Scanner;

public class BooksTable {
    public static void createBooksTable() {
        String sqlStatement = "CREATE TABLE Books (" +
                "id INT PRIMARY KEY," +
                "title VARCHAR(255)," +
                "author VARCHAR(255)," +
                "year INT" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             Statement statement = connection.createStatement()){

            statement.executeUpdate(sqlStatement);
            System.out.println("Books table created successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating Books table: " + e.getMessage());
        }
    }

    public static void insertIntoBooksTable(String title, String author, int year) {
        String sqlStatement = "INSERT INTO BOOKS (id, title, author, year) VALUES (?, ?, ?, ?)";

        int id = (int) (Math.random() * Integer.MAX_VALUE);

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setInt(4, year);

            preparedStatement.executeUpdate();

            System.out.println("1 row inserted.");
        } catch (SQLException e) {
            System.err.println("Error inserting book: " + e.getMessage());
        }
    }

    public static void readNewBookToInsert()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the book title:");
        String title = scanner.nextLine();

        System.out.println("Enter the book author:");
        String author = scanner.nextLine();

        System.out.println("Enter the publication year:");
        int year = Integer.parseInt(scanner.nextLine());

        scanner.close();

        insertIntoBooksTable(title, author, year);
    }

    public static void selectAllFromBooksTable() {
        String sqlStatement = "SELECT * FROM BOOKS";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int year = resultSet.getInt("year");

                System.out.println("Book ID: " + bookId);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Year: " + year);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving book: " + e.getMessage());
        }
    }

    public static void updateFromBooksTable(int id, String newTitle, String newAuthor, int newYear) {
        String sqlStatement = "UPDATE BOOKS SET title = ?, author = ?, year = ? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, newTitle);
            preparedStatement.setString(2, newAuthor);
            preparedStatement.setInt(3, newYear);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

            System.out.println("1 row updated.");
        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    public static void readNewBookToUpdate()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the id for the book you want to update:");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the new book title:");
        String title = scanner.nextLine();

        System.out.println("Enter the new book author:");
        String author = scanner.nextLine();

        System.out.println("Enter the new publication year:");
        int year = Integer.parseInt(scanner.nextLine());

        scanner.close();

        updateFromBooksTable(id, title, author, year);
    }

    public static void deleteFromBooksTable(int id) {
        String sqlStatement = "DELETE FROM BOOKS WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            System.out.println("1 row deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }

    public static void readBookIdToDelete() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the id for the book you want to delete:");
        int id = Integer.parseInt(scanner.nextLine());

        scanner.close();

        deleteFromBooksTable(id);
    }

    public static void getBooksByAuthorName(String authorName) {
        String sqlStatement = "SELECT * FROM BOOKS WHERE LOWER(author) LIKE ?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, authorName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int year = resultSet.getInt("year");

                System.out.println("Book ID: " + bookId);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Year: " + year);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving book: " + e.getMessage());
        }
    }

    public static void getBooksCountByYear(int givenYear) {
        String sqlStatement = "SELECT COUNT(*) FROM BOOKS WHERE year = ?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setInt(1, givenYear);

            System.out.println(preparedStatement.executeQuery() + " books were published in year " + givenYear);
        } catch (SQLException e) {
            System.err.println("Error retrieving book: " + e.getMessage());
        }
    }
}
