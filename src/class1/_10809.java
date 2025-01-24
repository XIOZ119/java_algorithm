package class1;

import java.util.Scanner;

public class _10809 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int num = str.length();

        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char[] arr = alpha.toCharArray();
        int num2 = arr.length;

        int[] result = new int[num2];

        for (int i = 0; i < num2; i++) {
            for (int j = 0; j < num; j++) {
                if (arr[i] == str.charAt(j)) {
                    result[i] = j;
                    break;
                } else {
                    result[i] = -1;
                }
            }
        }
        for (int k = 0; k < num2; k++) {
            System.out.print(result[k] + " ");
        }
    }
}
