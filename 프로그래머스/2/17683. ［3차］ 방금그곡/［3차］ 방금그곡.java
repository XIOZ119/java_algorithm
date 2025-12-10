import java.util.*;
import java.io.*;
import java.lang.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        int answerPlayTime = 0;
        
        String mm = convert(m);
        
        for(int i=0; i<musicinfos.length; i++) {
            String melody = repeatMelody(musicinfos[i]);
            
            if(!melody.contains(mm)) continue;
            if(answerPlayTime >= melody.length()) continue;
            
            answer = musicinfos[i].split(",")[2];
            answerPlayTime = melody.length();
        }
        
        if(answer.length() == 0) return "(None)";
        
        return answer;
    }
    
    private String repeatMelody(String musicin) {
        String[] split = musicin.split(",");
        
        split[3] = convert(split[3]);
        
        String[] splitTime = split[0].split(":");
        int startTime = Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
        
        splitTime = split[1].split(":");
        int endTime = Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
        
        int playTime = endTime - startTime;
        int len = split[3].length();

        if (playTime <= len) {
            return split[3].substring(0, playTime);
        }

        // playTime이 더 길면 반복해서 이어붙이기
        StringBuilder sb = new StringBuilder();

        // 전체 playTime까지 만들 때까지 sheet 반복
        while (sb.length() < playTime) {
            sb.append(split[3]);
        }

        // 정확히 playTime 길이만 자르기
        return sb.substring(0, playTime);
    }
    
    private String convert(String music) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<music.length(); i++) {
            char c = music.charAt(i);
            
            // 다음 글자 확인 
            if( i+1 < music.length() && music.charAt(i+1) == '#') {
                // 소문자로 치환 
                sb.append(Character.toLowerCase(c));
                i++; // # 건너뛰기
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}