package baekjoon.oneDimensionArray;

//시간이 1초 제한인데, 맞았다고 나오는데, 1.8초 걸려서 채점했음.
//BufferedReader를 써서 풀어봐야겠음.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10818_1 {
    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int N = 0;

            N = Integer.parseInt(br.readLine());

            int[] numArray = new int[N];
            int min= 0, max = 0;

            String input = br.readLine();
            String[] inputs = input.split(" ");

            for(int i=0; i<N; i++){
                numArray[i] = Integer.parseInt(inputs[i]);

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
