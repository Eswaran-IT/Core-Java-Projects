package persistence;

import model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataStore {
    private static final String FILE_NAME = "users.txt";

    // Constructor to create the file if it does not exist
    public UserDataStore() {
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

    public void save(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getAddress() + "," + user.getMobile());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String address = data[2];
                long mobile = Long.parseLong(data[3]);
                users.add(new User(id, name, address, mobile));
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing user data: " + e.getMessage());
        }
        return users;
    }
}
