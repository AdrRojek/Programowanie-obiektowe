package lab2.kalkulator;

import java.util.Scanner;

public class kalkulator {

    public static void main(String[] args) {
    Run();


    }
    public static void Menu() {
        System.out.println(" ==================== ");
        System.out.println("      Kalkulator      ");
        System.out.println(" ==================== ");
        System.out.println(" 1.Suma \n 2.Różnica \n 3.Iloraz  \n 4.Iloczyn \n 5.Potęga \n 6.Pierwiastek \n 7.Funkcje trygonometryczne \n 8.Wyjście \n ==================== \n Wybierz opcje:");

    }

    public static void Run(){
        int wybor;
        while (true){

            Menu();
            wybor= inputInt();
            switch (wybor){
                case 1:
                    Suma();
                    break;
                case 2:
                    Różnica();
                    break;
                case 3:
                    Iloraz();
                    break;
                case 4:
                    Iloczyn();
                    break;
                case 5:
                    Potęga();
                    break;
                case 6:
                    Pierwiastek();
                    break;
               case 7:
                    FunkcjeTrygonometryczne();
                    break;
                case 8:
                    Wyjście();
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja, Koniec programu");
                    System.exit(0);

            }
        }

}

    public static int inputInt() {
        Scanner input = new Scanner(System.in);
        int liczba = input.nextInt();
        return liczba;
    }
    public static int inputInt2() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wprowadź liczbę: ");
        int liczba2 = input.nextInt();
        return liczba2;
    }

    public static int Suma() {
        int a= inputInt2();
        int b= inputInt2();
        System.out.println("Wynik działania: "+(a+b));
        return a+b;
    }

    public static int Różnica() {
        int a= inputInt2();
        int b= inputInt2();
        System.out.println("Wynik działania: "+(a-b));
        return a-b;
    }

    public static double Iloraz() {
        double a= inputInt2();
        double b= inputInt2();
        System.out.println("Wynik działania: "+(a/b));
        return a/b;
    }

    public static int Iloczyn() {
        int a= inputInt2();
        int b= inputInt2();
        System.out.println("Wynik działania: "+(a*b));
        return a*b;
    }

    public static double Potęga() {
        double a= inputInt2();
        double b= inputInt2();
        a= Math.pow(a,b);
        System.out.println("Wynik działania: "+a);
        return a;
    }

    public static double Pierwiastek() {
        double a= inputInt2();
        double b= inputInt2();
        a = Math.pow(a,1/b);
        System.out.println("Wynik działania: "+a);
        return a;
    }

public static double FunkcjeTrygonometryczne() {
        double a = inputInt2();
        a=Math.toRadians(a);
       double sin=Math.sin(a);
       double cos=Math.cos(a);
       double tan=Math.tan(a);
       double ctg=1/Math.tan(a);
    System.out.println("Sin: "+sin+" cos: "+cos+" tan: "+tan+" ctg: "+ctg);
        return 0;
}



    public static double Wyjście() {
        System.out.println("Czy na pewno chcesz wyjść: T / N");
        String znak = inputString();
        String st1="t";
        String st2="T";
        if(znak.equals(st1)||znak.equals(st2)) System.exit(0);
        return 0;
    }

    private static String inputString() {
        Scanner input = new Scanner(System.in);
        String znak = input.nextLine();
        return znak;
    }


}
