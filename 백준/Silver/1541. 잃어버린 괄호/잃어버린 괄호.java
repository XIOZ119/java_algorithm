import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
        String[] str = input.split("-"); 
		
		int result = 0;

        if(!str[0].isEmpty()) { // 마이너스부터 나올 경우
            // 마이너스가 없을 경우, 마이너스가 플러스 뒤에 나올 경우  
            String[] plus = str[0].split("\\+");
            for (int i = 0; i < plus.length; i++) {
                result += Integer.parseInt(plus[i]);
            }
        }

        for(int i=1; i<str.length; i++) {
        	String[] plus = str[i].split("\\+");
        	int sum = 0;
        	for(int j=0; j<plus.length; j++) {
        		sum += Integer.parseInt(plus[j]);
    		}
        	result -= sum;
        }
		
		bw.write(result + "");
		bw.flush();
		bw.close();
	}
}