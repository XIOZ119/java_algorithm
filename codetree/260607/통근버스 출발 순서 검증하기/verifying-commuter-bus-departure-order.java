import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        int[] arr = new int[5000];
        int[] d = new int[5001];
        long res = 0; // 결과를 저장할 변수, unsigned long long 대신 long 사용
        n = sc.nextInt();
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt(); // 배열 입력

        // 각 원소마다 나머지 원소들과의 관계를 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) d[j] = 0; // d 배열 초기화

            // arr[i]보다 작은 값을 찾으면 d에 표시
            for (int j = 0; j < i; j++) 
                if (arr[i] > arr[j]) d[arr[j]] = 1;

            // d 배열을 업데이트하여 큰 값들을 누적
            for (int j = n - 1; j > 0; j--) 
                d[j] += d[j + 1];

            // arr[i]보다 큰 값들의 개수를 결과에 더함
            for (int j = i + 1; j < n; j++) 
                res += d[arr[j]];
        }

        System.out.println(res); // 결과 출력
    }
}
