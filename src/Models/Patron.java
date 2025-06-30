package Models;

import Enums.PatronType;
import Exceptions.MaxBorrowingLimitReached;
import Interfaces.IBorrowingStrategy;

import java.util.HashMap;

public class Patron {
    private String patronId;
    private String patronName;
    private PatronType patronType;
    private  String email;
    private  String phone;
    private IBorrowingStrategy borrowingStrategy;
    private HashMap<String,BorrowingRecord> borrowingRecords;
    private short BookBorrowed;
    public String getPatronId() {
        return patronId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public IBorrowingStrategy getBorrowingStrategy() {
        return borrowingStrategy;
    }

    public void setBorrowingStrategy(IBorrowingStrategy borrowingStrategy) {
        this.borrowingStrategy = borrowingStrategy;
    }

    public void setBorrowingRecords(HashMap<String, BorrowingRecord> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public PatronType getPatronType() {
        return patronType;
    }

    public void setPatronType(PatronType patronType) {
        this.patronType = patronType;
    }

    public Patron(String patronId, String patronName, PatronType patronType, String email, String phone, IBorrowingStrategy borrowingStrategy) {
        this.patronId = patronId;
        this.patronName = patronName;
        this.patronType = patronType;
        this.email = email;
        this.phone = phone;
        this.borrowingStrategy =borrowingStrategy;
        this.borrowingRecords = new HashMap<>();
    }


    public void AddBorrowingRecord(BorrowingRecord borrowingRecord)
    {
        if (null == borrowingRecord) {
            return;
        }

        if(BookBorrowed > borrowingStrategy.maxBooksLimit())
        {
            throw new MaxBorrowingLimitReached("User has already reached max borrow limit.");
        }
        BookBorrowed++;
        borrowingRecords.put(borrowingRecord.getCopyId(),borrowingRecord);
    }

    public BorrowingRecord RemoveBorrowingRecord(String copyId)
    {
        BorrowingRecord borrowingRecord = borrowingRecords.getOrDefault(copyId, null);
        return RemoveBorrowingRecord(borrowingRecord);
    }

    public BorrowingRecord RemoveBorrowingRecord(BorrowingRecord borrowingRecord)
    {
        if(null == borrowingRecord || borrowingRecord.GetReturnStatus())
        {
            return null;
        }
        BookBorrowed--;
        borrowingRecord.markReturned();
        borrowingRecords.remove(borrowingRecord.getCopyId());
        return borrowingRecord;
    }
    public HashMap<String, BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "patronId='" + patronId + '\'' +
                ", patronName='" + patronName + '\'' +
                ", patronType=" + patronType +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", borrowingRecords=" + borrowingRecords +
                '}';
    }

    public short getBookBorrowed() {
        return BookBorrowed;
    }

    public void setBookBorrowed(short bookBorrowed) {
        BookBorrowed = bookBorrowed;
    }

    public BorrowingRecord getRecordByCopyId(String copyId)
    {
        return borrowingRecords.getOrDefault(copyId, null);
    }
}
