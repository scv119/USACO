/*
ID: scv1191
LANG: JAVA
TASK: comehome
*/

import java.io.*;
import java.util.*;

public class comehome {
    static int g[][];
    static int len;
    static int INF = Integer.MAX_VALUE;

    static char retC;
    static int  retI;


    public static void main(String args[]) throws IOException{
        BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
//        BufferedReader f = new BufferedReader(new FileReader("/Users/shenchen/IdeaProjects/USACO/src/test.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        len = 52;
        g = new int[len][len];
        for(int i = 0 ; i < n ; i ++){
            st = new StringTokenizer(f.readLine());
            int a = char2idx(st.nextToken().charAt(0));
            int b = char2idx(st.nextToken().charAt(0));
            int v = Integer.parseInt(st.nextToken());
            if(v < g[a][b] || g[a][b] == 0)
            g[a][b] = g[b][a] = v;
        }
        solve();
        out.println(retC + " " + retI);
        out.close();
        System.exit(0);
    }

    static int char2idx(char c){
        if(c <= 'z' && c >= 'a')
            return c - 'a';
        return c - 'A' + 26;
    }

    static char idx2char(int idx){
        if(idx < 26)
            return (char)('a'+idx);
        return (char)('A'+idx-26);
    }

    static void solve(){
        dijkstra1();
//        dijkstra();
//        floyd();
    }

    static void floyd(){
        int d[][] = g.clone();
        for(int m = 0 ; m <len; m ++){
            for(int i = 0 ; i < len; i ++){
                if(i == m)
                    continue;
                for(int j = 0 ; j < len; j ++)
                    if(j != i && j !=m){
                        if(d[i][m] != 0 && d[j][m]!= 0 && (d[i][j] == 0 || d[i][j] > d[i][m] + d[j][m]))
                            d[j][i] = d[i][j] = d[i][m] + d[j][m];

                    }
            }
        }
        retI = INF;
        for(int i = 26 ; i < len-1; i ++){
            if(d[51][i] < retI && d[51][i] > 0){
                retI = d[51][i];
                retC = idx2char(i);
            }
        }

    }

    static void dijkstra(){
        int d[] = new int[len];
        int v[] = new int[len];
        int p[] = new int[len];

        Arrays.fill(d, INF);
        d[51] = 0;
        p[51] = 51;


        while(true){
            int min=INF;
            int midx = -1;
            for(int i = 0;i<len;i++){
                if(v[i] == 0 && d[i] < min){
                    midx = i;
                    min = d[i];
                }
            }
            if(midx == -1)
                break;

            v[midx] = 1;
            for(int n = 0 ; n < len ; n ++){
                if(g[n][midx] > 0){
                    if(g[n][midx] + d[midx] < d[n]){
                        d[n] = g[n][midx] + d[midx];
                        p[n] = midx;
                    }
                }
            }
        }

        retI = INF;
        for(int i = 26 ; i < len-1; i ++){
            if(d[i] < retI){
                retI = d[i];
                retC = idx2char(i);
            }
        }
    }

    static class Node implements Comparable{
        int id;
        int value;
        boolean visited = false;

        @Override
        public int compareTo(Object o) {
            return this.value - ((Node)o).value;
        }
    }
    static void dijkstra1(){
        Node list[] = new Node[len];

        for(int i = 0 ; i < len; i ++){
            list[i] = new Node();
            list[i].value = INF;
            list[i].id = i;
        }
        list[51].value = 0;
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        for(Node node:list)
            q.offer(node);

        while(q.size()>0){
            Node node = q.poll();
            node.visited = true;
            for(int i = 0 ; i < len; i ++){
                if(g[node.id][i] > 0){
                    if(node.value + g[node.id][i] < list[i].value){
                        list[i].value = node.value + g[node.id][i];
                        if(list[i].visited == false){
                            q.remove(list[i]);
                            q.offer(list[i]);
                        }
                    }
                }
            }
        }

        retI = INF;
        for(int i = 26 ; i < len-1; i ++){
            if(list[i].value < retI){
                retI = list[i].value;
                retC = idx2char(i);
            }
        }


    }

}
