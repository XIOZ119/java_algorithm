import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		// 범위 내의 소수를 구한 뒤, 그 수의 거듭제곱을 범위 내에서 찾는 것
		boolean[] isPrime = new boolean[(int)Math.sqrt(B)+1];
		Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
		
		for(int i=2; i*i<isPrime.length; i++) {
			if(isPrime[i]) {
				for(int j=i*i; j<isPrime.length; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		int count = 0;
		
		for(int i=2; i<isPrime.length; i++) {
			if(isPrime[i]) {
                long power = (long) i * i;
                
				while (power <= B) { 
                    if (power >= A) {
                        count++;
                    }
                    // 오버플로우 방지
                    if (power > B / i) {  // 다음 곱셈이 B를 초과하면 종료
                        break;
                    }
                    power *= i;
                }
			}
		}
		
		bw.write(count + "");
		bw.flush();
		bw.close();
	}
}