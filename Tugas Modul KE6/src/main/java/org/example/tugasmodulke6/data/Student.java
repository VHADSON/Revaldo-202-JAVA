package org.example.tugasmodulke6.data;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.tugasmodulke6.books.Book;
import org.example.tugasmodulke6.data.User;
import org.example.tugasmodulke6.util.iMenu;

import java.util.ArrayList;

import static org.example.tugasmodulke6.com.main.LibrarySystem.*;

public class Student extends User implements iMenu {

    private String nim;
    private String faculty;
    private String studyProgram;

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

    @Override
    public void menu() {
        // Ini adalah implementasi dari metode menu() yang diperlukan oleh iMenu
    }

    public void login(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8; ");
        Scene scene = new Scene(root, 600, 600);

        Label label = new Label("Menu Mahasiswa");
        label.setStyle("-fx-text-fill: white;");

        Button borrowBookButton = new Button("Pinjam Buku");
        borrowBookButton.setPrefWidth(200);
        borrowBookButton.setPrefHeight(25);
        borrowBookButton.setFont(new Font("Tahoma", 14));

        Button displayBooksButton = new Button("Tampilkan Daftar Buku");
        displayBooksButton.setPrefWidth(200);
        displayBooksButton.setPrefHeight(25);
        displayBooksButton.setFont(new Font("Tahoma", 14));

        Button logoutButton = new Button("Logout");
        logoutButton.setPrefWidth(200);
        logoutButton.setPrefHeight(25);
        logoutButton.setFont(new Font("Tahoma", 14));

        borrowBookButton.setOnAction(event -> borrowBook(stage));
        displayBooksButton.setOnAction(event -> displayBooks(stage));
        logoutButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(label, borrowBookButton, displayBooksButton, logoutButton);

        stage.setScene(scene);
    }

    private void borrowBook(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8; ");
        Scene scene = new Scene(root, 600, 600);

        TextField idBukuField = new TextField();
        idBukuField.setFont(new Font("Tahoma", 14));
        idBukuField.setMinWidth(200);
        idBukuField.setMinHeight(30);
        idBukuField.setMaxWidth(200);
        idBukuField.setMaxHeight(30);

        idBukuField.setPromptText("Masukkan ID buku yang ingin dipinjam");
        Button borrowButton = new Button("Pinjam");
        borrowButton.setPrefWidth(200);
        borrowButton.setPrefHeight(25);
        borrowButton.setFont(new Font("Tahoma", 14));

        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(25);
        backButton.setFont(new Font("Tahoma", 14));

        borrowButton.setOnAction(event -> {
            String idBuku = idBukuField.getText();
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
                    showSuccessDialog("Success", "Buku " + bookToBorrow.getJudul() + " berhasil dipinjam.");
                } else {
                    showErrorDialog("Error", "Stok buku " + bookToBorrow.getJudul() + " sedang kosong.");
                }
            } else {
                showErrorDialog("Error", "Buku dengan ID " + idBuku + " tidak ditemukan.");
            }
        });

        backButton.setOnAction(event -> login(stage));

        root.getChildren().addAll(idBukuField, borrowButton, backButton);

        stage.setScene(scene);
    }

    private void displayBooks(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8; ");
        Scene scene = new Scene(root, 600, 600);

        for (Book book : daftarBuku) {
            root.getChildren().add(new Label("ID Buku: " + book.getIdBuku()));
            root.getChildren().add(new Label("Judul: " + book.getJudul()));
            root.getChildren().add(new Label("Author: " + book.getAuthor()));
            root.getChildren().add(new Label("Category: " + book.getCategory()));
            root.getChildren().add(new Label("Stok: " + book.getStok()));
            root.getChildren().add(new Label(""));
        }

        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(25);
        backButton.setFont(new Font("Tahoma", 14));

        backButton.setOnAction(event -> login(stage));

        root.getChildren().add(backButton);

        stage.setScene(scene);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
