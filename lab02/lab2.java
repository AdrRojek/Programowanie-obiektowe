package lab2;

import java.util.Scanner;


public class lab2 {

    public static void main(String[] args) {

    //zadanie5();//Napiszprogram wyświetlający liczby od 20-0, z wyłączeniem liczb {2,6,9,15,19}. Do realizacji zadania wyłączenia użyj instrukcji continue;
    //  zadanie6();//Napisz program, który w nieskończoność pyta użytkownika o liczby całkowite. Pętla nieskończona powinna się zakończyć gdy użytkownik wprowadzi liczbę mniejszą od zera. Do opuszczenia pętli nieskończonej użyj instrukcji break.
    zadanie7();
    }

    public static void zadanie5() {
        for (int i = 20; i >= 0; i--){

            if (i == 2||i==6||i==9||i==15||i==19){
                continue;
            }
            System.out.println(i);

        }
    }


    public static void zadanie6(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Podaj liczbę");
            int a= sc.nextInt();
            if(a<0){
                break;
            }

        }
}

    public static void zadanie7() {
        System.out.println("Ile liczb: ");
        Scanner sc = new Scanner(System.in);
        int n = (sc.nextInt())+1;
        int tab[]=new int[n];
        for (int k = 0; k < n-1; k++) {
            System.out.print("Wprowadz "+ (k+1) +" liczbe: ");
            tab[k] = sc.nextInt();
        }int j=0,i=0;n=n-1;
        for(j=0;j<n;j++)
        {
            for(i=0;i<(n-1);i++){
                if(tab[i]>tab[i+1]){
                    int temp=tab[i];
                    tab[i]=tab[i+1];
                    tab[i+1]=temp;

                }
            }
        }
        for(int p=0;p<n;p++){
            System.out.println(tab[p]);
        }
    }







}

