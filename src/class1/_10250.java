package class1;

import java.util.Scanner;

public class _10250 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        for (int t = 0; t < testCase; t++) {
            int H = sc.nextInt();
            int W = sc.nextInt();
            int N = sc.nextInt();

            int count = 0;

            for (int i = 1; i <= W; i++) {
                for (int j = 1; j <= H; j++) {
                    count++;
                    if (count == N) {
                        String stringI = Integer.toString(i);
                        String stringJ = Integer.toString(j);
                        String result = "";

                        if (i < 10) {
                            result = stringJ + "0" + stringI;
                        } else {
                            result = stringJ + stringI;
                        }

                        System.out.println(Integer.parseInt(result));

                        break;
                    }
                }
            }
        }
    }
}