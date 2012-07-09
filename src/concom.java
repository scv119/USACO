/*
ID: scv1191
LANG: JAVA
TASK: concom
*/

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class concom {
    public static int N = 100;
    static int own[][];
    static int control[][];

    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("concom.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        int pair = Integer.parseInt(st.nextToken());    // first integer
        own = new int[100][100];
        control = new int[100][100];

        for(int i = 0 ; i < pair; i ++){
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            own[a-1][b-1]=c;
        }


        solve();
        for(int i = 0 ; i < 100; i ++)
            for(int j = 0 ; j < 100; j ++)
                if(i!=j && control[i][j] == 1)
                    out.println((i+1)+" "+(j+1));
//        out.println(result);                           // output result
        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void solve()
    {
        int o[] = new int[100];
        for(int n = 0 ; n < 100; n ++){

            Arrays.fill(o,0);
            Queue<Integer> q = new LinkedList<Integer>() ;
            for(int j = 0 ; j < 100; j ++){
                o[j] += own[n][j];
                if(own[n][j]>50) {
                    control[n][j] = 1;
                    q.add(j);
                }
            }
            while(q.size()>0){
                 while(q.size()>0){
                    int i = q.poll();
                    for(int j = 0 ; j < 100 ; j ++){
                        o[j] += own[i][j];
                    }
                 }
                for(int x = 0 ; x < 100 ; x++){
                    if(o[x]>50 && control[n][x]==0){
                        control[n][x] = 1;
                        q.add(x);
                    }
                }
            }
        }
    }

}