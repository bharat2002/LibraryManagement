package Services;

import Interfaces.IBookService;
import Models.BookMetaInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookService implements IBookService {

    private final Map<String, BookMetaInfo> bookMap = new HashMap<>();

    @Override
    public void addOrUpdateBook(BookMetaInfo book) {
        bookMap.put(book.getISBN(), book);
    }

    @Override
    public void removeBook(String isbn) {
        bookMap.remove(isbn);
    }

    @Override
    public BookMetaInfo findByIsbn(String isbn) {
        return bookMap.get(isbn);
    }

    @Override
    public List<BookMetaInfo> searchByTitle(String title) {
        return bookMap.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookMetaInfo> searchByAuthor(String author) {
        return bookMap.values().stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }
}
