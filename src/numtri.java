/*
ID: scv1191
LANG: JAVA
TASK: numtri
*/

import java.io.*;
import java.util.StringTokenizer;

public class numtri {
    static int R;
    static int dp[][];
    static int arr[];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        R = Integer.parseInt(st.nextToken());
        dp = new int[R][2];
        arr = new int[R];
        int idx = 1;
        for(int i = 1; i <= R; i ++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0 ; j < i ; j++)
                arr[j] = Integer.parseInt(st.nextToken());
            if(i == 1)
                dp[0][idx^1] = arr[0];
            else{
                dp[0][idx^1] = dp[0][idx] + arr[0];
                dp[i-1][idx^1] = dp[i-2][idx] + arr[i-1];
                for(int j = 1 ; j < i-1 ; j ++){
                    dp[j][idx^1] = Math.max(dp[j-1][idx],dp[j][idx])+arr[j];
                }
            }
            idx = idx ^1;
        }
        int max = 0;
        for(int i = 0 ; i < R; i ++){
            if(dp[i][idx] > max)
                max = dp[i][idx];
        }
        out.println(max);
        out.close();
        System.exit(0);
    }
}
