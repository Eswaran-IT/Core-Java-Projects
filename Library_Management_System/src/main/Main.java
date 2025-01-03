package main;

import model.Book;
import model.User;
import persistence.BookDataStore;
import persistence.UserDataStore;
import persistence.BorrowRecordDataStore;
import service.LibraryAdminService;
import service.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize data stores
        BookDataStore bookDataStore = new BookDataStore();
        UserDataStore userDataStore = new UserDataStore();
        BorrowRecordDataStore borrowRecordDataStore = new BorrowRecordDataStore();

        // Initialize services
        LibraryAdminService adminService = new LibraryAdminService(bookDataStore, userDataStore);
        LibraryService libraryService = new LibraryService(bookDataStore, userDataStore, borrowRecordDataStore);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Library Management System");

        // Admin or User login
        System.out.print("Are you an Admin or User? (Enter 'admin' or 'user'): ");
        String role = scanner.nextLine();

        if (role.equals("admin")) {
            // Admin actions
            System.out.println("--- Admin Menu ---");
            while (true) {
                System.out.println("1. Add Book");
                System.out.println("2. Add User");
                System.out.println("3. View All Books");
                System.out.println("4. View All Users");
                System.out.println("5. Exit");

                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Book Name: ");
                        String bookName = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        Book book = new Book(bookDataStore.load().size() + 1, bookName, author);
                        adminService.addBook(book);
                        break;
                    case 2:
                        System.out.print("Enter User Name: ");
                        String userName = scanner.nextLine();
                        System.out.print("Enter User Address: ");
                        String address = scanner.nextLine();
                        System.out.print("Enter User Mobile: ");
                        long mobile = scanner.nextLong();
                        scanner.nextLine(); // Consume newline
                        User user = new User(userDataStore.load().size() + 1, userName, address, mobile);
                        adminService.addUser(user);
                        break;
                    case 3:
                        adminService.viewAllBooks();
                        break;
                    case 4:
                        adminService.viewAllUsers();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } else if (role.equals("user")) {
            // User actions
            System.out.println("--- User Menu ---");
            while (true) {
                System.out.println("1. View All Books");
                System.out.println("2. Borrow Book");
                System.out.println("3. Exit");

                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        libraryService.viewAllBooks();
                        break;
                    case 2:
                        System.out.print("Enter Book ID to borrow: ");
                        int bookId = scanner.nextInt();
                        System.out.print("Enter User ID: ");
                        int userId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        libraryService.borrowBook(bookId, userId);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } else {
            System.out.println("Invalid role");
        }
    }
}
