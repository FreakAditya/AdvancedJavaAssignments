import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

class Member {
    private String name;
    private int age;
    private String contactNo;
    private int id;
    private double totalFine;


    private List<Integer> issuedBooks = new ArrayList<>();
    private Map<Integer, Date> dueDates = new HashMap<>();



    public Map<Integer, Date> getDueDates() {
        return dueDates;
    }



    public double getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(double fine) {
        this.totalFine = fine;
    }

    public void issueBook(int bookId, Date dueDate) {
        issuedBooks.add(bookId);
        dueDates.put(bookId, dueDate);
    }

    public void returnBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
        dueDates.remove(bookId);
    }

    public Member(String name, int age, String contactNo, int id) {
        this.name = name;
        this.age = age;
        this.contactNo = contactNo;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Contact No: " + contactNo;

    }



    public List<Integer> getIssuedBooks() {
        return issuedBooks;
    }

    public void issueBook(int bookId) {
        issuedBooks.add(bookId);
    }


}

class Book {
    private static int nextBookId = 1; // Initialize a static variable for book IDs
    private int bookId;
    private String title;
    private String author;
    private int copies;


    private Date dueDate; // Add a dueDate field

    public Book(String title, String author, int copies) {
        this.bookId = nextBookId++; // Assign a unique book ID
        this.title = title;
        this.author = author;
        this.copies = copies;
        long currentTimeMillis = System.currentTimeMillis();
        long dueTimeMillis = currentTimeMillis + (10L * 24 * 60 * 60 * 1000); // 10 days in milliseconds
        this.dueDate = new Date(dueTimeMillis);

    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopies() {
        return copies;
    }

    public void decreaseCopies() {
        if (copies > 0) {
            copies--;
        }
    }

    public void increaseCopies() {
        copies++;
    }

    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Copies: " + copies;
    }
}

