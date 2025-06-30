package Models;
import Interfaces.IBook;
public class BookMetaInfo implements IBook{

    private final String Title;
    private final String Author;
    private final String ISBN;
    private final Short PublicationYear;

    public BookMetaInfo(String title, String author, String isbn, Short publicationYear) {
        Title = title;
        Author = author;
        ISBN = isbn;
        PublicationYear = publicationYear;
    }

    @Override
    public String getISBN() {
        return ISBN;
    }

    @Override
    public String getTitle() {
        return Title;
    }

    @Override
    public String getAuthor() {
        return Author;
    }

    @Override
    public Short GetPublicationYear() {
        return PublicationYear;
    }
}
