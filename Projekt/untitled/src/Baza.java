import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Baza {
    public static void baza() {
        System.out.println("Łączenie z bazą danych:");

        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Połączono z bazą danych.");

            // Tworzenie tabel
            Statement statement = connection.createStatement();

            // Tworzenie tabeli pojazdy
            String createTablePojazdy = "CREATE TABLE IF NOT EXISTS pojazdy (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "marka VARCHAR(255) NOT NULL," +
                    "model VARCHAR(255) NOT NULL," +
                    "rokProdukcji INT NOT NULL," +
                    "przebieg INT NOT NULL " +
                    "rejestracja VARCHAR(255) NOT NULL" +
                    "`dataOddania` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP "+
                    ")";
            statement.execute(createTablePojazdy);

            // Tworzenie tabeli naprawy
            String createTableNaprawy = "CREATE TABLE IF NOT EXISTS naprawy (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "pojazdId INT NOT NULL," +
                    "opis TEXT NOT NULL," +
                    "FOREIGN KEY (pojazdId) REFERENCES pojazdy(id)" +
                    ")";
            statement.execute(createTableNaprawy);

            // Tworzenie tabeli mechanicy
            String createTableMechanicy = "CREATE TABLE IF NOT EXISTS mechanicy (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "imie VARCHAR(255) NOT NULL," +
                    "nazwisko VARCHAR(255) NOT NULL," +
                    "specjalizacja VARCHAR(255) NOT NULL" +
                    ")";
            statement.execute(createTableMechanicy);

            System.out.println("Utworzono tabele w bazie danych.");
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas łączenia z bazą danych.");
            e.printStackTrace();
        }
    }
}
