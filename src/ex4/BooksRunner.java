package ex4;

import java.util.Scanner;
import java.util.Stack;

public class BooksRunner {
    public record Book(String author, String title, String isbn) {}

    public static void main(String[] args) {
        Stack<Book> books = new Stack<>();

        while(true) {
            System.out.println("""
                    Welcome to the book collection
                    1. Show books
                    2. Show top book
                    3. Take top book
                    4. Add book
                    5. Exit
                    """);
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch(choice) {
                case 1 -> showBooks(books);
                case 2 -> showTopBook(books);
                case 3 -> takeTopBook(books);
                case 4 -> addBook(books);
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void showTopBook(Stack<Book> books) {
        try {
            Book book = books.peek();
            System.out.println("Top book is: " + book);
        } catch (Exception e) {
            System.out.println("No books found");
        }
    }

    private static void takeTopBook(Stack<Book> books) {
        try {
            Book book = books.pop();
            System.out.println("Taken top book: " + book);
        } catch (Exception e) {
            System.out.println("No books found");
        }
    }

    private static void addBook(Stack<Book> books) {
        System.out.println("Title:");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        System.out.println("Author:");
        String author = scanner.nextLine();
        System.out.println("ISBN:");
        String isbn = scanner.nextLine();
        books.add(new Book(title, author, isbn));
        System.out.println("Added book: "+ books.peek());
    }

    private static void showBooks(Stack<Book> books) {
        if (books.isEmpty()) {
            System.out.println("There are no books");
            return;
        }

        Stack<Book> stack = (Stack<Book>) books.clone();


        System.out.println("Your books:");

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
