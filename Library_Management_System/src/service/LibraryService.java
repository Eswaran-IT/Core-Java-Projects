package service;

import model.Book;
import model.BorrowRecord;
import model.User;
import persistence.BookDataStore;
import persistence.BorrowRecordDataStore;
import persistence.UserDataStore;

import java.util.List;

public class LibraryService {
    private final BookDataStore bookDataStore;
    private final UserDataStore userDataStore;
    private final BorrowRecordDataStore borrowRecordDataStore;

    public LibraryService(BookDataStore bookDataStore, UserDataStore userDataStore, BorrowRecordDataStore borrowRecordDataStore) {
        this.bookDataStore = bookDataStore;
        this.userDataStore = userDataStore;
        this.borrowRecordDataStore = borrowRecordDataStore;
    }

    public void borrowBook(int bookId, int userId) {
        List<Book> books = bookDataStore.load();
        List<BorrowRecord> borrowRecords = borrowRecordDataStore.load();

        // Check if book is available
        boolean bookFound = false;
        for (Book book : books) {
            if (book.getId() == bookId) {
                bookFound = true;
                break;
            }
        }

        if (!bookFound) {
            System.out.println("Book not found.");
            return;
        }

        // Check if user exists
        boolean userFound = false;
        for (User user : userDataStore.load()) {
            if (user.getId() == userId) {
                userFound = true;
                break;
            }
        }

        if (!userFound) {
            System.out.println("User not found.");
            return;
        }

        // Create borrow record
        borrowRecords.add(new BorrowRecord(bookId, userId));
        borrowRecordDataStore.save(borrowRecords);
        System.out.println("Book borrowed successfully.");
    }

    public void viewAllBooks() {
        List<Book> books = bookDataStore.load();
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
