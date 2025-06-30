package Models;

import Enums.BookStatus;


public class BookCopy {
    BookMetaInfo info;
    private final Integer copyId;
    private BookStatus status;

    public int getCopyId() {
        return copyId;
    }

    public BookMetaInfo getInfo() {
        return info;
    }

    public void setInfo(BookMetaInfo info) {
        this.info = info;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    private BookCopy(Integer copyId, BookStatus status, BookMetaInfo info)
   {
       this.info =info;
       this.copyId = copyId;
       this.status = status;
   }

   public static class BookCopyBuilder
   {
       static Integer BookId = 1;
       BookMetaInfo info;
       BookStatus status;

       public BookMetaInfo getInfo() {
           return info;
       }

       public void setInfo(BookMetaInfo info) {
           this.info = info;
       }

       public BookStatus getStatus() {
           return status;
       }

       public void setStatus(BookStatus status) {
           this.status = status;
       }

       BookCopy CreateBook()
       {
           if(info == null)
           {
               throw new UnsupportedOperationException();
           }
           return new BookCopy(BookId++,status, info);
       }
   }
}
