package Services;

import Interfaces.IPatronService;
import Models.BorrowingRecord;
import Models.Patron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatronService implements IPatronService {
    Map<String, Patron> patronMap;

    public PatronService() {
        this.patronMap = new HashMap<>();
    }

    @Override
    public Patron registerPatron(Patron patron) {
        if(null == patron)
        {
            return null;
        }
        patronMap.put(patron.getPatronId(), patron);
        return patron;
    }

    @Override
    public void updatePatron(Patron patron) {
//        patronMap.get(patron)
        if(!patronMap.containsKey(patron.getPatronId()))
        {
            throw new IllegalArgumentException("Patron not found: " + patron.getPatronId());
        }
        patronMap.put(patron.getPatronId(), patron);
    }

    @Override
    public Patron findPatronById(String patronId) {
        if(!patronMap.containsKey(patronId))
        {
            return null;
        }
        return patronMap.get(patronId);
    }

    @Override
    public List<Patron> getAllPatrons() {
        return patronMap.values().stream().toList();
    }

    @Override
    public void AddPatronBorrowingRecord(Patron patron, BorrowingRecord borrowingRecord) {
        patron.AddBorrowingRecord(borrowingRecord);
    }

    @Override
    public BorrowingRecord RemoveBorrowingRecord(Patron patron, BorrowingRecord borrowingRecord) {
        return patron.RemoveBorrowingRecord(borrowingRecord);
    }
}
