package ro.pao;

import java.time.LocalDate;
import java.util.Scanner;

import static ro.pao.SQL.AssociativeTable.createAssociativeTable;
import static ro.pao.SQL.AssociativeTable.insertIntoAssociativeTable;
import static ro.pao.SQL.BooksTable.*;
import static ro.pao.SQL.MembersTable.*;


public class Main {
    public static void main(String[] args) {
/*
        createBooksTable();

        readNewBookToInsert();

        selectAllFromBooksTable();

        readNewBookToUpdate();

        readBookIdToDelete();


        createMembersTable();

        readNewMemberToInsert();

        selectAllFromMembersTable();

        readNewMemberToUpdate();

        readMemberIdToDelete();


        Scanner scanner = new Scanner(System.in);
        String authorName = scanner.nextLine();
        getBooksByAuthorName(authorName);

        int givenYear = Integer.parseInt(scanner.next());
        getBooksCountByYear(givenYear);

        createAssociativeTable();

        insertIntoAssociativeTable(1330148182, 643927534, LocalDate.now());
        insertIntoAssociativeTable(696639286, 643927534, LocalDate.now().minusYears(7));
        insertIntoAssociativeTable(1417751671, 643927534, LocalDate.now().minusDays(42));

        insertIntoMembersTable("member2", "member2@email", "112");
        insertIntoMembersTable("member3", "member3@email", "0747447470");

        insertIntoAssociativeTable(1330148182, 963447378, LocalDate.now().minusMonths(18));
        insertIntoAssociativeTable(696639286, 1362358575, LocalDate.now().minusYears(1));
        insertIntoAssociativeTable(1417751671, 963447378, LocalDate.now().minusDays(222));
        insertIntoAssociativeTable(1068655147, 963447378, LocalDate.now().minusMonths(131));
        insertIntoAssociativeTable(1068655147, 1362358575, LocalDate.now().minusYears(3));
        insertIntoAssociativeTable(1417751671, 963447378, LocalDate.now().minusDays(2333));
    */
        joinMembersWithBorrowedBooks();
    }


}