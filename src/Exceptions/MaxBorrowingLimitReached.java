package Exceptions;

public class MaxBorrowingLimitReached extends RuntimeException {
    public MaxBorrowingLimitReached(String message) {
        super(message);
    }
}
