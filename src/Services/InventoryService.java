package Services;

import Enums.BookStatus;
import Interfaces.IInventoryService;
import Models.BookCopy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryService implements IInventoryService {
    private final Map<Integer, BookCopy> copyMap = new HashMap<>();

    @Override
    public void addCopy(BookCopy copy) {
        copyMap.put(copy.getCopyId(), copy);
    }

    @Override
    public void removeCopy(Integer copyId) {
        copyMap.remove(copyId);
    }

    @Override
    public BookCopy findCopyById(Integer copyId) {
        return copyMap.get(copyId);
    }

    @Override
    public List<BookCopy> getAllCopiesByIsbn(String isbn) {
        return copyMap.values().stream()
                .filter(copy -> copy.getInfo().getISBN().equals(isbn))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookCopy> getAvailableCopiesByIsbn(String isbn) {
        return copyMap.values().stream()
                .filter(copy -> copy.getInfo().getISBN().equals(isbn))
                .filter(copy -> copy.getStatus() == BookStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Integer copyId, BookStatus status) {
        BookCopy copy = copyMap.get(copyId);
        if (copy != null) {
            copy.setStatus(status);
        } else {
            throw new IllegalArgumentException("No such copy with ID: " + copyId);
        }
    }
}
