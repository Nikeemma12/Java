package Library;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Book> books = new ArrayList<>();
        Library lib1 = new Library(books);
        lib1.readBook();

        System.out.println("Welcome to Emma's Library");
        //Menu
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Search Book");
        System.out.println("4. View All Book");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("7. Exit");

        //User picks choice

        int choice = 0;


        String title;
        String author;
        String isbn;
        while (choice != 7) {
            try {
                System.out.print("Pick an option(1-7): ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter title of book: ");
                        title = scanner.nextLine().toUpperCase().trim();
                        System.out.print("Author: ");
                        author = scanner.nextLine().toUpperCase().trim();
                        System.out.print("Enter ISBN: ");
                        isbn = scanner.nextLine().toUpperCase().trim();
                        lib1.addBook(title, author, isbn);
                    }
                    case 2 -> {
                        System.out.print("Enter ISBN of book: ");
                        isbn = scanner.nextLine().toUpperCase();
                        lib1.removeBook(isbn);
                    }
                    case 3 -> {
                        System.out.print("Enter title of book: ");
                        title = scanner.nextLine().toUpperCase().trim();
                        lib1.searchBook(title);
                    }
                    case 4 -> lib1.DisplayBooks();
                    case 5 -> {
                        System.out.print("Enter title of book you want to borrow: ");
                        title = scanner.nextLine().toUpperCase().trim();
                        lib1.borrowBook(title);
                    }
                    case 6 -> {
                        System.out.print("Enter title of book you want to return: ");
                        title = scanner.nextLine().toUpperCase().trim();
                        lib1.returnBook(title);
                    }
                    case 7 -> System.out.println("You have exited Emma's Library");
                    default -> System.out.println("Incorrect choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
        }
        scanner.close();
        lib1.printBook();
        System.out.println("Have a nice day");

    }
}
