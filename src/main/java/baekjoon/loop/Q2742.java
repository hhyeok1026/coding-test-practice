package baekjoon.loop;

import java.util.Scanner;

public class Q2742 {
    public static void main(String[] agrs){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for(int i = N; i>=1; i--){
            System.out.println(i);
        }
    }
}
