package lab2;

import java.util.Random;
import java.util.Scanner;

public class lab2_1 {

    public static void main(String[] args) {
        //zadanie1();
        //zadanie2();
        //zadanie3();
        //zadanie4();
        //zadanie5();
    }

    public static void zadanie1(){
        System.out.print("Ile jest osob w klasie?: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int tab[]=new int[n];
        double srednia=0;
        int i=0;
        while(i<n){
            System.out.print("Wprowadź ocenę dla " + (i + 1) + " ucznia: ");
            tab[i]=sc.nextInt();
            srednia+=tab[i];
            i++;
        }
        System.out.println("Srednia: " + (srednia/n));



    }

    public static void zadanie2(){
        int i=0,j=0,z=0,sumamin=0,sumaplus=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Wprowadź 10 liczb:");
       for(i=0;i<10;i++) {
           int n = sc.nextInt();
            if(n>0)
            {
                sumaplus+=n;
                j++;
            }
            else{
                sumamin+=n;
                z++;
            }
       }
        System.out.println("Suma liczb dodatnich= "+sumaplus+" suma liczb ujemnych= "+sumamin+" ilosci liczb dodatnich= "+j+" ilosci liczb ujemnych="+z);
    }


    public static void zadanie3(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Do jakiej liczby ma być ciąg?: ");
            int n = sc.nextInt();
            int suma = 0;
            if (n>0) {
                for (int i = 0; i <= n; i++) {
                    if (i % 2 == 0) {
                        suma += i;
                    }
                }
                System.out.println("Suma liczb parzystych; " + suma);
            }
            else System.out.println("Ciag jest nieprawidłowy");
        }


    public static void zadanie4(){
        Scanner sc = new Scanner(System.in);
        int a=0,suma=0;

        System.out.println("Ile liczb chcesz wylosować?: ");
        int n = sc.nextInt();
        for(int i=0;i<n;i++) {
            Random rand = new Random();
            int liczba = rand.nextInt(55)-10;
            if(liczba%2==0) {
                suma+=liczba;
            }
        }
        System.out.println("Suma = "+suma);
    }

    public static void zadanie5(){

            Scanner sc = new Scanner(System.in);
            System.out.println("Podaj 1 wyraz: ");
            String a = sc.nextLine().toLowerCase();
            int h = a.length();

            int kk = 0;
            int k = (h - 1);
            for (int i = 0; i < h; i++) {

                if (a.charAt(i) != a.charAt(k)) {
                    kk = 1;
                    break;
                }
                k--;
            }

            if (kk == 1)
                System.out.println("Nie jest palindromem");

            else {
                System.out.println("Jest palindromem");
            }
    }














}
