/*
ID: scv1191
LANG: JAVA
TASK: holstein
*/

import java.io.*;
import java.util.*;

class holstein {
    static int V;
    static int r[];
    static int G;
    static int vt[][];
    static int ret[];
    static int size;

    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        V = Integer.parseInt(st.nextToken());
        r = new int[V];
        st = new StringTokenizer(f.readLine());
        for(int i = 0 ; i < V; i ++){
            r[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(f.readLine());
        G = Integer.parseInt(st.nextToken());
        vt = new int[G][V];
        for(int i = 0 ; i < G; i ++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0 ; j < V; j ++){
                vt[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(out);
        out.print(size);
        for(int i = 0 ; i < G; i ++){
            int mask = 1 << i;
            if((mask&ret[0]) == mask){
                out.print(" "+(i+1));
            }
        }
        out.println();
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void solve(PrintWriter out){
        List<int[]> list = new LinkedList<int[]>();
        int[] init = new int[V+1];
        list.add(init);
        for(int i = 1; i <= G; i++){
            //
            List<int[]> next = new LinkedList<int[]>();
            for(int[] tmp:list){
                int mask = tmp[0];
                int max = -1;
                for(int x = 0 ; x < G ; x ++){
                    int s = 1 << x;
                    if((s&mask) == s){
                        max = x;
                    }
                }
                for(int x = max + 1; x < G; x ++){
                    int[] arr = tmp.clone();
                    arr[0]|=1<<x;
                    boolean ok = true;
                    for(int j = 0; j < V; j ++){
                        arr[j+1]+=vt[x][j];
                        if(arr[j+1]<r[j])
                            ok = false;
                    }
                    if(ok){
                        ret = arr;
                        size = i;
                        return;
                    }
                    next.add(arr);
                }
            }
            list = next;
        }
    }


}