package Interfaces;

import Models.BookCopy;
import Models.BorrowingRecord;
import Models.Patron;

import java.util.List;

public interface ILendingService {
    BorrowingRecord checkoutBook(String patronId, Integer copyId);
    BorrowingRecord checkoutBook(Patron patron, BookCopy copy);
    BorrowingRecord returnBook(String patronId, String copyId);
    List<BorrowingRecord> getHistory(String patronId);
}
