package persistence;

import model.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDataStore {
    private static final String FILE_NAME = "books.txt";

    // Constructor to create the file if it does not exist
    public BookDataStore() {
        createFileIfNotExists(FILE_NAME);
    }

    // Create file if it doesn't exist
    private void createFileIfNotExists(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating the file: " + e.getMessage());
            }
        }
    }

    public void save(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                writer.write(book.getId() + "," + book.getName() + "," + book.getAuthor());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public List<Book> load() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String author = data[2];
                books.add(new Book(id, name, author));
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
        return books;
    }
}
