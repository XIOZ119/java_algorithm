package class2;

import java.util.*;
import java.io.*;

public class _2798 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = bf.readLine().split(" ");
        
        int N = Integer.parseInt(nm[0]);
        int M = Integer.parseInt(nm[1]);

        String[] arr = bf.readLine().split(" ");
        int[] arrInt = new int[N];
        
        for (int i=0; i<arr.length; i++) {
            arrInt[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(arrInt);
        int result = 0;

        for (int i=N-1; i>1; i--){
            for (int j=i-1; j>0; j--){
                for (int k=j-1; k>=0; k--){
                    if(arrInt[i]+arrInt[j]+arrInt[k] < M) {
                        if(arrInt[i]+arrInt[j]+arrInt[k] > result){
                            result = arrInt[i]+arrInt[j]+arrInt[k];
                        }
                    } else if (arrInt[i]+arrInt[j]+arrInt[k] == M){
                        System.out.println(M);
                        return;
                    }
                }
            }
        }
        if (result < M){
            System.out.println(result);
        }
    }
}