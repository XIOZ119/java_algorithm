import java.util.*;
import java.lang.*;
import java.io.*;

class Person {
    int age;
    String name; 
    int index;

    public Person(int age, String name, int index) {
        this.age = age;
        this.name = name;
        this.index = index;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());
        List<Person> list = new ArrayList<>(); 
        
        for(int i=0; i<count; i++) {
            String[] input = new String[2];
            input = br.readLine().split(" ");
            int age = Integer.parseInt(input[0]); 
            String name = input[1];

            list.add(new Person(age, name, i));
        } 

        Collections.sort(list, (p1, p2) -> Integer.compare(p1.age, p2.age));

        for(Person p : list) {
            bw.write(p.age + " " + p.name + "\n");
        }

        bw.flush();
        bw.close();
    }
}