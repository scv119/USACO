/*
ID: scv1191
LANG: JAVA
TASK: comehome
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class comehome {
    static int g[][];
    static int len = 52;
    static final int INF = Integer.MAX_VALUE;

    static int min;
    static char p;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster

        g = new int[len][len];
        for(int i = 0 ; i < len; i ++){
            Arrays.fill(g[i],INF);
        }

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < n ; i ++){
            st =new StringTokenizer(f.readLine());
            int x = char2idx(st.nextToken().charAt(0));
            int y = char2idx(st.nextToken().charAt(0));
            int dis = Integer.parseInt(st.nextToken());
            if(g[x][y] > dis){
                g[x][y] = g[y][x] = dis;
            }
        }

        // dijkstra();
        floyd();
        out.println(p+" "+min);
        out.close();
        System.exit(0);
    }

    static int char2idx(char c){
        if(c<='z' && c >= 'a')
            return c-'a';
        return c-'A'+26;
    }

    static void dijkstra(){

    }

    static void floyd(){
        int d[][] = g.clone();
        for(int m = 0 ; m < len; m ++){
            for(int i = 0; i < len; i ++)
                if(i!=m)
                for(int j = 0 ; j < len ;j ++){
                    if(j!=m && j!=i)
                    if(d[i][m]!=INF && d[j][m]!=INF && d[i][m]+d[m][j]<d[i][j]){
                        d[i][j] = d[i][m] + d[m][j];
                        d[j][i] = d[i][j];
                    }
                }
        }
        min = INF;
        int idx = -1;
        for(int j = 26; j < 50; j ++){
            if(d[51][j] < min){
                min = d[51][j];
                idx = j;
            }
        }
        p = (char)(idx-26+'A');
    }



}