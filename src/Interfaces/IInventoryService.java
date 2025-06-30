package Interfaces;

import Enums.BookStatus;
import Models.BookCopy;

import java.util.List;

public interface IInventoryService {
    void addCopy(BookCopy copy);
    void removeCopy(Integer copyId);
    BookCopy findCopyById(Integer copyId);
    List<BookCopy> getAllCopiesByIsbn(String isbn);
    List<BookCopy> getAvailableCopiesByIsbn(String isbn);
    void updateStatus(Integer copyId, BookStatus status);

//    void updateStatus(Integer copyId, BookStatus status);
}
