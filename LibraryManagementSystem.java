package basicsthread;

import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issueBook() { this.isIssued = true; }
    public void returnBook() { this.isIssued = false; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + (isIssued ? "Issued" : "Available");
    }
}

class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return id + " | " + name;
    }
}

class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Add book
    public void addBook(Book book) {
        books.add(book);
    }

    // Add user
    public void addUser(User user) {
        users.add(user);
    }

    // Show all books
    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // Show all users
    public void showUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
            return;
        }
        for (User u : users) {
            System.out.println(u);
        }
    }

    // Issue book
    public void issueBook(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId && !b.isIssued()) {
                b.issueBook();
                System.out.println("Book issued: " + b.getTitle());
                return;
            }
        }
        System.out.println("Book not available or already issued!");
    }

    // Return book
    public void returnBook(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId && b.isIssued()) {
                b.returnBook();
                System.out.println("Book returned: " + b.getTitle());
                return;
            }
        }
        System.out.println("Invalid book ID or book not issued!");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Preload sample data
        library.addBook(new Book(1, "Java Programming", "James Gosling"));
        library.addBook(new Book(2, "Python Basics", "Guido van Rossum"));
        library.addUser(new User(1, "Alice"));
        library.addUser(new User(2, "Bob"));

        int choice;
        do {
            System.out.println("\n--- Library Management ---");
            System.out.println("1. Show Books");
            System.out.println("2. Show Users");
            System.out.println("3. Add Book");
            System.out.println("4. Add User");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;
                case 2:
                    library.showUsers();
                    break;
                case 3:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    library.addUser(new User(uid, name));
                    break;
                case 5:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    library.issueBook(issueId);
                    break;
                case 6:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }
}
