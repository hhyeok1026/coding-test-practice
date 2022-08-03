package baekjoon.loop;

import java.util.Scanner;

public class Q1110 {

    //디버그용 flag
    static boolean isDebug = true;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        int lastNewNumber = input;
        int count = 0;

        while(true){
            count++;
            logger("count : " + count);

            String lastNewNumberString = Integer.toString(lastNewNumber);
            logger("lastNewNumberString : " + lastNewNumberString);

            if(lastNewNumber < 10){
                lastNewNumberString = "0" + lastNewNumberString;

                logger("10보다 작네");
                logger("lastNewNumberString : " + lastNewNumberString);

                /*if(input == Integer.parseInt(lastNewNumberString)){
                    logger("새로 만든 숫자에서 탈출!");
                    break;
                }*/
            }

            //주어진 숫자의 가장 오른쪽 숫자.
            int firstNumber = Character.getNumericValue(lastNewNumberString.charAt(1));

            //앞에서 구한 합
            int sumNumber = Character.getNumericValue(lastNewNumberString.charAt(0)) + firstNumber;

            //앞에서 구한 합의 가장 오른쪽 자리 수.
            int secondNumber;
            if(sumNumber < 10){
                secondNumber = sumNumber;
            }else{
                secondNumber = Character.getNumericValue(Integer.toString(sumNumber).charAt(1));
            }

            logger("firstNumber : " + firstNumber);
            logger("secondNumber : " + secondNumber);

            //새로운 수를 문자열 연산으로 이어 붙임.
            String newNumberString = Integer.toString(firstNumber) + Integer.toString(secondNumber);
            logger("newNumberString : " + newNumberString);

            //새로운 수
            int newNumber = Integer.parseInt(newNumberString);
            logger("newNumber : " + newNumber);

            lastNewNumber = newNumber;

            if(input == newNumber){
                logger("탈출!");
                break;
            }

            /*if(count > 5){
                System.out.println("뭔가 잘못짰다! 탈출!");
                break;
            }*/
        }

        System.out.println(count);
    }

    public static void logger(String s){
        if(isDebug){
            System.out.println(s);
        }
    }
}
