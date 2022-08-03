package baekjoon.loop;

import java.io.*;

public class Q15552 {
    public static void main(String[] args){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            int T = Integer.parseInt(br.readLine());

            for(int i=0; i<T; i++) {
                String input = br.readLine();
                int A = Integer.parseInt(input.split(" ")[0]);
                int B = Integer.parseInt(input.split(" ")[1]);

                bw.write(A + B + "\n");
            }

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
