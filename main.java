package lab1;

import java.util.Random;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        /*System.out.println(Name()); ctrl+shift+/ komentarz wieloliniowy
        System.out.println(wiek());*/
       /* int a = inputInt();
        int b = inputInt();
        dzialanie(a,b);
        Random r = new Random();
        //[0;10]
        int liczba = r.nextInt(11)+0;
        int liczba1 = r.nextInt(21)-5; //[-5:15}
        //x:y
        int x=5,y=10;

        int liczba2=r.nextInt(y-x+1)+x;*/

        //zadanie 3
        //System.out.println(parzyste(2));

        //zadanie 4
        //System.out.println(podzielne(15));

        //zadanie 5
        //System.out.println(do3(2));

        //zadanie 6
        //System.out.println(pierw(100));

        //zadanie 7
        System.out.println("Wprowadź przedział liczb : ");
        int lA=inputInt();
        int lB=inputInt();
        int lC=10;// wpisana liczba
        Random liczb = new Random();
        lA=liczb.nextInt(lB-lA)+lA;
        lB=liczb.nextInt(lB-lA)+lA;
        System.out.println(czytr(lA,lB,lC));


    }
    public static String Name(){
        return "Adrian";
    }
    public static int wiek(){
        return 20;
    }
    public static int inputInt(){
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj liczbe:");
        int liczba = input.nextInt();
        return liczba;
    }
    public static void dzialanie(int a,int b){

        System.out.println("Suma "+a+" + "+b+" = "+ (a+b));
        System.out.format("Różnica %d - %d = %d\n",a,b, (a-b));
        System.out.format("Iloczyna %d * %d = %d\n",a,b, (a*b));
    }

    public static boolean parzyste(int a) {
        boolean parzyste;
        if (a % 2 == 0) {
            parzyste = true;
            return parzyste;
        } else parzyste = false;
        return parzyste;
    }


    public static boolean podzielne(int a){
    boolean podzielne;
        if (a%3==0 && a%5==0){
            podzielne=true;
            return podzielne;
    }
        else podzielne=false;
        return podzielne;
    }

    public static int do3(int a){
        a = (int) Math.pow(a,3);
        return a;
    }

    public static double pierw(double a){
        a = Math.sqrt(a);
        return a;
    }


    public static int loslicz(int a){
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj liczbe:");
        int liczba = input.nextInt();
        return liczba;
    }
    public static boolean czytr(int a,int b, int c){
        if (a == b ){
            return false;}
            else{
            if (a + b > c || a + c > b || b + c > a) {
                return true;
            } else return false;
        }

    }



}
