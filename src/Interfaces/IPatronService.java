package Interfaces;
import Models.BorrowingRecord;
import Models.Patron;
import java.util.List;

public interface IPatronService {
    Patron registerPatron(Patron patron);

    void updatePatron(Patron patron);

    Patron findPatronById(String patronId);

    List<Patron> getAllPatrons();

    void AddPatronBorrowingRecord(Patron patron, BorrowingRecord borrowingRecord);

    BorrowingRecord RemoveBorrowingRecord(Patron patron, BorrowingRecord borrowingRecord);
}