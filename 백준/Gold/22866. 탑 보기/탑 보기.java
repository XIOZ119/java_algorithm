import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftCount = new int[N]; int[] rightCount = new int[N];
        int[] leftNear = new int[N]; int[] rightNear = new int[N];

        scan(0, arr.length, 1, leftCount, leftNear);
        scan(arr.length-1, -1, -1, rightCount, rightNear);

        for(int i=0; i<arr.length; i++){
            int count = leftCount[i] + rightCount[i];
            sb.append(count + " ");
            if(count > 0) {
                if(Math.abs((i+1)-leftNear[i]) < Math.abs(rightNear[i]-(i+1))) sb.append(leftNear[i]);
                else if(Math.abs((i+1)-leftNear[i]) == Math.abs(rightNear[i]-(i+1))) sb.append(Math.min(leftNear[i], rightNear[i]));
                else sb.append(rightNear[i]);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void scan(int start, int end, int step, int[] countArr, int[] nearArr){
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {start+1, arr[start]});
        countArr[start] = 0; nearArr[start] = Integer.MAX_VALUE;

        for(int i=start+step; i!=end; i+=step) {

            while(!stack.isEmpty() && stack.peek()[1] <= arr[i]){
                stack.pop();
            }

            if(!stack.isEmpty()) {
                nearArr[i] = stack.peek()[0];
                countArr[i] = stack.size();
            } else {
                countArr[i] = 0;
                nearArr[i] = Integer.MAX_VALUE;
            }

            stack.push(new int[]{i+1, arr[i]});
        }
    }
}