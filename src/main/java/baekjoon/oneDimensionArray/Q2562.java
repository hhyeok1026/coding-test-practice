package baekjoon.oneDimensionArray;

import java.util.Scanner;

public class Q2562 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] nums = new int[9];
        int max = 0;
        int maxsIndex = -1;

        for(int i=0;i<9;i++){
           nums[i] = sc.nextInt();

           if(max <= nums[i]){
               max = nums[i];
               maxsIndex = i;
           }
        }

        System.out.println(max);
        System.out.println(maxsIndex + 1);
    }
}
