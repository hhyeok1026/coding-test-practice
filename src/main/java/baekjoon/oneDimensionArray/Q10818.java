package baekjoon.oneDimensionArray;

import java.util.Scanner;

public class Q10818 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] numArray = new int[N];
        int min= 0, max = 0;

        for(int i=0; sc.hasNext(); i++){
            //System.out.println("i : " + i);
            numArray[i] = sc.nextInt();

            if(i!=0){
                if(numArray[i] < min){
                    min = numArray[i];
                }
                if(numArray[i] > max){
                    max = numArray[i];
                }
            }else{
                min = numArray[i];
                max = numArray[i];
            }
        }

        System.out.println(min + " " + max);
    }
}
