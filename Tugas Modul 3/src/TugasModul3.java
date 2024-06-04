import java.util.ArrayList;
import java.util.Scanner;

public class TugasModul3 {
    private static ArrayList<Book> daftarBuku = new ArrayList<>();
    private static ArrayList<Student> daftarMahasiswa = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Library System Login");
            System.out.println("1. Log in as Student");
            System.out.println("2. Log in as Admin");
            System.out.println("3. Exit");
            System.out.print("Choose between (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter your student ID : ");
                    String nimStudent = scanner.next();
                    if (nimStudent.length() != 15) {
                        System.out.println("ID not valid, max 15 numbers.");
                        break;
                    }
                    Student student = new Student(nimStudent);
                    student.login();
                    break;
                case 2:
                    Admin admin = new Admin();
                    admin.login();
                    break;
                case 3:
                    System.out.println("Thank you");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Not valid");
            }
        }
    }

    public static abstract class User {
        private String nim;

        public User(String nim) {
            this.nim = nim;
        }

        public String getNim() {
            return nim;
        }

        public abstract void displayBooks();
    }

    public static class Student extends User {
        private String name;
        private String faculty;
        private String studyProgram;
        private ArrayList<Book> borrowedBooks = new ArrayList<>();

        public Student(String nim) {
            super(nim);
        }

        public Student(String nim, String name, String faculty, String studyProgram) {
            super(nim);
            this.name = name;
            this.faculty = faculty;
            this.studyProgram = studyProgram;
        }

        public void login() {
            if (checkNim(getNim())) {
                System.out.println("Succesfully login as a student");
                menuStudent();
            } else {
                System.out.println("Non valid student ID or not found");
            }
        }

        private boolean checkNim(String nim) {
            return nim.length() == 15;
        }

        private void menuStudent() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Student Dashboard");
                System.out.println("1. Show Books");
                System.out.println("2. Borrow a Book");
                System.out.println("3. Return a Book");
                System.out.println("4. Logout");
                System.out.print("Choose between (1-4): ");
                int choice = scanner.nextInt();


                switch (choice) {
                    case 1:
                        displayBooks();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        logout();
                        return;
                    default:
                        System.out.println("Non valid choice");
                }
            }
        }

        @Override
        public void displayBooks() {
            // Implementasi menampilkan daftar buku
            System.out.println("List of an available book:");
            System.out.println("================================================================");
            System.out.println("|| No. || Book id || Book name || Author || Category || Stock ||");
            int index = 1;
            for (Book book : daftarBuku) {
                if (book != null) {
                    System.out.printf("|| %-4d || %-8s || %-20s || %-15s || %-10s || %-5d ||%n", index, book.getIdBuku(), book.getJudul(), book.getAuthor(), book.getCategory(), book.getStok());
                    System.out.println("================================================================");
                    index++;
                }
            }
        }

        private void borrowBook() {
            System.out.print("Enter book id that you want to borrow: ");
            String bookId = scanner.next();
            Book selectedBook = findBookById(bookId);
            if (selectedBook != null && selectedBook.getStok() > 0) {
                selectedBook.setStok(selectedBook.getStok() - 1);
                borrowedBooks.add(selectedBook);
                System.out.println("Succesfully borrowed: " + selectedBook.getJudul());
            } else {
                System.out.println("Book is out of stock or non valid ID");
            }
        }

        private void returnBook() {
            if (borrowedBooks.isEmpty()) {
                System.out.println("You are not borrowing any books.");
                return;
            }
            System.out.println("Book that you borrowed:");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                System.out.println((i + 1) + ". " + borrowedBooks.get(i).getJudul());
            }

            System.out.print("Choose the book to return (number): ");
            int choice = scanner.nextInt();
            if (choice > 0 && choice <= borrowedBooks.size()) {
                Book returnedBook = borrowedBooks.remove(choice - 1);
                returnedBook.setStok(returnedBook.getStok() + 1);
                System.out.println("Book " + returnedBook.getJudul() + " Succesfully returned.");
            } else {
                System.out.println("Not valid choice.");
            }
        }

        private Book findBookById(String id) {
            for (Book book : daftarBuku) {
                if (book != null && book.getIdBuku().equals(id)) {
                    return book;
                }
            }
            return null;
        }

        private void logout() {
            System.out.println("Succesfully logout.");
        }

        public String getName() {
            return name;
        }

        public String getFaculty() {
            return faculty;
        }

        public String getStudyProgram() {
            return studyProgram;
        }

        public ArrayList<Book> getBorrowedBooks() {
            return borrowedBooks;
        }
    }

    public static class Admin extends User {
        private ArrayList<Student> studentList = new ArrayList<>();

        public Admin() {
            super("admin");
        }

        public void login() {
            System.out.print("Enter an admin Username (admin): ");
            String username = scanner.next();
            System.out.print("Enter an password (admin): ");
            String password = scanner.next();
            if (isAdmin(username, password)) {
                System.out.println("Succesfully login as an admin");
                menuAdmin();
            } else {
                System.out.println("Admin user is not found");
            }
        }

        private boolean isAdmin(String username, String password) {
            // Implementasi verifikasi admin
            return username.equals("admin") && password.equals("admin");
        }

        private void menuAdmin() {
            while (true) {
                System.out.println("Admin Dashboard");
                System.out.println("1. Add Student");
                System.out.println("2. Display Students");
                System.out.println("3. Input Book");
                System.out.println("4. Display Book List");
                System.out.println("5. Logout");
                System.out.print("Choose between (1-5): ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayStudents();
                        break;
                    case 3:
                        inputBook();
                        break;
                    case 4:
                        displayBooks();
                        break;
                    case 5:
                        System.out.println("Succesfully logout.");
                        return;
                    default:
                        System.out.println("Non valid choice");
                }
            }
        }

        private void addStudent() {
            // Implementasi penambahan mahasiswa
            System.out.println("Adding an student...");
            System.out.print("Enter your name: ");
            scanner.nextLine(); // Menggunakan nextLine() untuk membaca nama dengan dua kata
            String name =scanner.nextLine();
            System.out.print("Enter your ID: ");
            String nim = scanner.next();

            if (nim.length() != 15) {
                System.out.println("Non valid ID, max 15 numbers");
                return;
            }
            scanner.nextLine(); // Membersihkan karakter newline
            System.out.print("Enter faculy: ");
            String faculty = scanner.nextLine();
            System.out.print("Major: ");
            String studyProgram = scanner.nextLine();
            studentList.add(new Student(nim, name, faculty, studyProgram));
            System.out.println("Student with ID " + nim + " succesfully added to the system.");
        }




        private void inputBook() {
            // Implementasi input buku
            System.out.println("Inserting book...");
            System.out.println("Choose the type of book:");
            System.out.println("1. History Book");
            System.out.println("2. Story Book");N
            System.out.println("3. Text Book");
            System.out.print("Choose the type of book (1-3): ");

            int bookType = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline

            String idBuku, judul, author,category = new String();
            int stok;
            System.out.print("Enter the book title: ");
            judul = scanner.nextLine();
            System.out.print("Enter the book author: ");
            author = scanner.nextLine();
            if (bookType == 1){
                category = "History";
            } else if (bookType == 2){
                category = "Story";
            } else if (bookType == 3){
                category = "Text";
            }
            System.out.print("Masukkan stok buku: ");
            stok = scanner.nextInt();
            scanner.nextLine(); // membersihkan newline

            switch (bookType) {
                case 1:
                    idBuku = generateId("HB");
N                    daftarBuku.add(new HistoryBook(idBuku, judul, stok,category, author));
                    break;
                case 2:
                    idBuku = generateId("SB");
                    daftarBuku.add(new StoryBook(idBuku, judul, stok ,category, author));
                    break;
                case 3:
                    idBuku = generateId("TB");
                    daftarBuku.add(new TextBook(idBuku, judul, stok ,category, author));
                    break;
                default:
                    System.out.println("Non valid choice");
                    return;
            }
            System.out.println("Book has ben added.");
        }

        @Override
        public void displayBooks() {
            // Implementasi menampilkan daftar buku
            System.out.println("List of an available books:");
            System.out.println("==============================================================================");
            System.out.println("||\tNo.\t||\tBook Id\t||\tBook Name\t||\tAuthor\t||\tCategory\t||\tStock\t||");

            int index = 1;
            for (Book book : daftarBuku) {
                if (book != null) {
                    System.out.println("||\t" + index + "\t||\t" + book.idBuku + "\t||\t" + book.getJudul() + "\t\t\t||\t" + book.getAuthor() + "\t\t||\t" + book.getCategory() + "\t\t||\t" + book.getStok() + "\t\t||\t");
                    System.out.println("==============================================================================");
                    index ++;
                }
            }
        }

        private void displayStudents() {
            // Implementasi menampilkan daftar mahasiswa
            System.out.println("List of students:");
            for (Student student : studentList) {
                System.out.println("\nName: " + student.getName());
                System.out.println("ID: " + student.getNim());
                System.out.println("Faculty: " + student.getFaculty());
                System.out.println("Major: " + student.getStudyProgram());
                if (!student.getBorrowedBooks().isEmpty()) {
                    System.out.println("  Borrowed books:");
                    for (Book book : student.getBorrowedBooks()) {
                        System.out.println("    - " + book.getJudul());
                    }
                }
            }
        }

        private String generateId(String prefix) {
            // Implementasi pembuatan ID unik
            // Contoh: HB001, SB002, TB003, dst.
            int nextId = daftarBuku.size() + 1;
            return prefix + String.format("%03d", nextId);
        }
    }

    public static class Book {
        private String idBuku;
        private String judul;
        private int stok;
        private String author;
        private String category;

        public Book(String idBuku, String judul, int stok, String category , String author) {
            this.idBuku = idBuku;
            this.judul = judul;
            this.stok = stok;
            this.category = category;
            this.author = author;
        }

        public String getIdBuku() {
            return idBuku;
        }

        public String getJudul() {
            return judul;
        }

        public int getStok() {
            return stok;
        }

        public String getCategory() {
            return category;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }

        public String getAuthor() {
            return author;
        }
    }
}

class HistoryBook extends TugasModul3.Book {
    public HistoryBook(String idBuku, String judul, int stok, String category , String author) {
        super(idBuku, judul, stok, category, author);
    }
}

class StoryBook extends TugasModul3.Book {
    public StoryBook(String idBuku, String judul, int stok, String category , String author) {
        super(idBuku, judul, stok, category, author);
    }
}

class TextBook extends TugasModul3.Book {
    public TextBook(String idBuku, String judul, int stok, String category , String author) {
        super(idBuku, judul, stok, category, author);
    }
}



abstract class User {
    private String nim;

    public User(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public abstract void displayBooks(TugasModul3.Book[] bookList);

    public abstract void displayBooks(ArrayList<TugasModul3.Book>bookList);
}