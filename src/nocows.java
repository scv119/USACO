/*
ID: scv1191
LANG: JAVA
TASK: nocows
*/
import java.io.*;
import java.util.*;

public class nocows {
    static int N;
    static int K;
    final static int mod = 9901;
    static int dp[][];


    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster  /Users/shenchen/Documents/zhihu/USACO/src
//        BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        N = Integer.parseInt(st.nextToken());    // first integer
        K = Integer.parseInt(st.nextToken());
        dp = new int[100][200];
        for(int i = 0 ; i <100; i ++)
        Arrays.fill(dp[i],-1);



        out.println(solve(K,N));                           // output result
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static int solve(int k, int n){
        if(dp[k][n]!= -1)
            return dp[k][n];
        int result = 0;

        if(k == 0 ){
            if(n == 0)
                result = 1;
            else
                result = 0;
        }
        else if(k == 1){
            if(n == 1)
                result = 1;
            else
                result = 0;
        }
        else{
            int min = k;
            int max = 199;
            if(k<10)
                max = Math.min((1<<k)-1,199);
            if(n<min || n > max)
                result = 0;
            else{
                for(int l = 0; l < n; l ++){
                    int r = n - 1 - l;
                    for(int k1 = 1; k1 <= k - 1; k1 ++){

                        int mul = 2;
                        if(k1 == k-1)
                            mul = 1;
                        result += solve(k-1,l)*solve(k1,r)*mul;
                        result%=mod;
                    }
                }
            }
        }
        dp[k][n] = result;
        return result;
    }

}