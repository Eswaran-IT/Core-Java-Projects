package persistence;

import model.BorrowRecord;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDataStore {
    private static final String FILE_NAME = "borrow_records.txt";

    // Constructor to create the file if it does not exist
    public BorrowRecordDataStore() {
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

    public void save(List<BorrowRecord> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (BorrowRecord record : records) {
                writer.write(record.getBookId() + "," + record.getUserId());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving borrow records: " + e.getMessage());
        }
    }

    public List<BorrowRecord> load() {
        List<BorrowRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int bookId = Integer.parseInt(data[0]);
                int userId = Integer.parseInt(data[1]);
                records.add(new BorrowRecord(bookId, userId));
            }
        } catch (IOException e) {
            System.out.println("Error loading borrow records: " + e.getMessage());
        }
        return records;
    }
}
