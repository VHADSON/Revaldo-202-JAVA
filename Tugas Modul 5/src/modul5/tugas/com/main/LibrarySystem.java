package modul5.tugas.com.main;

import modul5.tugas.books.*;
import modul5.tugas.data.Admin;
import modul5.tugas.data.Student;
import modul5.tugas.exception.custom.IllegalAdminAccess;

import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    public static ArrayList<Book> daftarBuku = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        daftarBuku.add(new StoryBook("SB32F", "OPM", 17, "Story", "Genos"));
        daftarBuku.add(new HistoryBook("HB324", "HxH", 2, "History", "Gon"));
        daftarBuku.add(new TextBook("TB323", "OP", 4, "Text", "Zoro"));

        studentList.add(new Student("202310370311202", "Revaldo", "Teknik", "Informatika"));
        studentList.add(new Student("202310370311203", "Reval", "Teknik", "DKV"));
        studentList.add(new Student("202310370311204", "Valdo", "Teknik", "Sistem Informasi"));

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login sebagai Mahasiswa");
            System.out.println("2. Login sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih antara (1-3): ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Masukkan NIM : ");
                        String nimStudent = scanner.next();
                        scanner.nextLine();
                        while (true) {
                            if (nimStudent.length() != 15) {
                                System.out.print("Nim Harus 15 Digit!!!\n");
                                System.out.print("Masukkan NIM : ");
                                nimStudent = scanner.nextLine();
                            } else if (checkNim(nimStudent)) {
                                Student student = new Student(nimStudent);
                                student.login();
                                break;
                            } else {
                                System.out.println("Nim tidak terdaftar!");
                                break;
                            }
                        }
                        break;
                    case 2:
                        Admin admin = new Admin();
                        admin.login();
                        break;
                    case 3:
                        System.out.println("Terima kasih semoga puas dengan pelayanan kami");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (IllegalAdminAccess e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }

    public static boolean checkNim(String nim) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }
}
