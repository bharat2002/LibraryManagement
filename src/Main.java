import Enums.PatronType;
import Models.BookCopy;
import Models.BorrowingRecord;
import Services.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Register a patron
        library.registerPatron("P001", "Alice", PatronType.STUDENT, "alice@example.com", "1234567890");

        // Add a book
        library.addBook("978-0134685991", "Effective Java", "Joshua Bloch", (short) 2018, 2);

        // Checkout a book
        BookCopy copy = library.getInventoryService().findCopyById(1);
        BorrowingRecord record = library.getLendingService().checkoutBook("P001", copy.getCopyId());
        if (record != null) {
            System.out.println("Book checked out successfully: " + record);
        } else {
            System.out.println("Checkout failed.");
        }

        // Return a book
        library.getLendingService().GetActiveBorrowing("P001").forEach(System.out::println);
        library.getLendingService().returnBook("P001", String.valueOf(copy.getCopyId()));

        // Display borrowing history
        System.out.println("Borrowing history:");
        library.getLendingService().GetActiveBorrowing("P001").forEach(System.out::println);
    }
}
