package ro.pao.SQL;

import ro.pao.config.DatabaseConfiguration;

import java.sql.*;
import java.util.Scanner;

public class MembersTable {
    public static void createMembersTable() {
        String sqlStatement = "CREATE TABLE Members (" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "email VARCHAR(255)," +
                "phone VARCHAR(255)" +
                ")";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             Statement statement = connection.createStatement()){

            statement.executeUpdate(sqlStatement);
            System.out.println("Members table created successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating Members table: " + e.getMessage());
        }
    }

    public static void insertIntoMembersTable(String name, String email, String phone) {
        String sqlStatement = "INSERT INTO MEMBERS (id, name, email, phone) VALUES (?, ?, ?, ?)";

        int id = (int) (Math.random() * Integer.MAX_VALUE);

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);

            preparedStatement.executeUpdate();

            System.out.println("1 row inserted.");
        } catch (SQLException e) {
            System.err.println("Error inserting member: " + e.getMessage());
        }
    }

    public static void readNewMemberToInsert()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the member name:");
        String name = scanner.nextLine();

        System.out.println("Enter the member email:");
        String email = scanner.nextLine();

        System.out.println("Enter the member phone:");
        String phone = scanner.nextLine();

        scanner.close();

        insertIntoMembersTable(name, email, phone);
    }

    public static void selectAllFromMembersTable() {
        String sqlStatement = "SELECT * FROM MEMBERS";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int memberId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                System.out.println("Member ID: " + memberId);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving member: " + e.getMessage());
        }
    }

    public static void updateFromMembersTable(int id, String newName, String newEmail, String newPhone) {
        String sqlStatement = "UPDATE MEMBERS SET name = ?, email = ?, phone = ? WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setString(3, newPhone);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

            System.out.println("1 row updated.");
        } catch (SQLException e) {
            System.err.println("Error updating member: " + e.getMessage());
        }
    }

    public static void readNewMemberToUpdate()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the id for the member you want to update:");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the new member name:");
        String name = scanner.nextLine();

        System.out.println("Enter the new member email:");
        String email = scanner.nextLine();

        System.out.println("Enter the new member phone:");
        String phone = scanner.nextLine();

        scanner.close();

        updateFromMembersTable(id, name, email, phone);
    }

    public static void deleteFromMembersTable(int id) {
        String sqlStatement = "DELETE FROM MEMBERS WHERE id = ?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            System.out.println("1 row deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting member: " + e.getMessage());
        }
    }

    public static void readMemberIdToDelete() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the id for the member you want to delete:");
        int id = Integer.parseInt(scanner.nextLine());

        scanner.close();

        deleteFromMembersTable(id);
    }

    public static void joinMembersWithBorrowedBooks() {
        String sqlStatement = "SELECT m.name, b.title, a.borrow_date FROM MEMBERS m, BOOKS b, ASSOCIATIVE_TABLE a WHERE m.id = a.member_id AND a.book_id = b.id ORDER BY m.id";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String title = resultSet.getString(2);
                Date borrowed_date = resultSet.getDate(3);

                System.out.println("Member Name: " + name);
                System.out.println("Book Title: " + title);
                System.out.println("Borrowed Date: " + borrowed_date);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving member-books: " + e.getMessage());
        }
    }
}
