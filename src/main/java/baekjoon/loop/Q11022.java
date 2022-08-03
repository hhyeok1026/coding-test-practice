package baekjoon.loop;

import java.io.*;

public class Q11022 {
    public static void main(String[] args){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            int T = Integer.parseInt(br.readLine());

            for(int i=0; i<T; i++) {
                String input = br.readLine();
                int A = Integer.parseInt(input.split(" ")[0]);
                int B = Integer.parseInt(input.split(" ")[1]);

                //여기서 괄호를 안치면 string연산되고, 괄호를 치면 숫자 연산을 하네;
                bw.write("Case #" + (i + 1) + ": " + A + " + " + B + " = " + (A + B) + "\n");
            }

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
