import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        String[][] express = new String[expressions.length][5];
        
        int max = 0;
        for(int i=0; i<expressions.length; i++) {
            express[i] = expressions[i].split(" ");
            
            for(int j=0; j<5; j++) {
                if(express[i][j].equals("+") || express[i][j].equals("-") || express[i][j].equals("=") || express[i][j].equals("X")) continue; 
                
                String str = express[i][j];
                for(char c: str.toCharArray()) {
                    max = Math.max(max, c - '0');
                }
            }
        }
        
        int minBase = Math.max(2, max + 1);
        
        boolean[] possible = new boolean[10];
        for(int i = minBase; i<=9; i++) {
            possible[i] = true;
        }
        
        for(int i=0; i<expressions.length; i++) {
            if(express[i][4].equals("X")) continue;
            
            for(int base = minBase; base<=9; base++) {
                if(!possible[base]) continue;
                
                int left = Integer.parseInt(express[i][0], base);
                int right = Integer.parseInt(express[i][2], base);
                int result = Integer.parseInt(express[i][4], base);
                
                int calculated;
                
                if(express[i][1].equals("+")) {
                    calculated = left + right;
                } else {
                    calculated = left - right;
                }
                
                if (calculated != result) {
                    possible[base] = false;
                }
            }
        }
        
        ArrayList<String> answers = new ArrayList<>();
        
        for (int i = 0; i < expressions.length; i++) {
            if (!express[i][4].equals("X")) continue;

            String expected = null;
            boolean same = true;

            for (int base = minBase; base <= 9; base++) {
                if (!possible[base]) continue;

                int left = Integer.parseInt(express[i][0], base);
                int right = Integer.parseInt(express[i][2], base);

                int calculated;

                if (express[i][1].equals("+")) {
                    calculated = left + right;
                } else {
                    calculated = left - right;
                }

                String result = Integer.toString(calculated, base);

                if (expected == null) {
                    expected = result;
                } else if (!expected.equals(result)) {
                    same = false;
                }
            }
            
            String result = same ? expected : "?";
            answers.add(
                express[i][0] + " "
                + express[i][1] + " "
                + express[i][2] + " = "
                + result
            );
        }
        
        return answers.toArray(new String[0]);
    }
}