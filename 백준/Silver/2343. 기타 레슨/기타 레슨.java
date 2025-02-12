import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int mid;
    private static int M, N, sum;
    private static int[] lecture;
    private static BufferedWriter bw;

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputString = br.readLine().split(" ");
        String[] inputLecture = br.readLine().split(" ");
        
        N = Integer.parseInt(inputString[0]); // 강의 수 
        M = Integer.parseInt(inputString[1]); // 블루레이 수
        lecture = new int[N];

        for(int i = 0; i < N; i++) {
            lecture[i] = Integer.parseInt(inputLecture[i]);
        }
        
        int min = 0;
        int max = 0;
        
        // 최소값 : 가장 긴 강의 길이, 최대값 : 모든 강의 더한 값
        for(int i=0; i<N; i++) {
        	min = Math.max(min, lecture[i]);
        	max += lecture[i];
        }
        
        // 이진 탐색
        int result = binarySearch(min, max);
        
        bw.write(result + " ");
        bw.flush();
    }

    private static int binarySearch(int min, int max) {
        int result = max;

        while (min <= max) {
            int mid = (min + max) / 2;

            // mid 값으로 블루레이 수가 M개 이하로 가능한지 확인
            if (canDivide(mid)) {
                result = mid;
                max = mid - 1; // 최소값을 줄여보는 방향으로 탐색
            } else {
                min = mid + 1; // 블루레이가 부족하면 최대값을 늘려보는 방향으로 탐색
            }
        }
        return result;
    }

    private static boolean canDivide(int maxLength) {
        int blurayCount = 1; // 블루레이 개수는 1부터 시작
        int sum = 0;

        for (int i = 0; i < N; i++) {
            if (sum + lecture[i] > maxLength) {
                blurayCount++;
                sum = lecture[i];

                if (blurayCount > M) {
                    return false; // 블루레이 개수가 M개를 넘으면 불가능
                }
            } else {
                sum += lecture[i];
            }
        }

        return true;
    }
}