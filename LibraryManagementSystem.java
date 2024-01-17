import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public Book(Scanner scanner) {
        System.out.print("Enter book title: ");
        this.title = scanner.nextLine();

        System.out.print("Enter author name: ");
        this.author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        this.isbn = scanner.nextLine();

        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + available;
    }
}

class ITBook extends Book {
    public ITBook(String title, String author, String isbn) {
        super(title, author, isbn);
    }
}

class MathsBook extends Book {
    public MathsBook(String title, String author, String isbn) {
        super(title, author, isbn);
    }
}

class Library {
    private List<Book> itBooks;
    private List<Book> mathsBooks;
    private List<Book> borrowedBooks;
    private List<Book> addedBooks;

    public Library() {
        this.itBooks = new ArrayList<>();
        this.mathsBooks = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
        this.addedBooks = new ArrayList<>();
    }

    public void addBook(Book book, String category) {
        if ("IT".equalsIgnoreCase(category)) {
            itBooks.add(book);
        } else if ("Maths".equalsIgnoreCase(category)) {
            mathsBooks.add(book);
        } else {
            System.out.println("Invalid category. Book not added.");
            return;
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");

        System.out.println("\nIT Books:");
        displayBooksByCategory(itBooks);

        System.out.println("\nMaths Books:");
        displayBooksByCategory(mathsBooks);
    }

    private void displayBooksByCategory(List<Book> bookList) {
        for (Book book : bookList) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    public Book borrowBook(String isbn, String category) {
        List<Book> bookList = "IT".equalsIgnoreCase(category) ? itBooks : mathsBooks;
        Book borrowedBook = null;

        for (Book book : bookList) {
            if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                book.setAvailable(false);
                borrowedBooks.add(book);
                borrowedBook = book;
                break;
            }
        }

        if (borrowedBook == null) {
            System.out.println("Book not available or not found.");
        }

        return borrowedBook;
    }

    public void returnBook(Book book) {
        book.setAvailable(true);
        borrowedBooks.remove(book);
        System.out.println("Book returned successfully.");
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<Book> getAddedBooks() {
        return addedBooks;
    }
}

class User {
    private static int userCount = 0;
    private int userId;
    private List<Book> borrowedBooks;

    public User() {
        this.userId = ++userCount;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

public class LibraryManagementSystem {
    private static List<String> actions = new ArrayList<>(); // Changed to a list of strings to store all actions
    private static boolean borrowWarningDisplayed = false; // Added a boolean flag for borrow warning
    private static boolean returnWarningDisplayed = false; // Added a boolean flag for return warning

    public static void main(String[] args) {
        boolean continueRunning = true;
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        String libraryName = "***Welcome to the ITUM Library***";
        String libraryAddress = "123, Horana Road, Diyagama, Homagama";
        String libraryContact = "011-1234567";

        printCentered(new String(new char[45]).replace("\0", "="), 75);
        printCentered(libraryName, 75);
        printCentered(new String(new char[45]).replace("\0", "="), 75);
        printCentered(libraryAddress, 75);
        printCentered(libraryContact, 75);
        int length = (int) Math.round(libraryName.length() * 2.5);
        printCentered(new String(new char[length]).replace("\0", "-"), 75);

        LocalDate todayDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();

        System.out.printf("Date: %-50s Student Name: %s%n", todayDate, studentName);
        System.out.printf("Time: %-50s Student ID: %s%n", nowTime, studentID);

        printLine((int) Math.round(libraryName.length() * 2.5));
        // IT books
        ITBook itBook1 = new ITBook("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin",
                "123");
        ITBook itBook2 = new ITBook("The Pragmatic Programmer: Your Journey To Mastery", "Andrew Hunt and David Thomas",
                "456");
        ITBook itBook3 = new ITBook("Design Patterns: Elements of Reusable Object-Oriented Software",
                " Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides", "789");
        ITBook itBook4 = new ITBook("Introduction to the Theory of Computation", "Michael Sipser", "896");
        ITBook itBook5 = new ITBook("Artificial Intelligence: A Modern Approach", " Stuart Russell and Peter Norvig",
                "864");
        // maths books
        MathsBook mathsBook1 = new MathsBook("Infinite Powers: How Calculus Reveals the Secrets of the Universe",
                "Steven Strogatz", "879");
        MathsBook mathsBook2 = new MathsBook(
                "Kindergarten Math Workbook: Kindergarten and 1st Grade Workbook Age 5-7 | Homeschool Kindergarteners | Addition and Subtraction Activities + Worksheets",
                "Modern Kid Press", "545");
        MathsBook mathsBook3 = new MathsBook(
                "The Ultimate Grade 2 Math Workbook: Multi-Digit Addition, Subtraction, Place Value, Measurement, Data, Geometry, Perimeter, Counting Money, and Time … Curriculum",
                "IXL Learning", "625");
        MathsBook mathsBook4 = new MathsBook("Gödel, Escher, Bach: An Eternal Golden Braid", "Douglas R. Hofstadter",
                "856");
        MathsBook mathsBook5 = new MathsBook("fermat's Enigma", "Simon Singh", "565");

        library.addBook(itBook1, "IT");
        library.addBook(itBook2, "IT");
        library.addBook(itBook3, "IT");
        library.addBook(itBook4, "IT");
        library.addBook(itBook5, "IT");
        library.addBook(mathsBook1, "Maths");
        library.addBook(mathsBook2, "Maths");
        library.addBook(mathsBook3, "Maths");
        library.addBook(mathsBook4, "Maths");
        library.addBook(mathsBook5, "Maths");

        User user = new User();

        while (true) {
            System.out.println("\n1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Add a New Book");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String isbnToBorrow = scanner.nextLine();
                    System.out.print("Enter the type of the book (IT/Maths): ");
                    String bookCategoryToBorrow = scanner.nextLine();
                    boolean isFictionToBorrow = "IT".equalsIgnoreCase(bookCategoryToBorrow);
                    Book borrowedBook = library.borrowBook(isbnToBorrow, isFictionToBorrow ? "IT" : "Maths");

                    if (borrowedBook != null) {
                        String borrowAction = "You borrowed a book successfully: " +
                                "Title: " + borrowedBook.getTitle() +
                                ", Author: " + borrowedBook.getAuthor() +
                                ", ISBN: " + borrowedBook.getIsbn();
                        actions.add(borrowAction);

                        if (!borrowWarningDisplayed) {
                            borrowWarningDisplayed = true;
                            System.out.println("Hey! You borrowed a book successfully.");
                            System.out.println("Keep the book safe and return it on time.");
                        }
                    } else {
                        actions.add("Book not available or not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to return: ");
                    String isbnToReturn = scanner.nextLine();
                    Book bookToReturn = null;

                    for (Book borrowed : user.getBorrowedBooks()) {
                        if (borrowed.getIsbn().equals(isbnToReturn)) {
                            bookToReturn = borrowed;
                            break;
                        }
                    }

                    if (bookToReturn != null) {
                        library.returnBook(bookToReturn);
                        user.returnBook(bookToReturn);
                        String returnAction = "Hey " + studentName + "! You returned a book successfully: " +
                                "Title: " + bookToReturn.getTitle() +
                                ", Author: " + bookToReturn.getAuthor() +
                                ", ISBN: " + bookToReturn.getIsbn();
                        actions.add(returnAction);

                        // Set return warning only if it has not been displayed yet
                        if (!returnWarningDisplayed) {
                            returnWarningDisplayed = true;
                            System.out.println("Hey " + studentName + "! You returned a book successfully.");
                            System.out.println("Thanks for keeping the book carefully and returning it.");
                        }
                    } else {
                        actions.add("Book not found in your borrowed list.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the type of the book (IT/Maths): ");
                    String bookCategoryToAdd = scanner.nextLine();
                    boolean isFictionToAdd = "IT".equalsIgnoreCase(bookCategoryToAdd);
                    Book newBook = new Book(scanner);
                    String addAction = "Hey! You added a new book to the library: " +
                            "Title: " + newBook.getTitle() +
                            ", Author: " + newBook.getAuthor() +
                            ", ISBN: " + newBook.getIsbn();
                    actions.add(addAction);
                    library.addBook(newBook, isFictionToAdd ? "IT" : "Maths");
                    System.out.println("Book added successfully.");
                    break;
                case 5:
                    System.out.println("Thank you for using the library.keep reading and keep learning.");

                    // Display actions performed
                    if (!actions.isEmpty()) {
                        System.out.println("Actions performed:");
                        for (String action : actions) {
                            System.out.println(action);
                        }
                    }

                    // Display warnings if they have been displayed
                    if (borrowWarningDisplayed) {
                        System.out.println("Keep the book safe and return it on time.");
                    }

                    if (returnWarningDisplayed) {
                        System.out.println("Thanks for keeping the book carefully and returning it.");
                    }

                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void printCentered(String s, int width) {
        int padSize = width - s.length();
        int padStart = s.length() + padSize / 2;
        s = String.format("%" + padStart + "s", s);
        s = String.format("%-" + width + "s", s);
        System.out.println(s);
    }

    private static void printLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print('-');
        }
        System.out.println();
    }
}