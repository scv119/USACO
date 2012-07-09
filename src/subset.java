/*
ID: scv1191
LANG: JAVA
TASK: subset
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class subset {
    static int N;
    static int sum;
    static int half;
    static long dp[][];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("subset.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/IdeaProjects/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        out.println(solve());
        out.close();
        System.exit(0);
    }

    static long solve(){
        sum = N * (1+N)/2;
        if(sum%2==1)
            return 0;
        half = sum/2;
        dp = new long[2][half+1];
        int idx = 0;
        dp[idx][0] = 1;
        for(int i = 1; i <= N ; i ++ ){
            for(int j = 0; j <= half; j ++){
                dp[idx^1][j] = dp[idx][j];
            }
            for(int x = 0 ; x <= half; x ++){
                int value = x + i;
                if(value <= half){
                    dp[idx^1][value]+=dp[idx][x];
                }
            }
            idx = idx ^ 1;
        }
        return dp[idx][half]/2;
    }
}
