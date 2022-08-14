package baekjoon.oneDimensionArray;

import java.util.Scanner;

public class Q2577 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        int AxBxC = A * B * C;

        int[] counts = new int[10];

        while(AxBxC != 0){

            //System.out.println("AxBxC : " + AxBxC);
            //System.out.println("AxBxC % 10 : " + AxBxC % 10);

            switch (AxBxC % 10){
                case 0:
                    counts[0]++;
                    break;
                case 1:
                    counts[1]++;
                    break;
                case 2:
                    counts[2]++;
                    break;
                case 3:
                    counts[3]++;
                    break;
                case 4:
                    counts[4]++;
                    break;
                case 5:
                    counts[5]++;
                    break;
                case 6:
                    counts[6]++;
                    break;
                case 7:
                    counts[7]++;
                    break;
                case 8:
                    counts[8]++;
                    break;
                case 9:
                    counts[9]++;
                    break;
            }

            AxBxC = AxBxC/10;
        }

        for(int i : counts){
            System.out.println(i);
        }
    }
}
