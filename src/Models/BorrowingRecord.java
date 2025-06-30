package Models;

import Interfaces.IBorrowingStrategy;

import java.time.LocalDate;
import java.time.LocalTime;

public class BorrowingRecord {
    private final String recordId;
    private final String patronId;
    private final String copyId;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private static long LastRecordId = 0;
    private Boolean isReturned;

    public String getRecordId() {
        return recordId;
    }

    public String getPatronId() {
        return patronId;
    }

    public String getCopyId() {
        return copyId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public static long getLastRecordId() {
        return LastRecordId;
    }

    public static void setLastRecordId(long lastRecordId) {
        LastRecordId = lastRecordId;
    }

    private BorrowingRecord(String recordId, String patronId, String copyId, LocalDate borrowDate, LocalDate dueDate) {
        this.recordId = recordId;
        this.patronId = patronId;
        this.copyId = copyId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        isReturned = false;
    }

    public static BorrowingRecord createInstance(String patronId, String copyId, IBorrowingStrategy borrowingStrategy) {
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(borrowingStrategy.getBorrowDurationDays());
        return new BorrowingRecord(String.valueOf(++LastRecordId), patronId,copyId, borrowDate, dueDate);
    }

    public void markReturned() {
        isReturned = true;
        this.returnDate = LocalDate.now();
    }

    public LocalDate GetReturnDate()
    {
        return returnDate;
    }

    public boolean isOverdue() {
        return returnDate == null && LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "recordId='" + recordId + '\'' +
                ", patronId='" + patronId + '\'' +
                ", copyId='" + copyId + '\'' +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }

    public boolean GetReturnStatus() {
        return isReturned;
    }
}
