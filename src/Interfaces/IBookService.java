package Interfaces;
import Models.BookMetaInfo;
import java.util.List;

public interface IBookService {
    void addOrUpdateBook(BookMetaInfo book);
    void removeBook(String isbn);
    BookMetaInfo findByIsbn(String isbn);
    List<BookMetaInfo> searchByTitle(String title);
    List<BookMetaInfo> searchByAuthor(String author);
}
