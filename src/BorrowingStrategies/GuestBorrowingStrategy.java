package BorrowingStrategies;

import Interfaces.IBorrowingStrategy;

public class GuestBorrowingStrategy implements IBorrowingStrategy {
    @Override
    public int getBorrowDurationDays() {
        return 0;
    }

    @Override
    public int maxBooksLimit() {
        return 0;
    }
}
