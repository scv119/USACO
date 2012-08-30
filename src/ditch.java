/*
ID: scv1191
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.*;

public class ditch {
    static int g[][];
    static int level[];
    static int M;
    static int N;
    static int S;
    static int E;

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("ditch.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new int[M][M];



        S = 0;
        E = M - 1;

        for(int i = 0 ; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end   = Integer.parseInt(st.nextToken())-1;
            int cap   = Integer.parseInt(st.nextToken());
            g[start][end] += cap;
            g[end][start] += cap;
        }
        out.println(dinic());
        out.close();
        System.exit(0);
    }

    static int dinic(){
        int ret = 0;
        while(bfs()){
            int tmp;
            while(true){
                tmp = dfs(0, Integer.MAX_VALUE);
                ret += tmp;
                if(tmp == 0)
                    break;
            }
        }
        return ret;
    }

    static int dfs(int now, int limit){
        int ret = limit;

        if(now == E)
            return limit;

        for(int next = 0; next < M; next ++){
            if(level[next]!=level[now]+1 || g[now][next] <= 0)
                continue;
            int cap = g[now][next];
            int real= dfs(next, Math.min(cap, ret));
            ret -= real;
            g[now][next]-=real;
            g[next][now]+=real;
        }
        return limit - ret;
    }

    static boolean bfs(){
        level = new int[M];
        Queue<Integer> q = new LinkedList<Integer>();
        level[S]  = 1;
        q.add(S);
        while(q.size()>0){
            int start = q.poll();
            for(int end  = 0 ; end < M ; end ++){
                if(level[end]>0 || g[start][end] <= 0)
                    continue;
                level[end] = level[start] + 1;
                q.add(end);
            }
        }
        return (level[E] > 0);

    }
}
