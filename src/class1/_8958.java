package class1;

import java.util.Scanner;

public class _8958 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        sc.nextLine(); // 버퍼 지우기

        for (int t = 0; t < testCase; t++) {

            int num = 0;
            int result = 0;

            String input = sc.nextLine();

            for (int i = 0; i < input.length(); i++) {
                if (i == 0) {
                    if (input.charAt(0) == 'O') {
                        num = 1;
                        result = 1;
                    } else {
                        num = 0;
                    }
                } else {
                    if ((input.charAt(i) == 'O')) {
                        if (input.charAt(i - 1) == 'O') {
                            num++;
                            result += num;
                        } else {
                            num = 1;
                            result += 1;
                        }
                    } else {
                        num = 0;
                    }
                }
            }
            System.out.println(result);
        }
    }
}
