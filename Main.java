import java.util.ArrayList;
import java.util.Scanner;

class Mahasiswa {
    private String nama;
    private String NIM;
    private String jurusan;
    private String prodi;

    public Mahasiswa(String nama, String NIM, String jurusan, String prodi) {
        this.nama = nama;
        if (NIM.length() == 15)
            this.NIM = NIM;
        else
            throw new IllegalArgumentException("NIM harus terdiri dari 15 karakter");
        this.jurusan = jurusan;
        this.prodi = prodi;
    }

    // Konstruktor kosong untuk menangani kasus di mana hanya NIM yang diberikan
    public Mahasiswa(String NIM) {
        this.NIM = NIM;
    }

    public String getNama() {
        return nama;
    }

    public String getNIM() {
        return NIM;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getProdi() {
        return prodi;
    }

    public void menuMahasiswa(ArrayList<Buku> daftarBuku) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("====== Menu Mahasiswa ======");
            System.out.println("1. Buku Terpinjam");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Log Out");
            System.out.print("Pilih Opsi (1-3) : ");
            pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    // Implementasi Buku Terpinjam
                    break;
                case 2:
                    tampilkanBuku(daftarBuku);
                    System.out.println("Input ID Buku yang Ingin Dipinjam (Ketik 99 Untuk Kembali ke-Menu Awal)");
                    System.out.print("Input : ");
                    String id = scanner.next();
                    if (id.equals("99")) {
                        System.out.println("Kembali ke Menu Awal...");
                    } else {
                        pinjamBuku(id, daftarBuku);
                    }
                    break;
                case 3:
                    System.out.println("Keluar dari Sistem...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
            }
        } while (pilihan != 3);
    }

    private void tampilkanBuku(ArrayList<Buku> daftarBuku) {
        System.out.println("===== Daftar Buku =====");
        for (Buku buku : daftarBuku) {
            System.out.println("ID Buku    : " + buku.getIdBuku());
            System.out.println("Judul Buku : " + buku.judul);
            System.out.println("Penulis    : " + buku.penulis);
            System.out.println("Genre      : " + buku.genre);
            System.out.println("Stok Buku  : " + buku.getStokBuku());
            System.out.println("---------------------------------");
        }
    }

    private void pinjamBuku(String id, ArrayList<Buku> daftarBuku) {
        for (Buku buku : daftarBuku) {
            if (buku.getIdBuku().equals(id)) {
                buku.kurangiStok();
                System.out.println("Buku berhasil dipinjam.");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan.");
    }
}

class Buku {
    private String idBuku;
    String judul;
    String penulis;
    String genre;
    private int stokBuku;

    public Buku(String idBuku, String judul, String penulis, String genre, int stokBuku) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.genre = genre;
        this.stokBuku = stokBuku;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public int getStokBuku() {
        return stokBuku;
    }

    public void kurangiStok() {
        if (stokBuku > 0) {
            stokBuku--;
        } else {
            System.out.println("Buku tidak tersedia saat ini.");
        }
    }
}

class Perpustakaan {
    private ArrayList<Mahasiswa> mahasiswas;
    private ArrayList<Buku> daftarBuku;
    private Scanner scanner;

    public Perpustakaan() {
        mahasiswas = new ArrayList<>();
        daftarBuku = new ArrayList<>();
        scanner = new Scanner(System.in);
        // Tambahkan beberapa contoh mahasiswa dan buku
        mahasiswas.add(new Mahasiswa("Revaldo Ramadana", "202310370311202", "Teknik", "Informatika"));
        mahasiswas.add(new Mahasiswa("Afla Abdi", "202310370311112", "Teknik", "Informatika"));
        mahasiswas.add(new Mahasiswa("Ahmad Nizar", "202310370311113", "Teknik", "Informatika"));

        daftarBuku.add(new Buku("388c-e681-9999", "Pemrograman Java OOP", "valdo", "Ilmu Pengetahuan", 10));
        daftarBuku.add(new Buku("ed90-be30-9999", "Laskar Pelangi", "Afla", "Fiksi", 20));
        daftarBuku.add(new Buku("d95e-0c4a-9999", "Matematika Diskrit", "Nizar", "Ilmu Pengetahuan", 30));
    }

    public void tampilkanMenu() {
        int pilihan;
        do {
            System.out.println("====== Sistem Perpustakaan ======");
            System.out.println("1. Login Sebagai Mahasiswa");
            System.out.println("2. Login Sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih Opsi (1-3): ");
            pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    loginMahasiswa();
                    break;
                case 2:
                    loginAdmin();
                    break;
                case 3:
                    keluar();
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
            }
        } while (pilihan != 3);
    }

    private void loginMahasiswa() {
        System.out.print("Masukkan NIM : ");
        String NIM = scanner.next();
        if (cekNIM(NIM)) {
            Mahasiswa mahasiswa = new Mahasiswa(NIM);
            mahasiswa.menuMahasiswa(daftarBuku);
        } else {
            System.out.println("User Tidak Ditemukan!!");
        }
    }

    private boolean cekNIM(String NIM) {
        for (Mahasiswa mahasiswa : mahasiswas) {
            if (mahasiswa.getNIM().equals(NIM)) {
                return true;
            }
        }
        return false;
    }

    private void loginAdmin() {
        System.out.print("Masukkan Username (admin) : ");
        String username = scanner.next();
        System.out.print("Masukkan Password (admin) : ");
        String pw = scanner.next();
        if (username.equals("admin") && pw.equals("admin")) {
            Admin admin = new Admin();
            admin.menuAdmin(mahasiswas);
        } else {
            System.out.println("Admin Tidak Ditemukan!!");
        }
    }

    private void keluar() {
        System.out.println("Terima Kasih!!!");
        System.exit(0);
    }
}

class Admin {
    public void menuAdmin(ArrayList<Mahasiswa> mahasiswas) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("===== Menu Admin =====");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tampilkan Daftar Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilih Opsi (1-3): ");
            pilihan = scanner.nextInt();
            switch (pilihan) {
                case 1:
                    tambahMahasiswa(mahasiswas);
                    break;
                case 2:
                    tampilkanMahasiswa(mahasiswas);
                    break;
                case 3:
                    System.out.println("Keluar dari Sistem...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
            }
        } while (pilihan != 3);
    }

    private void tambahMahasiswa(ArrayList<Mahasiswa> mahasiswas) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama     : ");
        String nama = scanner.nextLine();
        System.out.print("NIM      : ");
        String NIM = scanner.next();
        System.out.print("Jurusan  : ");
        String jurusan = scanner.next();
        System.out.print("Prodi    : ");
        String prodi = scanner.next();
        mahasiswas.add(new Mahasiswa(nama, NIM, jurusan, prodi));
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    private void tampilkanMahasiswa(ArrayList<Mahasiswa> mahasiswas) {
        System.out.println("===== Daftar Mahasiswa =====");
        for (Mahasiswa mahasiswa : mahasiswas) {
            System.out.println("Nama     : " + mahasiswa.getNama());
            System.out.println("NIM      : " + mahasiswa.getNIM());
            System.out.println("Jurusan  : " + mahasiswa.getJurusan());
            System.out.println("Prodi    : " + mahasiswa.getProdi());
            System.out.println("---------------------------------");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Perpustakaan perpustakaan = new Perpustakaan();
        perpustakaan.tampilkanMenu();
    }
}
