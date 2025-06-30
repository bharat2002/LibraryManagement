package Services;

import Enums.BookStatus;
import Interfaces.IBorrowingStrategy;
import Interfaces.IInventoryService;
import Interfaces.ILendingService;
import Interfaces.IPatronService;
import Models.BookCopy;
import Models.BorrowingRecord;
import Models.Patron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class LendingService implements ILendingService {
    private  IInventoryService inventoryService;
    private  IPatronService patronService;
    HashMap<String, BorrowingRecord> borrowingRecordHashMap;
    public LendingService(IInventoryService inventoryService, IPatronService patronService, IBorrowingStrategy borrowingStrategy) {
        this.inventoryService = inventoryService;
        this.patronService = patronService;
        borrowingRecordHashMap = new HashMap<>();
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

    @Override
    public BorrowingRecord checkoutBook(String patronId, Integer copyId) {
        Patron patron = patronService.findPatronById(patronId);
        BookCopy bookCopy = inventoryService.findCopyById(copyId);
        return checkoutBook(patron, bookCopy);
    }

    @Override
    public BorrowingRecord checkoutBook(Patron patron, BookCopy copy) {
        if(null == patron || null == copy)
        {
            return null;
        }
        copy.setStatus(BookStatus.BOOKED);
        BorrowingRecord borrowingRecord = BorrowingRecord.createInstance(patron.getPatronId(),String.valueOf(copy.getCopyId()),patron.getBorrowingStrategy());
        patronService.AddPatronBorrowingRecord(patron,borrowingRecord);
        borrowingRecordHashMap.put(borrowingRecord.getRecordId(),borrowingRecord);
        return borrowingRecord;
    }

    @Override
    public BorrowingRecord returnBook(String patronId, String copyId) {

        Patron patron = patronService.findPatronById(patronId);
        if (patron == null) {
            System.out.println("Invalid patron ID");
            return null;
        }

        BookCopy copy = inventoryService.findCopyById(Integer.parseInt(copyId));
        if (copy == null) {
            System.out.println("Invalid copy ID");
            return null;
        }

        BorrowingRecord borrowingRecord = patron.getRecordByCopyId(copyId);
        if (null == borrowingRecord) {
            System.out.println("No active borrowing found");
            return null ;
        }

        copy.setStatus(BookStatus.AVAILABLE);
        return patronService.RemoveBorrowingRecord(patron,borrowingRecord);
    }

    @Override
    public List<BorrowingRecord> GetActiveBorrowing(String patronId) {
       Patron patron = this.patronService.findPatronById(patronId);
       if(null != patron)
       {
           return new ArrayList<>(patron.getBorrowingRecords().values());
       }
       return null;
    }

}
