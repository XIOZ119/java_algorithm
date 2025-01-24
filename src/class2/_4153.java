package class2;

import java.util.Scanner;

public class _4153 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if (a == 0 && b == 0 && c == 0) {
                break;
            }

            boolean result = false;

            if (a >= b && a >= c) {
                if (Math.pow(c, 2) + Math.pow(b, 2) == Math.pow(a, 2)) {
                    result = true;
                }
            } else if (b >= a && b >= c) {
                if (Math.pow(c, 2) + Math.pow(a, 2) == Math.pow(b, 2)) {
                    result = true;
                }
            } else {
                if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
                    result = true;
                }
            }

            if (result == true) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
        sc.close();
    }
}
