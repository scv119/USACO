/*
ID: scv1191
LANG: JAVA
TASK: maze1
*/

import java.io.*;
import java.util.*;

public class maze1 {
    static int w;
    static int h;
    static String map[];
    static int len;
    static Set<Integer> s;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/IdeaProjects/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
//        g = new int[w*h][w*h];
        len = w* h;
        map = new String[h*2+1];
        s  = new HashSet<Integer>();
        for(int i = 0 ; i < h*2+1; i ++){
            String s= f.readLine();
            map[i] = s;
        }
        for(int i = 0 ; i < h ; i ++)
            for(int j = 0 ; j < w; j ++){


                if(i == 0){
                    if(map[0].charAt(j*2+1) == ' ')
                        s.add(i*w+j);
                }
                if(i == h-1){
                    if(map[h*2].charAt(j*2+1) == ' ')
                        s.add(i*w+j);
                }
                if(j == 0)
                    if(map[i*2+1].charAt(0) == ' ')
                        s.add(i*w+j);
                if(j == w-1)
                    if(map[i*2+1].charAt(w*2) == ' ')
                        s.add(i*w+j);
            }

        int[] res = null;
        for(int v:s){
            int[] value = dfs(v);
            if(res == null)
                res = value;
            else{
                for(int i = 0 ; i < len; i ++){
                    if(value[i]<res[i])
                        res[i] = value[i];
                }
            }
        }
        int max = 0;
        for(int v :res) {
            if(v > max)
                max = v;
        }
        out.println(max);

        out.close();
        System.exit(0);
    }

    static int[] dfs(int s){
        int res[] = new int[len];
        Arrays.fill(res,INF);
        res[s] = 1;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        while(q.size()>0){
            int idx = q.poll();
            int y = idx/w;
            int x = idx%w;

            for(int i = 0 ; i < 4; i ++) {
                int nidx = 0;
                if(i == 0 && x > 0){
                    char c = map[2*y+1].charAt(2*x);
                    if(c == '|')
                        continue;
                    nidx = idx - 1;
                }
                else if(i == 1 && x < w -1){
                    char c = map[2*y+1].charAt(2*x+2);
                    if(c == '|')
                        continue;
                    nidx = idx + 1;
                }
                else if(i == 2 && y > 0){
                    char c = map[2*y].charAt(2*x+1);
                    if(c == '-')
                        continue;
                    nidx = idx - w;
                }
                else if(i == 3 && y < h - 1){
                    char c = map[2*y+2].charAt(2*x+1);
                    if(c == '-')
                        continue;
                    nidx = idx + w;
                }
                else continue;


                if(nidx >= 0 && nidx < len && res[nidx] > res[idx]+1){
                    res[nidx] = res[idx]+1;
                    q.add(nidx);
                }
            }
        }
        return res;
    }
}
