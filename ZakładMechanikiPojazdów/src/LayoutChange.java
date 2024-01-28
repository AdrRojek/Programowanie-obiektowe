import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LayoutChange extends JFrame {
    private JPanel panel1;
    private JRadioButton nameRadioButton;
    private JRadioButton surnameRadioButton;
    private JRadioButton loginRadioButton;
    private JRadioButton specialtyRadioButton;
    private JTextField textField1;
    private JButton cancelButton;
    private JButton changeButton;
    private ButtonGroup buttonGroup;
    private String mechanicId; // Pole przechowujące ID mechanika

    public LayoutChange(String mechanicId) {
        this.mechanicId = mechanicId;
        //initializeComponents();
        setTitle("Zmień dane mechanika");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String columnName = getSelectedColumnName();
                String newValue = textField1.getText();
                if (columnName == null || newValue.isEmpty()) {
                    JOptionPane.showMessageDialog(LayoutChange.this, "Proszę wybrać pole i wprowadzić nową wartość.", "Błąd", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean isLoginChange = "Login".equals(columnName); // Sprawdzenie czy to zmiana loginu
                    updateMechanicInfo(columnName, newValue, mechanicId, isLoginChange);
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LayoutChange.this.dispose();
                new LayoutShowMech().setVisible(true);
            }
        });

        setContentPane(panel1); // Dopiero po zainicjalizowaniu składników
    }


    private void updateMechanicInfo(String columnName, String newValue, String mechanicId, boolean isLoginChange) {
        String sqlUpdateMechanic = "UPDATE mechanicy SET " + columnName + " = ? WHERE ID_Mechanika = ?";
        String sqlDeleteUser = "DROP USER ?";
        String sqlCreateUser = "CREATE USER ? IDENTIFIED BY ?";

        Connection conn = null;
        PreparedStatement pstmtUpdateMechanic = null;
        PreparedStatement pstmtDeleteUser = null;
        PreparedStatement pstmtCreateUser = null;

        try {
            conn = DatabaseConnection.Connector("admin", "admin");
            conn.setAutoCommit(false); // Wyłączenie auto-commit do zarządzania transakcją

            // Jeśli zmieniamy login, usuwamy stare konto i tworzymy nowe
            if (isLoginChange && "Login".equals(columnName)) {
                // Pobranie starego loginu
                String oldLogin = getOldLogin(mechanicId, conn);

                if (oldLogin != null) {
                    // Usunięcie starego konta
                    pstmtDeleteUser = conn.prepareStatement(sqlDeleteUser);
                    pstmtDeleteUser.setString(1, oldLogin);
                    pstmtDeleteUser.execute();

                    // Utworzenie nowego konta
                    pstmtCreateUser = conn.prepareStatement(sqlCreateUser);
                    pstmtCreateUser.setString(1, newValue);
                    pstmtCreateUser.setString(2, "nowe_haslo"); // Dostosuj hasło do swoich wymagań
                    pstmtCreateUser.execute();
                } else {
                    // Obsłuż sytuację, gdy nie można znaleźć starego loginu
                    JOptionPane.showMessageDialog(this, "Nie można znaleźć starego loginu.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    conn.rollback();
                    return;
                }
            }

            // Aktualizacja informacji mechanika
            pstmtUpdateMechanic = conn.prepareStatement(sqlUpdateMechanic);
            pstmtUpdateMechanic.setString(1, newValue);
            pstmtUpdateMechanic.setString(2, mechanicId);
            int affectedRowsMechanic = pstmtUpdateMechanic.executeUpdate();

            conn.commit(); // Zatwierdzenie transakcji
            JOptionPane.showMessageDialog(this, "Informacje zaktualizowane pomyślnie.");
            this.dispose();
            new LayoutShowMech().setVisible(true); // Odkomentuj, jeśli chcesz wrócić do LayoutAdmin
        } catch (SQLException ex) {
            try {
                if (conn != null) conn.rollback(); // Wycofanie transakcji w przypadku błędu
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd SQL: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Zamknięcie wszystkich zasobów
            try {
                if (pstmtUpdateMechanic != null) pstmtUpdateMechanic.close();
                if (pstmtDeleteUser != null) pstmtDeleteUser.close();
                if (pstmtCreateUser != null) pstmtCreateUser.close();
                if (conn != null) {
                    conn.setAutoCommit(true); // Przywrócenie auto-commit
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    private String getOldLogin(String mechanicId, Connection conn) throws SQLException {
        String sqlGetOldLogin = "SELECT Login FROM mechanicy WHERE ID_Mechanika = ?";
        try (PreparedStatement pstmtGetOldLogin = conn.prepareStatement(sqlGetOldLogin)) {
            pstmtGetOldLogin.setString(1, mechanicId);
            try (ResultSet rs = pstmtGetOldLogin.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Login");
                }
            }
        }
        return null; // lub wyrzuć wyjątek, jeśli stary login nie zostanie znaleziony
    }

    private String getSelectedColumnName() {
        if (loginRadioButton.isSelected()) {
            return "Login";
        } else if (nameRadioButton.isSelected()) {
            return "Imie";
        } else if (surnameRadioButton.isSelected()) {
            return "Nazwisko";
        } else if (specialtyRadioButton.isSelected()) {
            return "Specjalnosc";
        }
        return null; // Zwracamy null, gdy żaden przycisk nie jest wybrany
    }


}
