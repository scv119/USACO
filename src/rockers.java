/*
ID: scv1191
LANG: JAVA
TASK: rockers
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class rockers {
    static int N, T, M;
    static int song[];
    static int dp[][][][];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("rockers.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        song = new int[N];
        st = new StringTokenizer(f.readLine());
        for(int i = 0 ; i < N ; i ++){
            song[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N+1][N+1][T+1][M+1];
        for(int i = 0 ; i < N+1; i ++)
            for(int j = 0; j < N+1; j ++)
                for(int k = 0 ; k < T+1 ; k ++)
                    Arrays.fill(dp[i][j][k],-1);


        out.println(solve(0,0,T,M-1));
        out.close();
        System.exit(0);
    }

    static int solve(int now, int can, int t, int m){

        if(dp[now][can][t][m] != -1)
            return dp[now][can][t][m];


        int ret = 0;
        if(now < N){

            if(song[now]<=t){
                int tmp = 1 + solve(now + 1, now + 1, t - song[now], m);
                if(tmp > ret)
                    ret = tmp;
            }

            int tmp1 = solve(now + 1, can, t, m);
            if(tmp1 > ret)
                ret = tmp1;

            if(m > 0){
                int tmp = solve(can, can, T, m - 1);
                if(tmp > ret)
                    ret = tmp;
            }
        }
        else if(m > 0 && can < N){
            ret = solve(can, can, T, m - 1);
        }

        dp[now][can][t][m] = ret;
        return ret;

    }
}
