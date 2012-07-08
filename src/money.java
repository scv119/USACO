/*
ID: scv1191
LANG: JAVA
TASK: money
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class money {
    static int N;
    static int M;
    static int arr[];
    static long dp[][];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("money.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(f.readLine());
        for(int i = 0 ; i < N; i ++){
            if(!st.hasMoreTokens())
                st = new StringTokenizer(f.readLine());
            arr[i]= Integer.parseInt(st.nextToken());
        }
        dp = new long[2][M+1];
        out.println(solve());
        out.close();
        System.exit(0);
    }

    static long solve(){
        int idx = 0;
        dp[0][0] = 1;
        for(int i = 0 ; i < N; i ++){
            Arrays.fill(dp[idx^1],0);
            for(int count = 0; count * arr[i] <= M; count ++){
                int value = count * arr[i];
                for(int j = 0 ; j <=M; j ++){
                    if(j+value>M)
                        break;
                    dp[idx^1][j+value]+=dp[idx][j];
                }
            }
            idx = idx ^ 1;
        }
        return dp[idx][M];
    }
}
