package Library;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private String available;

    Book(String title, String author, String isbn){
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.isbn = isbn;
    }

    String getTitle() {
        return this.title;
    }
    String getAuthor(){
        return this.author;
    }
    boolean getIsAvailable(){
        return this.isAvailable;
    }
    String getIsbn() {
        return this.isbn;
    }
    String getAvailable(){
        return this.available;
    }

    void setAvailable(String available){
         this.available = available;
    }
    void setIsbn(String isbn){
        this.isbn = isbn;
    }
    void setTitle(String title){
        this.title = title;
    }
    void setAuthor(String author) {
        this.author = author;
    }
    void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    String printDetails() {
        if(isAvailable) {
            available = "AVAILABLE";
        }
        else {
           available = "NOT-AVAILABLE";
        }
        return this.isbn + " " + this.title + " by " + this.author + ". " + available;
    }
}
