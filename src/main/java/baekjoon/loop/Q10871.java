package baekjoon.loop;

//입력 N, X
//출력 : N개 받은 정수중에 X보다 작은거를 들어온 순서대로 출력

import java.util.Scanner;

public class Q10871 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();

        boolean didPassedFist = false;

        for(int i=0; i<N; i++){

            int A = sc.nextInt();

            if(A < X){

                if(didPassedFist){
                    System.out.print(" ");
                }else{
                    didPassedFist = true;
                }

                System.out.print(A);
            }
        }
    }
}
