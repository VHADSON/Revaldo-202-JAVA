import java.util.ArrayList;
import java.util.Scanner;

class User {
    public void displayBooks(ArrayList<Book> books) {
        System.out.println("===== Daftar Buku =====");
        for (Book book : books) {
            System.out.println("ID Buku    : " + book.getBookId());
            System.out.println("Judul Buku : " + book.getTitle());
            System.out.println("Penulis    : " + book.getAuthor());
            System.out.println("Kategori   : " + book.getCategory());
            System.out.println("Stok Buku  : " + book.getStock());
            System.out.println("-------------------------");
        }
    }
}

class Student extends User {
    private String name;
    private String nim;
    private String faculty;
    private String programStudi;
    private ArrayList<Book> borrowedBooks;

    public Student(String name, String nim, String faculty, String programStudi) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.programStudi = programStudi;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public void displayInfo() {
        System.out.println("===== Data Mahasiswa =====");
        System.out.println("Nama         : " + name);
        System.out.println("NIM          : " + nim);
        System.out.println("Fakultas     : " + faculty);
        System.out.println("Program Studi: " + programStudi);
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Belum ada buku yang dipinjam.");
        } else {
            System.out.println("===== Buku yang Dipinjam =====");
            displayBooks(borrowedBooks);
        }
    }

    public void logout() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Tidak ada buku yang dipinjam. Kembali ke menu utama.");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Batalkan Peminjaman");
            System.out.println("2. Proses Peminjaman");
            System.out.print("Pilih Opsi (1-2): ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    borrowedBooks.clear();
                    System.out.println("Peminjaman buku dibatalkan. Kembali ke menu utama.");
                    break;
                case 2:
                    System.out.println("Proses peminjaman berhasil. Kembali ke menu utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Kembali ke menu utama.");
            }
        }
    }

    @Override
    public void displayBooks(ArrayList<Book> books) {
        super.displayBooks(books);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Berapa hari Anda ingin meminjam buku? ");
        int days = scanner.nextInt();
        System.out.println("Proses peminjaman berhasil. Terima kasih!");
        // Kurangi stok buku sesuai dengan buku yang dipinjam
        for (Book book : books) {
            book.decreaseStock();
        }
        // Simpan buku yang dipinjam oleh mahasiswa
        borrowedBooks.addAll(books);
    }

    public void returnBooks(ArrayList<Book> books) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Buku yang Dipinjam =====");
        displayBooks(borrowedBooks);
        System.out.print("Masukkan ID Buku yang akan dikembalikan: ");
        String bookId = scanner.next();
        for (Book book : borrowedBooks) {
            if (book.getBookId().equals(bookId)) {
                book.increaseStock();
                borrowedBooks.remove(book);
                System.out.println("Buku berhasil dikembalikan.");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan dalam daftar peminjaman.");
    }
}

class Admin extends User {
    private ArrayList<Student> studentList;

    public Admin() {
        studentList = new ArrayList<>();
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("NIM: ");
        String nim = scanner.next();
        if (nim.length() != 15) {
            System.out.println("NIM tidak valid.");
            return;
        }
        System.out.print("Fakultas: ");
        String faculty = scanner.next();
        System.out.print("Program Studi: ");
        String programStudi = scanner.next();
        studentList.add(new Student(name, nim, faculty, programStudi));
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    public void inputBook(ArrayList<Book> books) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pilih jenis buku:");
        System.out.println("1. Story Book");
        System.out.println("2. History Book");
        System.out.println("3. Text Book");
        System.out.print("Pilih opsi (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Judul: ");
        String title = scanner.nextLine();
        System.out.print("Penulis: ");
        String author = scanner.nextLine();
        System.out.print("Stok: ");
        int stock = scanner.nextInt();
        String bookId = generateId(); // Generate unique ID
        switch (choice) {
            case 1:
                books.add(new StoryBook(bookId, title, author, stock));
                break;
            case 2:
                books.add(new HistoryBook(bookId, title, author, stock));
                break;
            case 3:
                books.add(new TextBook(bookId, title, author, stock));
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
        System.out.println("Buku berhasil ditambahkan.");
    }

    @Override
    public void displayBooks(ArrayList<Book> books) {
        super.displayBooks(books);
    }

    public void displayStudents() {
        System.out.println("===== Daftar Mahasiswa =====");
        for (Student student : studentList) {
            System.out.println("Nama         : " + student.getName());
            System.out.println("NIM          : " + student.getNim());
            System.out.println("Fakultas     : " + student.getFaculty());
            System.out.println("Program Studi: " + student.getProgramStudi());
            System.out.println("-----------------------------");
        }
    }

    public boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    private String generateId() {
        // Generate unique ID logic here
        return "UniqueID"; // Placeholder for demonstration
    }
}

class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int stock;

    public Book(String bookId, String title, String author, String category, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public void decreaseStock() {
        if (stock > 0) {
            stock--;
        } else {
            System.out.println("Stok buku habis.");
        }
    }

    public void increaseStock() {
        stock++;
    }
}

class HistoryBook extends Book {
    public HistoryBook(String bookId, String title, String author, int stock) {
        super(bookId, title, author, "History", stock);
    }
}

class StoryBook extends Book {
    public StoryBook(String bookId, String title, String author, int stock) {
        super(bookId, title, author, "Story", stock);
    }
}

class TextBook extends Book {
    public TextBook(String bookId, String title, String author, int stock) {
        super(bookId, title, author, "Text", stock);
    }
}

public class Main {
    public static void main(String[] args) {
        Perpustakaan perpustakaan = new Perpustakaan();
        perpustakaan.tampilkanMenu();
    }
}
