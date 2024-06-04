package org.example.tugasmodulke6.com.main;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.tugasmodulke6.books.*;
import org.example.tugasmodulke6.data.Admin;
import org.example.tugasmodulke6.data.Student;

import java.util.ArrayList;

public class LibrarySystem {
    public static ArrayList<Book> daftarBuku = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();

    public static void startLibrarySystem(Stage stage) {
        daftarBuku.add(new StoryBook("SB32F", "OPM", 17, "Story", "Genos"));
        daftarBuku.add(new HistoryBook("HB324", "HxH", 2, "History", "Gon"));
        daftarBuku.add(new TextBook("TB323", "OP", 4, "Text", "Zoro"));

        studentList.add(new Student("202310370311202", "Revaldo", "Teknik", "Informatika"));
        studentList.add(new Student("202310230311257", "Cherryl", "Psikologi", "Psikologi"));
        studentList.add(new Student("202310370311203", "Reval", "Teknik", "DKV"));
        studentList.add(new Student("202310370311204", "Valdo", "Teknik", "Sistem Informasi"));

        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: #7728e8; -fx-text-fill: white;");
        Scene scene = new Scene(root, 600, 600);
        root.setAlignment(Pos.CENTER);

        Image backgroundImage = new Image("file:src/main/resources/UI Menu2.png");
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));

        Button studentLoginButton = new Button("Login As Mahasiswa");
        studentLoginButton.setPrefWidth(200);
        studentLoginButton.setPrefHeight(25);
        studentLoginButton.setFont(new Font("Tahoma", 14));

        Button adminLoginButton = new Button("Login As Admin");
        adminLoginButton.setPrefWidth(200);
        adminLoginButton.setPrefHeight(25);
        adminLoginButton.setFont(new Font("Tahoma", 14));

        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(200);
        exitButton.setPrefHeight(25);
        exitButton.setFont(new Font("Tahoma", 14));

        studentLoginButton.setOnAction(event -> studentLogin(stage));
        adminLoginButton.setOnAction(event -> {
            try {
                new Admin().login(stage);
            } catch (Exception e) {
                showErrorDialog("Error", e.getMessage());
            }
        });
        exitButton.setOnAction(event -> stage.close());

        root.getChildren().addAll(studentLoginButton, adminLoginButton, exitButton);

        stage.setScene(scene);
        stage.setTitle("Library");
        stage.show();
    }

    private static void studentLogin(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #7728e8; ");
        Scene scene = new Scene(root, 600, 600);
        Label label = new Label("Masukkan NIM");
        label.setStyle("-fx-text-fill: white;");

        TextField nimField = new TextField();
        nimField.setFont(new Font("Tahoma", 14));
        nimField.setMinWidth(200);
        nimField.setMinHeight(30);
        nimField.setMaxWidth(200);
        nimField.setMaxHeight(30);

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(200);
        loginButton.setPrefHeight(25);

        Button backButton = new Button("Kembali");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(25);

        loginButton.setOnAction(event -> {
            String nimStudent = nimField.getText();
            if (nimStudent.length() == 15 && checkNim(nimStudent)) {
                Student student = new Student(nimStudent);
                student.login(stage);
            } else {
                showErrorDialog("Error", "Nim tidak terdaftar atau tidak valid!");
            }
        });

        backButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(label, nimField, loginButton, backButton);

        stage.setScene(scene);
    }

    private static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
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
