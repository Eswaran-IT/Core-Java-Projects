package model;

public class User {
    private int id;
    private String name;
    private String address;
    private long mobile;

    public User(int id, String name, String address, long mobile) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public long getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Address: " + address + ", Mobile: " + mobile;
    }
}
