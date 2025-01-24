package class2;

import java.io.*;
import java.util.*;

public class _30802 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();
        int person = Integer.parseInt(s);

        String str = bf.readLine();
        String[] strArr = str.split(" ");

        double[] sizeNum = new double[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            sizeNum[i] = Integer.parseInt(strArr[i]);
        }

        String tp = bf.readLine();
        String[] tpArr = tp.split(" ");

        double t = Integer.parseInt(tpArr[0]);
        int pen = Integer.parseInt(tpArr[1]);

        int result = 0;

        for (int i = 0; i < sizeNum.length; i++) {
            result += Math.ceil(sizeNum[i] / t);
        }
        System.out.println(result);
        System.out.println(person / pen + " " + person % pen);

    }
}