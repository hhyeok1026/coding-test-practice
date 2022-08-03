package baekjoon.loop;

//구구단 문제
//입력 : n
//출력 : n단 출력

//자바는 클래스 이름을 Main으로 변경해서 제출해야함.

import java.util.Scanner;

public class Q2739 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        for(int i=1;i<=9;i++){
            System.out.println(input + " * " + i + " = " + input * i);
        }
    }
}
