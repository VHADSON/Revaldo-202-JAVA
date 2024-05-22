package modul5.tugas.data;

import modul5.tugas.books.*;
import modul5.tugas.util.iMenu;

import java.util.ArrayList;
import java.util.Scanner;

import static modul5.tugas.com.main.LibrarySystem.*;

public class Student extends User implements iMenu {

    private String nim;
    private String faculty;
    private String studyProgram;

    Scanner scanner = new Scanner(System.in);

    public Student(String nim) {
        super("student");
        this.nim = nim;
    }

    public Student(String nim, String name, String faculty, String studyProgram) {
        super(name);
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void login() {
        System.out.println("Login berhasil sebagai Mahasiswa");
        menu();
    }

    public void menu() {
        try {
            while (true) {
                System.out.println("Menu Mahasiswa");
                System.out.println("1. Pinjam Buku");
                System.out.println("2. Tampilkan Daftar Buku");
                System.out.println("3. Logout");
                System.out.print("Pilih antara (1-3): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        borrowBook();
                        break;
                    case 2:
                        displayBooks(daftarBuku);
                        break;
                    case 3:
                        System.out.println("Logout berhasil.");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public void addStudent() {
        throw new UnsupportedOperationException("Mahasiswa tidak bisa menambahkan mahasiswa.");
    }

    private void borrowBook() {
        // Implementasi peminjaman buku
        System.out.print("Masukkan ID buku yang ingin dipinjam: ");
        String idBuku = scanner.next();
        Book bookToBorrow = null;

        for (Book book : daftarBuku) {
            if (book.getIdBuku().equals(idBuku)) {
                bookToBorrow = book;
                break;
            }
        }

        if (bookToBorrow != null) {
            if (bookToBorrow.getStok() > 0) {
                bookToBorrow.setStok(bookToBorrow.getStok() - 1);
                System.out.println("Buku " + bookToBorrow.getJudul() + " berhasil dipinjam.");
            } else {
                System.out.println("Stok buku " + bookToBorrow.getJudul() + " sedang kosong.");
            }
        } else {
            System.out.println("Buku dengan ID " + idBuku + " tidak ditemukan.");
        }
    }

    private void displayBooks(ArrayList<Book> daftarBuku) {
        // Implementasi menampilkan daftar buku
        for (Book buku : daftarBuku) {
            System.out.println("ID Buku: " + buku.getIdBuku());
            System.out.println("Judul: " + buku.getJudul());
            System.out.println("Author: " + buku.getAuthor());
            System.out.println("Category: " + buku.getCategory());
            System.out.println("Stok: " + buku.getStok());
            System.out.println();
        }
    }
}