public class LibraryPortal {
    private static ArrayList<Member> members = new ArrayList<>();
    private static ArrayList<Book> books = new ArrayList<>();
    private static int memberIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Library Portal Menu:");
            System.out.println("1. Enter as Librarian");
            System.out.println("2. Enter as Member");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
            case 1:
                librarianMenu(scanner);
                break;
            case 2:
                memberMenu(scanner);
                break;
            case 3:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
        scanner.close();
    }

    private static void librarianMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Librarian Menu:");
            System.out.println("1. Register a Member");
            System.out.println("2. Remove a Member");
            System.out.println("3. Add a Book");
            System.out.println("4. Remove a Book");
            System.out.println("5. View all members along with their books and fines to be paid");
            System.out.println("6. View All Books");
            System.out.println("7. Back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
            case 1:
                registerMember(scanner);
                break;
            case 2:
                removeMember(scanner);
                break;
            case 3:
                addBook(scanner);
                break;
            case 4:
                removeBook(scanner);
                break;
            case 5:
                viewAllMembers();
                break;
            case 6:
                viewAllBooks();
                break;
            case 7:
                System.out.println("Returning to previous menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    private static void registerMember(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter contact number: ");
        String contactNo = scanner.nextLine();
        members.add(new Member(name, age, contactNo, memberIdCounter));
        System.out.println("Member registered with ID: " + memberIdCounter);
        memberIdCounter++;
    }

    private static void removeMember(Scanner scanner) {
        System.out.print("Enter member ID to remove: ");
        int idToRemove = scanner.nextInt();
        boolean removed = false;
        for (Member member : members) {
            if (member.getId() == idToRemove) {
                members.remove(member);
                System.out.println("Member with ID " + idToRemove + " removed.");
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Member with ID " + idToRemove + " not found.");
        }
    }

    private static void addBook(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter number of copies: ");
        int copies = scanner.nextInt();
        books.add(new Book(title, author, copies));
        System.out.println("Book added successfully.");
    }

    private static void removeBook(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter book title: ");
        String titleToRemove = scanner.nextLine();
        boolean removed = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(titleToRemove)) {
                books.remove(book);
                System.out.println("Book '" + titleToRemove + "' removed.");
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Book '" + titleToRemove + "' not found.");
        }
    }

    private static void viewAllMembers() {
        System.out.println("All Members:");

        for (Member member : members) {
            System.out.println(member);

            System.out.println("Issued Books: ");
            List<Integer> issuedBooks = member.getIssuedBooks();
            for (Integer bookId : issuedBooks) {
                for (Book book : books) {
                    if (book.getBookId() == bookId) {
                        System.out.println("- " + book.getTitle() + " (ID: " + book.getBookId() + ")");
                    }
                }
            }

            double totalFine = 0;
            Map<Integer, Date> dueDates = member.getDueDates();
            for (Integer bookId : issuedBooks) {
                Date dueDate = dueDates.get(bookId);
                Date currentDate = new Date();

                if (currentDate.after(dueDate)) {
                    // Calculate fine
                    long diff = currentDate.getTime() - dueDate.getTime();
                    long seconds = diff / 1000; // seconds
                    double fine = seconds * 3; // 3 rupees per second
                    totalFine += fine;
                }
            }

            System.out.println("Total Fine: " + totalFine);
            System.out.println();
        }
    }

    private static void viewAllBooks() {
        System.out.println("All Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void memberMenu(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your contact number: ");
        String contactNo = scanner.nextLine();

        Member loggedInMember = null;
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name) && member.getContactNo().equals(contactNo)) {
                loggedInMember = member;
                break;
            }
        }

        if (loggedInMember != null) {
            System.out.println("Welcome, " + loggedInMember.getName() + " (ID: " + loggedInMember.getId() + ")");
            int choice;
            do {
                System.out.println("Member Menu:");
                System.out.println("1. List All Available Books");
                System.out.println("2. List My Books");
                System.out.println("3. Issue a Book");
                System.out.println("4. Return a Book");
                System.out.println("5. Pay Late Fine");
                System.out.println("6. Back");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                case 1:
                    listAvailableBooks();
                    break;
                case 2:
                    listMyBooks(loggedInMember);
                    break;
                case 3:
                    issueBook(loggedInMember, scanner);
                    break;
                case 4:
                    returnBook(loggedInMember, scanner);
                    break;
                case 5:
                    payLateFine(loggedInMember, scanner);
                    break;
                case 6:
                    System.out.println("Returning to previous menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);
        } else {
            System.out.println("Member not found. Please check your name and contact number.");
        }
    }

    private static void listAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.getCopies() > 0) {
                System.out.println(book);
            }
        }
    }

    private static void listMyBooks(Member member) {
        System.out.println("My Books (ID: " + member.getId() + "):");
        List<Integer> issuedBooks = member.getIssuedBooks();
        boolean hasBooks = false;

        for (Book book : books) {
            if (issuedBooks.contains(book.getBookId())) {
                System.out.println("Book ID: " + book.getBookId() + ", Title: " + book.getTitle() + ", Author: "
                                   + book.getAuthor());
                hasBooks = true;
            }
        }

        if (!hasBooks) {
            System.out.println("You have not issued any books.");
        }
    }

    private static void issueBook(Member member, Scanner scanner) {


        if(member.getTotalFine() > 0) {
            System.out.println("You have an outstanding fine of " + member.getTotalFine());
            System.out.println("Please pay the fine before issuing a new book.");
            return;
        }


        if(member.getIssuedBooks().size() >= 2) {
            System.out.println("You have already issued the maximum of 2 books.");
            return;
        }




        System.out.println("Available Books (with copies >= 1):");
        ArrayList<Book> availableBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getCopies() >= 1) {
                availableBooks.add(book);
                System.out.println("Book ID: " + book.getBookId() + ", Title: " + book.getTitle() + ", Author: "
                                   + book.getAuthor());
            }
        }

        if (availableBooks.isEmpty()) {
            System.out.println("No books available for issue.");
        } else {
            System.out.print("Enter the Book ID you want to issue: ");
            int bookIdToIssue = scanner.nextInt();

            Book bookToIssue = null;
            for (Book book : availableBooks) {
                if (book.getBookId() == bookIdToIssue) {
                    bookToIssue = book;
                    break;
                }
            }

            if (bookToIssue != null) {
                // Calculate due date (10 seconds from the current time)
                long currentTimeMillis = System.currentTimeMillis();
                long dueTimeMillis = currentTimeMillis + (10L * 1000); // 10 seconds in milliseconds
                Date dueDate = new Date(dueTimeMillis);

                // Assign the book to the member with the due date
                member.issueBook(bookToIssue.getBookId(), dueDate);
                bookToIssue.decreaseCopies();
                System.out.println(
                    "Book '" + bookToIssue.getTitle() + "' issued successfully to " + member.getName() + ".");
                System.out.println("Due Date: " + dueDate);
            } else {
                System.out.println("Invalid Book ID. Please try again.");
            }
        }
    }


    private static void returnBook(Member member, Scanner scanner) {
        List<Integer> issuedBooks = member.getIssuedBooks();

        if (issuedBooks.isEmpty()) {
            System.out.println("You have not issued any books.");
            return;
        }

        System.out.println("Books issued to you (ID: " + member.getId() + "):");

        for (Integer bookId : issuedBooks) {
            for (Book book : books) {
                if (book.getBookId() == bookId) {
                    System.out.println("Book ID: " + book.getBookId() + ", Title: " + book.getTitle() + ", Author: "
                                       + book.getAuthor());
                }
            }
        }

        System.out.print("Enter the Book ID to return: ");
        int bookIdToReturn = scanner.nextInt();

        boolean bookFound = false;

        for (Integer bookId : issuedBooks) {
            if (bookId == bookIdToReturn) {
                bookFound = true;
                break;
            }
        }

        if (bookFound) {
            for (Book book : books) {
                if (book.getBookId() == bookIdToReturn) {
                    Date dueDate = member.getDueDates().get(bookIdToReturn);
                    Date currentDate = new Date(); // Get the current date/time

                    if (currentDate.after(dueDate)) {
                        // Calculate the number of seconds the book is overdue
                        long millisecondsOverdue = currentDate.getTime() - dueDate.getTime();
                        int secondsOverdue = (int) (millisecondsOverdue / 1000);

                        // Calculate the late fee (3 rupees per second overdue)
                        double lateFee = secondsOverdue * 3.0; // Assuming 3 rupees per second late fee

                        // In returnBook()


                        double fine = lateFee;

                        member.setTotalFine(member.getTotalFine() + fine);
                        member.setTotalFine(member.getTotalFine() + lateFee);
                        book.increaseCopies(); // Add 1 to copies
                        member.returnBook(bookIdToReturn); // Remove the book from the member's issued books list
                        System.out.println("Book '" + book.getTitle() + "' returned successfully.");
                        System.out.println("Late fee of " + lateFee + " rupees charged for "
                                           + secondsOverdue + " days overdue.");
                    } else {
                        // No late fee if the book is returned on time (or early)
                        book.increaseCopies(); // Add 1 to copies
                        member.returnBook(bookIdToReturn); // Remove the book from the member's issued books list
                        System.out.println("Book '" + book.getTitle() + "' returned successfully.");
                    }
                    break;
                }
            }
        } else {
            System.out.println("Invalid Book ID. Please try again.");
        }
    }




    private static void payLateFine(Member member, Scanner scanner) {

        double totalFine = member.getTotalFine();

        System.out.println("Total fine for member " + member.getName() + ": " + totalFine);

        System.out.print("Enter amount to pay: ");
        double amountToPay = scanner.nextDouble();

        if(amountToPay <= totalFine) {

            member.setTotalFine(totalFine - amountToPay);

            System.out.println("Paid " + amountToPay + ". Remaining fine is " + member.getTotalFine());

        } else {

            System.out.println("Invalid amount. Please enter an amount less than or equal to total fine.");

        }

    }


}