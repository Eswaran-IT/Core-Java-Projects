package model;

public class BorrowRecord {
    private int bookId;
    private int userId;

    public BorrowRecord(int bookId, int userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", User ID: " + userId;
    }
}
