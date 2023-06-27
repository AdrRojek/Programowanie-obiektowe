import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private Menu menu;
    private Mechanicy mechanicy;
    private Naprawy naprawy;
    private Pojazd pojazd;
    private Baza baza;

    public Main(Menu menu, Mechanicy mechanicy, Naprawy naprawy, Pojazd pojazd,Baza baza) {
        this.menu = menu;
        this.mechanicy = mechanicy;
        this.naprawy = naprawy;
        this.pojazd = pojazd;
        this.baza = baza;
    }




    public static void main(String[] args) {


        boolean exit = false;
        while (!exit) {
            Menu.menu();
            int opcja = scanner.nextInt();
            scanner.nextLine();

            switch (opcja) {
                case 1:
                    Pojazd.dodaj();
                    break;
                case 2:
                    Pojazd.usun();
                    break;
                case 3:
                    Pojazd.wyswietl();
                    break;
                case 4:
                    Naprawy.dodaj();
                    break;
                case 5:
                    Naprawy.usun();
                    break;
                case 6:
                    Naprawy.wyswietl();
                    break;
                case 7:
                    Mechanicy.dodaj();
                    break;
                case 8:
                    Mechanicy.przydziel();
                    break;
                case 9:
                    Mechanicy.usun();
                    break;
                case 10:
                    Mechanicy.wyswietl();
                    break;
                case 11:
                    Baza.baza();
                    break;
                case 12:
                    exit = true;
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        }
    }


}
