import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] count = new int[2];
        String S = br.readLine();
        char[] arr = S.toCharArray();

        for(int i=0; i<S.length(); i++) {
            char ch = arr[i];

            if(ch == '0') count[0]++;
            if(ch == '1') count[1]++;
        }

        count[0] /= 2;
        count[1] /= 2;

        for(int i=0; i<arr.length; i++) {
            if(arr[i] == '1' && count[1] != 0) {
                arr[i] = 'a';
                count[1]--;
            }
        }

        for(int i=arr.length-1; i>=0; i--) {
            if(arr[i] == '0' && count[0] != 0) {
                arr[i] = 'a';
                count[0]--;
            }
        }

        for(int i=0; i<arr.length; i++) {
            if(arr[i] != 'a') sb.append(arr[i]);
        }

        System.out.println(sb.toString());
    }
}