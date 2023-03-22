package lab3;
import java.util.Random;
import java.util.Scanner;

public class zadania {
    public static void main(String[] args) {

        //zadanie1();
        //zadanie1fe();
        //zadanie2();
        //zadanie3();
        //zadanie4();
        //zadanie5();
        //zadanie6();
        //zadanie7();
        //lab2zad3();
        lab2zad4();
    }


    public static void zadanie1() {
        int l = 5;
        int[] tab = tablica(l);
        int suma = 0;
        for (int j = 0; j < l; j++) {
            suma = tab[j] + suma;
            System.out.println("tab" + tab[j]);
        }
        System.out.println(suma + " ");

        int srednia = suma / l;
        System.out.println("Średnia: " + srednia);

    }


    public static void zadanie1fe() {
        int l = 5;
        int[] tab = tablica(l);
        int suma = 0;
        for (int i : tab) {
            suma = suma + i;
        }
        int srednia = suma / l;
        System.out.println("Suma: " + suma + " Średnia: " + srednia);

    }

    public static void zadanie2() {
        int l = 10;
        int[] tab = tablica(l);
        int j = 1;
        for (int i = 0; i < tab.length; i++) {
            if (i % 2 != 0) {
                System.out.println(j + " nieparzysty element 1 tablicy to: " + tab[i]);
                j++;
            }
        }
        int[] tab2 = tablica(l);
        int k = 1;
        for (int i = 0; i < tab2.length; i++) {
            if (i % 2 == 0) {
                System.out.println(k + " parzysty element 2 tablicy to: " + tab2[i]);
                k++;
            }
        }
    }

    public static void zadanie3() {
        String[] miasta = {"Warszawa", "Moscow", "Los Angeles", "New York"};
        for (String s : miasta) {
            System.out.println("miasto: " + s.toUpperCase());
        }
    }

    public static void zadanie4() {
        int n = 5;
        Scanner sc = new Scanner(System.in);
        String[] tab = new String[n];
        String[][] tab2 = new String[n][n];
        for (int i = 0; i < n; i++) {
            System.out.println("Wprowadź " + (i + 1) + " słowo");
            tab[i] = sc.next();
            int h = tab[i].length();
            String[] temptab = new String[h];
            for (int j = 0; j < tab[i].length(); j++) {
                char tempost = tab[i].charAt(h - 1);
                char temppier = tab[i].charAt(j);
                temptab[j] = String.valueOf(tempost);
                temptab[h - 1] = String.valueOf(temppier);
                h--;
                tab2[i][j] = temptab[j];
                System.out.println("temp:" + tab2[i][j]);
            }

        }
        int i = 4, j = 0;
        while (j <= tab[i].length()) {
            System.out.print(tab2[i][j]);
            j++;
            if (j == tab[i].length()) {
                j = 0;
                --i;
                if (i == -1) {
                    break;
                }
            }

        }
        /*
        for(int j=0;j<tab[i].length();j++) {
            System.out.println(tab2[i][j]);
            if(j==tab[i].length()-1){
                System.out.print("i:"+j+"tab: "+tab2[i][j]);
                j=0;
                --i;
                if(i==-1){
                    break;
                }

            }
        }*/

    }

    public static void zadanie5() {
        Scanner sc = new Scanner(System.in);
        int n = 9;
        int tab[] = new int[n];
        for (int k = 0; k < n - 1; k++) {
            System.out.print("Wprowadz " + (k + 1) + " liczbe: ");
            tab[k] = sc.nextInt();
        }
        int j = 0, i = 0;
        n = n - 1;
        for (j = 0; j < n; j++) {
            for (i = 0; i < (n - 1); i++) {
                if (tab[i] > tab[i + 1]) {
                    int temp = tab[i];
                    tab[i] = tab[i + 1];
                    tab[i + 1] = temp;

                }
            }
        }
        for (int p = 0; p < n; p++) {
            System.out.print(tab[p] + ", ");
        }
    }

