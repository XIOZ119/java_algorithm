package class2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        boolean result = false;

        for (int i = 1; i <= N; i++) {
            String s = Integer.toString(i);
            int sum = 0;

            for (int j = 0; j < s.length(); j++) {
                int digit = Integer.parseInt(s.charAt(j) + "");
                sum += digit;
            }
            if (sum + i == N) {
                result = true;
                System.out.println(i);
                break;
            }
        }

        if (result == false) {
            System.out.println("0");
        }
    }
}
