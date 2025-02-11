import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int M = Integer.parseInt(br.readLine());
        String[] str2 = br.readLine().split(" ");

        // N 오름차순 정렬 
        Arrays.sort(str);

        // N의 중앙값과 M의 값 비교
        for (int i = 0; i < M; i++) {
            // str2[i]가 str 배열에 존재하는지 이진 탐색
            int index = Arrays.binarySearch(str, str2[i]);
            
            if (index >= 0) { // 찾으면 index >= 0
                bw.write("1" + "\n");
            } else { // 찾지 못하면 index < 0
                bw.write("0" + "\n");
            }
        }
        
        bw.flush();
    }
}