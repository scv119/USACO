/*
ID: scv1191
LANG: JAVA
TASK: inflate
*/

import java.io.*;
import java.util.*;

public class inflate {

    static int M;
    static int N;
    static int INF = Integer.MAX_VALUE;
    static int p[][];
    static int dp[];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));


        StringTokenizer st = new StringTokenizer(f.readLine());


        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new int[M+1];
        p = new int[N][2];


        for(int i = 0 ; i < N ;i ++){
            st = new StringTokenizer(f.readLine());
            p[i][0] = Integer.parseInt(st.nextToken());
            p[i][1] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        for(int m = 0 ; m <= M ; m ++){
            int max = 0;
            for(int j = 0 ; j < N ; j ++){
                int value = 0;
                if(m >= p[j][1]){
                    value = p[j][0] + dp[m-p[j][1]];
                }
                if(value > max)
                    max = value;
            }
            dp[m] = max;
        }


        out.println(dp[M]);

        out.close();
        System.exit(0);
    }
}
