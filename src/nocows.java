/*
ID: scv1191
LANG: JAVA
TASK: nocows
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class nocows {
    static int N;
    static int K;
    final static int mod = 9901;
    static int dp[][];
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[100][200];
        for(int i = 0 ; i < 100 ; i ++)
            Arrays.fill(dp[i],-1);
        out.println(solve(N,K));
        out.close();
        System.exit(0);
    }


    static int solve(int n, int k){
        if(dp[k][n]>=0)
            return dp[k][n];
        int result = 0;

        if(k == 1)
        {
            if(n == 1)
                result = 1;
            else
                result = 0;
        }
        else if(k > 1){
            int min = 2*k-1;
            int max = 199;
            if(k < 9){
                max = Math.min((int)Math.pow(2,k)-1,199);
            }
            if(n>= min && n <= max && n%2==1){
                int value = n -1;
                for(int x = 1; x <= value; x+=2){
                    int y = value - x;
                    if(y%2==0)
                        continue;
                    for(int kk = 1; kk < k; kk ++){
                        int mul = 2;
                        if(kk == k-1)
                            mul = 1;
                        result = result + solve(x,kk)*solve(y,k-1)*mul;
                        result = result%mod;
                    }
                }
            }
        }
        dp[k][n] = result;
        return result;
    }
}
