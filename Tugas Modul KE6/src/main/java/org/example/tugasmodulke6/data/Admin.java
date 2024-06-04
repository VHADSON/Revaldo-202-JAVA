package org.example.tugasmodulke6.data;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.tugasmodulke6.books.Book;
import javafx.scene.control.TextField;
import org.example.tugasmodulke6.books.HistoryBook;
import org.example.tugasmodulke6.books.StoryBook;
import org.example.tugasmodulke6.books.TextBook;
import org.example.tugasmodulke6.exception.custom.IllegalAdminAccess;
import org.example.tugasmodulke6.util.iMenu;

import java.util.ArrayList;
import java.util.Scanner;

import static org.example.tugasmodulke6.com.main.LibrarySystem.*;

public class Admin extends User implements iMenu {

    Scanner scanner = new Scanner(System.in);

    public Admin() {
        super("admin");
    }

    public void login(Stage stage) throws IllegalAdminAccess {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8; ");
        Scene scene = new Scene(root, 600, 600);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Masukkan Username (Admin)");
        usernameField.setFont(new Font("Tahoma", 10));
        usernameField.setMinWidth(200);
        usernameField.setMinHeight(30);
        usernameField.setMaxWidth(200);
        usernameField.setMaxHeight(30);

        PasswordField passwordField = new PasswordField();
        passwordField.setFont(new Font("Tahoma", 10));
        passwordField.setPromptText("Masukkan Password (Admin)");
        passwordField.setMinWidth(200);
        passwordField.setMinHeight(30);
        passwordField.setMaxWidth(200);
        passwordField.setMaxHeight(30);

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(200);
        loginButton.setPrefHeight(25);
        loginButton.setFont(new Font("Tahoma", 14));

        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(25);
        backButton.setFont(new Font("Tahoma", 14));

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (isAdmin(username, password)) {
                showAdminMenu(stage);
            } else {
                showErrorDialog("Error", "Invalid credentials");
            }
        });

        backButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll( usernameField, passwordField, loginButton, backButton);

        stage.setScene(scene);
    }

    private boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    private void showAdminMenu(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8; ");
        Scene scene = new Scene(root, 600, 600);

        Label label = new Label("Menu Admin");
        label.setStyle("-fx-text-fill: white;");

        Button addStudentButton = new Button("Tambah Mahasiswa");
        addStudentButton.setPrefWidth(200);
        addStudentButton.setPrefHeight(25);
        addStudentButton.setFont(new Font("Tahoma", 14));

        Button displayStudentsButton = new Button("Tampilkan Mahasiswa");
        displayStudentsButton.setPrefWidth(200);
        displayStudentsButton.setPrefHeight(25);
        displayStudentsButton.setFont(new Font("Tahoma", 14));

        Button inputBookButton = new Button("Input Buku");
        inputBookButton.setPrefWidth(200);
        inputBookButton.setPrefHeight(25);
        inputBookButton.setFont(new Font("Tahoma", 14));

        Button displayBooksButton = new Button("Tampilkan Daftar Buku");
        displayBooksButton.setPrefWidth(200);
        displayBooksButton.setPrefHeight(25);
        displayBooksButton.setFont(new Font("Tahoma", 14));

        Button logoutButton = new Button("Logout");
        logoutButton.setPrefWidth(200);
        logoutButton.setPrefHeight(25);
        logoutButton.setFont(new Font("Tahoma", 14));

        addStudentButton.setOnAction(event -> addStudent(stage));
        displayStudentsButton.setOnAction(event -> displayStudents(stage));
        inputBookButton.setOnAction(event -> inputBook(stage));
        displayBooksButton.setOnAction(event -> displayBooks(stage));
        logoutButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(label, addStudentButton, displayStudentsButton, inputBookButton, displayBooksButton, logoutButton);

        stage.setScene(scene);
    }

    private void addStudent(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8; ");
        Scene scene = new Scene(root, 600, 600);

        TextField nameField = new TextField();
        nameField.setPromptText("Masukkan Nama");
        nameField.setFont(new Font("Tahoma", 14));
        nameField.setMinWidth(200);
        nameField.setMinHeight(30);
        nameField.setMaxWidth(200);
        nameField.setMaxHeight(30);

        TextField nimField = new TextField();
        nimField.setPromptText("Masukkan NIM");
        nimField.setFont(new Font("Tahoma", 14));
        nimField.setMinWidth(200);
        nimField.setMinHeight(30);
        nimField.setMaxWidth(200);
        nimField.setMaxHeight(30);

        TextField facultyField = new TextField();
        facultyField.setPromptText("Masukkan Fakultas");
        facultyField.setFont(new Font("Tahoma", 14));
        facultyField.setMinWidth(200);
        facultyField.setMinHeight(30);
        facultyField.setMaxWidth(200);
        facultyField.setMaxHeight(30);

        TextField studyProgramField = new TextField();
        studyProgramField.setPromptText("Masukkan Program Studi");
        studyProgramField.setFont(new Font("Tahoma", 14));
        studyProgramField.setMinWidth(200);
        studyProgramField.setMinHeight(30);
        studyProgramField.setMaxWidth(200);
        studyProgramField.setMaxHeight(30);

        Button addButton = new Button("Tambah");
        addButton.setPrefWidth(200);
        addButton.setPrefHeight(25);
        addButton.setFont(new Font("Tahoma", 14));

        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(25);
        backButton.setFont(new Font("Tahoma", 14));

        addButton.setOnAction(event -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String faculty = facultyField.getText();
            String studyProgram = studyProgramField.getText();

            if (nim.length() == 15 && !checkNim(nim)) {
                studentList.add(new Student(nim, name, faculty, studyProgram));
                showAdminMenu(stage);
            } else {
                showErrorDialog("Error", "NIM tidak valid atau sudah terdaftar!");
            }
        });

        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().addAll(nameField, nimField, facultyField, studyProgramField, addButton, backButton);

        stage.setScene(scene);
    }

    private void inputBook(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8; ");
        Scene scene = new Scene(root, 600, 600);

        TextField titleField = new TextField();
        titleField.setPromptText("Masukkan Judul Buku");
        titleField.setFont(new Font("Tahoma", 14));
        titleField.setMinWidth(200);
        titleField.setMinHeight(30);
        titleField.setMaxWidth(200);
        titleField.setMaxHeight(30);

        TextField authorField = new TextField();
        authorField.setPromptText("Masukkan Author Buku");
        authorField.setFont(new Font("Tahoma", 14));
        authorField.setMinWidth(200);
        authorField.setMinHeight(30);
        authorField.setMaxWidth(200);
        authorField.setMaxHeight(30);

        TextField categoryField = new TextField();
        categoryField.setPromptText("Masukkan Categoryr Buku");
        categoryField.setFont(new Font("Tahoma", 14));
        categoryField.setMinWidth(200);
        categoryField.setMinHeight(30);
        categoryField.setMaxWidth(200);
        categoryField.setMaxHeight(30);

        TextField stockField = new TextField();
        stockField.setPromptText("Masukkan Stok Buku");
        stockField.setFont(new Font("Tahoma", 14));
        stockField.setMinWidth(200);
        stockField.setMinHeight(30);
        stockField.setMaxWidth(200);
        stockField.setMaxHeight(30);

        Button addButton = new Button("Tambah");
        addButton.setPrefWidth(200);
        addButton.setPrefHeight(25);
        addButton.setFont(new Font("Tahoma", 14));

        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(25);
        backButton.setFont(new Font("Tahoma", 14));

        addButton.setOnAction(event -> {
            String judul = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            int stok = Integer.parseInt(stockField.getText());

            daftarBuku.add(new Book(generateId("B"), judul, stok, category, author));
            showAdminMenu(stage);
        });

        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().addAll(titleField, authorField,  categoryField, stockField, addButton, backButton);

        stage.setScene(scene);
    }

    private String generateId(String prefix) {
        int id = daftarBuku.size() + 1;
        return prefix + String.format("%03d", id);
    }

    private void displayBooks(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8;");
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

        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().add(backButton);

        stage.setScene(scene);
    }

    private void displayStudents(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8; ");
        Scene scene = new Scene(root, 600, 600);

        for (Student student : studentList) {
            root.getChildren().add(new Label("NIM: " + student.getNim()));
            root.getChildren().add(new Label("Nama: " + student.getName()));
            root.getChildren().add(new Label("Fakultas: " + student.getFaculty()));
            root.getChildren().add(new Label("Program Studi: " + student.getStudyProgram()));
            root.getChildren().add(new Label(""));
        }

        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(25);
        backButton.setFont(new Font("Tahoma", 14));

        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().add(backButton);

        stage.setScene(scene);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();

    }

    @Override
    public void menu() {

    }
}
