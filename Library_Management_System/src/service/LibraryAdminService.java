package service;

import model.Book;
import model.User;
import persistence.BookDataStore;
import persistence.UserDataStore;

import java.util.List;

public class LibraryAdminService {
    private final BookDataStore bookDataStore;
    private final UserDataStore userDataStore;

    public LibraryAdminService(BookDataStore bookDataStore, UserDataStore userDataStore) {
        this.bookDataStore = bookDataStore;
        this.userDataStore = userDataStore;
    }

    public void addBook(Book book) {
        List<Book> books = bookDataStore.load();
        books.add(book);
        bookDataStore.save(books);
    }

    public void addUser(User user) {
        List<User> users = userDataStore.load();
        users.add(user);
        userDataStore.save(users);
    }

    public void viewAllBooks() {
        List<Book> books = bookDataStore.load();
        for (Book book : books) {
            System.out.println(book);
        }
    }
    public void viewAllUsers() {
        try {
            List<User> users = userDataStore.load();
            
            // Debugging print to see the loaded users
            if (users.isEmpty()) {
                System.out.println("No users available.");
            } else {
                System.out.println("List of users:");
                for (User user : users) {
                    System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Address: " + user.getAddress() + ", Mobile: " + user.getMobile());
                }
            }
        } catch (Exception e) {
            System.out.println("Error fetching users: " + e.getMessage());
        }
    }

}
