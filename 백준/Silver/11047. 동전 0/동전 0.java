import java.io.*;

public class Main {

	private static int[] arr;
    private static int N;
    private static BufferedWriter bw;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int result = greedy(K);

        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    private static int greedy(int k) throws IOException {
        int money = k; 
        int count = 0;

        for(int i=1; i<=N; i++) {
            if(arr[N-i] <= money) {
                count += money / arr[N-i]; 
                money = money % arr[N-i];
            }
        }
        return count;
    }
}