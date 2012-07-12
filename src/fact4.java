/*
ID: scv1191
LANG: JAVA
TASK: fact4
*/

import java.io.*;
import java.util.StringTokenizer;

public class fact4 {
    static int N;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());

        out.println(solve(N));
        out.close();
        System.exit(0);
    }

    static int solve(int v){
        int fc = 0;
        int x = 5;
        while(x <= v){
            fc += v/x;
            x*=5;
        }
        int ret = 1;
        for(int i = 1; i <= v ; i ++){
            int tmp = i;
            while(tmp%5==0){
                tmp = tmp/5;
            }
            while(tmp%2==0 && fc > 0){
                tmp = tmp/2;
                fc --;
            }
            ret = ret * tmp;
            ret = ret % 10;
        }
        return ret;
    }
}
