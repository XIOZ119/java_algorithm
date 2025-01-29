package class2;

import java.io.*;

public class _2292 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(bf.readLine());
        int sum = 1;

        for (int N = 0; true; N++) {
            if (sum == input){
                System.out.println(1);
                break;
            }
            if (sum < input && input <= sum + (6*N)) {
                System.out.println(N+1);
                break;
            } else {
                sum += N * 6;
            }
        }
    }
}
