class Solution {
    private static int opStart = 0, opEnd = 0;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int pos_min = Integer.parseInt(pos.split(":")[0]);
        int pos_sec = Integer.parseInt(pos.split(":")[1]);
        int position = pos_min * 60 + pos_sec;
        
        int op_start_min = Integer.parseInt(op_start.split(":")[0]);
        int op_start_sec = Integer.parseInt(op_start.split(":")[1]);
        opStart = op_start_min * 60 + op_start_sec;
        
        int op_end_min = Integer.parseInt(op_end.split(":")[0]);
        int op_end_sec = Integer.parseInt(op_end.split(":")[1]);
        opEnd = op_end_min * 60 + op_end_sec;
        
        int video_min = Integer.parseInt(video_len.split(":")[0]);
        int video_sec = Integer.parseInt(video_len.split(":")[1]);
        int video = video_min * 60 + video_sec;

        // 오프닝 위치 확인 
        if(isOpening(position)) {
            position = opEnd;
        }
        
        // 명령어 수행 
        for(int i=0; i<commands.length; i++){
            if(commands[i].equals("prev")){
                position -= 10;
                if(position < 0) position = 0;
            }
            else if(commands[i].equals("next")){
                position += 10;
                if(position > video) position = video;
            }
            
            if(isOpening(position)) {
                position = opEnd;
            }   
        }
        
        if(isOpening(position)) {
            position = opEnd;
        }
        
        int minuate = position / 60;
        int second = position % 60;
        
        String answerMin = "";
        String answerSec = "";
        
        if(minuate < 10) {
            answerMin = 0 + "" + minuate;
        } else {
            answerMin = minuate + "";
        }
        
        if(second < 10){
            answerSec = 0 + "" + second;
        } else{
            answerSec = second + "";
        }
            
        answer = answerMin + ":" + answerSec;
        
        System.out.println(answer);
        return answer;
    }
    
    private static boolean isOpening(int pos){
        if(opStart <= pos && opEnd >= pos){
            return true;
        }
        
        return false;
    }
}