package baekjoon.loop;

import java.util.Scanner;

//문자열 처리말고 숫자로 처리해보기.
public class Q1110_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        int count = 0;

        int lastNum = input;

        while (true){

            int lastNumsTen;
            int lastNumsOne;

            if(lastNum < 10){
                lastNumsTen = 0;
                lastNumsOne = lastNum;
            }else{
                lastNumsTen = lastNum / 10;
                lastNumsOne = lastNum % 10;
            }

            int lastNumsSum = lastNumsOne + lastNumsTen;

            int newNumsTen = lastNumsOne;
            int newNumsOne = lastNumsSum % 10;

            int newNum = newNumsTen * 10 + newNumsOne;
            lastNum = newNum;

            count++;

            if(newNum == input){
                break;
            }
        }

        System.out.println(count);
    }
}
