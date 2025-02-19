public class d4_pairing1218 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> MyStack = new Stack<Character>();

        for (int testCase = 1; testCase <= 3; testCase++) {
            int length = Integer.parseInt(br.readLine()); 
            String str = br.readLine();
            int result = 1;

            for(int i=0; i<length; i++) {
                char strC = str.charAt(i);
                if(strC == '(' || strC == '{' || strC == '<' || strC == '[') {
                    MyStack.push(strC);
                }
                if(strC == ')') {
                    char popit = MyStack.pop();
                    if(popit == '(') {
                        continue;
                    } else {
                        result = 0 ;
                        MyStack.clear();
                        break;
                    }
                } else if(strC == '}') {
                    char popit = MyStack.pop();
                    if(popit == '{') {
                        continue;
                    } else {
                        result = 0 ;
                        MyStack.clear();
                        break;
                    }
                } else if(strC == '>') {
                    char popit = MyStack.pop();
                    if(popit == '<') {
                        continue;
                    } else {
                        result = 0 ;
                        MyStack.clear();
                        break;
                    }
                } else if(strC == ']') {
                    char popit = MyStack.pop();
                    if(popit == '[') {
                        continue;
                    } else {
                        result = 0 ;
                        MyStack.clear();
                        break;
                    }
                }
            }

            bw.write("#" + testCase + " " + result + "\n");
        }
        bw.flush();
        bw.close();
    }
}
