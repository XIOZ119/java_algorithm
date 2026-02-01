import java.util.*;
import java.io.*;

class Solution {
    class FileInfo{
        String head;
        int number;
        int index;

        FileInfo(String head, int number, int index) {
            this.head = head;
            this.number = number;
            this.index = index;
        }
    }
    
    public String[] solution(String[] files) {
        ArrayList<FileInfo> list = new ArrayList<>();

        for(int i=0; i<files.length; i++) {
            String str = files[i];

            StringBuilder head = new StringBuilder();
            StringBuilder num = new StringBuilder();
            boolean filledHead = false;
            boolean filledNum = false;

            for(int j=0; j<str.length(); j++) {
                char c = str.charAt(j);

                // head 찾기 -> 소문자 저장
                if(!Character.isDigit(c) && !filledNum){
                    head.append(Character.toLowerCase(c));
                    filledHead = true;
                    continue;
                }

                // num 찾기
                if(Character.isDigit(c)){
                    num.append(c);
                    filledNum = true;
                    continue;
                }

                break;
            }

            FileInfo newFile = new FileInfo(head.toString(), Integer.parseInt(num.toString()), i);
            list.add(newFile);
        };

        Collections.sort(list, (a, b) -> {
            int h = a.head.compareTo(b.head);
            if(h != 0) return h;

            int n = a.number - b.number;
            if(n != 0) return n;

            return a.index - b.index;
        });

        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++) {
            FileInfo file = list.get(i);

            answer[i] = files[file.index];
        }

        return answer;
    }
}