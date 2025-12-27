package Library;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Library {
    ArrayList<Book>  books;


    Library(ArrayList<Book> books){
        this.books = books;
    }

    void readBook() {
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\Library\\Library Books.txt";
        try(BufferedReader bookRead = new BufferedReader(new FileReader(path))) {
            String line;
            String isbn, title, author, available;
            while((line = bookRead.readLine()) != null){
                isbn = line.substring(0, line.indexOf(" ")).trim();
                title = line.substring(line.indexOf(" "), line.indexOf("b".toLowerCase())).trim();
                author = line.substring(line.indexOf("y".toLowerCase())+1, line.indexOf(".")).trim();
                available = line.substring(line.indexOf(".")+1).trim();
                Book book = new Book(title, author, isbn);
                if(available.equals("AVAILABLE")){
                    book.setIsAvailable(true);
                } else if (available.equals("NOT-AVAILABLE")) {
                    book.setIsAvailable(false);
                }
                books.add(book);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    void addBook(String title, String author, String isbn){
        Book newBook = new Book(title, author, isbn);
        newBook.setIsAvailable(true);
        books.add(newBook);
    }
    void removeBook(String isbn) {
        boolean found = false;
        Book removedBook = null;
        for(Book book:books){
            if(isbn.equals(book.getIsbn())){
                found = true;
                removedBook = book;
                break;
            }
        }
        if(found) {
            books.remove(removedBook);
            System.out.println("Book successfully reomoved");
        } else {
            System.out.println("Book not found to remove");
        }
    }
    void searchBook(String title) {
        boolean found = false;
        String bookDetails = "";
        for (Book book:books) {
            if(book.getTitle().equals(title)) {
                found = true;
                bookDetails = book.printDetails();
                break;
            }
        }
        if(found){
            System.out.println(bookDetails);
            System.out.println("Book found");
        }else{
            System.out.println("Book not found");
        }
    }
    void DisplayBooks() {
        for (Book book:books){
            System.out.println(book.printDetails());
        }
    }
    void printBook(){
        String path = "C:\\Users\\pc\\OneDrive\\Desktop\\Java\\IdeaProjects\\OOP Projects\\src\\Library\\Library Books.txt";
        try(FileWriter bookWrite = new FileWriter(path)) {
            for(Book book : books) {
                bookWrite.write(book.printDetails());
                bookWrite.write("\n");
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }
    void borrowBook(String title) {
        boolean found  = false;
        boolean isborrowed = false;
        LocalDateTime dateTimeborrowed;
        DateTimeFormatter borrowedFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");
        String dtb = "";
        for(Book book: books) {
            if(book.getTitle().equals(title)) {
                found = true;
                if (book.getIsAvailable()) {
                    dateTimeborrowed = LocalDateTime.now();
                    dtb = dateTimeborrowed.format(borrowedFormatter);
                    book.setIsAvailable(false);
                    isborrowed = true;
                }
            }
        }
        if (found && isborrowed) {
            System.out.println("Book found in Library and available for Borrowing");
            System.out.println("Borrowed Successfully at " + dtb);
        } else if (found && !isborrowed) {
            System.out.println("Book found in Library but is already borrowed");
        } else {
            System.out.println("Book not found");
        }
    }
    void returnBook(String title) {
        boolean found = false;
        boolean isborrowed = true;
        LocalDateTime dateTimereturned;
        DateTimeFormatter retturnedFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");
        String dtr = "";
        for (Book book: books) {
            if (title.equals(book.getTitle())) {
                found = true;
                if(!book.getIsAvailable()) {
                    dateTimereturned = LocalDateTime.now();
                    dtr = dateTimereturned.format(retturnedFormatter);
                    book.setIsAvailable(true);
                    isborrowed = false;
                }
            }
        }
        if (found && !isborrowed) {
            System.out.println("Book found in Library");
            System.out.println("Book returned Successfully at " + dtr);
        } else if (found && isborrowed) {
            System.out.println("Book found in Library but was never returned");
        } else {
            System.out.println("This library doesn't own this book.");
            System.out.println("Book cant be returned");
        }
    }

}
