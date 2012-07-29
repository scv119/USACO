/*
ID: scv1191
LANG: JAVA
TASK: game1
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class game1 {
    static int count;
    static int arr[];
    static int dp[][][][];

    static int p1;
    static int p2;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("game1.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        count = Integer.parseInt(st.nextToken());
        arr = new int[count];
        dp = new int[count][count][2][2];

        for(int i = 0 ; i < count ; i ++)
            for(int j = 0 ; j < count; j ++)
                for(int k = 0 ; k < 2; k ++)
                    Arrays.fill(dp[i][j][k],-1);

        st = new StringTokenizer(f.readLine());
        for(int i = 0 ;  i < count ;i ++){
            if(!st.hasMoreElements())
                st = new StringTokenizer(f.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
//        solve();
        build();
        out.println(dp[0][count-1][0][0]+" "+dp[0][count-1][0][1]);
        out.close();
        System.exit(0);
    }

    static void build(){
        int tmpstart[] = new int[2];
        int tmpend[]   = new int[2];
        for(int len = 1; len <= count; len ++)
            for(int start = 0 ; (start + len - 1) < count; start++) {
                int end = start + len - 1;
                for(int player = 0; player < 2 ; player ++){
                    if(len == 1){
                        dp[start][end][player][player] = arr[start];
                        dp[start][end][player][player^1] = 0;
                    }
                    else{

                        tmpstart[player] = dp[start+1][end][player^1][player] + arr[start];
                        tmpstart[player^1] = dp[start+1][end][player^1][player^1];

                        tmpend  [player] = dp[start][end-1][player^1][player] + arr[end];
                        tmpend  [player^1] = dp[start][end-1][player^1][player^1];

                        dp[start][end][player][player] = Math.max(tmpstart[player], tmpend[player]);
                        dp[start][end][player][player^1] = Math.min(tmpstart[player^1], tmpend[player^1]);
                    }
                }
            }

    }

    static void solve(){
        int[] result = solve(0, count-1, 0);
        p1 = result[0];
        p2 = result[1];
    }

    static int[] solve(int start, int end, int player){
        int v[] = new int[2];
        if(dp[start][end][player][0] != -1) {
            v[0] = dp[start][end][player][0];
            v[1] = dp[start][end][player][1];
            return v;
        }
        if(start == end){
            if(player == 0)
                v[0] = arr[start];
            else
                v[1] = arr[start];
        }
        else{
            int[] tmpstart = solve(start+1, end, player^1);
            int[] tmpend   = solve(start, end-1, player^1);

            tmpstart[player] += arr[start];
            tmpend  [player] += arr[end];

            v[player] = Math.max(tmpstart[player], tmpend[player]);
            v[player^1] = Math.min(tmpstart[player^1], tmpend[player^1]);
        }

        dp[start][end][player][0] = v[0];
        dp[start][end][player][1] = v[1];
        return v;
    }
}
