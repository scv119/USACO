/*
ID: scv1191
LANG: JAVA
TASK: runround
*/

import java.io.*;
import java.util.StringTokenizer;

public class runround {
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("runround.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        long n = Long.parseLong(st.nextToken());
        out.println(solve(n+1));
        out.close();
        System.exit(0);
    }

    static long solve(long x){
        while(!valid(x)){
            x++;
        }
        return x;
    }

    static boolean valid(long value){
        String s = value + "";
        int len = s.length();
        int occupy[] = new int[len];
        int xx[] = new int[10];
        int cur = 0;
        occupy[cur] = 1;
        for(int i = 0 ; i< len; i ++){
            int v = s.charAt(cur) - '0';
            if(v == 0 || xx[v] == 1)
                return false;
            xx[v] = 1;
            cur = (cur + v ) % len;
            if(i != len -1 && occupy[cur] == 1)
                return false;
            occupy[cur] = 1;
        }
        if(cur != 0 )
            return false;
        return true;
    }
}
