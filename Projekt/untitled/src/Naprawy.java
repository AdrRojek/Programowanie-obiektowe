import java.sql.*;
import java.util.Scanner;

public class Naprawy {

    private Pojazd pojazd;
    private Connect connect;
    private Naprawy(Pojazd pojazd,Connect connect) {
        this.pojazd = pojazd;
        this.connect = connect;
    }
    private static Scanner scanner;

    public static void dodaj() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID pojazdu, do którego chcesz dodać naprawę:");
        int idPojazdu = scanner.nextInt();

        System.out.println("Podaj opis naprawy:");
        String opis = scanner.next();

        try {
            Connection connection = Connect.getConnection();
            String sql = "INSERT INTO naprawy (pojazdId , opis) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPojazdu);
            statement.setString(2, opis);
            statement.executeUpdate();

            System.out.println("Dodano naprawę do pojazdu o ID " + idPojazdu + ".");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas dodawania naprawy.");
            e.printStackTrace();
        }
    }
    public static void usun() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID naprawy do usunięcia:");
        int id = scanner.nextInt();

        try{
            Connection connection = Connect.getConnection();
            String sql = "DELETE FROM naprawy WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Naprawa o ID " + id + " została usunięty z bazy danych.");
            } else {
                System.out.println("Nie znaleziono naprawy o podanym ID w bazie danych.");
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas usuwania naprawy.");
            e.printStackTrace();
        }
    }
    public static void wyswietl() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID pojazdu, dla którego chcesz wyświetlić naprawy:");
        int idPojazdu = scanner.nextInt();

        try{
            Connection connection = Connect.getConnection();
            String sql = "SELECT * " +
                    "FROM pojazdy p " +
                    "JOIN naprawy n ON p.id = n.pojazdId " +
                    "WHERE p.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPojazdu);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String marka = resultSet.getString("marka");
                String model = resultSet.getString("model");
                String opisNaprawy = resultSet.getString("opis");
                String rejestacja = resultSet.getString("rejestracja");

                System.out.println("ID pojazdu: " + id);
                System.out.println("Marka: " + marka);
                System.out.println("Model: " + model);
                System.out.println("Rejestacja: " + rejestacja);
                System.out.println("Opis naprawy: " + opisNaprawy);
                System.out.println("--------------------------------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas wyświetlania napraw.");
            e.printStackTrace();
        }
    }
}
