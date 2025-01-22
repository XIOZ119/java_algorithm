package class1;

import java.util.Arrays;
import java.util.Scanner;

public class _3052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[10];
        Arrays.fill(arr, -1);

        int count = 0;

        for (int i = 0; i < 10; i++) {
            int dv = sc.nextInt() % 42;
            boolean isUnique = true;

            for (int j = 0; j < 10; j++) {
                if (arr[j] == dv) {
                    isUnique = false;
                }
            }

            if (isUnique) {
                arr[i] = dv;
            }
        }

        sc.close();

        for (int i = 0; i < 10; i++) {
            if (arr[i] != -1) {
                count += 1;
            }
        }

        System.out.println(count);

    }

}