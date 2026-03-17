import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        int windowSize = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='a') windowSize++;
        }

        int loc = 0;
        int bCount = 0;
        String fullStr = str + str;

        for(int i=0; i<windowSize; i++){
            char ch = fullStr.charAt(i);
            if (ch == 'b') bCount++;
            loc++;
        }
        int result = bCount;

        while(loc < fullStr.length()){
            if(fullStr.charAt(loc-windowSize)=='b') bCount--;
            char ch = fullStr.charAt(loc);
            if(ch == 'b') bCount++;
            result = Math.min(result, bCount);
            loc++;
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}