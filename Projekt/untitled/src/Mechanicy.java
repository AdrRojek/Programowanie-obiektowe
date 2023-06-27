import java.sql.*;
import java.util.Scanner;

public class Mechanicy {
    private Pojazd pojazd;
    private Scanner scanner;
    private Connect connect;
    public Mechanicy(Pojazd pojazd,Scanner scanner, Connect connect) {
        this.pojazd = pojazd;
        scanner = this.scanner;
        this.connect = connect;
    }

    public static void dodaj(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię mechanika:");
        String imie = scanner.nextLine();
        while(imie.isEmpty()) {
            System.out.println("Podaj imię mechanika:");
            imie = scanner.next();
        }

        System.out.println("Podaj nazwisko mechanika:");
        String nazwisko = scanner.next();
        while(nazwisko.isEmpty()) {
            System.out.println("Podaj nazwisko mechanika:");
            nazwisko = scanner.next();
        }


        try {
            Connection connection = Connect.getConnection();
            String sql = "INSERT INTO mechanicy (imie, nazwisko) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, imie);
            statement.setString(2, nazwisko);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idMechanika = generatedKeys.getInt(1);
                System.out.println("Dodano mechanika o ID " + idMechanika + " do bazy danych.");
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas dodawania mechanika.");
            e.printStackTrace();
        }
    }
    public static void przydziel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID mechanika, którego chcesz przydzielić do pojazdu:");
        int idMechanika = scanner.nextInt();

        System.out.println("Podaj ID pojazdu, do którego chcesz przydzielić mechanika:");
        int idPojazdu = scanner.nextInt();

        try{
            Connection connection = Connect.getConnection();
            String sql = "UPDATE mechanicy SET pojazdId = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(2, idMechanika);
            statement.setInt(1, idPojazdu);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Przydzielono mechanika o ID " + idMechanika + " do pojazdu o ID " + idPojazdu + ".");
            } else {
                System.out.println("Nie znaleziono pojazdu o podanym ID w bazie danych.");
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas przydzielania mechanika.");
            e.printStackTrace();
        }
    }
    public static void usun() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ID mechanika do zwolnienia:");
        int id = scanner.nextInt();

        try{
            Connection connection = Connect.getConnection();
            String sql = "DELETE FROM mechanicy WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Mechanik o ID " + id + " został zwolniony.");
            } else {
                System.out.println("Nie znaleziono mechanika o podanym ID w bazie danych.");
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas zwalniania mechanika.");
            e.printStackTrace();
        }
    }
    public static void wyswietl() {

        try {
            Connection connection = Connect.getConnection();
            String sql = "SELECT *  FROM mechanicy ";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idMechanika = resultSet.getInt("id");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                int idPojazdu = resultSet.getInt("pojazdId");

                System.out.println("ID mechanika: " + idMechanika);
                System.out.println("Imię: " + imie);
                System.out.println("Nazwisko: " + nazwisko);
                System.out.println("ID pojazdu: " + idPojazdu);
                System.out.println("--------------------------------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas wyświetlania mechaników.");
            e.printStackTrace();
        }
    }
}
