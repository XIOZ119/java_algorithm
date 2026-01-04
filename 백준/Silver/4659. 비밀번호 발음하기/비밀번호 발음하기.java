import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String str = br.readLine();
            if(str.equals("end")) break;

            int l = str.length();
            char[] password = new char[l];

            for(int i=0; i<l; i++) {
                password[i] = str.charAt(i);
            }

            if(l == 1) {
                if(isVowel(password[0])) sb.append("<" + str + "> is acceptable. \n");
                else sb.append("<" + str + "> is not acceptable. \n");
            }
            else if(l == 2) {
                if(isVowel(password[0]) || isVowel(password[1])) {
                    if(!isSameChar(password[0], password[1])) sb.append("<" + str + "> is acceptable. \n");
                    else sb.append("<" + str + "> is not acceptable. \n");
                } else {
                    sb.append("<" + str + "> is not acceptable. \n");
                }
            }
            else {
                boolean check = !isSameChar(password[0], password[1]);
                boolean vowel = isVowel(password[0]) || isVowel(password[1]);

                if(check) {
                    for(int i=2; i<l; i++) {
                        if(isVowel(password[i])) vowel = true;
                        if(isThird(password[i-2], password[i-1], password[i]) || isSameChar(password[i-1], password[i])) {
                            check = false;
                            break;
                        }
                    }
                }

                if(check && vowel) sb.append("<" + str + "> is acceptable. \n");
                else sb.append("<" + str + "> is not acceptable. \n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private static boolean isConsonant(char c) {
        return !isVowel(c);
    }

    private static boolean isThird(char c1, char c2, char c3) {
        return (isVowel(c1) && isVowel(c2) && isVowel(c3)) || (isConsonant(c1) && isConsonant(c2) && isConsonant(c3));
    }

    private static boolean isSameChar(char c1, char c2) {
        return (c1 == c2 && !(c1 == 'o' || c1 == 'e'));
    }

}