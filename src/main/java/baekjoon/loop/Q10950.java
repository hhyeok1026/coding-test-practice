package baekjoon.loop;

import java.util.Scanner;

//테스트 케이스 T 개에 대한, 간단한 더하기 후 출력을 만들어라.
public class Q10950 {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine();

        int[] result = new int[T];

        for(int i=0; i<T; i++) {
            String input = scanner.nextLine();
            int A = Integer.parseInt(input.split(" ")[0]);
            int B = Integer.parseInt(input.split(" ")[1]);
            result[i] = A + B;
        }

        for (int j : result) {
            System.out.println(j);
        }
    }
}
