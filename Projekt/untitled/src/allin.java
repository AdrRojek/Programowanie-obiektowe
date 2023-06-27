/* public static void dodajPojazd() {
        System.out.print("Podaj marka: ");
        String Marka = scanner.nextLine();
        System.out.print("Podaj model: ");
        String Model = scanner.nextLine();
        System.out.print("Podaj rok produkcji: ");
        int RokProdukcji = scanner.nextInt();
        System.out.print("Podaj przebieg: ");
        int Przebieg = scanner.nextInt();
        System.out.println("Podaj rejstacje: ");
        String Rejestacja=scanner.nextLine();

        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO pojazdy (marka, model, rokProdukcji, przebieg, rejestracja) VALUES (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Marka);
            statement.setString(2, Model);
            statement.setInt(3, RokProdukcji);
            statement.setInt(4, Przebieg);
            statement.setString(5, Rejestacja);

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
    private static void usunPojazd() {
        System.out.println("Podaj ID pojazdu do usunięcia:");
        int id = scanner.nextInt();

        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)){
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
    private static void wyswietlPojazdy() {
        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)){
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
    private static void dodajNaprawe() {
        System.out.println("Podaj ID pojazdu, do którego chcesz dodać naprawę:");
        int idPojazdu = scanner.nextInt();

        System.out.println("Podaj opis naprawy:");
        String opis = scanner.next();

        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)){
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
    private static void usunNaprawe() {
        System.out.println("Podaj ID naprawy do usunięcia:");
        int id = scanner.nextInt();

        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)){
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
    private static void wyswietlNaprawy() {
        System.out.println("Podaj ID pojazdu, dla którego chcesz wyświetlić naprawy:");
        int idPojazdu = scanner.nextInt();

        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)){
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
    private static void dodajMechanika() {
        System.out.println("Podaj imię mechanika:");
        String imie = scanner.next();

        System.out.println("Podaj nazwisko mechanika:");
        String nazwisko = scanner.next();

        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)){
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
    private static void przydzielMechanika() {

        System.out.println("Podaj ID mechanika, którego chcesz przydzielić do pojazdu:");
        int idMechanika = scanner.nextInt();

        System.out.println("Podaj ID pojazdu, do którego chcesz przydzielić mechanika:");
        int idPojazdu = scanner.nextInt();

        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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
    private static void usunMechanika() {
        System.out.println("Podaj ID mechanika do zwolnienia:");
        int id = scanner.nextInt();

        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)){
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
    private static void wyswietlMechanikow() {
        String url = "jdbc:mariadb://localhost:3306/zakladmechaniki";
        String user = "username";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)){
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
private static void utworzBaze() {
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
        "przebieg INT NOT NULL" +
        "rejestracja VARCHAR(255) NOT NULL" +
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
        }*/