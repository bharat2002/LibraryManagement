package Services;

import Enums.BookStatus;
import Interfaces.*;
import BorrowingStrategies.*;
import Enums.PatronType;
import Models.*;

public class Library {
    private  IBookService bookService;
    private  IInventoryService inventoryService;
    private  IPatronService patronService;
    private  ILendingService lendingService;

    public Library() {
        this.bookService = new BookService();
        this.inventoryService = new InventoryService();
        this.patronService = new PatronService();
        this.lendingService = new LendingService(inventoryService, patronService, null);
    }

    public IBookService getBookService() {
        return bookService;
    }

    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    public IInventoryService getInventoryService() {
        return inventoryService;
    }

    public void setInventoryService(IInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public IPatronService getPatronService() {
        return patronService;
    }

    public void setPatronService(IPatronService patronService) {
        this.patronService = patronService;
    }

    public ILendingService getLendingService() {
        return lendingService;
    }

    public void setLendingService(ILendingService lendingService) {
        this.lendingService = lendingService;
    }

    public void registerPatron(String id, String name, PatronType type, String email, String phone) {
        IBorrowingStrategy strategy = switch (type) {
            case STUDENT -> new StudentBorrowingStrategy();
            case FACULTY -> new FacultyBorrowingStrategy();
            case GUEST -> new GuestBorrowingStrategy();
        };

        Patron patron = new Patron(id, name, type, email, phone, strategy);
        patronService.registerPatron(patron);
    }

    public void addBook(String isbn, String title, String author, Short year, int numberOfCopies) {
        BookMetaInfo book = new BookMetaInfo(title, author, isbn, year);
        bookService.addOrUpdateBook(book);
        for (int i = 0; i < numberOfCopies; i++) {
            BookCopy copy = new BookCopy.BookCopyBuilder().setInfo(book).setStatus(BookStatus.AVAILABLE).CreateBook();
            System.out.println(copy.getCopyId());
            inventoryService.addCopy(copy);
        }
    }}
