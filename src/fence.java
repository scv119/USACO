/*
ID: scv1191
LANG: JAVA
TASK: fence
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class fence {
    static int N;
    static int[][] g;
    static int[][] visited;
    static int degree[];
    static int result[];
    static int idx;
    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("fence.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));



        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        g = new int[501][501];
        degree = new int[501];
        visited = new int[501][501];
        result = new int[N+1];
        idx = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < N ; i++ ){
            st = new StringTokenizer(f.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to   = Integer.parseInt(st.nextToken());
            if(from < min)
                min = from;
            if (to < min)
                min = to;
            g[from][to]++;
            g[to][from]++;
            degree[from]++;
            degree[to]++;
        }

        int run = 1;
        for(run = 1; run < 501 ; run ++)
            if(degree[run]%2==1) {
                dfs(run);
                break;
            }
        if(run == 501)
            dfs(min);
        for(int i = N ; i >= 0 ; i --){
            out.println(result[i]);
        }
        out.close();
        System.exit(0);
    }

    static void dfs(int value){
        for(int i = 0 ; i < 501; i ++){
            if(g[value][i] > 0){
                g[value][i]--;
                g[i][value]--;
                dfs(i);
            }
        }

        result[idx++] = value;


    }
}