    public static void zadanie6() {
        Scanner sc = new Scanner(System.in);
        int[] tab = new int[5];
        int[] tab2 = new int[5];
        int i = 0,k=0;
        for (i = 0; i < 5; i++) {
            tab[i] = sc.nextInt();
            k = tab[i];
            tab2[i] = tab[i];

        for (int l = 1; l < k; l++) {
            tab[i] = tab[i]*l;
            }
        }
        for(i=0;i<5;i++){
            System.out.println(tab2[i]+"!="+tab[i]);

        }
    }

    public static void zadanie7() {
        String[] tab = {"a", "b", "c", "d", "e"};
        String[] tab2 = {"a", "b", "c", "d", "e"};
        boolean z = false,z2=true;
        for(int i=0;i<5;i++){
            if(tab[i].equals(tab2[i])){
                z=true;
            }else{z2=false;}
        }
        if(z2==true){
        System.out.println("Dane w tablicy są takie same");
        }else System.out.println("Dane w tablicy nie są takie same");
    }

    public static void lab2zad3() {
        System.out.println("1.Wyświetlanie tablicy od pierwszego do ostatniego indeksu.");
        System.out.println("2.Wyświetlanie tablicy od ostatniego do pierwszego indeksu.");
        System.out.println("3.Wyświetlanie elementów o nieparzystych indeksach.");
        System.out.println("4.Wyświetlanie elementów o parzystych indeksach.");
        System.out.print("Wybierz program: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Wprowadź 10 liczb:");
        int[] tab = new int[10];
        for(int i=0;i<10;i++){
            System.out.print("Wprowadź "+(i+1)+" liczbe:");
            tab[i] = sc.nextInt();
        }
        if(n==1){
            for(int i=0;i<10;i++){
                System.out.print(tab[i]+", ");
            }
        } else if (n==2) {
            for (int i = 9; i >= 0; i--) {
                System.out.print(tab[i]+", ");
            }
        } else if (n==3) {
            for(int i=0;i<10;i++){
                if(i%2!=0){
                    System.out.print(tab[i]+", ");
                }
            }
        }else if (n==4) {
            for(int i=0;i<10;i++){
                if(i%2==0){
                    System.out.print(tab[i]+", ");
                }
            }
        }



    }

    public static void lab2zad4() {
        System.out.println("1.Oblicz sumę elementów tablicy.");
        System.out.println("2.Oblicz iloczyn elementów tablicy.");
        System.out.println("3.Wyznacz wartość średnią.");
        System.out.println("4.Wyznacz wartość minimalną.");
        System.out.println("5.Wyznacz wartość maxymalną.");
        System.out.print("Wybierz program: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Wprowadź 10 liczb:");
        int[] tab = new int[10];
        for(int i=0;i<10;i++){
            System.out.print("Wprowadź "+(i+1)+" liczbe:");
            tab[i] = sc.nextInt();
        }
        if(n==1){int suma=0;
            for (int i = 0; i < 10; i++) {
                suma=tab[i]+suma;
            }
            System.out.println("Suma: "+suma);
        } else if (n == 2) {
            int iloczyn=0;
            for (int i = 0; i < 10; i++) {
                iloczyn=tab[i]*iloczyn;
            }
            System.out.println("Iloczyn: "+iloczyn);
        } else if (n == 3) {
            int suma=0;
            for (int i = 0; i < 10; i++) {
                suma=tab[i]+suma;
            }
            System.out.println("Średnia= "+(suma/10));
        } else if (n==4) { int min=tab[0];
            for (int i = 0; i < 9; i++) {
                if (min < tab[i]) {

                }else min=tab[i];
            }
            System.out.println("minimum: "+min);
        } else if (n==5) { int max=tab[0];
            for (int i = 0; i < 9; i++) {
                if (max > tab[i]) {

                }else max=tab[i];
            }
            System.out.println("maximum: "+max);
        }


    }

    public static int[] tablica(int l){
        System.out.println("Podaj przedział:");
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int n1= sc.nextInt();
        int[] tab=new int[l];
        for(int i=0;i<l;i++){
        tab[i]=inputINT(n,n1);
    }

        return tab;
    }


    public static int inputINT(int n, int n1){
      Random rand=new Random();
      int liczba=rand.nextInt(n1-n)+n;
      return liczba;
    };

}
























