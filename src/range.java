/*
ID: scv1191
LANG: JAVA
TASK: range
*/

import java.io.*;
import java.util.StringTokenizer;

public class range {
    static int dp[][][];
    static int field[][];
    static int N;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("range.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/Documents/zhihu/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        field = new int[N][N];
        dp = new int[N][N][8];

        for(int i = 0 ; i < N ; i ++){
            String s= f.readLine();
            for(int j = 0 ; j <  N ; j ++){
                field[i][j] = s.charAt(j)-'0';
            }
        }

        build();
        for(int len = 2 ; len <= N ; len ++){
            int size = solve(len);
            if(size == 0)
                break;
            out.println(len+" "+size);
        }

        out.close();
        System.exit(0);
    }

    static int solve(int k){
        int offset = k/32;
        int mask   = 1 << (k%32);
        int sum = 0;

        for(int j = k - 1; j < N ;j ++){
            int len = 0;
            for(int i = 0; i < N ; i ++){
                if((dp[i][j][offset] & mask) == mask){
                    len ++;
                }
                else{
                    len = 0;
                }
                if(len == k){
                    len --;
                    sum ++;
                }
            }
        }
        return sum;
    }

    static void build(){
        for(int i = 0 ; i < N; i ++)
            for(int j = 0 ; j < N ; j ++){
                if(field[i][j] == 0)
                    continue;
                for(int k = j - 1; k >= 0 ; k --){
                    if(field[i][k] == 1){
                        int value = j - k + 1;
                        int offset = value/32;
                        int mask   = 1 << (value%32);
                        dp[i][j][offset] |= mask;
                    }
                    else break;
                }
            }
    }

}
