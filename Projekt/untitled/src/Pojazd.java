import java.sql.*;
import java.util.Scanner;

import static java.lang.Double.isNaN;

public class Pojazd {
    private static Scanner scanner;
    private Connect connect;
    private Pojazd(Connect connect) {
        this.connect = connect;
    }

    public static void dodaj() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj markę: ");
        String marka = scanner.nextLine();
        System.out.print("Podaj model: ");
        String model = scanner.nextLine();
        System.out.print("Podaj rok produkcji: ");
        int rokProdukcji = scanner.nextInt();
        while(rokProdukcji<1886 || rokProdukcji>2023)
        {
            System.out.print("Popraw rok produkcji: ");
            rokProdukcji = scanner.nextInt();
        }
        System.out.print("Podaj przebieg: ");
        int przebieg = scanner.nextInt();
        while(przebieg<0){
            System.out.print("Popraw przebieg");
            przebieg = scanner.nextInt();
        }
        System.out.print("Podaj rejestrację: ");
        String rejestracja = scanner.nextLine();
        while(rejestracja.equals("")){
            System.out.print("Wpisz rejestrację");
            rejestracja = scanner.nextLine();
        }

        try {
            Connection connection = Connect.getConnection();
            String sql = "INSERT INTO pojazdy (marka, model, rokProdukcji, przebieg, rejestracja) VALUES (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, marka);
            statement.setString(2, model);
            statement.setInt(3, rokProdukcji);
            statement.setInt(4, przebieg);
            statement.setString(5, rejestracja);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Pojazd został dodany do bazy danych.");
            } else {
                System.out.println("Wystąpił problem podczas dodawania pojazdu do bazy danych.");
            }
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas łączenia z bazą danych.");
            e.printStackTrace();
        }
    }
    public static void usun() {
        scanner = new Scanner(System.in);
        System.out.print("Podaj ID pojazdu do usunięcia:");
        int id = scanner.nextInt();

        try {
            Connection connection = Connect.getConnection();
            String sqlMechanicy = "UPDATE mechanicy SET pojazdId = NULL WHERE pojazdId = ?";
            PreparedStatement statementMechanicy = connection.prepareStatement(sqlMechanicy);
            statementMechanicy.setInt(1, id);
            statementMechanicy.executeUpdate();
            statementMechanicy.close();

            String sqlnaprawy = "DELETE FROM naprawy WHERE pojazdId = ?";
            PreparedStatement statementusun = connection.prepareStatement(sqlnaprawy);
            statementusun.setInt(1, id);
            statementusun.executeUpdate();
            statementusun.close();


            String sql = "DELETE FROM pojazdy WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();


            if (rowsDeleted > 0) {
                System.out.println("Pojazd o ID " + id + " został usunięty z bazy danych.");
            } else {
                System.out.println("Nie znaleziono pojazdu o podanym ID w bazie danych.");
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas usuwania pojazdu.");
            e.printStackTrace();
        }
    }
    public static void wyswietl() {

        try {
            Connection connection = Connect.getConnection();
            String sql = "SELECT * FROM pojazdy";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String marka = resultSet.getString("marka");
                String model = resultSet.getString("model");
                int rokProdukcji = resultSet.getInt("rokProdukcji");
                int przebieg = resultSet.getInt("przebieg");
                String rejestacja = resultSet.getString("rejestracja");

                System.out.println("ID: " + id);
                System.out.println("Marka: " + marka);
                System.out.println("Model: " + model);
                System.out.println("Rok produkcji: " + rokProdukcji);
                System.out.println("Przebieg: " + przebieg);
                System.out.println("Rejestacja: " + rejestacja);
                System.out.println("--------------------------------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas wyświetlania pojazdów.");
            e.printStackTrace();
        }
    }
}
