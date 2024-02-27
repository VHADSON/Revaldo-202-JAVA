import java.util.Scanner;

public class MenuProgramLogin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String NIM = "202310370311202";
        String username = "Admin";
        String password = "Admin";

        while (true) {
            // Menampilkan menu
            System.out.println("=== Menu Login ===:");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");

            System.out.print("Pilih opsi (1-3): ");
            int opsi = scanner.nextInt();
            String inputNIM, inputUsername, inputPassword;

            switch (opsi) {
                case 1:
                    System.out.println("Enter your NIM:");
                    inputNIM = scanner.next();
                    if (inputNIM.equals(NIM)) {
                        System.out.println("Successful Login as Student.");
                    } else {
                        System.out.println("Student user Not Found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter your username: ");
                    inputUsername = scanner.next();
                    System.out.print("Enter your password: ");
                    inputPassword = scanner.next();

                    if (inputUsername.equals(username) && inputPassword.equals(password)) {
                        System.out.println("Successful Login as Admin.");
                    } else {
                        System.out.println("Admin User Not Found.");
                    }
                    break;

                case 3:
                    System.out.println("Exit.");
                    scanner.close(); // Menutup scanner sebelum keluar dari program
                    System.exit(0); // Keluar dari program
                    break;

                default:
                    System.out.println("INVALID");
            }
        }
    }
}